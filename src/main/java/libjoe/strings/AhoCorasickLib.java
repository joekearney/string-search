package libjoe.strings;

import java.util.Collection;
import java.util.Set;

import libjoe.strings.util.CharSequenceExposingArray;

import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.google.monitoring.runtime.instrumentation.common.com.google.common.collect.Iterables;

public class AhoCorasickLib implements MultiPatternStringSearchAlgorithm {

	@Override
	public StringMatcher matchPattern(CharSequence needle) {
		return matchPattern(ImmutableSet.of(needle));
	}

	@Override
	public StringMatcher matchPattern(Set<? extends CharSequence> needles) {
		final Trie trie = new Trie();
		for (CharSequence needle : needles) {
			trie.addKeyword(CharSequenceExposingArray.toString(needle));
		}

		return new StringMatcher() {
			@Override
			public Optional<StringMatch> search(CharSequence haystack) {
				Collection<Emit> emits = trie.parseText(CharSequenceExposingArray.toString(haystack));
				if (emits.isEmpty()) {
					return Optional.absent();
				} else {
					Emit emit = Iterables.get(emits, 0);
					return Optional.of(new StringMatch(emit.getKeyword(), haystack, emit.getStart()));
				}
			}
		};
	}

}
