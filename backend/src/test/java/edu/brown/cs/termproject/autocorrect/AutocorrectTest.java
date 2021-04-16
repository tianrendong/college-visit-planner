package edu.brown.cs.termproject.autocorrect;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class AutocorrectTest {

  Autocorrector autocorrector;

  @Before
  public void setUp() {
    List<String> phrases = new ArrayList<>(
        Arrays.asList("Chihiro Ave", "Kamaji Pl", "Kamaji Pl2", "Radish Spirit Blvd",
            "Sootball Ln", "Yubaba St", "United States of America"));
    autocorrector = new Autocorrector(phrases, true, false, 5);
  }

  @Test
  public void suggest() {
    Set<String> suggestions = Sets.newHashSet("Chihiro Ave");
    assertEquals(suggestions, autocorrector.suggest("Chihiro Av"));
    assertEquals(suggestions, autocorrector.suggest("chihiro av"));
    assertEquals(suggestions, autocorrector.suggest("Chih"));
    assertEquals(suggestions, autocorrector.suggest("chihiroave"));
    assertEquals(suggestions, autocorrector.suggest("ChihiroA"));
    assertEquals(suggestions, autocorrector.suggest("Chihiro Pl"));

    suggestions = Sets.newHashSet("Kamaji Pl", "Kamaji Pl2");
    assertEquals(suggestions, autocorrector.suggest("jamaji"));
    assertEquals(suggestions, autocorrector.suggest("KAMAJI"));
    assertEquals(suggestions, autocorrector.suggest("KMaJI Pl"));

    suggestions = Sets.newHashSet("United States of America");
    assertEquals(suggestions, autocorrector.suggest("UnitedStatesofAmerica"));
  }


}
