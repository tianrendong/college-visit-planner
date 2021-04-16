package edu.brown.cs.termproject.repl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandTest  {
  private Command _addCommand;
  private Command _concatCommand;
  private Command _mixedCommand;
  private Command _mixedCommandDouble;

  @Before
  public void setUp() {
    _addCommand = new Command((Object[] params) ->
        String.valueOf((int) params[0] + (int) params[1]), "add",
        ArgType.INT, ArgType.INT);
    _concatCommand = new Command((Object[] params) ->
        ((String) params[0]).concat((String) params[1]), "concat",
        ArgType.STRING, ArgType.STRING);
    _mixedCommand = new Command((Object[] params) ->
        String.valueOf((int) params[1] + (int) params[2]), "add",
        ArgType.QUOTED_STRING, ArgType.INT, ArgType.INT);
    _mixedCommandDouble = new Command((Object[] params) ->
        String.valueOf((int) params[1] + (int) params[2]), "add",
        ArgType.QUOTED_STRING, ArgType.DOUBLE, ArgType.DOUBLE);
  }

  @Test
  public void getName() {
    assertEquals("add", _addCommand.getName());
    assertEquals("concat", _concatCommand.getName());
    assertEquals("add", _mixedCommand.getName());
    assertEquals("add", _mixedCommandDouble.getName());
  }

  @Test
  public void getArgs() {
    assertArrayEquals(new ArgType[] {ArgType.INT, ArgType.INT},
        _addCommand.getArgs().toArray());
    assertArrayEquals(new ArgType[] {ArgType.STRING, ArgType.STRING},
        _concatCommand.getArgs().toArray());
    assertArrayEquals(new ArgType[] {ArgType.QUOTED_STRING, ArgType.INT, ArgType.INT},
        _mixedCommand.getArgs().toArray());
    assertArrayEquals(new ArgType[] {ArgType.QUOTED_STRING, ArgType.DOUBLE, ArgType.DOUBLE},
        _mixedCommandDouble.getArgs().toArray());
  }

  @Test
  public void match() {
    assertTrue(_addCommand.match("add 1 -1"));
    assertTrue(_addCommand.match("add 10 792"));
    assertFalse(_addCommand.match("add 1 -1 23"));
    assertFalse(_addCommand.match("addin 1 -1"));
    assertFalse(_addCommand.match("1 -1"));
    assertFalse(_addCommand.match("add \"1\" \"-1\""));

    assertTrue(_concatCommand.match("concat 2 123"));
    assertTrue(_concatCommand.match("concat abf efdf"));
    // allow extra spaces
    assertTrue(_concatCommand.match("concat 23  123"));
    assertTrue(_concatCommand.match("concat 2 123 "));
    assertTrue(_concatCommand.match("concat text 123"));
    assertFalse(_concatCommand.match("concat 12 123  23"));
  }

  @Test
  public void call() {
    assertEquals("0", _addCommand.call("add 1 -1"));
    assertEquals("802", _addCommand.call("add 10 792"));
    assertThrows(IllegalArgumentException.class, () -> {
      _addCommand.call("add 1 -1 23");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      _addCommand.call("addin 1 -1");
    });

    assertEquals("1123", _concatCommand.call("concat 1 123"));
    assertEquals("abfef", _concatCommand.call("concat abf ef"));

    assertEquals("43", _mixedCommand.call("add \"\" 12 31"));
    assertEquals("-2", _mixedCommand.call("add \"\" -4 2"));

  }
}