package joe.strings;

/**
 * Implementation of {@link StringSearchAlgorithm} delegating to {@link String#indexOf(String)}.
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
		protected StringMatch doSearch(CharSequence haystack) {
			int indexOf = haystack.toString().indexOf(needle.toString());
			if (indexOf >= 0) {
				return newMatch(haystack, indexOf);
			} else {
				return null;
			}
		}
	}
}