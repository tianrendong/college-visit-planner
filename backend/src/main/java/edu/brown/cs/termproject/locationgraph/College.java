package edu.brown.cs.termproject.locationgraph;

import edu.brown.cs.termproject.graph.Vertex;
import edu.brown.cs.termproject.router.Locatable;

import java.util.List;

/**
 * The class College that implements Locatable interface.
 */
public class College implements Locatable, Vertex {

  private int id;
  private String name;
  private double lat;
  private double lon;
  private String url;
  private String state;
  private String city;
  private String description;
  private List<Integer> nearby;

  /**
   * Constructs a college.
   * @param id college id
   * @param name college name
   * @param lat latitude
   * @param lon longitude
   * @param city city of college
   * @param state state of college
   */
  public College(int id, String name, double lat, double lon, String city, String state) {
    this.id = id;
    this.name = name;
    this.lat = lat;
    this.lon = lon;
    this.city = city;
    this.state = state;
  }
  /**
   * returns college iD.
   * @return id
   */
  @Override
  public int getId() {
    return id;
  }

  /**
   * returns college name.
   * @return college name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets college URL.
   * @param url url for college website
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * Sets college description.
   * @param description description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Sets nearby colleges.
   * @param nearby nearby Colleges
   */
  public void setNearby(List<Integer> nearby) {
    this.nearby = nearby;
  }

  /**
   * returns nearby colleges.
   * @return list of nearby colleges
   */
  public List<Integer> getNearby() {
    return nearby;
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
