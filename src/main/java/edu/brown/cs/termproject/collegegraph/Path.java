package edu.brown.cs.termproject.collegegraph;

import edu.brown.cs.termproject.graph.Edge;
import edu.brown.cs.termproject.graph.Vertex;

public class Path implements Edge<College> {
  private College start;
  private College end;
  private double weight;

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

  public void setWeight(double w) {
    weight = w;
  }
}
