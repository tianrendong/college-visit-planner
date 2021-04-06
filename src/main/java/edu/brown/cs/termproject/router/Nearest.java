package edu.brown.cs.termproject.router;

import edu.brown.cs.termproject.iotools.DistanceCalculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nearest<A extends Locatable, B extends Locatable> {

//  /**
//   * Gets the closest location based on the each cluster in map.
//   * @param clusters map of centroid to Locatable.
//   * @return a map, mapping an airport to its cluster.
//   */
//  public Map<A, List<B>> findAllNearestLocations(
//      Map<B, List<B>> clusters, List<T> locations) {
//    Map<T, List<T>> nearestLocations = new HashMap<>();
//    for (Map.Entry<T, List<T>> entry : clusters.entrySet()) {
//      T curCentroid = entry.getKey();
//      T bestLocation = findNearestLocation(curCentroid, locations);
//      //if there is already the key with the same airport, combine
//      if (nearestLocations.containsKey(bestLocation)) {
//        nearestLocations.get(bestLocation).addAll(entry.getValue());
//      } else {
//        nearestLocations.put(bestLocation, entry.getValue());
//      }
//    }
//    return nearestLocations;
//  }

  /**
   * Finds the closet location to point.
   * @return
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
