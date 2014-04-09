package joe.strings;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import junit.framework.TestCase;

import com.google.common.primitives.Ints;

public class KnuthMorrisPrattJumpTableTester extends TestCase {
	public void testWikipediaJumpTableAbcd() throws Exception {
		int[] table = KnuthMorrisPratt.computeJumpTable("ABCDABC");
		assertThat(Ints.asList(table), contains(-1, 0, 0, 0, 0, 1, 2));
	}
	public void testWikipediaJumpTableParachute() throws Exception {
		int[] table = KnuthMorrisPratt.computeJumpTable("participate in parachute");
		assertThat(Ints.asList(table), contains(-1, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 0, 0, 0, 0, 1, 2, 3, 0, 0, 0, 0, 0));
	}
	public void testAaaaaaabJumpTable() throws Exception {
		int[] table = KnuthMorrisPratt.computeJumpTable("aaaaaaab");
		assertThat(Ints.asList(table), contains(-1, 0, 1, 2, 3, 4, 5, 6));
	}
}
