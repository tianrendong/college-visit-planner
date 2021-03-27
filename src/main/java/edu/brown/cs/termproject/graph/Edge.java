package edu.brown.cs.termproject.graph;

/**
 * Interface to represent edges of a graph.
 *
 * @param <V> Class to represent vertices of the graph.
 */
public interface Edge<V extends Vertex> {

  /**
   * Gets the vertex from which the edge extends.
   * @return Source vertex of the edge.
   */
  V getStart();

  /**
   * Gets the vertex to which the edge extends.
   * @return Target vertex of the edge.
   */
  V getEnd();

  /**
   * Gets the weight of the Edge.
   * @return weight of the edge.
   */
  double getWeight();
}
