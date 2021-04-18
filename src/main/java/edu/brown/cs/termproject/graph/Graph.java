package edu.brown.cs.termproject.graph;

import java.util.Set;

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

  /**
   * Gets all the vertices from the graph.
   * @return all vertices in the grpah.
   */
  Set<V> getVertices();

  /**
   * Gets all the edges from the graph.
   * @return all edges in the graph.
   */
  Set<E> getEdges();
}
