package joe.strings;

import java.util.Set;

public interface MultiPatternStringSearchAlgorithm extends StringSearchAlgorithm {
	StringMatcher matchPattern(Set<? extends CharSequence> needles);
}
