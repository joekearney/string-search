package joe.strings;

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