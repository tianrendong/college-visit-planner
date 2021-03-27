package edu.brown.cs.termproject.router;

import com.google.maps.errors.ApiException;
import edu.brown.cs.termproject.collegegraph.CollegeGraph;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The class for Minimum Spanning Trees.
 */
public class MST {
  /**
   * Given a complete CollegeGraph, build a minimum spanning tree.
   * @param graph a complete CollegeGraph
   * @return minimum spanning tree formed from graph
   * @throws InterruptedException if errors occur during Google Map API queries
   * @throws ApiException if errors occur during Google Map API queries
   * @throws IOException if errors occur during Google Map API queries
   */
  public CollegeGraph primMST(CollegeGraph graph) throws InterruptedException, ApiException, IOException {
    CollegeGraph mst = new CollegeGraph(new ArrayList<>());
    // TODO: build mst with graph
    return mst;
  }


}
