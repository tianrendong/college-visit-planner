package edu.brown.cs.termproject.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import edu.brown.cs.termproject.airport.Airport;
import edu.brown.cs.termproject.collegegraph.College;
import edu.brown.cs.termproject.database.AirportSQLManager;
import edu.brown.cs.termproject.database.CollegeSQLManager;
import edu.brown.cs.termproject.main.Main;
import edu.brown.cs.termproject.router.Nearest;
import spark.Route;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollegeAPI extends API{

  private CollegeSQLManager collegeDB;
  private static final Gson GSON = new Gson();
  private final Nearest nearest = new Nearest();

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
  public Route getNearbyAirport() {
    return nearbyAirport;
  }
  public Route getCollegesByID() {
    return collegesByID;
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

  private final Route nearbyAirport = (request, response) -> {
    JsonObject data = GSON.fromJson(request.body(), JsonObject.class);
    int collegeID = data.get("collegeID").getAsInt();
    College college = collegeDB.getCollegeByID(collegeID);
    System.out.println(college);

    List<Airport> airports = Main.getAirportDatabase().getAllAirports();
    Airport airport = (Airport) nearest.findNearestLocation(college, airports);
    return GSON.toJson(airport);
  };

  private final Route collegesByID = (request, response) -> {
    System.out.println(request.body());
    JsonObject data = GSON.fromJson(request.body(), JsonObject.class);
    JsonArray collegeIDsAsJson = data.get("collegeIDs").getAsJsonArray();
    System.out.println(collegeIDsAsJson);
    List<Integer> collegeIDs = GSON.fromJson(collegeIDsAsJson, new TypeToken<List<Integer>>() { }.getType());
    System.out.println("2");
    List<College> colleges = collegeDB.getCollegeByID(collegeIDs);
    System.out.println("3");
    return GSON.toJson(colleges);
  };

}
