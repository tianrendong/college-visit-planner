package edu.brown.cs.termproject.api;

import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import edu.brown.cs.termproject.airport.Airport;
import edu.brown.cs.termproject.collegegraph.College;
import edu.brown.cs.termproject.collegegraph.Location;
import edu.brown.cs.termproject.collegegraph.LocationGraph;
import edu.brown.cs.termproject.collegegraph.LocationPath;
import edu.brown.cs.termproject.database.UserDataManager;
import edu.brown.cs.termproject.iotools.CenterCalculator;
import edu.brown.cs.termproject.main.Main;
import edu.brown.cs.termproject.router.Clustering;
import edu.brown.cs.termproject.router.Nearest;
import edu.brown.cs.termproject.router.OrderRoute;
import edu.brown.cs.termproject.router.Point;
import edu.brown.cs.termproject.router.TSP;
import spark.Route;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * API for user related requests.
 */
public class UserAPI {

  private static final Gson GSON = new Gson();
  private UserDataManager userDB;
  private final TSP<Location, LocationPath> tspFinder = new TSP<>();;
  private final Nearest nearest = new Nearest();

  /**
   * Creates a TripPlannerAPI object to provide API handlers.
   *
   * @param userDB Database for colleges.
   */
  public UserAPI(UserDataManager userDB) {
    this.userDB = userDB;
  }

  /**
   * Gets login information.
   * @return Route containing login status.
   */
  public Route getLogin() {
    return login;
  }

  /**
   * Gets signup information.
   * @return Route containing signup status.
   */
  public Route getSignUp() {
    return signup;
  }

  /**
   * Gets username check.
   * @return Route indicating whether username is used.
   */
  public Route getCheckUsername() {
    return checkUsername;
  }

  /**
   * Gets user adding college request.
   * @return Route containing information for adding college.
   */
  public Route getUserAddCollege() {
    return userAddCollege;
  }

  /**
   * Gets user deleting college request.
   * @return Route containing information for deleting college.
   */
  public Route getUserDeleteCollege() {
    return userDeleteCollege;
  }

  /**
   * Gets information for route.
   * @return Route containing information about the calculated route.
   */
  public Route getRoute() {
    return updateRoute;
  }

  /**
   * Gets cluster information.
   * @return Route containing information for clusters.
   */
  public Route getClusters() {
    return clusters;
  }

  /**
   * Gets information for deleting user data.
   * @return Route indicating whether user data was deleted.
   */
  public Route deleteData() {
    return deleteData;
  }

  /**
   * Gets information for deleting user account.
   * @return Route indicating whether user account was deleted.
   */
  public Route deleteAccount() {
    return deleteAccount;
  }

  private final Route login = (request, response) -> {
    JsonObject data = GSON.fromJson(request.body(), JsonObject.class);
    String username = data.get("username").getAsString();
    String password = data.get("password").getAsString();
    return GSON.toJson(userDB.login(username, password));
  };

  private final Route signup = (request, response) -> {
    JsonObject data = GSON.fromJson(request.body(), JsonObject.class);
    String firstname = data.get("firstname").getAsString();
    String lastname = data.get("lastname").getAsString();
    String username = data.get("username").getAsString();
    String password = data.get("password").getAsString();
    return GSON.toJson(userDB.signup(username, password, firstname, lastname));
  };

  private final Route checkUsername = (request, response) -> {
    JsonObject data = GSON.fromJson(request.body(), JsonObject.class);
    String username = data.get("username").getAsString();
    return (userDB.checkUserExists(username));
  };

  private final Route userAddCollege = (request, response) -> {
    JsonObject data = GSON.fromJson(request.body(), JsonObject.class);
    String username = data.get("username").getAsString();
    int collegeID = data.get("collegeID").getAsInt();
    return userDB.addCollege(username, collegeID);
  };

  private final Route userDeleteCollege = (request, response) -> {
    JsonObject data = GSON.fromJson(request.body(), JsonObject.class);
    String username = data.get("username").getAsString();
    int collegeID = data.get("collegeID").getAsInt();
    return userDB.deleteCollege(username, collegeID);
  };

  private final Route deleteData = (request, response) -> {
    JsonObject data = GSON.fromJson(request.body(), JsonObject.class);
    String username = data.get("username").getAsString();
    return userDB.deleteUserData(username);
  };

  private final Route deleteAccount = (request, response) -> {
    JsonObject data = GSON.fromJson(request.body(), JsonObject.class);
    String username = data.get("username").getAsString();
    return userDB.deleteUserAccount(username);
  };

  private final Route clusters = (request, response) -> {
    JsonObject data = GSON.fromJson(request.body(), JsonObject.class);
    JsonArray collegesInJson = data.get("colleges").getAsJsonArray();
    Integer radius = data.get("radius").getAsInt();
    List<College> colleges = new Gson().fromJson(collegesInJson,
        new TypeToken<List<College>>() {
        }.getType());
    System.out.println(colleges);

    Clustering<College> clustering = new Clustering<>(radius);
    Map<Point, List<College>> clusterMap = clustering.makeClusters(colleges);
    List<List<College>> clusterList = new ArrayList<>();
    for (Map.Entry<Point, List<College>> entry : clusterMap.entrySet()) {
      clusterList.add(entry.getValue());
    }
    return GSON.toJson(clusterList);
  };

  private final Route updateRoute = (request, response) -> {
    JsonObject data = GSON.fromJson(request.body(), JsonObject.class);
    JsonArray collegesInJson = data.get("colleges").getAsJsonArray();
    List<College> colleges = new Gson().fromJson(collegesInJson,
        new TypeToken<List<College>>() {
        }.getType());
    System.out.println(colleges);

    // find center of the colleges and use it to find nearest airport
    Point center = CenterCalculator.getCentroid(colleges);
    List<Airport> airports = Main.getAirportDatabase().getAllAirports();
    Airport airport = (Airport) nearest.findNearestLocation(center, airports);

    // wrap all colleges and airports in Location objects and add them to a list
    List<Location> locations = new ArrayList<>();
    for (College c : colleges) {
      locations.add(new Location(c.getId(), c.getName(), c.getLat(), c.getLon(), "college", c));
    }
    locations.add(new Location(airport.getId(), airport.getName(), airport.getLat(), airport.getLon(), "airport", airport));

    //perform TSP and reorder the found route
    LocationGraph graph = new LocationGraph(locations);
    List<Location> tsp = OrderRoute.orderRoute(tspFinder.findRoute(graph));

    return GSON.toJson(tsp);
  };
}
