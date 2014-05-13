package joe.strings.impl;

import joe.strings.AbstractSequentialMultiPatternStringSearchAlgorithm;
import joe.strings.AbstractStringMatcher;
import joe.strings.StringMatch;
import joe.strings.StringMatcher;
import joe.strings.StringSearchAlgorithm;

/**
 * Implementation of {@link StringSearchAlgorithm} delegating to {@link String#indexOf(String)}.
 * 
 * @author Joe Kearney
 */
public final class StringIndexOf extends AbstractSequentialMultiPatternStringSearchAlgorithm {
	@Override
	public StringMatcher matchPattern(String needle) {
		return new StringIndexOfMatcher(needle);
	}
	private static final class StringIndexOfMatcher extends AbstractStringMatcher {
		StringIndexOfMatcher(String needle) {
			super(needle);
		}

		@Override
		protected StringMatch doSearch(CharSequence haystack) {
			int indexOf = haystack.toString().indexOf(needle);
			if (indexOf >= 0) {
				return newMatch(haystack, indexOf);
			} else {
				return null;
			}
		}
	}
}