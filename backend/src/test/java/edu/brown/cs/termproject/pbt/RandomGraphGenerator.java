package edu.brown.cs.termproject.pbt;

import com.google.maps.errors.ApiException;
import edu.brown.cs.termproject.locationgraph.Airport;
import edu.brown.cs.termproject.locationgraph.College;
import edu.brown.cs.termproject.locationgraph.Location;
import edu.brown.cs.termproject.locationgraph.LocationGraph;
import edu.brown.cs.termproject.database.AirportSQLManager;
import edu.brown.cs.termproject.database.CollegeSQLManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generates a random graph of locations for property based testing,
 * data points taken from universities and airports database.
 */
public class RandomGraphGenerator {

  private final CollegeSQLManager COLLEGE_DATABASE = new CollegeSQLManager();
  private final AirportSQLManager AIRPORT_DATABASE = new AirportSQLManager();
  private final Random random = new Random();

  /**
   * Instantiates the object using the universities database and airports database.
   */
  public RandomGraphGenerator() {
    COLLEGE_DATABASE.connect("./data/universities.sqlite3");
    AIRPORT_DATABASE.connect("./data/airports.sqlite3");
  }

  private Location getRandomCollege() throws SQLException {
    int id = random.nextInt(817) + 1;
    College c = COLLEGE_DATABASE.getCollegeByID(id);
    return new Location(c.getId(), c.getName(), c.getLat(), c.getLon(), "college", c);
  }

  private Location getRandomAirport() throws SQLException {
    int id = random.nextInt(40) + 1;
    Airport a = AIRPORT_DATABASE.getAirportById(id);
    return new Location(a.getId(), a.getName(), a.getLat(), a.getLon(), "airport", a);
  }

  /**
   * Generates a random graph with one airport and a random amount of colleges.
   * The colleges are chose randomly by id from the universities database.
   * The airport is chosen randomly by id from the airports database.
   *
   * @return A Graph with one Airport and multiple colleges wrapped in Location.
   */
  public LocationGraph generateRandomGraph()
      throws SQLException, InterruptedException, ApiException, IOException {
    int numColleges = random.nextInt(5) + 1;
    List<Location> locations = new ArrayList<>();
    for (int i = 0; i < numColleges; i++) {
      locations.add(getRandomCollege());
    }
    locations.add(getRandomAirport());
    return new LocationGraph(locations);
  }
}
