package edu.brown.cs.termproject.database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.brown.cs.termproject.autocorrect.Autocorrector;
import edu.brown.cs.termproject.collegegraph.College;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages SQL queries to the college database.
 */
public class CollegeSQLManager extends DatabaseManager {

  private static final int URL_COL = 7;
  private static final int DESCRIPTION_COL = 8;
  private static final int NEARBY_COL = 9;
  private static final double MAX_DISTANCE = 100;
  private static final double DISTANCE_LAT_RATIO = 69;
  private static final double DISTANCE_LON_RATIO = 54.6;

  private Autocorrector autocorrector;

  /**
   * Gets the auto-corrector.
   * @return the auto-corrector
   */
  public Autocorrector getAutocorrector() {
    return autocorrector;
  }

  /**
   * Connects to database.
   * @param filepath filepath to database.
   */
  public void connect(String filepath) {
    super.connect(filepath);
    try {
      loadTrie();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Loads the Autocorrect Trie using college names from the database.
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
   * @throws SQLException if errors occur during SQL query
   */
  public College getCollegeByID(int collegeId) throws SQLException {
    List<College> college = getColleges("id = " + collegeId);
    if (college.size() > 0) {
      return college.get(0);
    }
    return null;
  }

  /**
   * Gets a list of colleges from a list of college id.
   * @param collegeIds list of college id
   * @return list of College objects
   * @throws SQLException if errors occur during SQL query
   */
  public List<College> getCollegeByID(List<Integer> collegeIds) throws SQLException {
    List<College> colleges = new ArrayList<>();
    for (int id : collegeIds) {
      College c = getCollegeByID(id);
      if (c != null) {
        colleges.add(c);
      }
    }
    return colleges;
  }

  /**
   * Gets the college information from a college name.
   *
   * @param collegeName college name
   * @return College object
   * @throws SQLException if errors occur during SQL query
   */
  public List<College> getCollegeByName(String collegeName) throws SQLException {
    return getColleges("name = '" + collegeName + "'");
  }

  /**
   * Gets the default colleges to be displayed on the map.
   * @return list of colleges
   * @throws SQLException if errors occur during SQL query
   */
  public List<College> getDefaultColleges() throws SQLException {
    return getColleges("top_100 = 'true'");
  }

  /**
   * Gets colleges nearby for a specific college.
   * @param college target college to search around.
   * @return List of colleges nearby.
   * @throws SQLException when query errors.
   */
  public List<College> getNearbyColleges(College college) throws SQLException {
    double latRatio = MAX_DISTANCE / DISTANCE_LAT_RATIO;
    double lonRatio = MAX_DISTANCE / DISTANCE_LON_RATIO;

    double lat = college.getLat();
    double lon = college.getLon();

    double nwLat, nwLon, seLat, seLon;
    nwLat = lat + latRatio;
    nwLon = lon - lonRatio;
    seLat = lat - latRatio;
    seLon = lon + lonRatio;

    return getColleges("(latitude BETWEEN " + seLat + " AND " + nwLat
        + ") AND (longitude BETWEEN " + nwLon + " AND " + seLon + ")");
  }

  private List<College> getColleges(String condition) throws SQLException {
    if (getConnection() == null) {
      throw new IllegalStateException("Must open a database first.");
    }
    List<College> colleges = new ArrayList<College>();
    try (PreparedStatement getColleges = getConnection().prepareStatement(
        "SELECT id, name, latitude, longitude, city, state, url, description,"
            + "nearbyColleges FROM colleges WHERE " + condition + ";")) {
      try (ResultSet rs = getColleges.executeQuery()) {
        if (!rs.isClosed()) {
          while (rs.next()) {
            College newCollege = new College(
                rs.getInt(1),
                rs.getString(2),
                rs.getDouble(3),
                rs.getDouble(4),
                rs.getString(5),
                rs.getString(6)
            );

            String url =  rs.getString(URL_COL);
            if (url != null) {
              newCollege.setUrl(url);
            }

            String description = rs.getString(DESCRIPTION_COL);
            if (description != null) {
              newCollege.setDescription(description);
            }

            String nearbyCollegeIDs = rs.getString(NEARBY_COL);
            if (nearbyCollegeIDs != null) {
              List<Integer> nearbyColleges = (nearbyCollegeIDs == null) ? null
                  : new Gson().fromJson(nearbyCollegeIDs, new TypeToken<List<Integer>>() {
                  }.getType());
              newCollege.setNearbyColleges(nearbyColleges);
            }

            colleges.add(newCollege);
          }
        }
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
      return colleges;
    }
  }
}
