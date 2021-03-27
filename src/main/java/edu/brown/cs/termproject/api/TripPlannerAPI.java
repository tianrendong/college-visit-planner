package edu.brown.cs.termproject.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.brown.cs.termproject.collegegraph.College;
import edu.brown.cs.termproject.database.CollegeSQLManager;
import edu.brown.cs.termproject.collegegraph.*;
import edu.brown.cs.termproject.database.UserDataManager;
import edu.brown.cs.termproject.main.Encryption;
import edu.brown.cs.termproject.main.User;
import spark.Route;
import org.json.JSONObject;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.List;

public class TripPlannerAPI extends API{

  private CollegeSQLManager collegeDB;
  private UserDataManager userDB;
  private final ObjectMapper om = new ObjectMapper(); // used to turn objects into JSON

  /**
   * Creates a TripPlannerAPI object to provide API handlers.
   * @param db Database for colleges.
   */
  public TripPlannerAPI(CollegeSQLManager collegeDB, UserDataManager userDB) {
    this.collegeDB = collegeDB;
    this.userDB = userDB;
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

  private final Route colleges = (request, response) -> {
    List<College> colleges = collegeDB.getDefaultColleges();
    return om.writeValueAsString(colleges);
  };

  public Route getDefaultColleges() {
    return colleges;
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
  public Route getClusters() {
    return login;
  }
  public Route getRoute() {
    return login;
  }
  public Route getRelatedColleges() {
    return login;
  }
  public Route getCollegeInfo() {
    return login;
  }


}
