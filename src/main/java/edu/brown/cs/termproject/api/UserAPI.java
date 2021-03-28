package edu.brown.cs.termproject.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.brown.cs.termproject.database.UserDataManager;
import edu.brown.cs.termproject.main.Encryption;
import edu.brown.cs.termproject.main.User;
import spark.Route;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

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

  public Route getLogin() {
    return login;
  }
  public Route getSignUp() {
    return login;
  }
  public Route getCheckUsername() {
    return login;
  }
  public Route getUserAddCollege() {
    return login;
  }
  public Route getUserDeleteCollege() {
    return login;
  }

  private final Route login = (request, response) -> {
    JsonObject data = GSON.fromJson(request.body(), JsonObject.class);
    String username = data.get("username").getAsString();
    String password = data.get("password").getAsString();
    User user = userDB.getUserInfo(username);
    JsonObject payload = new JsonObject();

    if (user == null) {
      payload.addProperty("success", false);
      payload.addProperty("error", "User does not exist.");
      System.err.println("user does not exist");

    } else {
      if (Encryption.verify(password, user.getPassword())) {
        payload.addProperty("success", true);
        payload.addProperty("user", GSON.toJson(user));
      } else {
        payload.addProperty("success", false);
        payload.addProperty("error", "Password is incorrect.");
      }
      System.out.println(Encryption.verify(password, user.getPassword()));
    }
//    return om.writeValueAsString(user);
    return GSON.toJson(payload);
  };

  private final Route getCheckUsername = (request, response) -> {

    return null;
  };



}
