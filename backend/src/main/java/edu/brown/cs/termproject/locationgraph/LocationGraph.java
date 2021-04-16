package edu.brown.cs.termproject.locationgraph;

import com.google.maps.errors.ApiException;
import edu.brown.cs.termproject.graph.Graph;
import edu.brown.cs.termproject.iotools.GoogleMapAPIManager;

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
public class LocationGraph implements Graph<Location, Path> {

  private final Map<Location, List<Path>> graph = new HashMap<>();
  private Set<Location> locations;

  /**
   * Initializes a complete college graph with a list of colleges.
   * @param locations a list of colleges
   * @throws InterruptedException if errors occur during Google Map requests
   * @throws ApiException if errors occur during Google Map requests
   * @throws IOException if errors occur during Google Map requests
   */
  public LocationGraph(List<Location> locations)
      throws InterruptedException, ApiException, IOException {
    this.locations = new HashSet<>(locations);
    buildCompleteGraph(locations);
  }

//  public LocationGraph(List<T> vertices)
//      throws InterruptedException, ApiException, IOException {
//    List<LocationWrapper> locations = new ArrayList<>();
//    for (Vertex loc : vertices) {
//      locations.add(new LocationWrapper(loc.getName(), loc.getLat(), loc.getLon(), loc));
//    }
//    this.locations = new HashSet<>(locations);
//    buildCompleteGraph(locations);
//  }


  private void buildCompleteGraph(List<Location> allLocations)
      throws InterruptedException, ApiException, IOException {
    for (int i = 0; i < allLocations.size(); i++) {
      for (int j = i + 1; j < allLocations.size(); j++) {
        Location start = allLocations.get(i);
        Location end = allLocations.get(j);

        //get optimal distance between colleges.get(i) and colleges.get(j) using Google Maps API
        double distance = GoogleMapAPIManager.getTravelDistance(
            start.getLat(), start.getLon(), end.getLat(), end.getLon());

        // add path and reversed path to current graph
        addEdge(new Path(start, end, distance));
        addEdge(new Path(end, start, distance));
      }

    }
  }

  /**
   * Gets the graph.
   * @return graph.
   */
  public Map<Location, List<Path>> getGraph() {
    return graph;
  }

  @Override
  public void addEdge(Path p) {
    Location start = p.getStart();
    Location end = p.getEnd();
    locations.add(start);
    locations.add(end);
    if (graph.containsKey(start)) {
      graph.get(start).add(p);
    } else {
      graph.put(start, new ArrayList<>(Arrays.asList(p)));
    }
  }

  @Override
  public Set<Location> getVertices() {
    return locations;
  }

  @Override
  public Set<Path> getEdges() {
    Set<Path> edges = new HashSet<>();
    for (Map.Entry<Location, List<Path>> entry : graph.entrySet()) {
      edges.addAll(entry.getValue());
    }
    return edges;
  }

  /**
   * Adds a new college to the graph
   * by extending an edge from the new college to every existing college.
   * @param newLocation new college to add
   * @throws InterruptedException if errors occur during Google Map requests
   * @throws ApiException if errors occur during Google Map requests
   * @throws IOException if errors occur during Google Map requests
   */
  public void addNode(Location newLocation) throws InterruptedException, ApiException, IOException {
    Set<Location> allLocations = new HashSet<>(graph.keySet());
    // add a path from this node to every other node
    for (Location c : allLocations) {
      double distance = GoogleMapAPIManager.getTravelDistance(
          newLocation.getLat(), newLocation.getLon(), c.getLat(), c.getLon());
      addEdge(new Path(newLocation, c, distance));
      addEdge(new Path(c, newLocation, distance));
    }
  }

//  public void addNode(T newVertex) throws InterruptedException, ApiException, IOException {
//    Set<LocationWrapper> allLocations = new HashSet<>(graph.keySet());
//    System.out.println(allLocations);
//    LocationWrapper newLoc =
//    new LocationWrapper(newVertex.getName(), newVertex.getLat(), newVertex.getLon(), newVertex);
//    // add a path from this node to every other node
//    for (LocationWrapper loc : allLocations) {
//      System.out.println(loc);
//      double distance = GoogleMapAPIManager.getTravelDistance(
//          newLoc.getLat(), newLoc.getLon(), loc.getLat(), loc.getLon());
//      addEdge(new LocationPath(newLoc, loc, distance));
//      addEdge(new LocationPath(loc, newLoc, distance));
//    }
//  }

  @Override
  public String toString() {
    String output = "";
    List<Location> allLocations = new ArrayList<>(graph.keySet());
    for (int i = 0; i < allLocations.size(); i++) {
      List<Path> paths = graph.get(allLocations.get(i));
      for (int j = 0; j < paths.size(); j++) {
        Path p = paths.get(j);
        output += p.getStart().getName() + " -> "
            + p.getEnd().getName() + " : " + p.getWeight();
        output += (j < paths.size()) ? "\n" : "";
      }
      output += (i < allLocations.size()) ? "\n" : "";
    }
    return output;
  }
}

