package joe.strings.testfw;

import joe.strings.StringSearchAlgorithm;

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
