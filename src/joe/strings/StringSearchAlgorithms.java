package joe.strings;

import joe.strings.impl.BoyerMoore;
import joe.strings.impl.BruteForce;
import joe.strings.impl.KnuthMorrisPratt;
import joe.strings.impl.RabinKarp;
import joe.strings.impl.StringIndexOf;

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
	BOYER_MOORE {
		@Override
		public StringSearchAlgorithm get() {
			return new BoyerMoore();
		}
	},
	;
}
