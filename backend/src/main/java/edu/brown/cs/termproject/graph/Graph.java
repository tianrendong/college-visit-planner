package edu.brown.cs.termproject.graph;

/**
 * Interface for Graph.
 * @param <V> Vertex of the graph.
 * @param <E> Edge of the graph.
 */
public interface Graph<V extends Vertex, E extends Edge<V>> {

  /**
   * Adds an edge of type E to the graph.
   * @param edge edge to add.
   */
  void addEdge(E edge);
}
