package joe.strings;

import joe.strings.testfw.StringSearchAlgorithmTestSuiteBuilder;
import joe.strings.testfw.StringSearchFeature;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TestsForStringSearchAlgorithms {
	public static Test suite() {
		TestSuite testSuite = new TestSuite("string-matching");

		testSuite.addTest(StringSearchAlgorithmTestSuiteBuilder.forAlgorithm(StringSearchAlgorithms.BRUTE_FORCE)
				.withFeatures(StringSearchFeature.MULTI_PATTERN)
				.createTestSuite());
		testSuite.addTest(StringSearchAlgorithmTestSuiteBuilder.forAlgorithm(StringSearchAlgorithms.RABIN_KARP)
				.withFeatures(StringSearchFeature.MULTI_PATTERN)
				.createTestSuite());
		
		return testSuite;
	}
}
