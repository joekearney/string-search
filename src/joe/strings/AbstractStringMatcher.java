package joe.strings;

import com.google.common.base.Optional;

public abstract class AbstractStringMatcher implements StringMatcher {
	protected final String needle;

	public AbstractStringMatcher(String needle) {
		this.needle = needle;
	}
	
	@Override
	public final Optional<StringMatch> search(CharSequence haystack) {
		int windowLength = needle.length();
		if (haystack.length() < windowLength) {
			return Optional.absent();
		} else {
			return Optional.fromNullable(doSearch(haystack));
		}
	}

	protected abstract StringMatch doSearch(CharSequence haystack);

	/**
	 * Standard implementation of an equality check between the needle and a portion of the haystack, running in time linear in the length of the needle.
	 * 
	 * @param haystack string in which to test
	 * @param from index at which to start in the haystack
	 * @return {@code true} iff the next characters in the haystack are equal to the needle
	 */
	protected final boolean needleMatchesLinear(CharSequence haystack, int from) {
		int i = 0;
		int j = from;
		while (i < needle.length() && j < haystack.length() && needle.charAt(i) == haystack.charAt(j)) {
			i++;
			j++;
		}
		if (i < needle.length()) {
			// didn't get to end of needle before mismatch or end of haystack
			return false;
		}
		return true;
	}

	protected final StringMatch newMatch(CharSequence haystack, int from) {
		return new StringMatch(needle, haystack, from);
	}
}