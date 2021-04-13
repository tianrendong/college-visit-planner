package edu.brown.cs.termproject.pbt;

import com.google.maps.errors.ApiException;
import edu.brown.cs.termproject.collegegraph.Location;
import edu.brown.cs.termproject.collegegraph.LocationPath;
import edu.brown.cs.termproject.graph.Graph;
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
  public void pbt() {
    int i = 0;
    while (i < 1) {
      try {
        RandomGraphGenerator generator = new RandomGraphGenerator();
        Graph<Location, LocationPath> graph = generator.generateRandomGraph();
        System.out.println("GRAPH GENERATED");

        // Naive implementation approximation
        List<Location> naiveTSP = NaiveTSP.findRoute(graph);
        System.out.println("NAIVE FOUND");

        // Christofides Approximation
        TSP<Location, LocationPath> tsp = new TSP<>();
        List<Location> christofidesTSP = tsp.findRoute(graph);
        System.out.println("CHRISTOFIDES FOUND");

        // Compare distance
        // Christofides should be shorter than naive
        double naiveTotal = NaiveTSP.totalCost(naiveTSP);
        double christofidesTotal = NaiveTSP.totalCost(christofidesTSP);
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

//  @Test
//  public void testPermutation() {
//    try {
//      RandomGraphGenerator generator = new RandomGraphGenerator();
//      Graph<Location, LocationPath> graph = generator.generateRandomGraph();
//      Comparator<LocationPath> comparator = new Comparator<>() {
//        @Override
//        public int compare(LocationPath o1, LocationPath o2) {
//          return Double.compare(o1.getWeight(), o2.getWeight());
//        }
//      };
//      Set<LocationPath> mstEdges = MST.mst(graph, comparator);
//      NaiveTSP.bruteForce(mstEdges);
//    } catch (Exception e) {
//      System.out.println("ERRORED");
//    }
//  }
}
