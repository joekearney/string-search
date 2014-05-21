package libjoe.strings;

import java.util.Set;

import com.google.common.base.Optional;

/**
 * A support class to adapt
 *
 * @author Joe Kearney
 */
public abstract class AbstractSequentialMultiPatternStringSearchAlgorithm implements MultiPatternStringSearchAlgorithm {
	@Override
	public final StringMatcher matchPattern(Set<? extends CharSequence> needles) {
		return new SequentialMultiPatternMatcher(needles);
	}

	private final class SequentialMultiPatternMatcher implements StringMatcher {
		private final Set<? extends CharSequence> needles;

		SequentialMultiPatternMatcher(Set<? extends CharSequence> needles) {
			this.needles = needles;
		}

		@Override
		public Optional<StringMatch> search(CharSequence haystack) {
			for (CharSequence needle : needles) {
				Optional<StringMatch> match = matchPattern(needle).search(haystack);
				if (match.isPresent()) {
					return match;
				}
			}
			return Optional.absent();
		}
	}
}
