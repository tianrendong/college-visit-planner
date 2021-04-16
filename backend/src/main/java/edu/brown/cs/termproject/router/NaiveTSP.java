package edu.brown.cs.termproject.router;

import com.google.maps.errors.ApiException;
import edu.brown.cs.termproject.locationgraph.Location;
import edu.brown.cs.termproject.locationgraph.LocationGraph;
import edu.brown.cs.termproject.locationgraph.Path;
import edu.brown.cs.termproject.graph.Edge;
import edu.brown.cs.termproject.graph.Graph;
import edu.brown.cs.termproject.graph.Vertex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
   * @param graph Minimum spanning tree of all the nodes to visit.
   * @param <V> type of the nodes that extends Vertex.
   * @param <E> type of the edge that extends Edge of V.
   * @return list in visiting order of the nodes.
   * @throws InterruptedException when interrupted.
   * @throws ApiException when google maps api errors.
   * @throws IOException when I/O errors.
   */
  public static <V extends Vertex, E extends Edge<V>> List<V> findRoute(Graph<V, E> graph)
      throws InterruptedException, ApiException, IOException {
    Comparator<E> comparator = new Comparator<>() {
      @Override
      public int compare(E o1, E o2) {
        return Double.compare(o1.getWeight(), o2.getWeight());
      }
    };
    Set<E> mst = MST.mst(graph, comparator);
    List<V> tsp = new ArrayList<>();
    for (E e : mst) {
      if (!tsp.contains(e.getStart())) {
        tsp.add(e.getStart());
      }
      if (!tsp.contains(e.getEnd())) {
        tsp.add(e.getEnd());
      }
    }
    return tsp;
  }

//  /**
//   * Finds all permutations and finds the shortest path.
//   * @param mst mst containing all nodes to visit.
//   * @param <T> type that extends Vertex.
//   * @return the order of visiting that is the most optimal.
//   */
//  public static <T extends Vertex> List<T> bruteForce(Set<? extends Edge<T>> mst)
//      throws InterruptedException, ApiException, IOException {
//    // Find all permutations
//    Set<List<T>> allPermutations = new HashSet<>();
//    List<T> allVertices = new ArrayList<>();
//    for (Edge<T> e : mst) {
//      if (!allVertices.contains(e.getStart())) {
//        allVertices.add(e.getStart());
//      }
//      if (!allVertices.contains(e.getEnd())) {
//        allVertices.add(e.getEnd());
//      }
//    }
//    permute(allVertices, 0, allVertices.size() - 1, allPermutations);
//
//    // Find the permutation with least cost
////    for (List<T> p : allPermutations) {
////      System.out.println(p);
////    }
//    System.out.println("Size of all permutations " + allPermutations.size());
//    List<T> result = null;
//    for (List<T> perm : allPermutations) {
//      if (result == null) {
//        result = perm;
//      } else {
//        double best = totalCost(result);
//        double cost = totalCost(perm);
//        System.out.println(best);
//        System.out.println(cost);
//        if (cost < best) {
//          result = perm;
//        }
//      }
//    }
//    return result;
//  }
//
//  private static <T extends Vertex> void permute(
//      List<T> list, int l, int r, Set<List<T>> accumulation) {
//    if (l == r) {
//      accumulation.add(list);
////      System.out.println(list);
//    } else {
//      for (int i = l; i <= r; i++) {
//        swap(list, l, i);
//        permute(list, l + 1, r, accumulation);
//        swap(list, l, i);
//      }
//    }
//  }
//
//  private static <T extends Vertex> void swap(List<T> list, int i, int j) {
//    T temp = list.get(i);
//    list.set(i, list.get(j));
//    list.set(j, temp);
//  }

  /**
   * Computes the total cost of a tsp route.
   * @param route to compute total cost.
   * @param completeGraph complete graph with all nodes in route.
   * @return total cost of the route.
   */
  public static double totalCost(List<Location> route, LocationGraph completeGraph) {
    double cost = 0;
    Map<Location, List<Path>> graph = completeGraph.getGraph();
    for (int i = 0; i < route.size() - 1; i++) {
      List<Path> outgoing = graph.get(route.get(i));
      for (Path p : outgoing) {
        if (p.getEnd().equals(route.get(i + 1))) {
          cost += p.getWeight();
        }
      }
    }
    Location first = route.get(route.size() - 1);
    List<Path> lastOutgoing = graph.get(first);
    for (Path p : lastOutgoing) {
      if (p.getEnd().equals(first)) {
        cost += p.getWeight();
      }
    }
    return cost;
  }

//  /**
//   * Computes the total cost of a tsp route.
//   * @param route to compute total cost.
//   * @param completeGraph complete graph with all nodes in route.
//   * @param <V> type that extends Vertex.
//   * @param <E> type that extends Edge of V.
//   * @return total cost of the route.
//   */
//  public static <V extends Vertex, E extends Edge<V>> double totalCost(
//      List<V> route, Graph<V, E> completeGraph) {
//    double total = 0;
//    try {
//      for (int i = 0; i < route.size() - 1; i++) {
//        V start = route.get(i);
//        V end = route.get(i + 1);
//        total += GoogleMapAPIManager.getTravelDistance(
//            start.getLat(), start.getLon(), end.getLat(), end.getLon());
//      }
//      total += GoogleMapAPIManager.getTravelDistance(
//          route.get(route.size() - 1).getLat(), route.get(route.size() - 1).getLon(),
//          route.get(0).getLat(), route.get(0).getLon());
//    } catch (Exception e) {
//      System.out.println("error");
//    }
//    return total;
//  }
}
