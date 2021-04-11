package edu.brown.cs.termproject.collegegraph;

import edu.brown.cs.termproject.graph.Vertex;
import edu.brown.cs.termproject.router.Locatable;

import java.util.List;

/**
 * The class College that implements Locatable interface.
 */
public class LocationWrapper implements Locatable, Vertex {

  private String name;
  private double lat;
  private double lon;
  private Object content;

  public LocationWrapper(String name, double lat, double lon, Object content) {
    this.name = name;
    this.lat = lat;
    this.lon = lon;
    this.content = content;
  }

  /**
   * returns college name.
   * @return college name
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * returns latitude of college.
   * @return latitude
   */
  @Override
  public double getLat() {
    return lat;
  }

  /**
   * returns longitude of college.
   * @return longitude
   */
  @Override
  public double getLon() {
    return lon;
  }

  /**
   * custom toString method.
   * @return college in string.
   */
  @Override
  public String toString() {
    return name + ": (" + lat + ", " + lon + ")";
  }
}
