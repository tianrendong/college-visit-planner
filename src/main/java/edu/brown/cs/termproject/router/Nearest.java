package edu.brown.cs.termproject.router;

import edu.brown.cs.termproject.iotools.DistanceCalculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for getting nearest point.
 * @param <A> type that extends Locatable.
 * @param <B> type that extends Locatable.
 */
public class Nearest<A extends Locatable, B extends Locatable> {

  /**
   * Gets the closest location based on the each cluster in map.
   * @param clusters map of centroid to Locatable.
   * @param locations list of locations.
   * @return a map, mapping an airport to its cluster.
   */
  public Map<B, List<A>> findAllNearestLocations(
      Map<A, List<A>> clusters, List<B> locations) {
    Map<B, List<A>> nearestLocations = new HashMap<>();
    for (Map.Entry<A, List<A>> entry : clusters.entrySet()) {
      A curCentroid = entry.getKey();
      B bestLocation = findNearestLocation(curCentroid, locations);
      //if there is already the key with the same airport, combine
      if (nearestLocations.containsKey(bestLocation)) {
        nearestLocations.get(bestLocation).addAll(entry.getValue());
      } else {
        nearestLocations.put(bestLocation, entry.getValue());
      }
    }
    return nearestLocations;
  }

  /**
   * Finds the closet location to point.
   * @param point nearest from point
   * @param locations list of locations
   * @return best location.
   */
  public B findNearestLocation(A point, List<B> locations) {
    double bestDistance = Double.POSITIVE_INFINITY;
    B bestLocation = null;
    for (B loc : locations) {
      double newDistance = DistanceCalculator.getDistance(loc, point);
      if (newDistance < bestDistance) {
        bestDistance = newDistance;
        bestLocation = loc;
      }
    }
    return bestLocation;
  }

}
