package joe.strings.testfw;

import java.util.List;

import joe.strings.StringSearchAlgorithm;

import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.testing.AbstractTester;
import com.google.common.collect.testing.FeatureSpecificTestSuiteBuilder;

public class StringSearchAlgorithmTestSuiteBuilder extends
		FeatureSpecificTestSuiteBuilder<StringSearchAlgorithmTestSuiteBuilder, StringSearchAlgorithmTestSubjectGenerator> {

	private StringSearchAlgorithmTestSuiteBuilder(StringSearchAlgorithm algorithm) {
		usingGenerator(new StringSearchAlgorithmTestSubjectGenerator(algorithm));
		named(algorithm.getClass().getSimpleName());
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List<Class<? extends AbstractTester>> getTesters() {
		return ImmutableList.<Class<? extends AbstractTester>> of(SimpleExamplesTester.class);
	}

	public static StringSearchAlgorithmTestSuiteBuilder forAlgorithm(Supplier<StringSearchAlgorithm> supplier) {
		return new StringSearchAlgorithmTestSuiteBuilder(supplier.get());
	}

}
