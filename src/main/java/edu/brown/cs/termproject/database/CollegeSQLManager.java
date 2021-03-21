package edu.brown.cs.termproject.database;

import edu.brown.cs.termproject.main.College;

import java.util.ArrayList;
import java.util.List;


public class CollegeSQLManager extends DatabaseManager {

  private final static DatabaseManager DB = new DatabaseManager();
  private final String COLLEGE_DATABASE = ""; //TODO:


  public CollegeSQLManager() {
    DB.connect(COLLEGE_DATABASE);
  }

  /**
   * Given a user input, finds 5 related colleges.
   * @param input user input
   * @return 5 related colleges
   */
  public List<College> searchRelevantColleges(String input) {
    List<College> colleges = new ArrayList<>();
    return colleges;
  }

  /**
   * Gets the college information from a college id.
   * @param collegeId college id
   * @return College object
   */
  public College getCollege(int collegeId) {
    if (DB.getConnection() == null) {
      throw new IllegalStateException("Must open a database first.");
    }
    // TODO:
    College college = new College(0, 0, 0);
    return college;
  }


}
