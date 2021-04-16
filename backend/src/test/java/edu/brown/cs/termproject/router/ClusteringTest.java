package edu.brown.cs.termproject.router;

import edu.brown.cs.termproject.locationgraph.College;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class ClusteringTest {

  public final int NUM_TESTS = 10;

  @Test
  public void definedClusterTest() {
    College c1 = new College(1, "A", 1, 1, "placeholder", "placeholder");
    College c2 = new College(2, "B", 1, 4, "placeholder", "placeholder");
    College c3 = new College(3, "C", 2, 2, "placeholder", "placeholder");
    College c4 = new College(4, "D", 2, 3, "placeholder", "placeholder");
    College c5 = new College(9, "E", 12, 4, "placeholder", "placeholder");
    College c6 = new College(6, "F", 7, 8, "placeholder", "placeholder");
    College c7 = new College(7, "G", 8, 9, "placeholder", "placeholder");
    College c8 = new College(8, "H", 9, 10, "placeholder", "placeholder");
    College c9 = new College(5, "I", 12, 10, "placeholder", "placeholder");
    College c10 = new College(10, "J", 9, 3,"placeholder", "placeholder");
    College c11 = new College(11, "K", 8, 2, "placeholder", "placeholder");
    College c12 = new College(12, "L", 12, 1, "placeholder", "placeholder");
    List<Locatable> colleges = new ArrayList<>(Arrays.asList(c1, c2, c3, c4, c5, c6, c7,
        c8, c9, c10, c11, c12));
    Clustering clustering = new Clustering (555);
    for (int i = 0; i < NUM_TESTS; i++) {
      Collections.shuffle(colleges);
      Map<Locatable, List<Locatable>> clusters =  clustering.makeClusters(colleges);
      int numClusters = 0;
      for (Map.Entry<Locatable, List<Locatable>> entry : clusters.entrySet()) {
        numClusters += 1;
      }
      assertEquals(3, numClusters);
    }
  }

  @Test
  public void OneClusterTest() {
    College c1 = new College(1, "A", 1, 1, "placeholder", "placeholder");
    College c2 = new College(2, "B", 2, 2, "placeholder", "placeholder");
    College c3 = new College(3, "C", 3, 3, "placeholder", "placeholder");
    College c4 = new College(4, "D", 1, 2, "placeholder", "placeholder");
    College c5 = new College(9, "E", 3, 2, "placeholder", "placeholder");
    College c6 = new College(6, "F", 3, 1, "placeholder", "placeholder");
    College c7 = new College(7, "G", 2, 3, "placeholder", "placeholder");
    College c8 = new College(8, "H", 4, 1, "placeholder", "placeholder");
    College c9 = new College(5, "I", 4, 4, "placeholder", "placeholder");
    College c10 = new College(10, "J", 4, 2, "placeholder", "placeholder");
    College c11 = new College(11, "K", 2, 4, "placeholder", "placeholder");
    College c12 = new College(12, "L", 3, 3, "placeholder", "placeholder");
    List<Locatable> colleges = new ArrayList<>(Arrays.asList(c1, c2, c3, c4, c5, c6, c7,
        c8, c9, c10, c11, c12));
    Clustering clustering = new Clustering (555);
    for (int i = 0; i < NUM_TESTS; i++) {
      Collections.shuffle(colleges);
      Map<Locatable, List<Locatable>> clusters =  clustering.makeClusters(colleges);
      int numClusters = 0;
      for (Map.Entry<Locatable, List<Locatable>> entry : clusters.entrySet()) {
        numClusters += 1;
      }
      assertEquals(1, numClusters);
    }
  }
}
