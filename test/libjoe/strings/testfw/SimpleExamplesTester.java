package libjoe.strings.testfw;

import static libjoe.strings.util.SampleStrings.EMPTY_STRING;
import static libjoe.strings.util.SampleStrings.HUNDRED_A_B;
import static libjoe.strings.util.SampleStrings.SENTENCE;
import static libjoe.strings.util.SampleStrings.SINGLE_CHAR;
import static libjoe.strings.util.SampleStrings.THOUSAND_A;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import com.google.common.collect.ImmutableSet;
import java.util.Optional;
import libjoe.strings.StringMatch;
import libjoe.strings.testfw.StringSearchFeature.Require;

public class SimpleExamplesTester extends AbstractStringSearchTester {
	public void testShortStringMatchesSelf() throws Exception {
		expectMatchesSelf(SINGLE_CHAR);
	}
	public void testLongStringMatchesSelf() throws Exception {
		expectMatchesSelf(SENTENCE);
	}
	public void testLongNeedleInShortHaystack() throws Exception {
		expectNoMatch(SINGLE_CHAR + SINGLE_CHAR, SINGLE_CHAR);
	}
	public void testStringNotInEmptyString() throws Exception {
		expectNoMatch(SINGLE_CHAR, EMPTY_STRING);
		expectNoMatch(SENTENCE, EMPTY_STRING);
	}
	public void testSingleCharacterPrefixOfLongString() {
		expectMatch(SINGLE_CHAR, SINGLE_CHAR + SENTENCE, 0);
	}
	public void testSingleCharacterInLongString() {
		expectMatch(SINGLE_CHAR, SENTENCE, SENTENCE.indexOf(SINGLE_CHAR.charAt(0)));
	}

	@Require(StringSearchFeature.MULTI_PATTERN)
	public void testMultiMatchSingleChar() {
		String char2 = String.valueOf(SENTENCE.charAt(1));
		String char3 = String.valueOf(SENTENCE.charAt(2));

		Optional<StringMatch> match = getAsMultiPattern().matchPattern(ImmutableSet.of(char2, char3)).search(SENTENCE);
		assertTrue("No match was found", match.isPresent());
		assertThat(match.get().getMatchIndex(), is(1));
		assertThat(match.get().getMatchedText().toString(), is(char2));
	}
	@Require(absent=StringSearchFeature.EXPECTED_SLOW)
	public void testPathologicalMiss() throws Exception {
		expectNoMatch(HUNDRED_A_B, THOUSAND_A);
	}
//	public void testShakespeare() throws Exception {
//		expectMatch(SENTENCE, TWELFTH_NIGHT);
//	}

	private void expectMatchesSelf(String text) {
		StringMatch match = expectMatch(text, text);
		assertThat(match.getMatchIndex(), is(0));
		assertThat(match.getMatchedText().toString(), is(text));
	}
}
