package libjoe.strings;

/**
 * The result of a search &ndash; a pattern found within a text.
 *
 * @author Joe Kearney
 */
public final class StringMatch {
	private final CharSequence needle;
	private final CharSequence haystack;
	private final int from;

	public StringMatch(CharSequence needle, CharSequence haystack, int from) {
		this.needle = needle;
		this.haystack = haystack;
		this.from = from;
	}

	public CharSequence getMatchedText() {
		return haystack;
	}
	public CharSequence getPattern() {
		return needle;
	}
	public int getMatchIndex() {
		return from;
	}
}
