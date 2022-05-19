package libjoe.strings.testfw;

import java.util.List;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import libjoe.strings.StringSearchAlgorithm;
import libjoe.strings.StringSearchAlgorithms;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.testing.AbstractTester;
import com.google.common.collect.testing.FeatureSpecificTestSuiteBuilder;

public final class StringSearchAlgorithmTestSuiteBuilder extends
		FeatureSpecificTestSuiteBuilder<StringSearchAlgorithmTestSuiteBuilder, StringSearchAlgorithmTestSubjectGenerator> {

	private ImmutableSet<Class<? extends TestCase>> customTestCases = ImmutableSet.of();
	private ImmutableSet<Class<?>> quickcheckTests = ImmutableSet.of();

	private StringSearchAlgorithmTestSuiteBuilder(StringSearchAlgorithm algorithm) {
		usingGenerator(new StringSearchAlgorithmTestSubjectGenerator(algorithm));
		named(algorithm.getClass().getSimpleName());
	}

	public static StringSearchAlgorithmTestSuiteBuilder forAlgorithm(StringSearchAlgorithms algorithm) {
		return new StringSearchAlgorithmTestSuiteBuilder(algorithm.get());
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List<Class<? extends AbstractTester>> getTesters() {
		return ImmutableList.<Class<? extends AbstractTester>> of(SimpleExamplesTester.class);
	}

	@SafeVarargs
	public final StringSearchAlgorithmTestSuiteBuilder withCustomTestClasses(Class<? extends TestCase> ... testClasses) {
		customTestCases = ImmutableSet.copyOf(testClasses);
		return this;
	}

	public final StringSearchAlgorithmTestSuiteBuilder withQuickcheckTest(Class<?> ... testClasses) {
		quickcheckTests = ImmutableSet.copyOf(testClasses);
		return this;
	}

	@Override
	public TestSuite createTestSuite() {
		TestSuite testSuite = super.createTestSuite();
		for (Class<? extends TestCase> testCase : customTestCases) {
			testSuite.addTestSuite(testCase);
		}
		for (Class<?> quickcheckTest : quickcheckTests) {
			testSuite.addTest(new JUnit4TestAdapter(quickcheckTest));
		}
		return testSuite;
	}
}
