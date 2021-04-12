package edu.brown.cs.termproject.autocorrect;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Trie data structure for storing strings, prefixes in particular.
 */
public class Trie {

  private final Map<Character, Trie> children;
  private boolean isWord;

  /**
   * Creates an empty Trie object.
   */
  public Trie() {
    isWord = false;
    children = new HashMap<>();
  }

  /**
   * Inserts a single word into Trie.
   *
   * @param word Inserted word
   */
  public void insert(String word) {
    if (word.equals("")) {
      isWord = true;
    } else {
      char firstChar = word.charAt(0);
      if (!(children.containsKey(firstChar))) {
        children.put(firstChar, new Trie());
      }
      children.get(firstChar).insert(word.substring(1));
    }
  }

  /**
   * Inserts a list of words into Trie.
   *
   * @param words Inserted words
   */
  public void insertAll(List<String> words) {
    for (String word : words) {
      insert(word);
    }
  }

  /**
   * Determines if word input is in Trie.
   *
   * @param word Word to check
   * @param caseSensitive if the check is case sensitive
   * @return True if held, false otherwise
   */
  public boolean hasWord(String word, boolean caseSensitive) {
    if (word.equals("")) {
      return isWord;
    } else {
      char firstChar = word.charAt(0);
      char firstCharUpper = Character.toUpperCase(firstChar);
      char firstCharLower = Character.toLowerCase(firstChar);

      if (caseSensitive) {
        if (children.containsKey(firstChar)) {
          return children.get(firstChar).hasWord(word.substring(1), true);
        } else {
          return false;
        }

      } else {
        boolean hasWord = false;
        if (children.containsKey(firstCharUpper)) {
          hasWord = children.get(firstCharUpper).hasWord(word.substring(1), false);
        }
        if (children.containsKey(firstCharLower)) {
          hasWord = hasWord || children.get(firstCharLower).hasWord(word.substring(1), false);
        }
        return hasWord;
      }
    }
  }


  /**
   * Finds all words in trie with given prefix.
   *
   * @param prefix the prefix in question
   * @param caseSensitive if the search is case sensitive
   * @return A set of strings that are in trie and have input prefix
   */
  public Set<String> findAllWithPrefix(String prefix, boolean caseSensitive) {
    Set<String> listTotalPrefix = new TreeSet<>();
    listTotalPrefix.add(prefix);
    return findAllWithPrefixHelper(prefix, prefix, caseSensitive, 0, listTotalPrefix, this);
  }

  /**
   * Helper function to find all words in trie with given prefix.
   *
   * @param prefix the prefix in question
   * @param totalPrefix repeat parameter necessary for building strings.
   * @param caseSensitive if the search is case sensitive
   * @param charIndex index of the current Char
   * @return A set of strings that are in trie and have input prefix
   */
  private Set<String> findAllWithPrefixHelper(
      String prefix, String totalPrefix, boolean caseSensitive, int charIndex,
      Set<String> currentList, Trie trie
  ) {
    if (prefix.equals("")) {
      Set<String> output = new TreeSet<>();
      for (String pref : currentList) {
        output.addAll(Trie.getPossibleCompletions(trie, pref));
      }
      return output;

    } else {
      char firstChar = prefix.charAt(0);

      if (caseSensitive) {
        if (children.containsKey(firstChar)) {
          return children.get(firstChar).findAllWithPrefixHelper(
                  prefix.substring(1), totalPrefix, true, 0, currentList, trie
          );
        } else {
          return Collections.emptySet();
        }

      } else {

        char firstCharUpper = Character.toUpperCase(firstChar);
        char firstCharLower = Character.toLowerCase(firstChar);

        if (children.containsKey(firstCharUpper)) {
          String modifiedTotalPrefix =
              totalPrefix.substring(0, charIndex)
                      + firstCharUpper + totalPrefix.substring(charIndex + 1);

          currentList.add(modifiedTotalPrefix);

          return children.get(firstCharUpper)
              .findAllWithPrefixHelper(prefix.substring(1),
                  modifiedTotalPrefix, false, charIndex + 1, currentList, trie);

        } else if (children.containsKey(firstCharLower)) {
          String modifiedTotalPrefix =
              totalPrefix.substring(0, charIndex)
                      + firstCharLower + totalPrefix.substring(charIndex + 1);

          currentList.add(modifiedTotalPrefix);

          return children.get(firstCharLower)
              .findAllWithPrefixHelper(prefix.substring(1),
                  modifiedTotalPrefix, false, charIndex + 1, currentList, trie);

        } else {
          return Collections.emptySet();
        }

      }

    }
  }

