package edu.brown.cs.termproject.main;

import com.google.maps.errors.ApiException;
import edu.brown.cs.termproject.iotools.GoogleMapAPIManager;
import org.junit.Test;

import java.io.IOException;

public class GoogleMapAPIManagerTest {

  @Test
  public void test() throws InterruptedException, ApiException, IOException {
    System.out.println(
        GoogleMapAPIManager.getTravelInfo(
            42.374443, -71.116943,42.360001, -71.092003));
  }

  @Test
  public void test2() throws InterruptedException, ApiException, IOException {
    System.out.println(
        GoogleMapAPIManager.getTravelInfo(
            34.068921, -118.4451811,42.360001, -71.092003));
  }
}
