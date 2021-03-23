package edu.brown.cs.termproject.database;

import edu.brown.cs.termproject.main.Account;
import org.checkerframework.checker.units.qual.A;

/**
 * Manages SQL queries to the user database.
 */
public class UserDataManager {

  private final static DatabaseManager DB = new DatabaseManager();
  private final String USER_DATABASE = ""; //TODO:

  public UserDataManager() {
    DB.connect(USER_DATABASE);
  }

  public void register(String username) {

  }

  public void login(String username) {

  }

  public Account getAccountInfo(String username) {
    // TODO: perform SQL query
//    Account acc = new Account(username,);
    return  null;
  }

  public void addCollege(int collegeId) {

  }

  public void deleteCollege(int collegeId) {

  }

}
