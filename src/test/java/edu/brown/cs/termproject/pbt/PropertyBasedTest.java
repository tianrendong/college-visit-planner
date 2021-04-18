package edu.brown.cs.termproject.pbt;

import edu.brown.cs.termproject.locationgraph.Location;
import edu.brown.cs.termproject.locationgraph.LocationGraph;
import edu.brown.cs.termproject.locationgraph.Path;
import edu.brown.cs.termproject.router.NaiveTSP;
import edu.brown.cs.termproject.router.TSP;
import org.junit.Test;

import java.util.List;

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
        LocationGraph graph = generator.generateRandomGraph();
//        System.out.println("GRAPH GENERATED");

        // Naive implementation approximation
        List<Location> naiveTSP = NaiveTSP.findRoute(graph);
//        System.out.println("NAIVE FOUND");

        // Christofides Approximation
        TSP<Location, Path> tsp = new TSP<>();
        List<Location> christofidesTSP = tsp.findRoute(graph);
//        System.out.println("CHRISTOFIDES FOUND");

        // Compare distance
        // Christofides should be shorter than naive
        double naiveTotal = NaiveTSP.totalCost(naiveTSP, graph);
        double christofidesTotal = NaiveTSP.totalCost(christofidesTSP, graph);
//        System.out.println("TOTAL COSTS FOUND");
        if (naiveTotal >= christofidesTotal) {
          System.out.println("CORRECT\n");
        } else {
          System.out.println("WRONG: Naive is " + naiveTotal + ", Christofides is " + christofidesTotal);
//          System.out.println(graph + "\n");
        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
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
