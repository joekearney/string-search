package libjoe.strings.impl;

import static com.google.common.base.Preconditions.checkArgument;
import libjoe.strings.AbstractSequentialMultiPatternStringSearchAlgorithm;
import libjoe.strings.AbstractStringMatcher;
import libjoe.strings.StringMatch;
import libjoe.strings.StringMatcher;
import libjoe.strings.util.CharSequenceExposingArray;

import com.google.common.annotations.VisibleForTesting;

/**
 * 
 * 
 * @author Joe Kearney
 */
public class KnuthMorrisPratt extends AbstractSequentialMultiPatternStringSearchAlgorithm {
	private static final boolean DEBUG = false;

	@Override
	public StringMatcher matchPattern(CharSequence needle) {
		return new KnuthMorrisPrattMatcher(needle, computeJumpTable(needle));
	}

	@VisibleForTesting
	static int[] computeJumpTable(CharSequence needle) {
		char[] needleArray = CharSequenceExposingArray.toCharArray(needle);
		int needleLength = needleArray.length;
		checkArgument(needleLength > 0, "Can't compute a KMP jumpTable for a zero-length pattern");
		if (needleLength == 1) {
			return new int[] { -1 };
		}

		int[] jumpTable = new int[needleLength];
		jumpTable[0] = -1;
		jumpTable[1] = 0;

		int candidate = 0;
		int pos = 2;
		while (pos < needleLength) {
			if (needleArray[pos - 1] == needleArray[candidate]) {
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
		protected StringMatch doSearch(CharSequence haystack, char[] haystackArray) {
			if (DEBUG) { System.out.println("Searching for [" + needle + "] in [" + (haystack.length() > 20 ? (haystack.subSequence(0, 20) + "...") : haystack) + "]"); }

			int needleLength = needleArray.length;
			int i = 0; // index in pattern
			int m = 0; // index in haystack of prospective Match

			while (m + i < haystackArray.length) {
				if (DEBUG) { System.out.println("m=" + m + ", i=" + i); }
				if (needleArray[i] == haystackArray[m + i]) {
					i++;
					if (i == needleLength) {
						// found the match!
						return newMatch(haystack, m);
					}
				} else {
					int jump = jumpTable[i];

					m = m + i - jump;
					if (jump >= 0) {
						i = jump;
					} else {
						i = 0;
					}
				}
			}

			return null;
		}
	}
}
