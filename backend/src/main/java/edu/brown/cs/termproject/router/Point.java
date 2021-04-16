package edu.brown.cs.termproject.router;

/**
 * Class representing point.
 */
public class Point implements Locatable {
  private final double lat;
  private final double lon;

  /**
   * Constructs a Point.
   * @param lat latitude
   * @param lon longitude
   */
  public Point(double lat, double lon) {
    this.lat = lat;
    this.lon = lon;
  }
  @Override
  public double getLat() {
    return this.lat;
  }
  @Override
  public double getLon() {
    return this.lon;
  }
  @Override
  public String toString() {
    return "Point: (" + this.lat + ", " + this.lon + ")";
  }
}
