package edu.brown.cs.termproject.main;

import java.util.ArrayList;
import java.util.List;
import edu.brown.cs.termproject.collegegraph.*;

public class User {
  private String username;
  private String password;
  private String firstname;
  private String lastname;
  private List<List<College>> route;
  private List<College> colleges;

  public User(String username, String password, String firstname, String lastname) {
    this.username = username;
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
  }

  public String getUserName() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public List<List<College>> getRoute() {
    return route;
  }

  public List<College> getColleges() {
    return colleges;
  }
}
