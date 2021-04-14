package edu.brown.cs.termproject.locationgraph;

import edu.brown.cs.termproject.graph.Vertex;
import edu.brown.cs.termproject.router.Locatable;

/**
 * Class representing airports.
 */
public class Airport implements Locatable, Vertex {
  private final int id;
  private final String name;
  private final double lat;
  private final double lon;
  private final String website;
  private final String city;
  private final String state;

  /**
   * Constructs an airport object.
   * @param id id
   * @param name name
   * @param lat latitude
   * @param lon longitude
   * @param city city
   * @param state state
   * @param website website url
   */
  public Airport(int id, String name, double lat, double lon,
                 String city, String state, String website) {
    this.id = id;
    this.name = name;
    this.lat = lat;
    this.lon = lon;
    this.city = city;
    this.state = state;
    this.website = website;
  }

  /**
   * Gets the id.
   * @return id.
   */
  public int getId() {
    return id;
  }

  /**
   * Gets the name.
   * @return name.
   */
  public String getName() {
    return this.name;
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
    return name + ": (" + lat + ", " + lon + ")";
  }
}
