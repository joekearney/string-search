package joe.strings;

import static joe.strings.testfw.SampleStrings.HUNDRED_A_B;
import static joe.strings.testfw.SampleStrings.PARAGRAPH;
import static joe.strings.testfw.SampleStrings.SENTENCE;
import static joe.strings.testfw.SampleStrings.SINGLE_CHAR;
import static joe.strings.testfw.SampleStrings.THOUSAND_A;
import static joe.strings.testfw.SampleStrings.TWELFTH_NIGHT;
import static joe.strings.testfw.SampleStrings.WORD;
import joe.strings.testfw.CharSequenceExposingArray;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.caliper.runner.CaliperMain;
import com.google.common.base.Optional;

public class StringSearchBenchmark {
	enum BenchmarkCases {
		ONE_CHAR_in_SENTENCE(SINGLE_CHAR, SENTENCE),
		WORD_in_SENTENCE(WORD, SENTENCE),
		SENTENCE_in_LONGER_SENTENCE(SENTENCE, WORD + SENTENCE + WORD),
		SENTENCE_in_SHAKESPEARE(SENTENCE, TWELFTH_NIGHT),
		LONG_MISSING_in_LONGER(HUNDRED_A_B, THOUSAND_A),
		PARAGRAPH_in_SHAKESPEARE(PARAGRAPH, TWELFTH_NIGHT),
		;

		private final CharSequence needle;
		private final CharSequence haystack;

		private BenchmarkCases(String needle, String haystack) {
			this.haystack = CharSequenceExposingArray.forString(haystack);
			this.needle = CharSequenceExposingArray.forString(needle);
		}
	}

	@Param
	private StringSearchAlgorithms algorithm;
	@Param
	private BenchmarkCases params;

	private StringMatcher matcher;

	@BeforeExperiment
	protected void setUp() throws Exception {
		matcher = algorithm.get().matchPattern(params.needle);
	}

	@Benchmark
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
