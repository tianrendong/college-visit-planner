package edu.brown.cs.termproject.collegegraph;

import edu.brown.cs.termproject.graph.Vertex;
import edu.brown.cs.termproject.router.Locatable;

import java.util.List;

public class College implements Locatable, Vertex {

  private int id;
  private String name;
  private double lat;
  private double lon;
  private String url;
  private String state;
  private String city;
  private String description;
  private List<Integer> nearbyColleges;

  /**
   * Constructs a college.
   * @param id college id
   * @param name college name
   * @param lat latitude
   * @param lon longitude
   */
  public College(int id, String name, double lat, double lon, String city, String state) {
    this.id = id;
    this.name = name;
    this.lat = lat;
    this.lon = lon;
    this.city = city;
    this.state = state;
  }

  public College(int id, String name, double lat, double lon, String city, String state, String url,
                 String description, List<Integer> nearbyColleges) {
    this.id = id;
    this.name = name;
    this.lat = lat;
    this.lon = lon;
    this.city = city;
    this.state = state;
    this.url = url;
    this.description = description;
    this.nearbyColleges = nearbyColleges;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setUrl(String url) { this.url = url;}

  public void setDescription(String description) { this.description = description;}

  public void setNearbyColleges(List<Integer> nearbyColleges) { this.nearbyColleges = nearbyColleges;}

  @Override
  public double getLat() {
    return lat;
  }

  @Override
  public double getLon() {
    return lon;
  }

  @Override
  public String toString() {
    return name + ": (" + lat + ", " + lon + ")";
  }
}
