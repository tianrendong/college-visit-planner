package edu.brown.cs.termproject.collegegraph;

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
  private List<Integer> nearbyColleges;

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
   * Constructs a college.
   * @param id college id
   * @param name college name
   * @param lat latitude
   * @param lon longitude
   * @param city city of college
   * @param state state of college
   * @param url url
   * @param description description
   * @param nearbyColleges nearby colleges
   */
  @SuppressWarnings("checkstyle:ParameterNumber")
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

  /**
   * returns college iD.
   * @return id
   */
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
   * @param url
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
   * @param nearbyColleges nearby Colleges
   */
  public void setNearbyColleges(List<Integer> nearbyColleges) {
    this.nearbyColleges = nearbyColleges;
  }

  /**
   * returns nearby colleges.
   * @return list of nearby colleges
   */
  public List<Integer> getNearbyColleges() {
    return nearbyColleges;
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
