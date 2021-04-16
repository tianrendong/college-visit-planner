package edu.brown.cs.termproject.autocorrect;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class TrieTest {
  private Trie _trie;

  /**
   * Sets up the trie using a few sample words.
   */
  @Before
  public void setUp() {
    _trie = new Trie();
    List<String> words = new ArrayList<String>();
    words.add("abc");
    words.add("Car ride");
    words.add("card");
    words.add("carpool");
    words.add("dig");
    words.add("dive");
    words.add("drive");
    words.add("way");
    words.add("big");
    words.add("bite");
    words.add("United States of America");
    _trie.insertAll(words);

  }

  /**
   * Resets the Trie.
   */
  @After
  public void tearDown() {
    _trie = null;

  }

  /**
   ** Tests whether the trie contains a given word.
   */

  @Test
  public void testHasWord() {
    setUp();
    assertTrue(_trie.hasWord("carpool", true));
    assertTrue(_trie.hasWord("AbC", false));
    assertTrue(_trie.hasWord("carpool", false));
    assertTrue(_trie.hasWord("CarpOol", false));
    assertFalse(_trie.hasWord("CarpOol", true));
    assertFalse(_trie.hasWord("code", true));
    tearDown();
  }

  /**
   ** Tests whether the words in trie with given prefix are returned correctly.
   */

  @Test
  public void testAllWithPrefix() {
    setUp();
    Set<String> wordsWithPref = _trie.findAllWithPrefix("ca", true);
    assertEquals(2, wordsWithPref.size());
    assertFalse(wordsWithPref.contains("Car ride"));
    assertTrue(wordsWithPref.contains("card"));
    assertTrue(wordsWithPref.contains("carpool"));
    assertFalse(wordsWithPref.contains("drive"));

    wordsWithPref = _trie.findAllWithPrefix("ca", false);
    assertEquals(3, wordsWithPref.size());
    assertTrue(wordsWithPref.contains("Car ride"));
    assertTrue(wordsWithPref.contains("carpool"));

    wordsWithPref = _trie.findAllWithPrefix("united", false);
    assertTrue(wordsWithPref.contains("United States of America"));


    wordsWithPref = _trie.findAllWithPrefix("uniTEd", false);
    assertTrue(wordsWithPref.contains("United States of America"));

    tearDown();

  }


  /**
   ** Tests that the correct led is found between two words.
   */

  @Test
  public void testLedDistance() {
    setUp();
    assertEquals(Trie.getLedDistance("car", "card"), 1);
    assertEquals(Trie.getLedDistance("big", "dig"), 1);
    assertEquals(Trie.getLedDistance("dig", "dive"), 2);
    assertEquals(Trie.getLedDistance("drive", "dive"), 1);
    tearDown();
  }

  /**
   ** Tests that the set of all words in Trie with led less than or equal to input phrase
   * has correct output.
   */

  @Test
  public void testLed() {
    setUp();
    Set<String> wordsWithinLed = _trie.findLedWithinRoot("cat", 2);
    System.out.println(wordsWithinLed);
    assertEquals(2, wordsWithinLed.size());
    assertTrue(wordsWithinLed.contains("card"));
    assertTrue(wordsWithinLed.contains("way"));
    Set<String> wordsWithinLed2 = _trie.findLedWithinRoot("dig", 2);
    assertEquals(wordsWithinLed2.size(), 3);
    assertTrue(wordsWithinLed2.contains("dig"));
    assertTrue(wordsWithinLed2.contains("big"));
    assertTrue(wordsWithinLed2.contains("dive"));
    tearDown();

  }



}
