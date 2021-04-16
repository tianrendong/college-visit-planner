package edu.brown.cs.termproject.iotools;

import edu.brown.cs.termproject.router.Locatable;

/**
 * Utility class for calculating distance.
 */
public final class DistanceCalculator {

  private static final int R = 6371;

  private DistanceCalculator() { }

  /**
   * Returns the haversine distance in kilometers between two locatable nodes.
   * formula from:
   * https://www.igismap.com/haversine-formula-calculate-geographic-distance-earth/
   * @param start The starting point.
   * @param end The ending point.
   * @param <L> the locatable object.
   * @return Distance of way in meters.
   */
  public static <L extends Locatable> double getDistance(L start, L end) {
    double latStart = start.getLat();
    double latEnd = end.getLat();
    double lonStart = start.getLon();
    double lonEnd = end.getLon();
    double dLat = Math.toRadians(latEnd - latStart);
    double dLon = Math.toRadians(lonEnd - lonStart);
    latStart = Math.toRadians(latStart);
    latEnd = Math.toRadians(latEnd);

    double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2)
        * Math.cos(latStart) * Math.cos(latEnd);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    return c * R;
  }
}
