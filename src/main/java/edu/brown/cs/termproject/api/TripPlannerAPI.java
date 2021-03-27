package edu.brown.cs.termproject.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.brown.cs.termproject.collegegraph.College;
import edu.brown.cs.termproject.database.CollegeSQLManager;
import spark.Route;

import java.util.List;

public class TripPlannerAPI extends API{

  private CollegeSQLManager db;
  private final ObjectMapper om = new ObjectMapper(); // used to turn objects into JSON

  /**
   * Creates a TripPlannerAPI object to provide API handlers.
   * @param db Database for colleges.
   */
  public TripPlannerAPI(CollegeSQLManager db) {
    this.db = db;
  }

  private final Route login = (request, response) -> {
//    return om.writeValueAsString(__);
    return true;
  };

  private final Route colleges = (request, response) -> {
    List<College> colleges = db.getDefaultColleges();
    return om.writeValueAsString(colleges);
  };

  public Route getDefaultColleges() {
    System.out.println("a");
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
