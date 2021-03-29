package edu.brown.cs.termproject.collegegraph;

import edu.brown.cs.termproject.graph.Edge;

/**
 * The class for Path.
 */
public class Path implements Edge<College> {
  private College start;
  private College end;
  private double weight;

  /**
   * Constructor for Path between colleges.
   * @param start start College.
   * @param end end College.
   * @param weight weight of the Path.
   */
  public Path(College start, College end, double weight) {
    this.start = start;
    this.end = end;
    this.weight = weight;
  }

  @Override
  public College getStart() {
    return start;
  }

  @Override
  public College getEnd() {
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
