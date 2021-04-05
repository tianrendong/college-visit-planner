package edu.brown.cs.termproject.database;

import com.google.maps.errors.ApiException;
import edu.brown.cs.termproject.collegegraph.College;
import edu.brown.cs.termproject.collegegraph.CollegeGraph;
import edu.brown.cs.termproject.router.Airport;
import org.checkerframework.checker.units.qual.A;
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
