package joe.strings;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.annotations.VisibleForTesting;

/**
 *
 *
 * @author Joe Kearney
 */
public class KnuthMorrisPratt extends AbstractSequentialMultiPatternStringSearchAlgorithm {
	@Override
	public StringMatcher matchPattern(CharSequence needle) {
		return new KnuthMorrisPrattMatcher(needle, computeJumpTable(needle));
	}

	@VisibleForTesting
	static int[] computeJumpTable(CharSequence needle) {
		checkArgument(needle.length() > 0, "Can't compute a KMP jumpTable for a zero-length pattern");
		if (needle.length() == 1) {
			return new int[] { -1 };
		}

		int[] jumpTable = new int[needle.length()];
		jumpTable[0] = -1;
		jumpTable[1] = 0;

		int candidate = 0;
		int pos = 2;
		while (pos < jumpTable.length) {
			if (needle.charAt(pos - 1) == needle.charAt(candidate)) {
				// match! record that we've gone further down a prefix
				candidate++;
				jumpTable[pos] = candidate;
				pos++;
			} else if (candidate > 0) {
				// end of a match
				candidate = jumpTable[candidate];
				pos++;
			} else {
				// no match,
				jumpTable[pos] = 0;
				pos++;
			}
		}

		return jumpTable;
	}

	private static final class KnuthMorrisPrattMatcher extends AbstractStringMatcher {
		private final int[] jumpTable;

		public KnuthMorrisPrattMatcher(CharSequence needle, int[] jumpTable) {
			super(needle);
			this.jumpTable = jumpTable;
		}

		@Override
		protected StringMatch doSearch(CharSequence haystack) {
			int i = 0; // index in pattern
			int m = 0; // index in haystack of prospective Match

			while (m + i < haystack.length()) {
				if (needle.charAt(i) == haystack.charAt(m + i)) {
					i++;
					if (i == needle.length()) {
						// found the match!
						return newMatch(haystack, m);
					}
				} else {
					int jump = jumpTable[i];

					m = m + i - jump;
					if (jump > 0) {
						i = i - jump;
					} else {
						i = 0;
					}
				}
			}

			return null;
		}
	}
}
