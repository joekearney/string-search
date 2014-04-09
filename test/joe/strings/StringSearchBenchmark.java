package joe.strings;

import static joe.strings.testfw.SampleStrings.MANY_A;
import static joe.strings.testfw.SampleStrings.PARAGRAPH_FROM_12N;
import static joe.strings.testfw.SampleStrings.PARAGRAPH_FROM_SHAKESPEARE;
import static joe.strings.testfw.SampleStrings.SENTENCE;
import static joe.strings.testfw.SampleStrings.SHAKESPEARE;
import static joe.strings.testfw.SampleStrings.TEN_THOUSAND_A_B;
import static joe.strings.testfw.SampleStrings.TWELFTH_NIGHT;
import joe.strings.testfw.CharSequenceExposingArray;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Param;
import com.google.caliper.api.Macrobenchmark;
import com.google.caliper.runner.CaliperMain;
import com.google.common.base.Optional;

public class StringSearchBenchmark {
	enum BenchmarkCases {
//		ONE_CHAR_in_SENTENCE(SINGLE_CHAR, SENTENCE),
//		WORD_in_SENTENCE(WORD, SENTENCE),
//		SENTENCE_in_LONGER_SENTENCE(SENTENCE, WORD + SENTENCE + WORD),
		LONG_MISSING_in_LONGER(TEN_THOUSAND_A_B, MANY_A),
//		SENTENCE_in_TWELFTH_NIGHT(SENTENCE, TWELFTH_NIGHT),
//		PARAGRAPH_in_TWELFTH_NIGHT(PARAGRAPH_FROM_12N, TWELFTH_NIGHT),
		SENTENCE_in_SHAKESPEARE(SENTENCE, SHAKESPEARE),
		SENTENCE_not_in_SHAKESPEARE(SENTENCE + SENTENCE, SHAKESPEARE),
		PARAGRAPH_in_SHAKESPEARE(PARAGRAPH_FROM_SHAKESPEARE, SHAKESPEARE),
		PARAGRAPH_not_in_SHAKESPEARE(PARAGRAPH_FROM_12N, SHAKESPEARE),
		TWELFTH_NIGHT_not_in_SHAKESPEARE(TWELFTH_NIGHT, SHAKESPEARE),
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

	@Macrobenchmark
	public int timeSearch() {
		int found = 0;
//		for (int i = 0; i < reps; i++) {
			Optional<StringMatch> search = matcher.search(params.haystack);
			if (search.isPresent()) {
				found++;
			}
//		}
		return found;
	}

	public static void main(String[] args) {
		CaliperMain.main(StringSearchBenchmark.class, args);
	}
}
