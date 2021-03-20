package edu.brown.cs.termproject.repl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * A command-line REPL that reads user inputs, matches them with various commands, runs the
 * corresponding command with parsed parameters, and prints the output.
 */
public class Repl  {
  private final List<Command> commands = new LinkedList<>();

  /**
   * Processes a single line of user input.
   * @param input User input.
   * @return The command output to print.
   */
  public String processLine(String input) {
    for (Command command : commands) {
      if (command.match(input)) {
        try {
          return command.call(input);
        } catch (Exception e) {
          return "ERROR: " + e.getMessage();
        }
      }
    }
    // if no command matches
    return "ERROR: Input matches no known command pattern.";
  }

  /**
   * Launches the REPL, looping to read, evaluate, and print until EOF is read.
   */
  public void run() {
    BufferedReader reader = new BufferedReader(new InputStreamReader((System.in)));
    String commandStr = "";
    try {
      while ((commandStr = reader.readLine()) != null) {
        String output = processLine(commandStr);
        if (!output.equals("")) {
          System.out.println(output);
        }
      }
    } catch (IOException e) {
      System.out.println("ERROR: fail to read from the stream. Shutting down.");
    }
  }

  /**
   * Load a new command to listen to.
   * @param command Command to load
   */
  public void addCommand(Command command) {
    commands.add(command);
  }

  /**
   * Removes all commands with a given name.
   * @param name Name of the command(s) to remove
   */
  public void removeCommands(String name) {
    commands.removeIf(command -> command.getName().equals(name));
  }
}
