package joe.strings;

/**
 * String searching that keeps track of a hash of a rolling window of the haystack the same length as the needle.
 * 
 * @author Joe Kearney
 */
public final class RabinKarp extends AbstractSequentialMultiPatternStringSearchAlgorithm {
	@Override
	public StringMatcher matchPattern(String needle) {
		return new RabinKarpMatcher(needle);
	}

	private static final class RabinKarpMatcher extends AbstractStringMatcher {
		private final int exitMultiplier;
		private final int needleHash;

		public RabinKarpMatcher(String needle) {
			super(needle);
			this.exitMultiplier = exitMult(needle.length());
			needleHash = hash(needle, needle.length());
		}

		@Override
		protected StringMatch doSearch(CharSequence haystack) {
			int windowLength = needle.length();
			int windowHash = hash(haystack, windowLength);

			if (needleHash == windowHash && needleMatchesLinear(haystack, 0)) {
				return newMatch(needle, 0);
			}

			int i = windowLength;
			int haystackLength = haystack.length();
			while (i < haystackLength) {
				windowHash = updateWindowHash(haystack, windowLength, windowHash, i);
				if (needleHash == windowHash && needleMatchesLinear(haystack, i - windowLength + 1)) {
					return newMatch(needle, i - windowLength + 1);
				} else {
					i++;
				}
			}

			return null;
		}

		private int updateWindowHash(CharSequence haystack, int windowLength, int windowHash, int i) {
			int outgoing = haystack.charAt(i - windowLength);
			int incoming = haystack.charAt(i);
			
			windowHash -= exitMultiplier * outgoing;
			windowHash = (windowHash * 31) + incoming;
			
			return windowHash;
		}

		private static int exitMult(int windowLength) {
			int m = 1;
			// one fewer iterations than characters
			for (int i = 1; i < windowLength; i++) {
				m *= 31;
			}
			return m;
		}

		private static int hash(CharSequence haystack, int length) {
			int hash = 0;
			for (int i = 0; i < length; i++) {
				hash = (hash * 31) + (int) haystack.charAt(i);
			}
			return hash;
		}
	}
}
