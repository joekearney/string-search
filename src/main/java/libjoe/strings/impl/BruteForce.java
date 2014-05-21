package libjoe.strings.impl;

import libjoe.strings.AbstractSequentialMultiPatternStringSearchAlgorithm;
import libjoe.strings.AbstractStringMatcher;
import libjoe.strings.StringMatch;
import libjoe.strings.StringMatcher;

/**
 * Brute force approach to string searching. For every index into the haystack we test to see whether the needle matches the next characters
 *
 * @author Joe Kearney
 */
public final class BruteForce extends AbstractSequentialMultiPatternStringSearchAlgorithm {
	@Override
	public StringMatcher matchPattern(CharSequence needle) {
		return new BruteForceMatcher(needle);
	}

	private static final class BruteForceMatcher extends AbstractStringMatcher {
		BruteForceMatcher(CharSequence needle) {
			super(needle);
		}

		@Override
		protected StringMatch doSearch(CharSequence haystack, char[] haystackArray) {
			final int length = haystackArray.length;
			int i = 0;
			while (i < length) {
				if (needleMatchesLinear(haystackArray, i)) {
					return newMatch(haystack, i);
				}
				i++;
			}
			return null;
		}
	}
}
