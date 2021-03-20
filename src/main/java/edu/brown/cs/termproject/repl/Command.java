package edu.brown.cs.termproject.repl;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a command for the REPL to listen for and to execute when an input matches the
 * command's parameter pattern.
 */
public class Command {
  private final Callable callback; // the callback function
  private final String name; // name of the command
  private final List<ArgType> args; // the expected arguments' types

  // split by space, except when surrounded by double quotes
  private final Pattern splitter = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"");

  /**
   * Initializes a Command with its associated callback, name, and argument types.
   * @param callback Callback code when an input line matches
   * @param name Name of the command
   * @param args List of argument types dictating what correct usage of the command
   *            looks like
   */
  public Command(Callable callback, String name, ArgType... args) {
    this.callback = callback;
    this.name = name;
    this.args = List.of(args);
  }

  /**
   * Gets the name of the command.
   * @return Name of the command
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the arguments of the command.
   * @return Arguments of the command
   */
  public List<ArgType> getArgs() {
    return args;
  }

  /**
   * Decomposes a full input line into String components by splitting on spaces not between
   * parentheses.
   * @param input Full input line
   * @return Decomposed components
   */
  private List<String> decompose(String input) {
    List<String> components = new ArrayList<>();
    Matcher match = splitter.matcher(input);
    while (match.find()) {
      components.add(match.group());
    }
    return components;
  }

  /**
   * Checks if an input line matches the command name and required parameter types of the
   * command.
   * @param input Input line.
   * @return Whether the input matches the required pattern.
   */
  public boolean match(String input) {
    List<String> components = decompose(input);
    if (components.size() != (args.size() + 1) || !components.get(0).equals(name)) {
      return false;
    }

    for (int i = 1; i < components.size(); i++) {
      String component = components.get(i);
      ArgType arg = args.get(i - 1);
      if (!arg.isType(component)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Given an input line, calls the callback with the input's provided parameters parsed.
   * @param input Input line
   * @return Output of the callback to print
   */
  public String call(String input) {
    if (!match(input)) {
      throw new IllegalArgumentException("Input does not match command expectation.");
    }

    List<String> components = decompose(input);
    List<Object> parameters = new LinkedList<>();
    for (int i = 1; i < components.size(); i++) {
      String component = components.get(i);
      ArgType arg = args.get(i - 1);
      parameters.add(arg.toType(component));
    }

    return callback.call(parameters.toArray());
  }
}
