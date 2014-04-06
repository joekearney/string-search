package joe.strings;

public class BoyerMoore extends AbstractSequentialMultiPatternStringSearchAlgorithm {
	@Override
	public StringMatcher matchPattern(String needle) {
		return new BoyerMooreMatcher(needle);
	}

	private static final class BoyerMooreMatcher extends AbstractStringMatcher {
		public BoyerMooreMatcher(String needle) {
			super(needle);
		}

		@Override
		protected StringMatch doSearch(CharSequence haystack) {
			
		}
	}
}
