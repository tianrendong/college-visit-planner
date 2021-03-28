package edu.brown.cs.termproject.router;

import com.google.maps.errors.ApiException;
import edu.brown.cs.termproject.collegegraph.CollegeGraph;
import edu.brown.cs.termproject.collegegraph.Path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * The class for Minimum Spanning Trees.
 */
public final class MST {

  private MST() {

  }

  /**
   * Given a complete CollegeGraph, build a minimum spanning tree.
   * @param graph a complete CollegeGraph
   * @return minimum spanning tree formed from graph
   * @throws InterruptedException if errors occur during Google Map API queries
   * @throws ApiException if errors occur during Google Map API queries
   * @throws IOException if errors occur during Google Map API queries
   */
  public static CollegeGraph primMST(CollegeGraph graph)
      throws InterruptedException, ApiException, IOException {
    CollegeGraph mst = new CollegeGraph(new ArrayList<>());
    Set<Path> edges = graph.getEdges();

    PriorityQueue<Path> pqEdges = new PriorityQueue<>(new Comparator<Path>() {
      @Override
      public int compare(Path o1, Path o2) {
        return Double.compare(o1.getWeight(), o2.getWeight());
      }
    });
    pqEdges.addAll(edges);

    while (!mst.getVertices().equals(graph.getVertices())) {
      Path lightest = pqEdges.poll();
      mst.addEdge(lightest);
    }
    return mst;
  }
}
