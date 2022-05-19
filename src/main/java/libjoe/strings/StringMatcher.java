package libjoe.strings;

import java.util.Optional;

/**
 * A {@code StringMatcher} can perform a search over a text. Any required pre-processing of the pattern(s) has been done in creating this object.
 * 
 * @author Joe Kearney
 */
public interface StringMatcher {
	Optional<StringMatch> search(CharSequence haystack);
}
