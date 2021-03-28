package edu.brown.cs.termproject.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.brown.cs.termproject.database.UserDataManager;
import edu.brown.cs.termproject.main.Encryption;
import edu.brown.cs.termproject.main.User;
import org.json.JSONObject;
import spark.Route;

public class RouteAPI extends API {

//  private UserDataManager userDB;
  private final ObjectMapper om = new ObjectMapper(); // used to turn objects into JSON

  public RouteAPI() {
//    this.userDB = userDB;
  }

  public Route getClusters() {
    return null;
  }
  public Route getRoute() {
    return  null;
  }




}
