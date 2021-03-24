package edu.brown.cs.termproject.collegegraph;

import com.google.maps.errors.ApiException;
import edu.brown.cs.termproject.main.GoogleMapAPIManager;
import org.junit.Test;

import java.io.IOException;

public class GoogleMapAPIManagerTest {

  @Test
  public void test() throws InterruptedException, ApiException, IOException {
    System.out.println(
        GoogleMapAPIManager.getTravelInfo(
            42.374443, -71.116943,42.360001, -71.092003));
  }
}
