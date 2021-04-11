package edu.brown.cs.termproject.airport;

import edu.brown.cs.termproject.graph.Vertex;
import edu.brown.cs.termproject.router.Locatable;

public class Airport implements Locatable, Vertex {
  private final int id;
  private final String name;
  private final double lat;
  private final double lon;
  private final String website;
  private final String city;
  private final String state;

  public Airport(int id, String name, double lat, double lon, String city, String state, String website) {
    this.id = id;
    this.name = name;
    this.lat = lat;
    this.lon = lon;
    this.city = city;
    this.state = state;
    this.website = website;
  }
  public int getId() {
    return id;
  }
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
