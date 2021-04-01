package edu.brown.cs.termproject.router;

import com.google.maps.errors.ApiException;
import edu.brown.cs.termproject.collegegraph.College;
import edu.brown.cs.termproject.collegegraph.CollegeGraph;
import edu.brown.cs.termproject.collegegraph.Path;
import edu.brown.cs.termproject.graph.Graph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class ClusteringTest {

  List<Locatable> _colleges;

  @Before
  public void setUp() {
    College c1 = new College(1, "A", 1, 1);
    College c2 = new College(2, "B", 1, 4);
    College c3 = new College(3, "C", 2, 2);
    College c4 = new College(4, "D", 2, 3);
    College c5 = new College(5, "E", 12, 10);
    College c6 = new College(6, "F", 7, 8);
    College c7 = new College(7, "G", 8, 9);
    College c8 = new College(8, "H", 9, 10);
    _colleges = new ArrayList<>(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8));
  }

  @After
  public void tearDown() {
    _colleges = null;
  }

  @Test
  public void testClustering() {
    setUp();
    Clustering clustering = new Clustering (6.0);
    Map<Locatable, List<Locatable>> clusters =  clustering.makeClusters(_colleges);
    for (Map.Entry<Locatable, List<Locatable>> entry : clusters.entrySet())
      System.out.println("Key = " + entry.getKey() +
          ", Value = " + entry.getValue());
    tearDown();
  }
}
