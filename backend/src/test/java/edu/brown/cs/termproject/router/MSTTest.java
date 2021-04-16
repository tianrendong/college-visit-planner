package edu.brown.cs.termproject.router;

import com.google.maps.errors.ApiException;
import edu.brown.cs.termproject.locationgraph.*;
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

public class MSTTest {

  List<College> _colleges;
  List<Location> _locations;

  @Before
  public void setUp() {
    College c1 = new College(1, "Massachusetts Institute of Technology", 42.360001, -71.092003, "placeholder", "placeholder");
    College c2 = new College(2, "Stanford University", 37.428230, -122.168861, "placeholder", "placeholder");
    College c3 = new College(3, "Harvard University", 42.374443, -71.116943, "placeholder", "placeholder");
    College c4 = new College(4, "California Institute of Technology", 34.138000, -118.125000, "placeholder", "placeholder");
    _colleges = new ArrayList<>(Arrays.asList(c1, c2, c3, c4));
    _locations = new ArrayList<>();
    for (College c : _colleges) {
      _locations.add(new Location(c.getId(), c.getName(), c.getLat(), c.getLon(), "college", c));
    }
  }

  @Test
  public void testMST() throws InterruptedException, ApiException, IOException {
    setUp();
    Graph<Location, Path> graph = new LocationGraph(_locations);
    Comparator<Path> comparator = new Comparator<>() {
      @Override
      public int compare(Path o1, Path o2) {
        return Double.compare(o1.getWeight(), o2.getWeight());
      }
    };
    Set<Path> mstEdges = MST.mst(graph, comparator);
    Graph<Location, Path> mst = new LocationGraph(new ArrayList<>());
    for (Path p : mstEdges) {
      mst.addEdge(p);
      System.out.println(p);
    }
    assertEquals(graph.getVertices(), mst.getVertices());
    assertEquals(3, mst.getEdges().size());
  }
}
