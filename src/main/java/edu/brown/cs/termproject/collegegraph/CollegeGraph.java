package edu.brown.cs.termproject.collegegraph;

import com.google.maps.errors.ApiException;
import edu.brown.cs.termproject.graph.Graph;
import edu.brown.cs.termproject.main.GoogleMapAPIManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class for an undirected college graph.
 */
public class CollegeGraph implements Graph<College, Path> {

  private final Map<College, List<Path>> graph = new HashMap<>(); // graph stored as adjacency list
  private Set<College> colleges;

  /**
   * Initializes a complete college graph with a list of colleges.
   * @param colleges a list of colleges
   * @throws InterruptedException if errors occur during Google Map requests
   * @throws ApiException if errors occur during Google Map requests
   * @throws IOException if errors occur during Google Map requests
   */
  public CollegeGraph(List<College> colleges)
      throws InterruptedException, ApiException, IOException {
    this.colleges = new HashSet<>(colleges);
    buildCompleteGraph(colleges);
  }


  private void buildCompleteGraph(List<College> allColleges)
      throws InterruptedException, ApiException, IOException {
    for (int i = 0; i < allColleges.size(); i++) {
      for (int j = i + 1; j < allColleges.size(); j++) {
        College start = allColleges.get(i);
        College end = allColleges.get(j);

        //get optimal distance between colleges.get(i) and colleges.get(j) using Google Maps API
        double distance = GoogleMapAPIManager.getTravelDistance(
            start.getLat(), start.getLon(), end.getLat(), end.getLon());

        // add path and reversed path to current graph
        addEdge(new Path(start, end, distance));
        addEdge(new Path(end, start, distance));
      }

    }
  }

  @Override
  public void addEdge(Path p) {
    College start = p.getStart();
    College end = p.getEnd();
    colleges.add(start);
    colleges.add(end);
    if (graph.containsKey(start)) {
      graph.get(start).add(p);
    } else {
      graph.put(start, new ArrayList<>(Arrays.asList(p)));
    }
  }

  @Override
  public Set<College> getVertices() {
    return colleges;
  }

  @Override
  public Set<Path> getEdges() {
    Set<Path> edges = new HashSet<>();
    for (Map.Entry<College, List<Path>> entry : graph.entrySet()) {
      edges.addAll(entry.getValue());
    }
    return edges;
  }

  /**
   * Adds a new college to the graph
   * by extending an edge from the new college to every existing college.
   * @param newCollege new college to add
   * @throws InterruptedException if errors occur during Google Map requests
   * @throws ApiException if errors occur during Google Map requests
   * @throws IOException if errors occur during Google Map requests
   */
  public void addNode(College newCollege) throws InterruptedException, ApiException, IOException {
    Set<College> allColleges = graph.keySet();
    // add a path from this node to every other node
    for (College c : allColleges) {
      double distance = GoogleMapAPIManager.getTravelDistance(
          newCollege.getLat(), newCollege.getLon(), c.getLat(), c.getLon());
      addEdge(new Path(newCollege, c, distance));
      addEdge(new Path(c, newCollege, distance));
    }
  }

  @Override
  public String toString() {
    String output = "";
    List<College> allColleges = new ArrayList<>(graph.keySet());
    for (int i = 0; i < allColleges.size(); i++) {
      List<Path> paths = graph.get(allColleges.get(i));
      for (int j = 0; j < paths.size(); j++) {
        Path p = paths.get(j);
        output += p.getStart().getName() + " -> "
            + p.getEnd().getName() + " : " + p.getWeight();
        output += (j < paths.size()) ? "\n" : "";
      }
      output += (i < allColleges.size()) ? "\n" : "";
    }
    return output;
  }
}

