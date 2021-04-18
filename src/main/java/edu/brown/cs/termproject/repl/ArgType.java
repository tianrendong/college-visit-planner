package edu.brown.cs.termproject.repl;

/**
 * Represents a command-line argument of a certain type, providing functionality to match a string
 * with the specified formatting and to convert a string into the type.
 */
public enum ArgType {
  /**
   * Represents an integer plainly formatted, e.g., 12.
   */
  INT {
    @Override
    public boolean isType(String s) {
      try {
        Integer.parseInt(s);
        return true;
      } catch (NumberFormatException e) {
        return false;
      }
    }
    @Override
    public Integer toType(String s) {
      if (!isType(s)) {
        throw new IllegalArgumentException("Input must be formatted as an integer.");
      }
      return Integer.parseInt(s);
    }
  },
  /**
   * Represents a quoted string, e.g., "text".
   */
  QUOTED_STRING {
    @Override
    public boolean isType(String s) {
      return s.matches("\".*\"");
    }
    @Override
    public String toType(String s) {
      if (!isType(s)) {
        throw new IllegalArgumentException("Input must be formatted as a string.");
      }
      return s.substring(1, s.length() - 1);
    }
  },
  /**
   * Represents an unquoted string, e.g., test.
   */
  STRING {
    @Override
    public boolean isType(String s) {
      return true;
    }
    @Override
    public String toType(String s) {
      return s;
    }
  },
  /**
   * Represents a real number, e.g., 12.5.
   */
  DOUBLE {
    @Override
    public boolean isType(String s) {
      try {
        Double.parseDouble(s);
        return true;
      } catch (NumberFormatException e) {
        return false;
      }
    }
    @Override
    public Double toType(String s) {
      if (!isType(s)) {
        throw new IllegalArgumentException("Input must be formatted as a double.");
      }
      return Double.parseDouble(s);
    }
  };

  /**
   * Checks if a string matches the required format.
   * @param s Input string
   * @return Whether the string matches
   */
  public abstract boolean isType(String s);

  /**
   * Parses a string and converts it into the given type.
   * @param s Input string
   * @return Converted type
   */
  public abstract Object toType(String s);
}
