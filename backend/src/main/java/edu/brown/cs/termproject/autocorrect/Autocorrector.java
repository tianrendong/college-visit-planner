package edu.brown.cs.termproject.autocorrect;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class to create autocorrect suggestions given input.
 */
public class Autocorrector {
  private final Trie trie;
  private final boolean prefix;
  private final boolean caseSensitive;
  private final int led;

  /**
   * Constructs a autocorrector.
   * @param words list of existing words
   * @param prefixIn true if autocorrector searches for words based on prefix
   * @param caseSensitiveIn true if autocorrector is case sensiitve
   * @param ledIn Levenshtein edit distance
   */
  public Autocorrector(List<String> words, boolean prefixIn, boolean caseSensitiveIn, int ledIn) {
    trie = new Trie();
    trie.insertAll(words);
    prefix = prefixIn;
    caseSensitive = caseSensitiveIn;
    led = ledIn;
  }

  /**
   * Given phrase input by user, gives autocorrect suggestions.
   *
   * @param phrase Input phrase to suggest autocorrections to
   * @return Set of strings representing suggestions
   */
  public Set<String> suggest(String phrase) {
    // Search trie for each type of flag set and add resulting words.
    Set<String> trieOutput = new TreeSet<>();
    if (prefix) {
      if (caseSensitive) {
        trieOutput.addAll(trie.findAllWithPrefix(phrase, true));
      } else {
        trieOutput.addAll(trie.findAllWithPrefix(phrase, false));
      }
    }
    // led distance only turned on when length of input > 3
    if ((led > 0) && (phrase.length() > 3)) {
      trieOutput.addAll(trie.findLedWithinRoot(phrase, led));
    }

    // Append suggestions to earlier part of phrase.
    List<String> trieOutputAsList = new ArrayList<>(trieOutput);
    Set<String> suggestions = new TreeSet<>();

    for (int i = 0; i < Math.min(5, trieOutputAsList.size()); i++) {
      suggestions.add(trieOutputAsList.get(i));
    }
    return suggestions;
  }

}
