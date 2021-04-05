package edu.brown.cs.termproject.database;

import edu.brown.cs.termproject.airport.Airport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirportSQLManager extends DatabaseManager {
  public void connect(String filepath) {
    super.connect(filepath);
  }

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
                    rs.getString(2),
                    rs.getDouble(5),
                    rs.getDouble(6),
                    rs.getString(7)
                ));
          }
        }
      }
    }
    return airports;
  }
}
