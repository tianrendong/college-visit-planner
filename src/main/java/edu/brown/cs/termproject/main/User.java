package edu.brown.cs.termproject.main;

import java.util.ArrayList;
import java.util.List;
import edu.brown.cs.termproject.collegegraph.*;

public class User {
  private String username;
  private String password;
  private String firstname;
  private String lastname;
  private List<College> colleges;

  public User(String username, String password, String firstname, String lastname) {
    this.username = username;
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
  }

  public User(String username, String password, String firstname,
              String lastname, List<College> colleges) {
    this.username = username;
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
    this.colleges = colleges;
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

  public List<College> getColleges() {
    return colleges;
  }

  public void setColleges(List<College> colleges) {
    this.colleges = colleges;
  }
}
