package edu.brown.cs.termproject.main;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;

import java.io.IOException;

public class GoogleMapAPIManager {

  private static GeoApiContext geoAPIContext = new GeoApiContext.Builder()
      .apiKey("AIzaSyBIJk5AqilYH8PHt2TP4f5d7QY-UxtJf58")
      .build();

  private static DirectionsResult getDirectionResult(double startLat, double startLng, double destLat, double destLng)
      throws InterruptedException, ApiException, IOException {
    DirectionsResult result =
        DirectionsApi.newRequest(geoAPIContext)
            .mode(TravelMode.DRIVING)
//              .avoid(
//                  DirectionsApi.RouteRestriction.TOLLS)
            .units(Unit.METRIC)
            .origin(new LatLng(startLat, startLng))
            .destination(new LatLng(destLat, destLng))
            .await();
    return result;
  }


  public static double getTravelTime(double startLat, double startLng, double destLat, double destLng)
      throws InterruptedException, ApiException, IOException {
    DirectionsResult result = getDirectionResult(startLat, startLng, destLat, destLng);
    return result.routes[0].legs[0].duration.inSeconds;
  }

  public static double getTravelDistance(double startLat, double startLng, double destLat, double destLng)
      throws InterruptedException, ApiException, IOException {
    DirectionsResult result = getDirectionResult(startLat, startLng, destLat, destLng);
    return result.routes[0].legs[0].distance.inMeters;
  }

  public static String getTravelInfo(double startLat, double startLng, double destLat, double destLng)
      throws InterruptedException, ApiException, IOException {
    DirectionsResult result = getDirectionResult(startLat, startLng, destLat, destLng);
    return "Time :" + result.routes[0].legs[0].duration.humanReadable +
        " Distance :" + result.routes[0].legs[0].distance.humanReadable;
  }

  }