  /**
   * Finds the trie branch that oomes after prefix.
   *
   * @param prefix Prefix
   * @return Trie branch that comes after prefix.
   *         E.g. if prefix is "Ca", returns the Trie branch that starts with "a"
   */
  private Trie search(final String prefix) {
    char firstChar = prefix.charAt(0);
    final Trie child = children.get(firstChar);
    if (child == null || prefix.length() == 1) {
      return child;
    } else {
      return child.search(prefix.substring(1));
    }
  }

  /**
   * Find all possible words that start with prefix from trie.
   *
   * @param trie Trie to search from
   * @param prefix Prefix
   * @return all possible completions
   */
  public static Set<String> getPossibleCompletions(Trie trie, final String prefix) {
    final Set<String> completions = new HashSet<>();
    final Trie current = trie.search(prefix);
    if (current == null) {
      return completions;
    }
    current.getPossibleCompletionsRec(prefix, completions);
    return completions;
  }

  /**
   * Helper function to find all possible words that start with prefix.
   * @param current the completed part of word so far
   * @param completions found words
   */
  private void getPossibleCompletionsRec(final String current,
                                         final Set<String> completions) {
    if (isWord) {
      completions.add(current);
    }
    Map<Character, Trie> possibilities = this.children;
    // loop through all the characters that follow
    for (char c : possibilities.keySet()) {
      possibilities.get(c).getPossibleCompletionsRec(current + c, completions);
    }
  }

  /**
   * Finds all words within a certain led from some phrase in trie.
   *
   * @param phrase      Phrase of query
   * @param maxDistance Max led
   * @param prefix      Current prefix built up to this point
   * @param currentList Current set of words found
   * @return Returns set of all words with led less than or equal to phrase in
   * trie.
   */
  private Set<String> findLedWithin(String phrase, int maxDistance,
                                    String prefix, Set<String> currentList) {

    int currentDist = maxDistance + 1;

    if (isWord) {
      currentDist = getLedDistance(phrase, prefix);
    }

    if (currentDist <= maxDistance) {
      currentList.add(prefix);
    }
    if (prefix.length() - phrase.length() <= maxDistance) {
      Map<Character, Trie> possibilities = children;
      for (char c : possibilities.keySet()) {
        String sb = prefix + c;
        possibilities.get(c).findLedWithin(phrase, maxDistance, sb,
            currentList);
      }
    }
    return currentList;
  }

  /**
   * Finds all words in Trie with led less than or equal to maxDistance from
   * phrase.
   *
   * @param phrase Phrase from query
   * @param maxDistance max led
   * @return Returns list of all words with led less than or equal to phrase in
   * trie
   */
  public Set<String> findLedWithinRoot(String phrase, int maxDistance) {
    return findLedWithin(phrase, maxDistance, "", new TreeSet<>());
  }

  /**
   * Gets led between two words.
   * Uses dynamic programming.
   *
   * @param word1 First word
   * @param word2 Second word
   * @return led between two words
   */
  public static int getLedDistance(String word1, String word2) {
    int size1 = word1.length();
    int size2 = word2.length();
    int[][] ledMatrix = new int[size1 + 1][size2 + 1];

    for (int i = 0; i < size1 + 1; i++) {
      for (int j = 0; j < size2 + 1; j++) {

        // Comparing if word1 was empty.
        if (i == 0) {
          ledMatrix[i][j] = j;
        } else if (j == 0) {
          // Comparing is word2 was empty.
          ledMatrix[i][j] = i;
        } else {
          // See if adding corresponding characters would not increase led.
          int substitution = 1;
          if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            substitution = 0;
          }
          // Take min of insertion, deletion, substituion.
          ledMatrix[i][j] = Math.min(Math.min(ledMatrix[i][j - 1] + 1, ledMatrix[i - 1][j] + 1),
              ledMatrix[i - 1][j - 1] + substitution);
        }
      }
    }
    return ledMatrix[size1][size2];
  }
}
