package edu.brown.cs.termproject.pbt;

import com.google.maps.errors.ApiException;
import edu.brown.cs.termproject.collegegraph.Location;
import edu.brown.cs.termproject.collegegraph.LocationPath;
import edu.brown.cs.termproject.graph.Graph;
import edu.brown.cs.termproject.main.GoogleMapAPIManager;
import edu.brown.cs.termproject.router.MST;
import edu.brown.cs.termproject.router.NaiveTSP;
import edu.brown.cs.termproject.router.TSP;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Property based testing TSP using NaiveTSP.
 */
public class PropertyBasedTest {
  @Test
  public void pbt() throws SQLException, InterruptedException, ApiException, IOException {
    int i = 0;
    while (i < 1) {
      try {
        RandomGraphGenerator generator = new RandomGraphGenerator();
        Graph<Location, LocationPath> graph = generator.generateRandomGraph();
        System.out.println("GRAPH GENERATED");

        // Naive implementation approximation
        Comparator<LocationPath> comparator = new Comparator<>() {
          @Override
          public int compare(LocationPath o1, LocationPath o2) {
            return Double.compare(o1.getWeight(), o2.getWeight());
          }
        };
        Set<LocationPath> mstEdges = MST.mst(graph, comparator);
        List<Location> naiveTSP = NaiveTSP.findRoute(mstEdges);
        System.out.println("NAIVE FOUND");

        // Christofides Approximation
        TSP<Location, LocationPath> tsp = new TSP<>();
        List<Location> christofidesTSP = tsp.findRoute(graph);
        System.out.println("CHRISTOFIDES FOUND");

        // Compare distance
        // Christofides should be shorter than naive
        double naiveTotal = totalCost(naiveTSP);
        double christofidesTotal = totalCost(christofidesTSP);
        System.out.println("TOTAL COSTS FOUND");
        if (naiveTotal >= christofidesTotal) {
          System.out.println("CORRECT\n");
        } else {
          System.out.println("WRONG: Naive is " + naiveTotal + ", Christofides is " + christofidesTotal);
          System.out.println(graph + "\n");
        }
      } catch (Exception e) {
        System.out.println("ERRORED");
      }
      i++;
    }
  }

  private double totalCost(List<Location> route)
      throws InterruptedException, ApiException, IOException {
    double total = 0;
    for (int i = 0; i < route.size() - 1; i++) {
      Location start = route.get(i);
      Location end = route.get(i + 1);
      total += GoogleMapAPIManager.getTravelDistance(
          start.getLat(), start.getLon(), end.getLat(), end.getLon());
    }
    total += GoogleMapAPIManager.getTravelDistance(
        route.get(route.size() - 1).getLat(), route.get(route.size() - 1).getLon(),
        route.get(0).getLat(), route.get(0).getLon());
    return total;
  }
}
