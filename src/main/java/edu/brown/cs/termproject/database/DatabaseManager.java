package edu.brown.cs.termproject.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Class for a database.
 */
public class DatabaseManager {

  private Connection conn = null;

  /**
   * Constructs a DatabaseManager.
   */
  public DatabaseManager() { }

  /**
   * Connects to a database.
   * @param filepath filepath to a SQLite database.
   */
  public void connect(String filepath) {
    // reset any previous connection
    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException e) {
        System.err.println("ERROR: unable to close previous connection.");
      }
    }
    if (fileExists(filepath)) {
      try {
        // loads the driver manager class
        Class.forName("org.sqlite.JDBC");
        String urlToDB = "jdbc:sqlite:" + filepath;
        conn = DriverManager.getConnection(urlToDB);
        // tell the database to enforce foreign keys during operations
        Statement stat = conn.createStatement();
        stat.executeUpdate("PRAGMA foreign_keys=ON;");
      } catch (SQLException e) {
        System.err.println("ERROR: " + e.getMessage());
      } catch (ClassNotFoundException e) {
        System.err.println("ERROR: couldn't establish connection to database");
      }
    }
  }

  /**
   * Checks if a file exists.
   * Throws error if file does not exist.
   * @param filepath path to a file
   * @return true if filepath exists and is a file
   */
  public static boolean fileExists(String filepath) {
    File file = new File(filepath);
    if (!file.exists() || !file.isFile()) {
      System.err.println("ERROR: file does not exist");
      return false;
    }
    return true;
  }

  /**
   * Gets the connection to a database.
   * @return connection to database
   */
  public Connection getConnection() {
    return conn;
  }

  /**
   * Check if columns exist in a table in a database.
   * @param columns a list of column names
   * @param table table name
   * @return true if table exists in database and if all the columns exist in the table
   * @throws SQLException if any error occurs in SQL query
   */
  public boolean checkColumnsExist(List<String> columns, String table) throws SQLException {
    boolean columnsExist = true;
    DatabaseMetaData dbm = conn.getMetaData();
    for (String column : columns) {
      // attempt to get "column" from "table" from database
      ResultSet rs = dbm.getColumns(null, null, table, column);
      if (!rs.next()) { // Table or Column do not exist
        columnsExist = false;
      }
    }
    return columnsExist;
  }

}
