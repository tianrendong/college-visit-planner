package edu.brown.cs.termproject.database;


public class CollegeDatabase extends Database {

  private static Database db;
  private String loadedFile = "";


  /**
   * Database constructor.
   *
   * @param filepath file name of SQLite3 database to open.
   */
  public CollegeDatabase(String filepath) {
    super(filepath);
  }

  public CollegeDatabase() {
    super();
  }


}
