package edu.brown.cs.termproject.router;

import com.google.maps.errors.ApiException;
import edu.brown.cs.termproject.graph.Edge;
import edu.brown.cs.termproject.graph.Graph;
import edu.brown.cs.termproject.graph.Vertex;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * The class for Minimum Spanning Trees.
 */
public final class MST {
  private MST() {
  }

  /**
   * Given a complete CollegeGraph, build a minimum spanning tree
   * using Kruskal's algorithm.
   *
   * @param <V>        Type that implements Vertex.
   * @param <E>        Type that implements Edge.
   * @param graph      a complete CollegeGraph.
   * @param comparator Comparator for ordering edges.
   * @return minimum spanning tree formed from graph
   * @throws InterruptedException if errors occur during Google Map API queries
   * @throws ApiException         if errors occur during Google Map API queries
   * @throws IOException          if errors occur during Google Map API queries
   */
  public static <V extends Vertex, E extends Edge<V>> Set<E> mst(
      Graph<V, E> graph, Comparator<E> comparator)
      throws InterruptedException, ApiException, IOException {
    Set<E> mst = new HashSet<>();
    Set<E> edges = graph.getEdges();

    PriorityQueue<E> pqEdges = new PriorityQueue<>(comparator);
    pqEdges.addAll(edges);

    Map<V, UnionFind<V>> sets = new HashMap<>();
    for (V v : graph.getVertices()) {
      sets.put(v, new UnionFind<>(v));
    }

    while (!pqEdges.isEmpty()) {
      E lightest = pqEdges.poll();
      if (lightest != null) {
        UnionFind<V> lightestStart = sets.get(lightest.getStart());
        UnionFind<V> lightestEnd = sets.get(lightest.getEnd());
        // Check if the two vertices have the same root:
        // If they don't have the same root,
        // They don't form a cycle in the graph.
        if (lightestStart.find() != lightestEnd.find()) {
          lightestStart.union(lightestEnd);
          mst.add(lightest);
        }
      }
    }

    return mst;
  }
}
