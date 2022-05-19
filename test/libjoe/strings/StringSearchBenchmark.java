package libjoe.strings;

import static libjoe.strings.util.SampleStrings.HUNDRED_A_B;
import static libjoe.strings.util.SampleStrings.PARAGRAPH;
import static libjoe.strings.util.SampleStrings.SENTENCE;
import static libjoe.strings.util.SampleStrings.SINGLE_CHAR;
import static libjoe.strings.util.SampleStrings.THOUSAND_A;
import static libjoe.strings.util.SampleStrings.TWELFTH_NIGHT;
import static libjoe.strings.util.SampleStrings.WORD;

import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.caliper.runner.CaliperMain;
import java.util.Optional;

public class StringSearchBenchmark extends Benchmark {
	enum BenchmarkCases {
		ONE_CHAR_in_SENTENCE(SINGLE_CHAR, SENTENCE),
		WORD_in_SENTENCE(WORD, SENTENCE),
		SENTENCE_in_LONGER_SENTENCE(SENTENCE, WORD + SENTENCE + WORD),
		SENTENCE_in_SHAKESPEARE(SENTENCE, TWELFTH_NIGHT),
		LONG_MISSING_in_LONGER(HUNDRED_A_B, THOUSAND_A),
		PARAGRAPH_in_SHAKESPEARE(PARAGRAPH, TWELFTH_NIGHT),
		;
		
		private final String needle;
		private final String haystack;

		private BenchmarkCases(String needle, String haystack) {
			this.haystack = haystack;
			this.needle = needle;
		}
	}

	@Param
	private StringSearchAlgorithms algorithm;
	@Param
	private BenchmarkCases params;

	private StringMatcher matcher;

	@Override
	protected void setUp() throws Exception {
		matcher = algorithm.get().matchPattern(params.needle);
	}

	public int timeSearch(int reps) {
		int found = 0;
		for (int i = 0; i < reps; i++) {
			Optional<StringMatch> search = matcher.search(params.haystack);
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
