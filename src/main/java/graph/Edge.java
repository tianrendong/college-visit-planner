package graph;

/**
 * Interface to represent edges of a graph.
 *
 * @param <V> Class to represent vertices of the graph.
 */
public interface Edge<V extends Vertex> {

  /**
   * Gets the ID of the edge.
   * @return ID of the edge.
   */
  String getId();

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
}
