package edu.brown.cs.termproject.router;

import com.google.maps.errors.ApiException;
import edu.brown.cs.termproject.collegegraph.College;
import edu.brown.cs.termproject.collegegraph.CollegeGraph;
import edu.brown.cs.termproject.collegegraph.Path;
import edu.brown.cs.termproject.graph.Graph;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for TSP algorithm.
 */
public class TSPTest {

  List<College> _colleges;
  TSP<College, Path> _tsp;

  @Before
  public void setUp() {
    College c1 = new College(2, "UCLA", 34.068921, -118.4451811, "placeholder", "placeholder");
    College c2 = new College(2, "Stanford University", 37.428230, -122.168861, "placeholder", "placeholder");
    College c3 = new College(2, "UCB", 37.8718992, -122.2585399, "placeholder", "placeholder");
    College c4 = new College(4, "California Institute of Technology", 34.138000, -118.125000, "placeholder", "placeholder");
    _colleges = new ArrayList<>(Arrays.asList(c1, c2, c3, c4));
    _tsp = new TSP<>();
  }

  @Test
  public void testFindRoute() throws InterruptedException, ApiException, IOException {
    CollegeGraph graph = new CollegeGraph(_colleges);
    List<College> tsp = _tsp.findRoute(graph);
    System.out.println(tsp);
  }
}
