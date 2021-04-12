package edu.brown.cs.termproject.router;

import com.google.maps.errors.ApiException;
import edu.brown.cs.termproject.collegegraph.College;
import edu.brown.cs.termproject.collegegraph.Location;
import edu.brown.cs.termproject.collegegraph.LocationGraph;
import edu.brown.cs.termproject.collegegraph.LocationPath;
import edu.brown.cs.termproject.graph.Graph;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for Naive implementation of TSP.
 */
public class NaiveTSPTest {
  @Test
  public void testTSP() throws InterruptedException, ApiException, IOException {
    College c1 = new College(1, "Massachusetts Institute of Technology", 42.360001, -71.092003, "placeholder", "placeholder");
    College c2 = new College(2, "Stanford University", 37.428230, -122.168861, "placeholder", "placeholder");
    College c3 = new College(3, "Harvard University", 42.374443, -71.116943, "placeholder", "placeholder");
    College c4 = new College(4, "California Institute of Technology", 34.138000, -118.125000, "placeholder", "placeholder");
    List<College> colleges = new ArrayList<>(Arrays.asList(c1, c2, c3, c4));
    List<Location> locations = new ArrayList<>();
    for (College c : colleges) {
      locations.add(new Location(c.getId(), c.getName(), c.getLat(), c.getLon(), "college", c));
    }
    Graph<Location, LocationPath> graph = new LocationGraph(locations);
    Comparator<LocationPath> comparator = new Comparator<>() {
      @Override
      public int compare(LocationPath o1, LocationPath o2) {
        return Double.compare(o1.getWeight(), o2.getWeight());
      }
    };
    Set<LocationPath> mstEdges = MST.mst(graph, comparator);
    List<Location> tsp = NaiveTSP.findRoute(mstEdges);
    assertEquals(4, tsp.size());
    System.out.println(tsp);
  }
}
