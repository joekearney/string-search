package joe.strings;

import joe.strings.testfw.StringSearchFeature;

import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableSet;

public enum StringSearchAlgorithms implements Supplier<StringSearchAlgorithm> {
	STRING_INDEX_OF(StringSearchFeature.EXPECTED_SLOW) {
		@Override
		public StringSearchAlgorithm get() {
			return new StringIndexOf();
		}
	},
	BRUTE_FORCE(StringSearchFeature.EXPECTED_SLOW) {
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
	AHO_CORASICK_LIB {
		@Override
		public StringSearchAlgorithm get() {
			return new AhoCorasickLib();
		}
	},
	;

	public final ImmutableSet<StringSearchFeature> features;

	private StringSearchAlgorithms(StringSearchFeature ... features) {
		this.features = ImmutableSet.copyOf(features);
	}
}
