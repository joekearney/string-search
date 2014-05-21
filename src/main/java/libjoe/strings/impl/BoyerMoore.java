package libjoe.strings.impl;

import static com.google.common.base.Preconditions.checkArgument;
import libjoe.strings.AbstractSequentialMultiPatternStringSearchAlgorithm;
import libjoe.strings.AbstractStringMatcher;
import libjoe.strings.StringMatch;
import libjoe.strings.StringMatcher;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.CharMatcher;

public class BoyerMoore extends AbstractSequentialMultiPatternStringSearchAlgorithm {
	private static final int ALPHABET_SIZE = 256; //Character.MAX_VALUE;
	
	@Override
	public StringMatcher matchPattern(CharSequence needle) {
		return new BoyerMooreMatcher(needle, computeJumpTable(needle), computeBadCharTable(needle));
	}

	private static int[] computeBadCharTable(CharSequence needle) {
		int[] table = new int[ALPHABET_SIZE];
		int needleLength = needle.length();
		for (int i = 0; i < ALPHABET_SIZE; i++) {
			table[i] = needleLength; // default value
		}
		for (int i = 0; i < needleLength; i++) {
			table[needle.charAt(i)] = needleLength - 1 - i;
		}
		return table;
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

	private static final class BoyerMooreMatcher extends AbstractStringMatcher {
		private final int[] jumpTable;
		private final int[] badCharRule;

		public BoyerMooreMatcher(CharSequence needle, int[] jumpTable, int[] badCharRule) {
			super(needle);
			this.jumpTable = jumpTable;
			this.badCharRule = badCharRule;
		}

		@Override
		protected StringMatch doSearch(CharSequence haystack, char[] array) {
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
