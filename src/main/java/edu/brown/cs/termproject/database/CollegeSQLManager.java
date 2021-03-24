package edu.brown.cs.termproject.database;

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

  private final static DatabaseManager DB = new DatabaseManager();
  private final String COLLEGE_DATABASE = "./data/sampleColleges.sqlite3"; //TODO:


  public CollegeSQLManager() {
    DB.connect(COLLEGE_DATABASE);
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
   * Gets the college information from a college id.
   *
   * @param collegeId college id
   * @return College object
   */
  public College getCollege(int collegeId) {
    if (DB.getConnection() == null) {
      throw new IllegalStateException("Must open a database first.");
    }
    // TODO:
    College college = new College(0, "", 0, 0);
    return college;
  }


  public List<College> getDefaultColleges() throws SQLException {
    if (DB.getConnection() == null) {
      throw new IllegalStateException("Must open a database first.");
    }

    List<College> colleges = new ArrayList<College>();
    College c = new College(0, "", 0,0 );
    try (PreparedStatement getColleges = DB.getConnection().prepareStatement(
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
