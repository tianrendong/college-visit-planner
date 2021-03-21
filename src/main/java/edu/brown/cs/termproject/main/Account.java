package edu.brown.cs.termproject.main;

import java.util.List;

public class Account {
  private String username;
  private List<List<College>> route;
  private List<College> colleges;

  public Account(String username, List<List<College>> route, List<College> colleges) {
    this.username = username;
    this.route = route;
    this.colleges = colleges;
  }
}
