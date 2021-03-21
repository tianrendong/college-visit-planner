package edu.brown.cs.termproject.main;

import edu.brown.cs.termproject.router.Locatable;

public class College implements Locatable {

  private int id;
  private double lat;
  private double lon;

  /**
   * Constructs a college.
   * @param id college id
   * @param lat latitude
   * @param lon longitude
   */
  public College(int id, double lat, double lon) {
    this.id = id;
    this.lat = lat;
    this.lon = lon;
  }

  public int getId() {
    return id;
  }

  @Override
  public double getLat() {
    return lat;
  }

  @Override
  public double getLon() {
    return lon;
  }

}
