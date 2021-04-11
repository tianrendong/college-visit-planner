package edu.brown.cs.termproject.api;

import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import edu.brown.cs.termproject.airport.Airport;
import edu.brown.cs.termproject.collegegraph.*;
import edu.brown.cs.termproject.database.UserDataManager;
import edu.brown.cs.termproject.iotools.CenterCalculator;
import edu.brown.cs.termproject.main.Main;
import edu.brown.cs.termproject.router.*;
import spark.Route;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.*;

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

  public Route getLogin() {
    return login;
  }

  public Route getSignUp() {
    return signup;
  }

  public Route getCheckUsername() {
    return checkUsername;
  }

  public Route getUserAddCollege() {
    return userAddCollege;
  }

  public Route getUserDeleteCollege() {
    return userDeleteCollege;
  }

  public Route getRoute() {
    return updateRoute;
  }

  public Route getClusters() {
    return clusters;
  }

  public Route deleteData() {
    return deleteData;
  }

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
      locations.add(new Location(c.getId(), c.getName(), c.getLat(), c.getLon(), "college"));
    }
    locations.add(new Location(airport.getId(), airport.getName(), airport.getLat(), airport.getLon(), "airport"));

    //perform TSP and reorder the found route
    LocationGraph graph = new LocationGraph(locations);
    List<Location> tsp = OrderRoute.orderRoute(tspFinder.findRoute(graph));

    return GSON.toJson(tsp);
  };

}