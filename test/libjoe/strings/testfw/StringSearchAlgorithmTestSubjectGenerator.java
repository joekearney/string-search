package libjoe.strings.testfw;

import libjoe.strings.StringSearchAlgorithm;

import com.google.common.collect.testing.TestSubjectGenerator;

public class StringSearchAlgorithmTestSubjectGenerator implements TestSubjectGenerator<StringSearchAlgorithm> {
	private final StringSearchAlgorithm algorithm;
	
	public StringSearchAlgorithmTestSubjectGenerator(StringSearchAlgorithm algorithm) {
		this.algorithm = algorithm;
	}

	@Override
	public StringSearchAlgorithm createTestSubject() {
		return algorithm;
	}
}
