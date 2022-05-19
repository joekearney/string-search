package libjoe.strings;

import static com.google.common.truth.Truth8.assertThat;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assume.assumeThat;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import java.util.Optional;
import libjoe.strings.impl.BruteForce;
import libjoe.strings.impl.KnuthMorrisPratt;
import libjoe.strings.impl.RabinKarp;
import libjoe.strings.impl.StringIndexOf;
import org.junit.Test;
import org.junit.runner.RunWith;

public abstract class StringMatcherQuickcheckTest {
  @RunWith(JUnitQuickcheck.class)
  public static class KnuthMorrisPrattQuickcheckTest extends StringMatcherQuickcheckTest {
    @Override
    protected StringSearchAlgorithm makeStringSearchAlgorithm() {
      return new KnuthMorrisPratt();
    }
  }
  @RunWith(JUnitQuickcheck.class)
  public static class StringIndexOfQuickcheckTest extends StringMatcherQuickcheckTest {
    @Override
    protected StringSearchAlgorithm makeStringSearchAlgorithm() {
      return new StringIndexOf();
    }
  }
  @RunWith(JUnitQuickcheck.class)
  public static class RabinKarpQuickcheckTest extends StringMatcherQuickcheckTest {
    @Override
    protected StringSearchAlgorithm makeStringSearchAlgorithm() {
      return new RabinKarp();
    }
  }
  @RunWith(JUnitQuickcheck.class)
  public static class BruteForceQuickcheckTest extends StringMatcherQuickcheckTest {
    @Override
    protected StringSearchAlgorithm makeStringSearchAlgorithm() {
      return new BruteForce();
    }
  }
  @RunWith(JUnitQuickcheck.class)
  public static class AhoCorasickQuickcheckTest extends StringMatcherQuickcheckTest {
    @Override
    protected StringSearchAlgorithm makeStringSearchAlgorithm() {
      return new AhoCorasickLib();
    }
  }

  @Property
  public void matchFound(String prefix, String needle, String suffix) {
    assumeThat(needle, not(isEmptyString()));
    String haystack = prefix + needle + suffix;
    StringMatcher stringMatcher = makeStringSearchAlgorithm().matchPattern(needle);
    Optional<Integer> result = stringMatcher.search(haystack).map(StringMatch::getMatchIndex);
    assertThat(result).hasValue(prefix.length());
  }

  protected abstract StringSearchAlgorithm makeStringSearchAlgorithm();

  @Test
  public void testAbc() {
    matchFound("a", "b", "c");
  }

  @Test
  public void testEmpty() {
    matchFound("", "", "");
  }

  @Test
  public void testNeedleIsHaystack() {
    matchFound("", "a", "");
  }
}
