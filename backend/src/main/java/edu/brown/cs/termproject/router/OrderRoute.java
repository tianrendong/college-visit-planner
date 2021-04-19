package edu.brown.cs.termproject.router;

import edu.brown.cs.termproject.locationgraph.Location;

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
   * Given a route returned from TSP, orders it such that the airport is the first item in the list.
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
        if (loc.getType().equals("airport")) {
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
