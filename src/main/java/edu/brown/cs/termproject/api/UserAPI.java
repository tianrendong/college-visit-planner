package edu.brown.cs.termproject.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import edu.brown.cs.termproject.collegegraph.College;
import edu.brown.cs.termproject.database.UserDataManager;
import edu.brown.cs.termproject.main.Encryption;
import edu.brown.cs.termproject.main.User;
import edu.brown.cs.termproject.router.Clustering;
import edu.brown.cs.termproject.router.Locatable;
import edu.brown.cs.termproject.router.Point;
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

  /**
   * Creates a TripPlannerAPI object to provide API handlers.
   * @param userDB Database for colleges.
   */
  public UserAPI(UserDataManager userDB) {
    this.userDB = userDB;
  }

  public Route getLogin() { return login; }
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
  public Route getRoute() { return updateRoute; }
  public Route deleteData() { return deleteData; }
  public Route deleteAccount() { return deleteAccount; }

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
    System.out.println(request.body());
    JsonObject data = GSON.fromJson(request.body(), JsonObject.class);
    String username = data.get("username").getAsString();
    int collegeID = data.get("collegeID").getAsInt();
    return userDB.addCollege(username, collegeID);
  };

  private final Route userDeleteCollege = (request, response) -> {
    System.out.println(request.body());
    JsonObject data = GSON.fromJson(request.body(), JsonObject.class);
    String username = data.get("username").getAsString();
    int collegeID = data.get("collegeID").getAsInt();
    System.out.println(username);
    System.out.println(collegeID);
    return userDB.deleteCollege(username, collegeID);
  };

  private final Route deleteData = (request, response) -> {
    System.out.println(request.body());
    JsonObject data = GSON.fromJson(request.body(), JsonObject.class);
    String username = data.get("username").getAsString();
    System.out.println(username);
    return userDB.deleteUserData(username);
  };

  private final Route deleteAccount = (request, response) -> {
    System.out.println(request.body());
    JsonObject data = GSON.fromJson(request.body(), JsonObject.class);
    String username = data.get("username").getAsString();
    System.out.println(username);
    return userDB.deleteUserAccount(username);
  };

  private final Route updateRoute = (request, response) -> {
    JsonObject data = GSON.fromJson(request.body(), JsonObject.class);
    JsonArray collegesInJson = data.get("colleges").getAsJsonArray();
    List<College> colleges = new Gson().fromJson(collegesInJson,
        new TypeToken<List<College>>() { }.getType());
    System.out.println(colleges);
    // TODO: set maxDistance to user input
    double maxDistance = 200.0;
    //clustering algorithm
    Clustering clustering = new Clustering(maxDistance);
    Map<Point, List<College>> clusterMap = clustering.makeClusters(colleges);
    List<List<College>> clusterList = new ArrayList<>();
    for (Map.Entry<Point, List<College>> entry : clusterMap.entrySet()) {
      clusterList.add(entry.getValue());
    }
    // TODO: TSP on each cluster
//    for (List<College> cluster : clusters) {
//      //TSP on each cluster
//    }

    // TODO: update this route for the user(store in DB)
    // return route


    College c1 = new College(1, "Massachusetts Institute of Technology", 42.360001, -71.092003,
        "placeholder", "placeholder");
    College c2 = new College(2, "Stanford University", 37.428230, -122.168861,
        "placeholder", "placeholder");
    College c3 = new College(3, "Harvard University", 42.374443, -71.116943,
        "placeholder", "placeholder");
    College c4 = new College(4, "California Institute of Technology", 34.138000, -118.125000,
        "placeholder", "placeholder");
    College c5 = new College(4, "UCLA", 34.05224385538, -118.2436864078,
        "placeholder", "placeholder");

    List<College> cluster1 = new ArrayList<>(Arrays.asList(c2, c4, c5));
    List<College> cluster2 = new ArrayList<>(Arrays.asList(c1, c3));
    List<List<College>> route1= new ArrayList<>(Arrays.asList(cluster1, cluster2));

    return GSON.toJson(route1);
  };
}
