package edu.brown.cs.termproject.iotools;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;

import java.io.IOException;

/**
 * Class for handling requests to GoogleMapsAPI.
 */
public final class GoogleMapAPIManager {

  private GoogleMapAPIManager() {

  }

  private static final GeoApiContext GEO_API_CONTEXT = new GeoApiContext.Builder()
      .apiKey("AIzaSyBIJk5AqilYH8PHt2TP4f5d7QY-UxtJf58")
      .build();

  private static DirectionsResult getDirectionResult(
      double startLat, double startLng, double destLat, double destLng)
      throws InterruptedException, ApiException, IOException {
    DirectionsResult result =
        DirectionsApi.newRequest(GEO_API_CONTEXT)
            .mode(TravelMode.DRIVING)
//              .avoid(
//                  DirectionsApi.RouteRestriction.TOLLS)
            .units(Unit.METRIC)
            .origin(new LatLng(startLat, startLng))
            .destination(new LatLng(destLat, destLng))
            .await();
    return result;
  }

  /**
   * Gets the travel time between two points.
   * @param startLat latitude of start point.
   * @param startLng longitude of start point.
   * @param destLat latitude of end point.
   * @param destLng longitude of end point.
   * @return time to travel from start to end.
   * @throws InterruptedException if errors during request to API.
   * @throws ApiException if errors during request to API.
   * @throws IOException if errors during request to API.
   */
  public static double getTravelTime(
      double startLat, double startLng, double destLat, double destLng)
      throws InterruptedException, ApiException, IOException {
    DirectionsResult result = getDirectionResult(startLat, startLng, destLat, destLng);
    return result.routes[0].legs[0].duration.inSeconds;
  }

  /**
   * Gets the travel distance between start and end points.
   * @param startLat latitude of start point.
   * @param startLng longitude of start point.
   * @param destLat latitude of end point.
   * @param destLng longitude of end point.
   * @return distance to travel from start to end.
   * @throws InterruptedException if errors during request to API.
   * @throws ApiException if errors during request to API.
   * @throws IOException if errors during request to API.
   */
  public static double getTravelDistance(
      double startLat, double startLng, double destLat, double destLng)
      throws InterruptedException, ApiException, IOException {
    DirectionsResult result = getDirectionResult(startLat, startLng, destLat, destLng);
    return result.routes[0].legs[0].distance.inMeters;
  }

  /**
   * Gets the travel information between start and end points.
   * @param startLat latitude of start point.
   * @param startLng longitude of start point.
   * @param destLat latitude of end point.
   * @param destLng longitude of end point.
   * @return travel information as a string.
   * @throws InterruptedException if errors during request to API.
   * @throws ApiException if errors during request to API.
   * @throws IOException if errors during request to API.
   */
  public static String getTravelInfo(
      double startLat, double startLng, double destLat, double destLng)
      throws InterruptedException, ApiException, IOException {
    DirectionsResult result = getDirectionResult(startLat, startLng, destLat, destLng);
    return "Time :" + result.routes[0].legs[0].duration.humanReadable
        + " Distance :" + result.routes[0].legs[0].distance.humanReadable;
  }

//  private static DirectionsResult x(String location)
//      throws InterruptedException, ApiException, IOException {
//    DirectionsResult result =
//        DirectionsApi.newRequest(geoAPIContext)
//            .mode(TravelMode.DRIVING)
////              .avoid(
////                  DirectionsApi.RouteRestriction.TOLLS)
//            .units(Unit.METRIC)
//            .origin(new LatLng(startLat, startLng))
//            .destination(new LatLng(destLat, destLng))
//            .await();
//    return result;
}
