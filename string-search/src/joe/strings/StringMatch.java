package joe.strings;

/**
 * The result of a search &ndash; a pattern found within a text.
 *
 * @author Joe Kearney
 */
public final class StringMatch {
	private final String needle;
	private final CharSequence haystack;
	private final int from;

	public StringMatch(String needle, CharSequence haystack, int from) {
		this.needle = needle;
		this.haystack = haystack;
		this.from = from;
	}

	public CharSequence getMatchedText() {
		return haystack;
	}
	public String getPattern() {
		return needle;
	}
	public int getMatchIndex() {
		return from;
	}
}
