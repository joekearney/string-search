package joe.strings.testfw;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import joe.strings.StringMatch;
import joe.strings.testfw.StringSearchFeature.Require;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

public class SimpleExamplesTester extends AbstractStringSearchingTester {
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
	
	private void expectMatchesSelf(String text) {
		StringMatch match = searchExpectingPresent(text, text);
		assertThat(match.getMatchIndex(), is(0));
		assertThat(match.getMatchedText().toString(), is(text));
	}
}
