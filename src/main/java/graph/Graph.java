package graph;

import java.util.List;

/**
 * Interface to represent graphs.
 *
 * @param <V> Class for vertices of the graph.
 * @param <E> Class for edges of the graph.
 */

public interface Graph<V extends Vertex, E extends Edge<V>> {

  /**
   * Gets outgoing edges from a vertex.
   * @param v Vertex from which to get outgoing edges.
   * @return Outgoing edges.
   */
  List<E> getOutEdges(V v);
}
