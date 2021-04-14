package edu.brown.cs.termproject.locationgraph;

import com.google.maps.errors.ApiException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationGraphTest {

//  List<College> _colleges;

//  @Before
//  public void setUp() {
//    College c1 = new College(1, "Massachusetts Institute of Technology", 42.360001, -71.092003, "placeholder", "placeholder");
//    College c2 = new College(2, "Stanford University", 37.428230, -122.168861, "placeholder", "placeholder");
//    College c3 = new College(3, "Harvard University", 42.374443, -71.116943, "placeholder", "placeholder");
//    College c4 = new College(4, "California Institute of Technology", 34.138000, -118.125000,"placeholder", "placeholder");
//    _colleges = new ArrayList<>(Arrays.asList(c1, c2, c3, c4));
//  }

//  @Test
//  public void testGraph() throws InterruptedException, ApiException, IOException {
//    College c1 = new College(2, "UCLA", 34.068921, 118.4451811, "placeholder", "placeholder");
//    College c2 = new College(2, "Stanford University", 37.428230, 122.168861, "placeholder", "placeholder");
//    College c3 = new College(2, "UCB", 37.8718992, 122.2585399, "placeholder", "placeholder");
//    College c4 = new College(4, "California Institute of Technology", 34.138000, 118.125000, "placeholder", "placeholder");
//    Airport a = new Airport("LAX", 33.9416, 118.4085, "city", "state", "url");
//    List<College> _colleges = new ArrayList<>(Arrays.asList(c1, c2, c4));
//    LocationGraph graph = new LocationGraph(_colleges);
//    graph.addNode(a);
////    List<LocationWrapper> _locations = new ArrayList<>();
////    for (College c : _colleges) {
////      _locations.add(new LocationWrapper(c.getName(), c.getLat(), c.getLon(), c));
////    }
////    _locations.add(new LocationWrapper(a.getName(), a.getLat(), a.getLon(), a));
////
////    LocationGraph graph = new LocationGraph(_locations);
//    System.out.println(graph.toString());
//  }
//}

  @Test
  public void testGraph2() throws InterruptedException, ApiException, IOException {
    College c1 = new College(2, "UCLA", 34.068921, 118.4451811, "placeholder", "placeholder");
    College c2 = new College(2, "Stanford University", 37.428230, 122.168861, "placeholder", "placeholder");
    College c3 = new College(2, "UCB", 37.8718992, 122.2585399, "placeholder", "placeholder");
    College c4 = new College(4, "California Institute of Technology", 34.138000, 118.125000, "placeholder", "placeholder");
    Airport a = new Airport(1, "LAX", 33.9416, 118.4085, "city", "state", "url");
    List<College> _colleges = new ArrayList<>(Arrays.asList(c1, c2, c3, c4));

    List<Location> _locations = new ArrayList<>();
    for (College c : _colleges) {
      _locations.add(new Location(c.getId(), c.getName(), c.getLat(), c.getLon(), "college", c));
    }
    _locations.add(new Location(a.getId(), a.getName(), a.getLat(), a.getLon(), "airport", a));

    LocationGraph graph = new LocationGraph(_locations);
    System.out.println(graph.toString());
  }
}
