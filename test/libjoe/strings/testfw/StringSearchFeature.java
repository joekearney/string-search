package libjoe.strings.testfw;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Set;

import libjoe.strings.StringSearchAlgorithm;

import com.google.common.collect.testing.Helpers;
import com.google.common.collect.testing.features.Feature;
import com.google.common.collect.testing.features.TesterAnnotation;

@SuppressWarnings("unchecked")
public enum StringSearchFeature implements Feature<StringSearchAlgorithm> {
	MULTI_PATTERN, EXPECTED_SLOW;

  private final Set<Feature<? super StringSearchAlgorithm>> implied;

  StringSearchFeature(Feature<? super StringSearchAlgorithm> ... implied) {
    this.implied = Helpers.copyToSet(implied);
  }

  @Override
  public Set<Feature<? super StringSearchAlgorithm>> getImpliedFeatures() {
    return implied;
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Inherited
  @TesterAnnotation
  public @interface Require {
    public abstract StringSearchFeature[] value() default {};
    public abstract StringSearchFeature[] absent() default {};
  }
}