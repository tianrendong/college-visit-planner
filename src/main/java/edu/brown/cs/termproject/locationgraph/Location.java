package edu.brown.cs.termproject.locationgraph;

import edu.brown.cs.termproject.graph.Vertex;
import edu.brown.cs.termproject.router.Locatable;

import java.util.Objects;


/**
 * The class College that implements Locatable interface.
 */
public class Location implements Locatable, Vertex {

  private int id;
  private String name;
  private double lat;
  private double lon;
  private String type;
  private Object content;

  /**
   * Constructs a location.
   * @param id id
   * @param name name
   * @param lat latitude.
   * @param lon longitude
   * @param type type of location
   * @param content Object being wrapped.
   */
  public Location(int id, String name, double lat, double lon, String type, Object content) {
    this.id = id;
    this.name = name;
    this.lat = lat;
    this.lon = lon;
    this.type = type;
    this.content = content;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Location location = (Location) o;
    return id == location.id
        && Double.compare(location.lat, lat) == 0
        && Double.compare(location.lon, lon) == 0
        && name.equals(location.name)
        && type.equals(location.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, lat, lon, type);
  }

  /**
   * Gets the type of location.
   * @return type of location.
   */
  public String getType() {
    return type;
  }

  /**
   * Gets the content of location.
   * @return location content
   */
  public Object getContent() {
    return content;
  }
}
