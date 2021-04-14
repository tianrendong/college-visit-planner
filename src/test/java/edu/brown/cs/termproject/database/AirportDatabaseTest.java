package edu.brown.cs.termproject.database;

import edu.brown.cs.termproject.locationgraph.Airport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AirportDatabaseTest {

  AirportSQLManager airportManager;
  @Before
  public void setUp() {
    airportManager = new AirportSQLManager();
    airportManager.connect("./data/airports.sqlite3");
  }

  @After
  public void tearDown() {
    airportManager = null;
  }

  @Test
  public void testAllAirports() throws SQLException {
    List<Airport> airports = airportManager.getAllAirports();
    assertEquals(40, airports.size());
  }
}
