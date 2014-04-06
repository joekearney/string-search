package joe.strings.testfw;

import java.util.List;

import joe.strings.StringSearchAlgorithm;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.testing.AbstractTester;
import com.google.common.collect.testing.FeatureSpecificTestSuiteBuilder;

public final class StringSearchAlgorithmTestSuiteBuilder extends
		FeatureSpecificTestSuiteBuilder<StringSearchAlgorithmTestSuiteBuilder, StringSearchAlgorithmTestSubjectGenerator> {

	private ImmutableSet<Class<? extends TestCase>> customTestCases = ImmutableSet.of();

	private StringSearchAlgorithmTestSuiteBuilder(StringSearchAlgorithm algorithm) {
		usingGenerator(new StringSearchAlgorithmTestSubjectGenerator(algorithm));
		named(algorithm.getClass().getSimpleName());
	}
	
	public static StringSearchAlgorithmTestSuiteBuilder forAlgorithm(Supplier<StringSearchAlgorithm> supplier) {
		return new StringSearchAlgorithmTestSuiteBuilder(supplier.get());
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

	@Override
	public TestSuite createTestSuite() {
		TestSuite testSuite = super.createTestSuite();
		for (Class<? extends TestCase> testCase : customTestCases) {
			testSuite.addTestSuite(testCase);
		}
		return testSuite;
	}
}
