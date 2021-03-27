package edu.brown.cs.termproject.api;

import spark.Request;

/**
 * Utility class to provide methods to parse query parameters.
 */
public class API {
  /**
   * Parses a string parameter from the request.
   * @param name Name of the parameter
   * @param request Request object
   * @return Value of the parameter
   */
  public String getString(String name, Request request) {
    String value = request.queryParams(name);
    if (value == null) {
      throw new IllegalArgumentException("Must supply string " + name + ".");
    }
    return value;
  }

  /**
   * Parses an integer parameter from the request.
   * @param name Name of the parameter
   * @param request Request object
   * @return Value of the parameter
   */
  public int getInt(String name, Request request) {
    String value = request.queryParams(name);
    if (value == null) {
      throw new IllegalArgumentException("Must supply integer " + name + ".");
    }
    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(name + " must be a integer. Got " + value + ".");
    }
  }

  /**
   * Parses a real number parameter from the request.
   * @param name Name of the parameter
   * @param request Request object
   * @return Value of the parameter
   */
  public double getDouble(String name, Request request) {
    String value = request.queryParams(name);
    if (value == null) {
      throw new IllegalArgumentException("Must supply double " + name + ".");
    }
    try {
      return Double.parseDouble(value);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(name + " must be a double. Got " + value + ".");
    }
  }
}
