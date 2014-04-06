package joe.strings;


public final class BruteForce extends AbstractSequentialMultiPatternStringSearchAlgorithm {
	@Override
	public StringMatcher matchPattern(String needle) {
		return new BruteForceMatcher(needle);
	}

	private static final class BruteForceMatcher extends AbstractStringMatcher {
		BruteForceMatcher(String needle) {
			super(needle);
		}

		@Override
		protected StringMatch doSearch(CharSequence haystack) {
			final int length = haystack.length();
			int i = 0;
			while (i < length) {
				if (needleMatchesLinear(haystack, i)) {
					return newMatch(haystack.subSequence(i, i + needle.length()), i);
				}
				i++;
			}
			return null;
		}
	}
}
