package joe.strings.testfw;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import joe.strings.MultiPatternStringSearchAlgorithm;
import joe.strings.StringMatch;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.testing.AbstractTester;

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
	
	public static final class SampleStrings {
		public static final String EMPTY_STRING = "";
		public static final String SINGLE_CHAR = "a";
		public static final String WORD = "play";
		public static final String SENTENCE = "This fellow's wise enough to play the fool;";
		
		public static final String TWELFTH_NIGHT;
		static {
			try {
				URL url = Resources.getResource("joe/strings/testfw/twelfth-night.txt");
				TWELFTH_NIGHT = Resources.toString(url, Charsets.UTF_8);
			} catch (Throwable e) {
				throw new RuntimeException("Failed to load long text", e);
			}
		}
		public static final String PARAGRAPH = TWELFTH_NIGHT.substring(TWELFTH_NIGHT.length() / 2, (TWELFTH_NIGHT.length() / 2) + 2000);
		
		public static final String THOUSAND_A = Strings.repeat("a", 1000);
		public static final String HUNDRED_A_B = Strings.repeat("a", 100) + "b";

		private SampleStrings() {}
	}
}