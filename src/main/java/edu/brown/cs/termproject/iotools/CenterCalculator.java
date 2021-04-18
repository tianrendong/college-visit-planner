package edu.brown.cs.termproject.iotools;

import edu.brown.cs.termproject.router.Locatable;
import edu.brown.cs.termproject.router.Point;

import java.util.List;

/**
 * Calculator for center of multiple coordinates in latitude/longitude.
 */
public final class CenterCalculator {

  private static final int RADIAN = 180;
  private CenterCalculator() { }

  /**
   * Gets the middle centroid point of a cluster given a list of locations.
   * @param locations list of Locatable.
   * @param <L> tyep that extends locatable.
   * @return the centroid point of the cluster.
   */
  public static <L extends Locatable> Point getCentroid(List<L> locations) {
    if (locations.size() <= 0) {
      return null;
    }

    int numLoc = locations.size();

    double x = 0.0;
    double y = 0.0;
    double z = 0.0;

    for (L location : locations) {
      double lat = location.getLat() * Math.PI / RADIAN;
      double lon = location.getLon() * Math.PI / RADIAN;

      double a = Math.cos(lat) * Math.cos(lon);
      double b = Math.cos(lat) * Math.sin(lon);
      double c = Math.sin(lat);

      x += a;
      y += b;
      z += c;
    }

    x = x / numLoc;
    y = y / numLoc;
    z = z / numLoc;

    double lon = Math.atan2(y, x);
    double hyp = Math.sqrt(x * x + y * y);
    double lat = Math.atan2(z, hyp);

    double newX = (lat * RADIAN / Math.PI);
    double newY = (lon * RADIAN / Math.PI);

    return new Point(newX, newY);
  }
}
