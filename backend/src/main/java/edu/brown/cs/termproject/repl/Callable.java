package edu.brown.cs.termproject.repl;

/**
 * Utility class for a lambda function that executes some arbitrary code given input parameters.
 */
public interface Callable {
  /**
   * Passes in the parsed parameters to an arbitrary callback function to execute some code.
   * @param parameters Parsed parameters of various types. While they are stored as an array of
   *                   Object, they should be type-casted into their parsed types
   * @return The command's resulting output to the console
   */
  String call(Object[] parameters);
}
