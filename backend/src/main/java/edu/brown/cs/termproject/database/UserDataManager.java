package edu.brown.cs.termproject.database;

import com.google.gson.reflect.TypeToken;
import edu.brown.cs.termproject.iotools.Encryption;
import edu.brown.cs.termproject.main.Main;
import edu.brown.cs.termproject.main.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


/**
 * Manages SQL queries to the user database.
 */
public class UserDataManager extends DatabaseManager {

  private static final Gson GSON = new Gson();
  private static final CollegeSQLManager COLLEGE_DB = Main.getCollegeDatabase();

  /**
   * Logs in the user.
   * @param username the user
   * @param password password
   * @param firstname the first name
   * @param lastname lastname
   * @return JsonObject with payload
   * @throws SQLException when no database connection is found.
   */
  public JsonObject signup(String username, String password, String firstname, String lastname)
      throws SQLException {
    if (getConnection() == null) {
      throw new IllegalStateException("Must open a database first.");
    }
    JsonObject payload = new JsonObject();

    if (checkUserExists(username)) {
      payload.addProperty("success", false);
      payload.addProperty("error", "Username already exists.");
    } else {
      try (PreparedStatement registerUser = getConnection().prepareStatement(
          "INSERT INTO users (id, password, firstname, lastname, colleges)"
              + "VALUES (?, ?, ?, ?, ?);"
      )) {
        registerUser.setString(1, username);
        registerUser.setString(2, Encryption.encrypt(password));
        registerUser.setString(3, firstname);
        registerUser.setString(4, lastname);
        registerUser.setString(5, GSON.toJson(new ArrayList<>()));
        registerUser.executeUpdate();
        payload.addProperty("success", true);
      }
    }
    return payload;
  }

  /**
   * Logs in the user.
   * @param username the user
   * @param password password
   * @return JsonObject with payload
   * @throws SQLException when no database connection is found.
   * @throws InvalidKeySpecException when key is invalid
   * @throws NoSuchAlgorithmException when algorithm not found
   * @throws UnsupportedEncodingException when encoding not supported
   */
  public JsonObject login(String username, String password)
      throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException,
      UnsupportedEncodingException {
    JsonObject payload = new JsonObject();
    User user = getUserInfo(username);
    if (user == null) {
      payload.addProperty("success", false);
      payload.addProperty("error", "User does not exist.");
      System.err.println("user does not exist");

    } else {
      if (Encryption.verify(password, user.getPassword())) {
        payload.addProperty("success", true);
        payload.addProperty("user", GSON.toJson(user));
      } else {
        payload.addProperty("success", false);
        payload.addProperty("error", "Password is incorrect.");
      }
      System.out.println(Encryption.verify(password, user.getPassword()));
    }
    return payload;
  }

  /**
   * Checks whether the user exists.
   * @param username the user account to add college to
   * @return boolean whether the user exists
   * @throws SQLException when no database connection is found.
   */
  public boolean checkUserExists(String username) throws SQLException {
    if (getConnection() == null) {
      throw new IllegalStateException("Must open a database first.");
    }

    try (PreparedStatement getUserInfo = getConnection().prepareStatement(
        "SELECT * FROM users WHERE id = ?")) {
      getUserInfo.setString(1, username);

      try (ResultSet rs = getUserInfo.executeQuery()) {
        return !rs.isClosed();
      }
    }
  }

  /**
   * Get the information of the user.
   * @param username the user account to add college to
   * @return User the user objectto return.
   * @throws SQLException when no database connection is found.
   */
  public User getUserInfo(String username) throws SQLException {
    if (getConnection() == null) {
      throw new IllegalStateException("Must open a database first.");
    }
    User user = null;
    try (PreparedStatement getUserInfo = getConnection().prepareStatement(
        "SELECT password, firstname, lastname, colleges FROM users WHERE id = ?")) {
      getUserInfo.setString(1, username);

      try (ResultSet rs = getUserInfo.executeQuery()) {
        if (!rs.isClosed()) {
          while (rs.next()) {
            String password = rs.getString(1);
            String firstname = rs.getString(2);
            String lastname = rs.getString(3);
            String colleges = rs.getString(4);

            user = new User(username, password, firstname, lastname);

            if ((colleges != null) && (COLLEGE_DB.getConnection() != null)) {
              List<Integer> collegeIDs = new Gson().fromJson(colleges,
                  new TypeToken<List<Integer>>() { }.getType());
              user.setColleges(COLLEGE_DB.getCollegeByID(collegeIDs));
            }

          }
        }
      }
      return (user == null) ? null : user;
    }
  }

