package joe.strings;

import joe.strings.testfw.AbstractStringSearchingTester;

import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.caliper.runner.CaliperMain;
import com.google.common.base.Optional;

public class StringSearchBenchmark extends Benchmark {
	enum Sample {
		ONE_CHAR("a"),
		SENTENCE(AbstractStringSearchingTester.SENTENCE),
		TWELFTH_NIGHT(AbstractStringSearchingTester.TWELFTH_NIGHT),
		;

		private final String value;

		private Sample(String value) {
			this.value = value;
		}
	}

	@Param
	private StringSearchAlgorithms algorithm;
	@Param(value= {"ONE_CHAR", "SENTENCE"})
	private Sample needle;
	@Param
	private Sample haystack;

	private StringMatcher matcher;

	@Override
	protected void setUp() throws Exception {
		matcher = algorithm.get().matchPattern(needle.value);
	}

	public int timeSearch(int reps) {
		int found = 0;
		for (int i = 0; i < reps; i++) {
			Optional<StringMatch> search = matcher.search(haystack.value);
			if (search.isPresent()) {
				found++;
			}
		}
		return found;
	}
	
	public static void main(String[] args) {
		CaliperMain.main(StringSearchBenchmark.class, args);
	}
}
