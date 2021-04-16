package edu.brown.cs.termproject.graph;

/**
 * Class for generic edge.
 * @param <V> Vertex stored in the edge.
 */
public class GenericEdge<V extends Vertex> implements Edge<V> {
  private V start;
  private V end;
  private double weight;

  /**
   * Constructor for Path between colleges.
   * @param start start College.
   * @param end end College.
   * @param weight weight of the Path.
   */
  public GenericEdge(V start, V end, double weight) {
    this.start = start;
    this.end = end;
    this.weight = weight;
  }

  /**
   * Gets the start of edge.
   * @return start of edge
   */
  @Override
  public V getStart() {
    return start;
  }

  /**
   * Gets the end of edge.
   * @return end of edge
   */
  @Override
  public V getEnd() {
    return end;
  }

  /**
   * Gets the weight of the Path.
   * @return weight
   */
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
}
