package edu.brown.cs.termproject.airport;

import edu.brown.cs.termproject.router.Locatable;

public class Airport implements Locatable {
  private final String name;
  private final double lat;
  private final double lon;
  private final String website;
  public Airport(String name, double lat, double lon, String website) {
    this.name = name;
    this.lat = lat;
    this.lon = lon;
    this.website = website;
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
  public String getWebsite() {
    return this.website;
  }
  @Override
  public String toString() {
    return name + ": (" + lat + ", " + lon + ")";
  }
}
