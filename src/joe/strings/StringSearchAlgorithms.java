package joe.strings;

import com.google.common.base.Supplier;

public enum StringSearchAlgorithms implements Supplier<StringSearchAlgorithm> {
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
}
