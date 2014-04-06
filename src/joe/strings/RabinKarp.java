package joe.strings;

public final class RabinKarp extends AbstractSequentialMultiPatternStringSearchAlgorithm {
	@Override
	public StringMatcher matchPattern(String needle) {
		return new RabinKarpMatcher(needle);
	}

	private static final class RabinKarpMatcher extends AbstractStringMatcher {
		private final int exitMultiplier;

		public RabinKarpMatcher(String needle) {
			super(needle);
			this.exitMultiplier = exitMult(needle.length());
		}

		@Override
		protected StringMatch doSearch(CharSequence haystack) {
			int windowLength = needle.length();
			int needleHash = hash(needle, windowLength);
			int windowHash = hash(haystack, windowLength);

			if (needleHash == windowHash && needleMatchesLinear(haystack, 0)) {
				return newMatch(needle, 0);
			}

			int i = windowLength;
			while (i < haystack.length()) {
				windowHash = updateWindowHash(haystack, windowLength, windowHash, i);
				if (needleHash == windowHash && needleMatchesLinear(haystack, i - windowLength + 1)) {
					return newMatch(needle, i - windowLength + 1);
				}

				i++;
			}

			return null;
		}

		private int updateWindowHash(CharSequence haystack, int windowLength, int windowHash, int i) {
			windowHash -= exitMultiplier * (int) haystack.charAt(i - windowLength);
			windowHash = (windowHash * 31) + haystack.charAt(i);
			return windowHash;
		}

		private int exitMult(int windowLength) {
			int m = 1;
			// one fewer iterations than characters
			for (int i = 1; i < windowLength; i++) {
				m *= 31;
			}
			return m;
		}

		private int hash(CharSequence haystack, int length) {
			int hash = 0;
			for (int i = 0; i < length; i++) {
				hash = (hash * 31) + (int) haystack.charAt(i);
			}
			return hash;
		}

	}
}
