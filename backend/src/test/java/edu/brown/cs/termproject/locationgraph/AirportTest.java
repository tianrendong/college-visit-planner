package edu.brown.cs.termproject.locationgraph;

import edu.brown.cs.termproject.locationgraph.Airport;
import edu.brown.cs.termproject.locationgraph.College;
import edu.brown.cs.termproject.database.AirportSQLManager;
import edu.brown.cs.termproject.router.Clustering;
import edu.brown.cs.termproject.router.Locatable;
import edu.brown.cs.termproject.router.Nearest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class AirportTest {

  AirportSQLManager airportDB;
  Nearest nearest = new Nearest();

  @Before
  public void setUp() throws SQLException {
    airportDB = new AirportSQLManager();
    airportDB.connect("./data/airports.sqlite3");
  }

  @After
  public void tearDown() {
    airportDB = null;
  }

  @Test
  public void simpleAirportTest() throws SQLException {
    College c1 = new College(1, "Massachusetts Institute of Technology", 42.360001, -71.092003, "placeholder", "placeholder");
    College c2 = new College(2, "Stanford University", 37.428230, -122.168861, "placeholder", "placeholder");
    College c3 = new College(3, "Harvard University", 42.374443, -71.116943, "placeholder", "placeholder");
    College c4 = new College(4, "California Institute of Technology", 34.138000, -118.125000, "placeholder", "placeholder");
    List<Locatable> colleges = new ArrayList<>(Arrays.asList(c1, c2, c3, c4));
    Clustering clustering = new Clustering (100.0);
    List<Airport> allAirports = airportDB.getAllAirports();
    Map<Locatable, List<Locatable>> clusters =  clustering.makeClusters(colleges);
    Map<Airport, List<Locatable>> nearestMap = nearest.findAllNearestLocations(clusters, allAirports);
    int numClusters = 0;
    for (Map.Entry<Airport, List<Locatable>> entry : nearestMap.entrySet()) {
      numClusters += 1;
    }
    assertEquals(3, numClusters);
  }


  //TODO: test edge cases where multiple clusters have same airport
}
