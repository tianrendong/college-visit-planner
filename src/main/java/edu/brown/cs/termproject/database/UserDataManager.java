package edu.brown.cs.termproject.database;

import edu.brown.cs.termproject.collegegraph.College;
import edu.brown.cs.termproject.main.Encryption;
import edu.brown.cs.termproject.main.User;
import org.checkerframework.checker.units.qual.A;

import javax.sql.rowset.serial.SerialBlob;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages SQL queries to the user database.
 */
public class UserDataManager extends DatabaseManager {

  public void register(String username, String password, String firstname, String lastname)
      throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException {
    if (getConnection() == null) {
      throw new IllegalStateException("Must open a database first.");
    }

    try (PreparedStatement registerUser = getConnection().prepareStatement(
        "INSERT INTO users (id, password, firstname, lastname)" +
            "VALUES (?, ?, ?, ?);"
    )) {
      registerUser.setString(1, username);
      registerUser.setBlob(2, new SerialBlob(Encryption.encrypt(password)));
      registerUser.setString(3, firstname);
      registerUser.setString(4, lastname);

      registerUser.executeUpdate();

    }
  }

  public void login(String username) {

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
            byte[] password = rs.getBytes(1);
            String firstname = rs.getString(2);
            String lastname = rs.getString(3);
            String colleges = rs.getString(4);
            String route = rs.getString(5); //TODO: pasrse JSON
            user = new User(username, password, firstname, lastname);
          }
        }
      }
      return (user == null) ? null : user;
    }
  }

  public void addCollege(int collegeId) {

  }

  public void deleteCollege(int collegeId) {

  }

}
