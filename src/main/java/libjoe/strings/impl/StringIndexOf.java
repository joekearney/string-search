package libjoe.strings.impl;

import libjoe.strings.AbstractSequentialMultiPatternStringSearchAlgorithm;
import libjoe.strings.AbstractStringMatcher;
import libjoe.strings.StringMatch;
import libjoe.strings.StringMatcher;
import libjoe.strings.StringSearchAlgorithm;

/**
 * Implementation of {@link StringSearchAlgorithm} delegating to {@link String#indexOf(String)}. This is really fast, likely due to intrinsified instructions in
 * the SSE 4.2 instruction set. // TODO benchmark without intrinsifying this?
 *
 * @author Joe Kearney
 */
public final class StringIndexOf extends AbstractSequentialMultiPatternStringSearchAlgorithm {
	@Override
	public StringMatcher matchPattern(CharSequence needle) {
		return new StringIndexOfMatcher(needle);
	}
	private static final class StringIndexOfMatcher extends AbstractStringMatcher {
		StringIndexOfMatcher(CharSequence needle) {
			super(needle);
		}

		@Override
		protected StringMatch doSearch(CharSequence haystack, char[] array) {
			int indexOf = haystack.toString().indexOf(needle.toString());
			if (indexOf >= 0) {
				return newMatch(haystack, indexOf);
			} else {
				return null;
			}
		}
	}
}