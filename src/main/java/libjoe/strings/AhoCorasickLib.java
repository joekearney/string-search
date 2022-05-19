package libjoe.strings;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;
import org.ahocorasick.trie.Trie.TrieBuilder;

public class AhoCorasickLib implements MultiPatternStringSearchAlgorithm {

	@Override
	public StringMatcher matchPattern(CharSequence needle) {
		return matchPattern(ImmutableSet.of(needle));
	}

	@Override
	public StringMatcher matchPattern(Set<? extends CharSequence> needles) {
		final TrieBuilder trieBuilder = Trie.builder();
		for (CharSequence needle : needles) {
			trieBuilder.addKeyword(needle.toString());
		}

		Trie trie = trieBuilder.build();

		return haystack -> {
			Collection<Emit> emits = trie.parseText(haystack);
			if (emits.isEmpty()) {
				return Optional.empty();
			} else {
				Emit emit = Iterables.get(emits, 0);
				return Optional.of(new StringMatch(emit.getKeyword(), haystack, emit.getStart()));
			}
		};
	}

}
