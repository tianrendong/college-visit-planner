package edu.brown.cs.termproject.router;

import com.google.maps.errors.ApiException;
import edu.brown.cs.termproject.locationgraph.Airport;
import edu.brown.cs.termproject.locationgraph.*;
import edu.brown.cs.termproject.graph.Graph;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for TSP algorithm.
 */
public class TSPTest {

  private final List<College> _colleges = new ArrayList<>();
  private final List<Location> _locations = new ArrayList<>();
  private final TSP<Location, Path> _tsp = new TSP<>();;

  public void setUp() {
    College c1 = new College(2, "UCLA", 34.068921, -118.4451811, "placeholder", "placeholder");
    College c2 = new College(2, "Stanford University", 37.428230, -122.168861, "placeholder", "placeholder");
    College c3 = new College(2, "UCB", 37.8718992, -122.2585399, "placeholder", "placeholder");
    College c4 = new College(4, "California Institute of Technology", 34.138000, -118.125000, "placeholder", "placeholder");
    _colleges.addAll(new ArrayList<>(Arrays.asList(c1, c2, c3, c4)));
    for (College c : _colleges) {
      _locations.add(new Location(c.getId(), c.getName(), c.getLat(), c.getLon(), "college", c));
    }
  }

  public void tearDown() {
    _colleges.clear();
    _locations.clear();
  }

//  @Test
//  public void testFindRouteReachesAll() throws InterruptedException, ApiException, IOException {
//    College c1 = new College(2, "UCLA", 34.068921, -118.4451811, "placeholder", "placeholder");
//    College c2 = new College(2, "Stanford University", 37.428230, -122.168861, "placeholder", "placeholder");
//    College c3 = new College(2, "UCB", 37.8718992, -122.2585399, "placeholder", "placeholder");
//    College c4 = new College(4, "California Institute of Technology", 34.138000, -118.125000, "placeholder", "placeholder");
//    _colleges = new ArrayList<>(Arrays.asList(c1, c2, c3, c4));
//
//    CollegeGraph graph = new CollegeGraph(_colleges);
//    List<College> tsp = _tsp.findRoute(graph);
//    System.out.println(tsp);
//
//    assertTrue(tsp.contains(c1));
//    assertTrue(tsp.contains(c2));
//    assertTrue(tsp.contains(c3));
//    assertTrue(tsp.contains(c4));
//  }

@Test
public void testFindRouteReachesAll() throws InterruptedException, ApiException, IOException {
  setUp();
  Graph<Location, Path> graph = new LocationGraph(_locations);
  List<Location> tsp = _tsp.findRoute(graph);
  System.out.println(tsp);

  assertTrue(tsp.contains(_locations.get(0)));
  assertTrue(tsp.contains(_locations.get(1)));
  assertTrue(tsp.contains(_locations.get(2)));
  assertTrue(tsp.contains(_locations.get(3)));
  tearDown();
}

  @Test
  public void testFindRouteVisitsOnce() throws InterruptedException, ApiException, IOException {
    setUp();
    Graph<Location, Path> graph = new LocationGraph(_locations);
    List<Location> tsp = _tsp.findRoute(graph);
    System.out.println(tsp);

    int count1 = 0;
    int count2 = 0;
    int count3 = 0;
    int count4 = 0;
    for (Location c : tsp) {
      if (c.equals(_locations.get(0))) {
        count1 += 1;
      } else if (c.equals(_locations.get(1))) {
        count2 += 1;
      } else if (c.equals(_locations.get(2))) {
        count3 += 1;
      } else if (c.equals(_locations.get(3))) {
        count4 += 1;
      }
    }

    assertEquals(1, count1);
    assertEquals(1, count2);
    assertEquals(1, count3);
    assertEquals(1, count4);
    tearDown();
  }

  @Test
  public void testOneLocation() throws InterruptedException, ApiException, IOException {
    College c = new College(2, "UCLA", 34.068921, -118.4451811, "placeholder", "placeholder");
    _colleges.add(c);
    _locations.add(new Location(c.getId(), c.getName(), c.getLat(), c.getLon(), "college", c));

    Graph<Location, Path> graph = new LocationGraph(_locations);
    List<Location> tsp = _tsp.findRoute(graph);
    System.out.println(tsp);
    assertEquals(1, tsp.size());
    tearDown();
  }

  @Test
  public void testGenerics() throws InterruptedException, ApiException, IOException {
    College c1 = new College(2, "UCLA", 34.068921, 118.4451811, "placeholder", "placeholder");
    College c2 = new College(2, "Stanford University", 37.428230, 122.168861, "placeholder", "placeholder");
    College c3 = new College(2, "UCB", 37.8718992, 122.2585399, "placeholder", "placeholder");
    College c4 = new College(4, "California Institute of Technology", 34.138000, 118.125000, "placeholder", "placeholder");
    Airport a = new Airport(1,"LAX", 33.9416, 118.4085, "city", "state", "url");
    List<College> _colleges = new ArrayList<>(Arrays.asList(c1, c2, c3, c4));

    List<Location> _locations = new ArrayList<>();
    for (College c : _colleges) {
      _locations.add(new Location(c.getId(), c.getName(), c.getLat(), c.getLon(), "college", c));
    }
    LocationGraph graph = new LocationGraph(_locations);
    graph.addNode(new Location(a.getId(), a.getName(), a.getLat(), a.getLon(), "airport", a));
    TSP<Location, Path> _tsp2 = new TSP<>();;
    List<Location> tsp = _tsp2.findRoute(graph);
    System.out.println(tsp);
  }
}
