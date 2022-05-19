package libjoe.strings;

import junit.framework.Test;
import junit.framework.TestSuite;
import libjoe.strings.StringMatcherQuickcheckTest.AhoCorasickQuickcheckTest;
import libjoe.strings.StringMatcherQuickcheckTest.BruteForceQuickcheckTest;
import libjoe.strings.StringMatcherQuickcheckTest.KnuthMorrisPrattQuickcheckTest;
import libjoe.strings.StringMatcherQuickcheckTest.RabinKarpQuickcheckTest;
import libjoe.strings.StringMatcherQuickcheckTest.StringIndexOfQuickcheckTest;
import libjoe.strings.impl.KnuthMorrisPrattJumpTableTester;
import libjoe.strings.testfw.StringSearchAlgorithmTestSuiteBuilder;
import libjoe.strings.testfw.StringSearchFeature;

public class TestsForStringSearchAlgorithms {

  public static Test suite() {
    TestSuite testSuite = new TestSuite("string-matching");

    testSuite.addTest(StringSearchAlgorithmTestSuiteBuilder
        .forAlgorithm(StringSearchAlgorithms.STRING_INDEX_OF)
        .withFeatures(StringSearchFeature.EXPECTED_SLOW)
        .withQuickcheckTest(StringIndexOfQuickcheckTest.class)
        .createTestSuite());
    testSuite.addTest(StringSearchAlgorithmTestSuiteBuilder
        .forAlgorithm(StringSearchAlgorithms.BRUTE_FORCE)
        .withFeatures(StringSearchFeature.EXPECTED_SLOW)
        .withQuickcheckTest(BruteForceQuickcheckTest.class)
        .createTestSuite());
    testSuite.addTest(StringSearchAlgorithmTestSuiteBuilder
        .forAlgorithm(StringSearchAlgorithms.RABIN_KARP)
        .withQuickcheckTest(RabinKarpQuickcheckTest.class)
        .createTestSuite());
    testSuite.addTest(StringSearchAlgorithmTestSuiteBuilder
        .forAlgorithm(StringSearchAlgorithms.KNUTH_MORRIS_PRATT)
        .withCustomTestClasses(KnuthMorrisPrattJumpTableTester.class)
        .withQuickcheckTest(KnuthMorrisPrattQuickcheckTest.class)
        .createTestSuite());
    testSuite.addTest(StringSearchAlgorithmTestSuiteBuilder
        .forAlgorithm(StringSearchAlgorithms.AHO_CORASICK_LIB)
        .withQuickcheckTest(AhoCorasickQuickcheckTest.class)
        .createTestSuite());

    return testSuite;
  }
}
