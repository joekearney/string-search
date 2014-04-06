package joe.strings.testfw;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.URL;

import joe.strings.MultiPatternStringSearchAlgorithm;
import joe.strings.StringMatch;

import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.collect.testing.AbstractTester;
import com.google.common.io.Resources;

public abstract class AbstractStringSearchTester extends AbstractTester<StringSearchAlgorithmTestSubjectGenerator> {
	protected void expectNoMatch(String needle, String haystack) {
		Optional<StringMatch> match = getSubjectGenerator().createTestSubject().matchPattern(needle).search(haystack);
		assertFalse("Expected no match, found: " + match, match.isPresent());
	}
	protected void expectMatch(String needle, String haystack, int expectedMatchIndex) {
		StringMatch match = searchExpectingPresent(needle, haystack);
		assertThat(match.getMatchIndex(), is(expectedMatchIndex));
	}
	protected StringMatch searchExpectingPresent(String needle, String haystack) {
		Optional<StringMatch> match = getSubjectGenerator().createTestSubject().matchPattern(needle).search(haystack);
		assertTrue("Expected match, found none", match.isPresent());
		return match.get();
	}
	protected MultiPatternStringSearchAlgorithm getAsMultiPattern() {
		return (MultiPatternStringSearchAlgorithm) getSubjectGenerator().createTestSubject();
	}
}