package edu.brown.cs.termproject.collegegraph;

import edu.brown.cs.termproject.graph.Vertex;
import edu.brown.cs.termproject.router.Locatable;

import java.util.List;

/**
 * The class College that implements Locatable interface.
 */
public class Location implements Locatable, Vertex {

  private int id;
  private String name;
  private double lat;
  private double lon;
  private String type;

  public Location(int id, String name, double lat, double lon, String type) {
    this.id = id;
    this.name = name;
    this.lat = lat;
    this.lon = lon;
    this.type = type;
  }

  /**
   * returns college name.
   * @return college name
   */
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

  @Override
  public int getId() {
    return id;
  }

  public String getType() {
    return type;
  }
}
