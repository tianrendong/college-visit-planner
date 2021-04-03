package edu.brown.cs.termproject.graph;

import edu.brown.cs.termproject.router.Locatable;

/**
 * Interface to represent vertices of a graph.
 */
public interface Vertex extends Locatable {

  /**
   * Gets the ID of the vertex.
   * @return ID of the vertex.
   */
  int getId();

}
