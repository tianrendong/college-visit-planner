package edu.brown.cs.termproject.database;

import edu.brown.cs.termproject.locationgraph.Airport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for managing airport database.
 */
public class AirportSQLManager extends DatabaseManager {
  public static final int NAME_COL = 2;
  public static final int CITY_COL = 3;
  public static final int STATE_COL = 4;
  public static final int LAT_COL = 5;
  public static final int LON_COL = 6;
  public static final int WEBSITE_COL = 7;
  public static final int ID_COL = 8;

  /**
   * Function that connects to the database.
   * @param filepath filepath to the SQL database
   */
  public void connect(String filepath) {
    super.connect(filepath);
  }

  /**
   * Function that get all the airports in the database and returns them as Airport objects.
   * @return list of airport
   * @throws SQLException when query erroed.
   */
  public List<Airport> getAllAirports() throws SQLException {
    List<Airport> airports = new ArrayList<>();
    if (getConnection() == null) {
      throw new IllegalStateException("Must open a database first.");
    }
    try (PreparedStatement getAirports = getConnection().prepareStatement(
        "SELECT * FROM airports;")) {

      try (ResultSet rs = getAirports.executeQuery()) {
        if (!rs.isClosed()) {
          while (rs.next()) {
            airports.add(
                new Airport(
                    rs.getInt(ID_COL),
                    rs.getString(NAME_COL),
                    rs.getDouble(LAT_COL),
                    rs.getDouble(LON_COL),
                    rs.getString(CITY_COL),
                    rs.getString(STATE_COL),
                    rs.getString(WEBSITE_COL)
                ));
          }
        }
      }
    }
    return airports;
  }

  /**
   * Gets the airport with id from the connected database.
   * @param id ID of the airport to get.
   * @return Airport with id in the database.
   * @throws SQLException when query errored.
   */
  public Airport getAirportById(int id) throws SQLException {
    if (getConnection() == null) {
      throw new IllegalStateException("Must open a database first.");
    }
    try (PreparedStatement getAirports = getConnection().prepareStatement(
        "SELECT * FROM airports WHERE id = ?;")) {
      getAirports.setInt(1, id);
      try (ResultSet rs = getAirports.executeQuery()) {
        List<Airport> airports = new ArrayList<>();
        if (!rs.isClosed()) {
          while (rs.next()) {
            airports.add(
                new Airport(
                    rs.getInt(ID_COL),
                    rs.getString(NAME_COL),
                    rs.getDouble(LAT_COL),
                    rs.getDouble(LON_COL),
                    rs.getString(CITY_COL),
                    rs.getString(STATE_COL),
                    rs.getString(WEBSITE_COL)
                ));
          }
        }
        if (airports.size() > 0) {
          return airports.get(0);
        }
      }
    }
    return null;
  }
}
