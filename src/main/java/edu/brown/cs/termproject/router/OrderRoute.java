package edu.brown.cs.termproject.router;

import edu.brown.cs.termproject.collegegraph.Location;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class OrderRoute {

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
    orderedRoute.add(foundAirport); //add airport again (go back to airport)
    return orderedRoute;
  }
}
