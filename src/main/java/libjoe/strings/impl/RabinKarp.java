package libjoe.strings.impl;

import libjoe.strings.AbstractSequentialMultiPatternStringSearchAlgorithm;
import libjoe.strings.AbstractStringMatcher;
import libjoe.strings.StringMatch;
import libjoe.strings.StringMatcher;

/**
 * String searching that keeps track of a hash of a rolling window of the haystack the same length as the needle.
 *
 * @author Joe Kearney
 */
public final class RabinKarp extends AbstractSequentialMultiPatternStringSearchAlgorithm {
	@Override
	public StringMatcher matchPattern(CharSequence needle) {
		return new RabinKarpMatcher(needle);
	}

	private static final class RabinKarpMatcher extends AbstractStringMatcher {
		private final int exitMultiplier;
		private final int needleHash;

		public RabinKarpMatcher(CharSequence needle) {
			super(needle);
			this.exitMultiplier = exitMult(needle.length());
			needleHash = hash(needleArray, needleArray.length);
		}

		@Override
		protected StringMatch doSearch(CharSequence haystack, char[] haystackArray) {
			int windowLength = needleArray.length;
			int windowHash = hash(haystackArray, windowLength);

			if (needleHash == windowHash && needleMatchesLinear(haystackArray, 0)) {
				return newMatch(haystack, 0);
			}

			int i = windowLength;
			int haystackLength = haystackArray.length;
			while (i < haystackLength) {
				windowHash = updateWindowHash(haystackArray, windowLength, windowHash, i);
				if (needleHash == windowHash && needleMatchesLinear(haystackArray, i - windowLength + 1)) {
					return newMatch(haystack, i - windowLength + 1);
				} else {
					i++;
				}
			}

			return null;
		}

		private int updateWindowHash(char[] haystackArray, int windowLength, int windowHash, int i) {
			int outgoing = haystackArray[i - windowLength];
			int incoming = haystackArray[i];

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

		private static int hash(char[] haystack, int length) {
			int hash = 0;
			for (int i = 0; i < length; i++) {
				hash = (hash * 31) + haystack[i];
			}
			return hash;
		}
	}
}
