package edu.brown.cs.termproject.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import edu.brown.cs.termproject.locationgraph.Airport;
import edu.brown.cs.termproject.locationgraph.College;
import edu.brown.cs.termproject.database.CollegeSQLManager;
import edu.brown.cs.termproject.main.Main;
import edu.brown.cs.termproject.router.Nearest;
import spark.Route;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * API class for college related requests.
 */
public class CollegeAPI {

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

  /**
   * Gets the default colleges.
   * @return Route that contains the default colleges.
   */
  public Route getDefaultColleges() {
    return defaultColleges;
  }

  /**
   * Gets the autocorrect suggestions.
   * @return Route containing autocorrect suggestions.
   */
  public Route getAutocorrect() {
    return autocorrect;
  }

  /**
   * Gets the information of a college.
   * @return Route containing college information.
   */
  public Route getCollegeInfo() {
    return collegeInfo;
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

  private final Route collegeInfo = (request, response) -> {
    JsonObject data = GSON.fromJson(request.body(), JsonObject.class);
    int collegeID = data.get("collegeID").getAsInt();
    College college = collegeDB.getCollegeByID(collegeID);
    JsonObject payload = new JsonObject();
    payload.addProperty("college", GSON.toJson(college)); // add college

    List<Airport> airports = Main.getAirportDatabase().getAllAirports();
    Airport airport = (Airport) nearest.findNearestLocation(college, airports);
    payload.addProperty("nearbyAirport", GSON.toJson(airport)); // add airport

    List<College> colleges = collegeDB.getNearbyColleges(college);
    payload.addProperty("nearbyColleges", GSON.toJson(colleges)); // add nearby colleges
    System.out.println(colleges);

    return payload;
  };


}
