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

public class CollegeAPI extends API{

  private CollegeSQLManager collegeDB;
  private final ObjectMapper om = new ObjectMapper(); // used to turn objects into JSON

  /**
   * Creates a CollegeAPI object to provide API handlers.
   * @param collegeDB Database for colleges.
   */
  public CollegeAPI(CollegeSQLManager collegeDB) {
    this.collegeDB = collegeDB;
  }

  private final Route colleges = (request, response) -> {
    List<College> colleges = collegeDB.getDefaultColleges();
    return om.writeValueAsString(colleges);
  };

  public Route getDefaultColleges() {
    return colleges;
  }
  public Route getRelatedColleges() {
    return  null;
  }
  public Route getCollegeInfo() {
    return  null;
  }


}