  /**
   * Adds college to user account.
   * @param username the user account to add college to
   * @param collegeID the ID of the college to add
   * @return JsonObject indicating college info
   * @throws SQLException when no database connection is found.
   */
  public JsonObject addCollege(String username, int collegeID) throws SQLException {
    JsonObject payload = new JsonObject();
    List<Integer> currentColleges = getUserColleges(username);
    if (currentColleges.contains(collegeID)) {
      payload.addProperty("error", "College already added.");
    } else {
      payload.addProperty("newCollege", GSON.toJson(COLLEGE_DB.getCollegeByID(collegeID)));
      currentColleges.add(collegeID);
    }

    updateUserInfo(username, "colleges = '" + GSON.toJson(currentColleges) + "'");

    return payload;
  }

  /**
   * Gets the colleges stored in user.
   * @param username the user account.
   * @return List of College IDs associated with the user.
   * @throws SQLException when no database connection is found.
   */
  public List<Integer> getUserColleges(String username) throws SQLException {
    if (getConnection() == null) {
      throw new IllegalStateException("Must open a database first.");
    }
    List<Integer> collegeIDs = new ArrayList<>();

    try (PreparedStatement getUser = getConnection().prepareStatement(
        "SELECT colleges FROM users WHERE id = ?")) {
      getUser.setString(1, username);

      try (ResultSet rs = getUser.executeQuery()) {
        if (!rs.isClosed()) {
          while (rs.next()) {
            String colleges = rs.getString(1);
            if (colleges != null) {
              collegeIDs = new Gson().fromJson(colleges, new TypeToken<List<Integer>>() {
              }.getType());
            }
          }
        }
      }

      return collegeIDs;
    }
  }

  /**
   * Deletes a college.
   * @param username the user account to delete.
   * @param collegeID the college to delete
   * @return JsonObject indicating whether deletion was successful
   * @throws SQLException when no database connection is found.
   */
  public JsonObject deleteCollege(String username, int collegeID) throws SQLException {
    JsonObject payload = new JsonObject();
    List<Integer> currentColleges = getUserColleges(username);
    if (!currentColleges.contains(collegeID)) {
      payload.addProperty("error", "College not stored");
    } else {
      currentColleges =
          currentColleges.stream().filter(id -> id != collegeID).collect(
              Collectors.toCollection(ArrayList::new));
      payload.addProperty("deletedCollegeID", collegeID);
    }
    updateUserInfo(username, "colleges = '" + GSON.toJson(currentColleges) + "'");
    return payload;
  }
  /**
   * Updates user information.
   * @param username the user account to update
   * @param condition the conditions
   * @throws SQLException when no database connection is found.
   */
  private void updateUserInfo(String username, String condition) throws SQLException {
    try (PreparedStatement addCollege = getConnection().prepareStatement(
        "UPDATE users SET " + condition + " WHERE id= ? ;"
    )) {
      addCollege.setString(1, username);
      addCollege.executeUpdate();
    }
  }

  /**
   * Deletes all college data for a specific user.
   * @param username the user whose data needs to be deleted.
   * @return JsonObject indicating whether deletion was successful.
   * @throws SQLException when no database connection is found.
   */
  public boolean deleteUserData(String username) throws SQLException {
    if (checkUserExists(username)) {
      try (PreparedStatement delete = getConnection().prepareStatement(
          "UPDATE users SET colleges = NULL WHERE id= ? ;"
      )) {
        delete.setString(1, username);
        delete.executeUpdate();
        return true;
      }
    }
    return false;
  }

  /**
   * Deletes an user account.
   * @param username the user account to delete.
   * @return JsonObject indicating whether deletion was successful
   * @throws SQLException when no database connection is found.
   */
  public boolean deleteUserAccount(String username) throws SQLException {
    if (checkUserExists(username)) {
      try (PreparedStatement delete = getConnection().prepareStatement(
          "DELETE FROM users WHERE id = ?;"
      )) {
        delete.setString(1, username);
        delete.executeUpdate();
        return true;

      }
    }
    return false;
  }
}
