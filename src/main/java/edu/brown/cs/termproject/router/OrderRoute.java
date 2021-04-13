package edu.brown.cs.termproject.router;

import edu.brown.cs.termproject.collegegraph.Location;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Class for ordering route.
 */
public final class OrderRoute {
  private OrderRoute() { }

  /**
   * Orders route into tsp order.
   * @param locations the locations to order.
   * @return ordered route.
   */
  public static List<Location> orderRoute(List<Location> locations) {
    Location foundAirport = null;
    Queue<Location> tempLocations = new LinkedList<>();
    List<Location> orderedRoute = new ArrayList<>();
    for (Location loc : locations) {
      if (foundAirport != null) { // if airport is found in the list
        tempLocations.add(loc);
      } else {
        if (loc.getType() == "airport") {
          foundAirport = loc;
          orderedRoute.add(loc);
        } else {
          tempLocations.add(loc);
        }
      }
    }

    while (tempLocations.peek() != null) {
      orderedRoute.add(tempLocations.poll());
    }

    return orderedRoute;
  }
}
