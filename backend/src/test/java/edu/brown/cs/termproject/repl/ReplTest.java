package edu.brown.cs.termproject.repl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReplTest {
  private Repl repl;

  @Before
  public void setUp() {
    repl = new Repl();
    repl.addCommand(new Command((Object[] args) -> "Moving " + ((String) args[0])
        + " to " + ((String) args[1]),
        "mv", ArgType.QUOTED_STRING, ArgType.QUOTED_STRING));
    repl.addCommand(new Command((Object[] args) ->
        "Moving " + ((String) args[0]) + " to " + ((String) args[1]) + " "
            + String.valueOf((int) args[2]) + " times",
        "mv", ArgType.QUOTED_STRING, ArgType.QUOTED_STRING, ArgType.INT));
    repl.addCommand(new Command((Object[] args) -> "Copying " + ((String) args[0])
        + " to " + ((String) args[1]),
        "cp", ArgType.STRING, ArgType.STRING));
    repl.addCommand(new Command((Object[] args) -> {
      int num = (int) args[0];
      if (num < 0) throw new IllegalArgumentException("Number must be positive.");
      return String.valueOf(Math.sqrt(num));
    }, "sqrt", ArgType.INT));
    repl.addCommand(new Command((Object[] args) -> {
      double num = (double) args[0];
      if (num < 0) throw new IllegalArgumentException("Number must be positive.");
      return String.valueOf(Math.sqrt(num));
    }, "sqrt", ArgType.DOUBLE));
  }

  @After
  public void tearDown() {
    repl = null;
  }

  @Test
  public void processLine() {
    assertEquals("Moving a to b", repl.processLine("mv \"a\" \"b\""));
    assertEquals("Moving a to b 2 times", repl.processLine("mv \"a\" \"b\" 2"));
    assertEquals("ERROR: Input matches no known command pattern.",
        repl.processLine("mv \"a\" 2 2"));
    assertEquals("Copying a to b", repl.processLine("cp a b"));
    assertEquals("2.0", repl.processLine("sqrt 4"));
    assertEquals("0.5", repl.processLine("sqrt 0.25"));
    assertEquals("ERROR: Number must be positive.", repl.processLine("sqrt -2"));
  }

  @Test
  public void addCommand() {
    assertEquals("ERROR: Input matches no known command pattern.",
        repl.processLine("mv \"a\""));
    repl.addCommand(new Command((Object[] args) -> "Moving " + ((String) args[0]),
        "mv", ArgType.QUOTED_STRING));
    assertEquals("Moving a", repl.processLine("mv \"a\""));
  }

  @Test
  public void removeCommands() {
    assertEquals("Moving a to b", repl.processLine("mv \"a\" \"b\""));
    repl.removeCommands("mv");
    assertEquals("ERROR: Input matches no known command pattern.", repl.processLine("mv \"a\" \"b\""));
    assertEquals("ERROR: Input matches no known command pattern.", repl.processLine("mv \"a\" \"b\" 2"));
    assertEquals("Copying a to b", repl.processLine("cp a b"));
  }


}