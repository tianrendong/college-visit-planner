package edu.brown.cs.termproject.main;

import java.util.List;

import edu.brown.cs.termproject.locationgraph.College;


/**
 * The class representing user account.
 */
public class User {
  private String username;
  private String password;
  private String firstname;
  private String lastname;
  private List<College> colleges;

  /**
   * Constructs a user account.
   * @param username username for account.
   * @param password password for the account.
   * @param firstname first name of the user.
   * @param lastname last name of the user.
   */
  public User(String username, String password, String firstname, String lastname) {
    this.username = username;
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
  }

  /**
   * Constructs a user account.
   * @param username username for account.
   * @param password password for the account.
   * @param firstname first name of the user.
   * @param lastname last name of the user.
   * @param colleges colleges added in the account.
   */
  public User(String username, String password, String firstname,
              String lastname, List<College> colleges) {
    this.username = username;
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
    this.colleges = colleges;
  }

  /**
   * Gets the username of the account.
   * @return username of the account.
   */
  public String getUserName() {
    return username;
  }

  /**
   * Gets the password for the account.
   * @return password for the account.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Gets the first name of the user.
   * @return first name of the user.
   */
  public String getFirstname() {
    return firstname;
  }

  /**
   * Gets the last name of the user.
   * @return last name of the user.
   */
  public String getLastname() {
    return lastname;
  }

  /**
   * Gets the colleges the account added.
   * @return colleges the account added.
   */
  public List<College> getColleges() {
    return colleges;
  }

  /**
   * Sets the colleges the account added to a new one.
   * @param colleges new colleges to set to.
   */
  public void setColleges(List<College> colleges) {
    this.colleges = colleges;
  }
}
