package edu.brown.cs.termproject.database;

import com.google.gson.reflect.TypeToken;
import edu.brown.cs.termproject.collegegraph.College;
import edu.brown.cs.termproject.main.Encryption;
import edu.brown.cs.termproject.main.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


/**
 * Manages SQL queries to the user database.
 */
public class UserDataManager extends DatabaseManager {

  private static final Gson GSON = new Gson();

  public JsonObject signup(String username, String password, String firstname, String lastname) throws SQLException {
    if (getConnection() == null) {
      throw new IllegalStateException("Must open a database first.");
    }
    JsonObject payload = new JsonObject();

    if (checkUserExists(username)) {
      payload.addProperty("success", false);
      payload.addProperty("error", "Username already exists.");
    } else {
      try (PreparedStatement registerUser = getConnection().prepareStatement(
          "INSERT INTO users (id, password, firstname, lastname)" +
              "VALUES (?, ?, ?, ?);"
      )) {
        registerUser.setString(1, username);
        registerUser.setString(2, Encryption.encrypt(password));
        registerUser.setString(3, firstname);
        registerUser.setString(4, lastname);
        registerUser.executeUpdate();
        payload.addProperty("success", true);
      }
    }
    return payload;
  }

  public JsonObject login(String username, String password)
      throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException, UnsupportedEncodingException {
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

  public User getUserInfo(String username) throws SQLException {
    if (getConnection() == null) {
      throw new IllegalStateException("Must open a database first.");
    }

    User user = null;
    try (PreparedStatement getUserInfo = getConnection().prepareStatement(
        "SELECT password, firstname, lastname, colleges, route FROM users WHERE id = ?")) {
      getUserInfo.setString(1, username);

      try (ResultSet rs = getUserInfo.executeQuery()) {
        if (!rs.isClosed()) {
          while (rs.next()) {
            String password = rs.getString(1);
            String firstname = rs.getString(2);
            String lastname = rs.getString(3);
            String colleges = rs.getString(4);
            String route = rs.getString(5); //TODO: pasrse JSON
            List<College> collegeAsObject =
                new Gson().fromJson(colleges, new TypeToken<List<List<College>>>(){}.getType());
            List<List<College>> routeAsObject =
                new Gson().fromJson(route, new TypeToken<List<List<College>>>(){}.getType());
            user = new User(username, password, firstname, lastname, collegeAsObject, routeAsObject);
          }
        }
      }
      return (user == null) ? null : user;
    }
  }


}
