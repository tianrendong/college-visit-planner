package edu.brown.cs.termproject.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.brown.cs.termproject.database.UserDataManager;
import edu.brown.cs.termproject.main.Encryption;
import edu.brown.cs.termproject.main.User;
import org.json.JSONObject;
import spark.Route;

public class UserAPI extends API {

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
  public Route getRegister() {
    return login;
  }
  public Route getUserAddCollege() {
    return login;
  }
  public Route getUserDeleteCollege() {
    return login;
  }

  private final Route login = (request, response) -> {
    JSONObject data = new JSONObject(request.body());
    String username = data.getString("username");
    String password = data.getString("password");
    System.out.println(password);
    User user = userDB.getUserInfo(username);
    if (user == null) {
      System.out.println("user does not exist");
    } else {
      System.out.println(Encryption.verify(password, user.getPassword()));
    }


//    return om.writeValueAsString();
    return true;
  };



}
