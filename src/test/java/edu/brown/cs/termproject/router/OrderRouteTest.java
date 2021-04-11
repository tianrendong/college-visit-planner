package edu.brown.cs.termproject.router;

import edu.brown.cs.termproject.airport.Airport;
import edu.brown.cs.termproject.collegegraph.College;
import edu.brown.cs.termproject.collegegraph.Location;
import org.junit.Test;

import java.util.*;


public class OrderRouteTest {

  @Test
  public void testOrderRoute() {
    College c1 = new College(2, "UCLA", 34.068921, 118.4451811, "placeholder", "placeholder");
    College c2 = new College(2, "Stanford University", 37.428230, 122.168861, "placeholder", "placeholder");
    College c3 = new College(2, "UCB", 37.8718992, 122.2585399, "placeholder", "placeholder");
    College c4 = new College(4, "California Institute of Technology", 34.138000, 118.125000, "placeholder", "placeholder");
    Airport a = new Airport(1, "LAX", 33.9416, 118.4085, "city", "state", "url");
    List<College> _colleges = new ArrayList<>(Arrays.asList(c1, c2, c3, c4));

    List<Location> _locations = new ArrayList<>();
    for (College c : _colleges) {
      _locations.add(new Location(c.getId(), c.getName(), c.getLat(), c.getLon(), "college"));
    }
    _locations.add(new Location(a.getId(), a.getName(), a.getLat(), a.getLon(), "airport"));
    System.out.println(OrderRoute.orderRoute(_locations));
  }


}
