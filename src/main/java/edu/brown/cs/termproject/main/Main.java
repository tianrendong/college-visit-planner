package edu.brown.cs.termproject.main;

import edu.brown.cs.termproject.airport.AirportManager;
import edu.brown.cs.termproject.api.CollegeAPI;
import edu.brown.cs.termproject.api.UserAPI;
import edu.brown.cs.termproject.database.AirportSQLManager;
import edu.brown.cs.termproject.database.CollegeSQLManager;
import edu.brown.cs.termproject.database.UserDataManager;
import edu.brown.cs.termproject.repl.Repl;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import spark.Spark;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * The Main class of our project. This is where execution begins.
 *
 */
public final class Main {
  private static final int DEFAULT_PORT = 4567;
  private static final String DEFAULT_COLLEGE_DB = "./data/sampleColleges.sqlite3";
  private static final String DEFAULT_USER_DB = "./data/sampleUsers.sqlite3";
  private static final String DEFAULT_AIRPORT_DB = "./data/airports.sqlite3";
  private static final CollegeSQLManager collegeDatabase = new CollegeSQLManager();
  private static final UserDataManager userDatabase = new UserDataManager();
  private static final AirportSQLManager airportDatabase = new AirportSQLManager();
  private final CollegeAPI collegeAPI = new CollegeAPI(collegeDatabase);
  private final UserAPI userAPI = new UserAPI(userDatabase);
  private final Repl repl = new Repl();

  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */
  public static void main(String[] args) {
    new Main(args).run();
  }

  private final String[] args;

  private Main(String[] args) {
    this.args = args;
  }

  public static CollegeSQLManager getCollegeDatabase() {
    return collegeDatabase;
  }
  public static AirportSQLManager getAirportDatabase() {
    return airportDatabase;
  }

  private void run() {
    // Parse command line arguments
    OptionParser parser = new OptionParser();
    parser.accepts("gui");
    parser.accepts("port").withRequiredArg().ofType(Integer.class)
        .defaultsTo(DEFAULT_PORT);
    parser.accepts("college-database").withRequiredArg().ofType(String.class)
        .defaultsTo(DEFAULT_COLLEGE_DB);
    parser.accepts("user-database").withRequiredArg().ofType(String.class)
        .defaultsTo(DEFAULT_USER_DB);
    parser.accepts("airport-database").withRequiredArg().ofType(String.class)
        .defaultsTo(DEFAULT_AIRPORT_DB);
    OptionSet options = parser.parse(args);

    collegeDatabase.connect((String) options.valueOf("college-database"));
    userDatabase.connect((String) options.valueOf("user-database"));
    airportDatabase.connect((String) options.valueOf("airport-database"));

    if (options.has("gui")) {
      runSparkServer((int) options.valueOf("port"));
    }
    repl.run();
  }

  private void runSparkServer(int port) {
    Spark.port(port);

    // handler exceptions
    Spark.exception(RuntimeException.class, (e, req, res) -> {
      res.status(400);
      res.body(e.getMessage());
    });

    Spark.exception(Exception.class, (e, req, res) -> {
      res.status(500);
      StringWriter stacktrace = new StringWriter();
      try (PrintWriter pw = new PrintWriter(stacktrace)) {
        pw.println("<pre>");
        e.printStackTrace(pw);
        pw.println("</pre>");
      }
      res.body(stacktrace.toString());
    });

    initializeSpark();

  }

  /**
   * Initializes Spark to handle API requests.
   */
  private void initializeSpark() {
    Spark.path("/api/user", () -> {
      Spark.post("/login", userAPI.getLogin());
      Spark.post("/signup", userAPI.getSignUp());
      Spark.post("/checkUsername", userAPI.getCheckUsername());
      Spark.post("/addCollege", userAPI.getUserAddCollege());
      Spark.post("/deleteCollege", userAPI.getUserDeleteCollege());
      Spark.post("/getRoute", userAPI.getRoute());
    });
    Spark.path("/api/college", () -> {
      Spark.get("/defaultColleges", collegeAPI.getDefaultColleges());
      Spark.post("/autocorrect", collegeAPI.getAutocorrect());
      Spark.post("/nearbyAirport", collegeAPI.getNearbyAirport());
    });
  }

}
