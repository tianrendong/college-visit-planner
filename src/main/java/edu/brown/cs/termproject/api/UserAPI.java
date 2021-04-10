package edu.brown.cs.termproject.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import edu.brown.cs.termproject.collegegraph.College;
import edu.brown.cs.termproject.collegegraph.CollegeGraph;
import edu.brown.cs.termproject.collegegraph.Path;
import edu.brown.cs.termproject.database.UserDataManager;
import edu.brown.cs.termproject.main.Encryption;
import edu.brown.cs.termproject.main.User;
import edu.brown.cs.termproject.router.Clustering;
import edu.brown.cs.termproject.router.Locatable;
import edu.brown.cs.termproject.router.Point;
import edu.brown.cs.termproject.router.TSP;
import spark.Route;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UserAPI extends API {

  private static final Gson GSON = new Gson();
  private UserDataManager userDB;
  private final ObjectMapper om = new ObjectMapper(); // used to turn objects into JSON
  private final TSP<College, Path> tspFinder = new TSP<>();

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
    List<College> colleges = new Gson().fromJson(collegesInJson,
        new TypeToken<List<College>>() {
        }.getType());
    System.out.println(colleges);

    double maxDistance = 300.0;
    Clustering<College> clustering = new Clustering<>(maxDistance);
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

    CollegeGraph clusterGraph = new CollegeGraph(colleges);
    System.out.println(140);
    System.out.println(clusterGraph);
    List<College> orderedCluster = tspFinder.findRoute(clusterGraph);
    System.out.println(143);
    System.out.println(orderedCluster);

    return GSON.toJson(orderedCluster);
  };
}