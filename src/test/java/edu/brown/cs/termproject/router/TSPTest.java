package edu.brown.cs.termproject.router;

import com.google.maps.errors.ApiException;
import edu.brown.cs.termproject.airport.Airport;
import edu.brown.cs.termproject.collegegraph.*;
import org.junit.Before;
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

  List<College> _colleges;
  TSP<College, Path> _tsp;

  @Before
  public void setUp() {
    _tsp = new TSP<>();
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
  College c1 = new College(2, "UCLA", 34.068921, -118.4451811, "placeholder", "placeholder");
  College c2 = new College(2, "Stanford University", 37.428230, -122.168861, "placeholder", "placeholder");
  College c3 = new College(2, "UCB", 37.8718992, -122.2585399, "placeholder", "placeholder");
  College c4 = new College(4, "California Institute of Technology", 34.138000, -118.125000, "placeholder", "placeholder");
  _colleges = new ArrayList<>(Arrays.asList(c1, c2, c3, c4));
  CollegeGraph graph = new CollegeGraph(_colleges);
  List<College> tsp = _tsp.findRoute(graph);
  System.out.println(tsp);

  assertTrue(tsp.contains(c1));
  assertTrue(tsp.contains(c2));
  assertTrue(tsp.contains(c3));
  assertTrue(tsp.contains(c4));
}

  @Test
  public void testFindRouteVisitsOnce() throws InterruptedException, ApiException, IOException {
    College c1 = new College(2, "UCLA", 34.068921, -118.4451811, "placeholder", "placeholder");
    College c2 = new College(2, "Stanford University", 37.428230, -122.168861, "placeholder", "placeholder");
    College c3 = new College(2, "UCB", 37.8718992, -122.2585399, "placeholder", "placeholder");
    College c4 = new College(4, "California Institute of Technology", 34.138000, -118.125000, "placeholder", "placeholder");
    _colleges = new ArrayList<>(Arrays.asList(c1, c2, c3, c4));

    CollegeGraph graph = new CollegeGraph(_colleges);
    List<College> tsp = _tsp.findRoute(graph);
    System.out.println(tsp);

    int count1 = 0;
    int count2 = 0;
    int count3 = 0;
    int count4 = 0;
    for (College c : tsp) {
      if (c.equals(c1)) {
        count1 += 1;
      } else if (c.equals(c2)) {
        count2 += 1;
      } else if (c.equals(c3)) {
        count3 += 1;
      } else if (c.equals(c4)) {
        count4 += 1;
      }
    }
    assertEquals(1, count1);
    assertEquals(1, count2);
    assertEquals(1, count3);
    assertEquals(1, count4);
  }

  @Test
  public void testOne() throws InterruptedException, ApiException, IOException {
    College c1 = new College(2, "UCLA", 34.068921, -118.4451811, "placeholder", "placeholder");
    _colleges = new ArrayList<>(Arrays.asList(c1));

    CollegeGraph graph = new CollegeGraph(_colleges);
    List<College> tsp = _tsp.findRoute(graph);
    System.out.println(tsp);
    assertEquals(1, tsp.size());
  }

//  @Test
//  public void testGenerics() throws InterruptedException, ApiException, IOException {
//    College c1 = new College(2, "UCLA", 34.068921, 118.4451811, "placeholder", "placeholder");
//    College c2 = new College(2, "Stanford University", 37.428230, 122.168861, "placeholder", "placeholder");
////    College c3 = new College(2, "UCB", 37.8718992, 122.2585399, "placeholder", "placeholder");
//    College c4 = new College(4, "California Institute of Technology", 34.138000, 118.125000, "placeholder", "placeholder");
//    Airport a = new Airport("LAX", 33.9416, 118.4085, "city", "state", "url");
//    List<College> _colleges = new ArrayList<>(Arrays.asList(c1, c2, c4));
//
//    LocationGraph graph = new LocationGraph(_colleges);
//    graph.addNode(a);
//    TSP<LocationWrapper, LocationPath> _tsp2 = new TSP<>();;
//    List<LocationWrapper> tsp = _tsp2.findRoute(graph);
//    System.out.println(tsp);
//  }

  @Test
  public void testGenerics2() throws InterruptedException, ApiException, IOException {
    College c1 = new College(2, "UCLA", 34.068921, 118.4451811, "placeholder", "placeholder");
    College c2 = new College(2, "Stanford University", 37.428230, 122.168861, "placeholder", "placeholder");
    College c3 = new College(2, "UCB", 37.8718992, 122.2585399, "placeholder", "placeholder");
    College c4 = new College(4, "California Institute of Technology", 34.138000, 118.125000, "placeholder", "placeholder");
    Airport a = new Airport("LAX", 33.9416, 118.4085, "city", "state", "url");
    List<College> _colleges = new ArrayList<>(Arrays.asList(c1, c2, c3, c4));

    List<LocationWrapper> _locations = new ArrayList<>();
    for (College c : _colleges) {
      _locations.add(new LocationWrapper(c.getName(), c.getLat(), c.getLon(), c));
    }
    LocationGraph graph = new LocationGraph(_locations);
    graph.addNode(new LocationWrapper(a.getName(), a.getLat(), a.getLon(), a));
    TSP<LocationWrapper, LocationPath> _tsp2 = new TSP<>();;
    List<LocationWrapper> tsp = _tsp2.findRoute(graph);
    System.out.println(tsp);
  }
}
