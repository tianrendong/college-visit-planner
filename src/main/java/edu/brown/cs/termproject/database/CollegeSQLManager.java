package edu.brown.cs.termproject.database;

import edu.brown.cs.termproject.autocorrect.Autocorrector;
import edu.brown.cs.termproject.collegegraph.College;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages SQL queries to the college database.
 */
public class CollegeSQLManager extends DatabaseManager {

  private Autocorrector autocorrector;
  public Autocorrector getAutocorrector() {
    return autocorrector;
  }

  public void connect(String filepath) {
    super.connect(filepath);
    try {
      loadTrie();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Given a user input, finds 5 related colleges.
   *
   * @param input user input
   * @return 5 related colleges
   */
  public List<College> searchRelevantColleges(String input) {
    List<College> colleges = new ArrayList<>();
    return colleges;
  }

  /**
   * Loads the Autocorrect Trie using traversable way names from the loaded map.
   * @throws SQLException if errors occur during SQL query
   */
  private void loadTrie() throws SQLException {
    if (getConnection() == null) {
      throw new IllegalStateException("Must open a maps database first.");
    }
    List<String> collegeNames = new ArrayList<>();
    try (PreparedStatement getWays = getConnection().prepareStatement(
        "SELECT DISTINCT name FROM colleges;"
    )) {
      try (ResultSet rs = getWays.executeQuery()) {
        while (rs.next()) {
          collegeNames.add(rs.getString(1));
        }
      }
    }
    autocorrector = new Autocorrector(collegeNames, true, false, 3);
  }

  /**
   * Gets the college information from a college id.
   *
   * @param collegeId college id
   * @return College object
   */
  public College getCollegeByID(int collegeId) throws SQLException {
    if (getConnection() == null) {
      throw new IllegalStateException("Must open a database first.");
    }
    College college = null;
    try (PreparedStatement getColleges = getConnection().prepareStatement(
        "SELECT id, name, latitude, longitude, city, state, url, description FROM colleges WHERE id = ?;")) {
      getColleges.setInt(1, collegeId);

      try (ResultSet rs = getColleges.executeQuery()) {
        if (!rs.isClosed()) {
          while (rs.next()) {
            college =
                new College(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getDouble(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8)
                );
          }
        }
      }
    }
    return college;
  }

  /**
   * Gets the college information from a college name.
   *
   * @param collegeName college name
   * @return College object
   */
  public List<College> getCollegeByName(String collegeName) throws SQLException {
    if (getConnection() == null) {
      throw new IllegalStateException("Must open a database first.");
    }
    List<College> colleges = new ArrayList<>();
    try (PreparedStatement getColleges = getConnection().prepareStatement(
        "SELECT id, name, latitude, longitude, city, state, url, description FROM colleges WHERE name = ?;")) {
      getColleges.setString(1, collegeName);

      try (ResultSet rs = getColleges.executeQuery()) {
        if (!rs.isClosed()) {
          while (rs.next()) {
            colleges.add(
                new College(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getDouble(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8)

                ));
          }
        }
      }
    }
    return colleges;
  }


  public List<College> getDefaultColleges() throws SQLException {
    if (getConnection() == null) {
      throw new IllegalStateException("Must open a database first.");
    }

    List<College> colleges = new ArrayList<College>();
    College c = new College(0, "", 0,0 );
    try (PreparedStatement getColleges = getConnection().prepareStatement(
        "SELECT id, name, latitude, longitude FROM colleges WHERE 1 <= rank <= 20;")) {

      try (ResultSet rs = getColleges.executeQuery()) {
        if (!rs.isClosed()) {
          while (rs.next()) {
            colleges.add(
                new College(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getDouble(4)
                )
            );
          }
        }
      }
      return colleges;
    }

  }
}
