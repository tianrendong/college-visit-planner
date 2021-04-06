package edu.brown.cs.termproject.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import edu.brown.cs.termproject.airport.Airport;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollegeAPI extends API{

  private CollegeSQLManager collegeDB;
  private static final Gson GSON = new Gson();
  private final ObjectMapper om = new ObjectMapper(); // used to turn objects into JSON

  /**
   * Creates a CollegeAPI object to provide API handlers.
   * @param collegeDB Database for colleges.
   */
  public CollegeAPI(CollegeSQLManager collegeDB) {
    this.collegeDB = collegeDB;
  }

  public Route getDefaultColleges() {
    return defaultColleges;
  }
  public Route getAutocorrect() {
    return autocorrect;
  }
  public Route getNearbyAirports() {
    return nearbyAirports;
  }

  private final Route defaultColleges = (request, response) -> {
    List<College> colleges = collegeDB.getDefaultColleges();
    return GSON.toJson(colleges);
  };

  private final Route autocorrect = (request, response) -> {
    JsonObject data = GSON.fromJson(request.body(), JsonObject.class);
    String input = data.get("input").getAsString();

    List<College> colleges = new ArrayList<>();
    Set<String> suggestions = new HashSet<>();
    if ((input != null) && (!input.equals(""))) {
      suggestions = collegeDB.getAutocorrector().suggest(input);
    }

    for (String collegeName: suggestions) {
      colleges.addAll(collegeDB.getCollegeByName(collegeName));
    }
    return GSON.toJson(colleges);
  };

  private final Route nearbyColleges = (request, response) -> {
    JsonObject data = GSON.fromJson(request.body(), JsonObject.class);
    JsonObject collegeAsJson = data.get("college").getAsJsonObject();
    College college = GSON.fromJson(collegeAsJson, College.class);
    System.out.println(college); // TODO: I already retrieved this college object that you can pass as input
    List<College> airports = new ArrayList<>(); // TODO: get nearby airports

    return GSON.toJson(airports);
  };



}
