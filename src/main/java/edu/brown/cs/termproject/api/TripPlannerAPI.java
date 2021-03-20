package edu.brown.cs.termproject.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.brown.cs.termproject.database.CollegeDatabase;
import spark.Route;

public class TripPlannerAPI extends API{

  private CollegeDatabase db; // local reference to the REPL's MapsSQLManager
  private final ObjectMapper om = new ObjectMapper(); // used to turn objects into JSON

  /**
   * Creates a TripPlannerAPI object to provide API handlers.
   */
  public TripPlannerAPI(CollegeDatabase db) {
    this.db = db;
  }

  private final Route root = (request, response) -> {
//    List<Star> allStars = stars.getStars();
//    return om.writeValueAsString(allStars);
    return true;
  };

  /**
   * Handles get request to the root node, returning all loaded stars.
   * @return Root handler
   */
  public Route getRoot() {
    return root;
  }

}
