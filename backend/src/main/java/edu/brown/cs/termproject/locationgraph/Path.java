package edu.brown.cs.termproject.locationgraph;

import edu.brown.cs.termproject.graph.Edge;

/**
 * The class for Path.
 */
public class Path implements Edge<Location> {
  private Location start;
  private Location end;
  private double weight;

  /**
   * Constructor for Path between colleges.
   * @param start start College.
   * @param end end College.
   * @param weight weight of the Path.
   */
  public Path(Location start, Location end, double weight) {
    this.start = start;
    this.end = end;
    this.weight = weight;
  }

  @Override
  public Location getStart() {
    return start;
  }

  @Override
  public Location getEnd() {
    return end;
  }

  @Override
  public double getWeight() {
    return weight;
  }

  /**
   * Sets the weight of the Path to w.
   * @param w new weight of the Path.
   */
  public void setWeight(double w) {
    weight = w;
  }

  @Override
  public String toString() {
    return getStart().getName() + " -> "
        + getEnd().getName() + " : "
        + getWeight();
  }
}
