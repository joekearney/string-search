package libjoe.strings.testfw;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import com.google.common.collect.testing.AbstractTester;
import java.util.Optional;
import libjoe.strings.MultiPatternStringSearchAlgorithm;
import libjoe.strings.StringMatch;

public abstract class AbstractStringSearchTester extends AbstractTester<StringSearchAlgorithmTestSubjectGenerator> {
	protected void expectNoMatch(String needle, String haystack) {
		Optional<StringMatch> match = getSubjectGenerator().createTestSubject().matchPattern(needle).search(haystack);
		assertFalse("Expected no match, found: " + match, match.isPresent());
	}
	protected void expectMatch(String needle, String haystack, int expectedMatchIndex) {
		StringMatch match = expectMatch(needle, haystack);
		assertThat(match.getMatchIndex(), is(expectedMatchIndex));
	}
	protected StringMatch expectMatch(String needle, String haystack) {
		Optional<StringMatch> match = getSubjectGenerator().createTestSubject().matchPattern(needle).search(haystack);
		assertTrue("Expected match, found none", match.isPresent());
		return match.get();
	}
	protected MultiPatternStringSearchAlgorithm getAsMultiPattern() {
		return (MultiPatternStringSearchAlgorithm) getSubjectGenerator().createTestSubject();
	}
}