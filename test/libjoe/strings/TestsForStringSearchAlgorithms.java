package libjoe.strings;

import junit.framework.Test;
import junit.framework.TestSuite;
import libjoe.strings.impl.KnuthMorrisPrattJumpTableTester;
import libjoe.strings.testfw.StringSearchAlgorithmTestSuiteBuilder;
import libjoe.strings.testfw.StringSearchFeature;

public class TestsForStringSearchAlgorithms {
	public static Test suite() {
		TestSuite testSuite = new TestSuite("string-matching");

		testSuite.addTest(StringSearchAlgorithmTestSuiteBuilder
				.forAlgorithm(StringSearchAlgorithms.STRING_INDEX_OF)
				.withFeatures(StringSearchFeature.EXPECTED_SLOW)
				.createTestSuite());
		testSuite.addTest(StringSearchAlgorithmTestSuiteBuilder
				.forAlgorithm(StringSearchAlgorithms.BRUTE_FORCE)
				.withFeatures(StringSearchFeature.EXPECTED_SLOW)
				.createTestSuite());
		testSuite.addTest(StringSearchAlgorithmTestSuiteBuilder
				.forAlgorithm(StringSearchAlgorithms.RABIN_KARP)
				.createTestSuite());
		testSuite.addTest(StringSearchAlgorithmTestSuiteBuilder
				.forAlgorithm(StringSearchAlgorithms.KNUTH_MORRIS_PRATT)
				.withCustomTestClasses(KnuthMorrisPrattJumpTableTester.class)
				.createTestSuite());
		testSuite.addTest(StringSearchAlgorithmTestSuiteBuilder
				.forAlgorithm(StringSearchAlgorithms.AHO_CORASICK_LIB)
				.createTestSuite());

		return testSuite;
	}
}
