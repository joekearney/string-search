package joe.strings;

import com.google.common.base.Supplier;

public enum StringSearchAlgorithms implements Supplier<StringSearchAlgorithm> {
	STRING_INDEX_OF {
		@Override
		public StringSearchAlgorithm get() {
			return new StringIndexOf();
		}
	},
	BRUTE_FORCE {
		@Override
		public StringSearchAlgorithm get() {
			return new BruteForce();
		}
	},
	RABIN_KARP {
		@Override
		public StringSearchAlgorithm get() {
			return new RabinKarp();
		}
	},
	KNUTH_MORRIS_PRATT {
		@Override
		public StringSearchAlgorithm get() {
			return new KnuthMorrisPratt();
		}
	},
	;
}
