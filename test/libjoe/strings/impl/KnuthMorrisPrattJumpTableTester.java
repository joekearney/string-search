package libjoe.strings.impl;

import static com.google.common.truth.Truth.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import com.google.common.truth.Truth;
import java.util.List;
import junit.framework.TestCase;
import libjoe.strings.impl.KnuthMorrisPratt;

import com.google.common.primitives.Ints;

public class KnuthMorrisPrattJumpTableTester extends TestCase {

  public void testWikipediaJumpTableAbcd() throws Exception {
    int[] table = KnuthMorrisPratt.computeJumpTable("ABCDABC");
    assertThat(table).asList().containsExactly(-1, 0, 0, 0, 0, 1, 2).inOrder();
  }

  public void testWikipediaJumpTableParachute() throws Exception {
    int[] table = KnuthMorrisPratt.computeJumpTable("participate in parachute");
    assertThat(table).asList()
        .containsExactly(-1, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 0, 0, 0, 0, 1, 2, 3, 0, 0, 0, 0, 0)
        .inOrder();
  }
}
