package joe.strings;

import joe.strings.impl.KnuthMorrisPrattJumpTableTester;
import joe.strings.testfw.StringSearchAlgorithmTestSuiteBuilder;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TestsForStringSearchAlgorithms {
	public static Test suite() {
		TestSuite testSuite = new TestSuite("string-matching");

		testSuite.addTest(StringSearchAlgorithmTestSuiteBuilder.forAlgorithm(StringSearchAlgorithms.STRING_INDEX_OF)
				.createTestSuite());
		testSuite.addTest(StringSearchAlgorithmTestSuiteBuilder.forAlgorithm(StringSearchAlgorithms.BRUTE_FORCE)
				.createTestSuite());
		testSuite.addTest(StringSearchAlgorithmTestSuiteBuilder.forAlgorithm(StringSearchAlgorithms.RABIN_KARP)
				.createTestSuite());
		testSuite.addTest(StringSearchAlgorithmTestSuiteBuilder.forAlgorithm(StringSearchAlgorithms.KNUTH_MORRIS_PRATT)
				.withCustomTestClasses(KnuthMorrisPrattJumpTableTester.class)
				.createTestSuite());
		testSuite.addTest(StringSearchAlgorithmTestSuiteBuilder.forAlgorithm(StringSearchAlgorithms.BOYER_MOORE)
				.createTestSuite());
		
		return testSuite;
	}
}
