package edu.brown.cs.termproject.router;

import edu.brown.cs.termproject.graph.Edge;
import edu.brown.cs.termproject.graph.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Approximation of TSP through MST.
 * Depth first search through the MST.
 * Worst case cost no more than twice of MST.
 */
public final class NaiveTSP {
  private NaiveTSP() { }

  /**
   * Finds the approximation from a MST.
   * @param mst Minimum spanning tree of all the nodes to visit.
   * @param <T> Type of the nodes that extends
   * @return list in visiting order of the nodes.
   */
  public static <T extends Vertex> List<T> findRoute(Set<? extends Edge<T>> mst) {
    List<T> tsp = new ArrayList<>();
    for (Edge<T> e : mst) {
      if (!tsp.contains(e.getStart())) {
        tsp.add(e.getStart());
      }
      if (!tsp.contains(e.getEnd())) {
        tsp.add(e.getEnd());
      }
    }
    return tsp;
  }
}
