package edu.brown.cs.termproject.router;

import com.google.maps.errors.ApiException;
import edu.brown.cs.termproject.collegegraph.College;
import edu.brown.cs.termproject.collegegraph.CollegeGraph;
import edu.brown.cs.termproject.collegegraph.Path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * The class for Traveling Salesman Problem algorithm.
 */
public class TSP {
  public TSP(){}

  /**
   * Finds the route that visits all the Locatables in locations once.
   * @param g compelte CollegeGraph
   * @return List of Locatable in the optimal visiting order.
   */
  public List<Locatable> findRoute(CollegeGraph g) throws InterruptedException, ApiException, IOException {
    List<Locatable> route = new ArrayList<>();
    Comparator<Path> comp = new Comparator<Path>() {
      @Override
      public int compare(Path o1, Path o2) {
        return Double.compare(o1.getWeight(), o2.getWeight());
      }
    };
    Set<Path> mst = MST.mst(g,comp);

    return route;
  }

  private class tspGraph{
    CollegeGraph initGraph;
    private tspGraph(CollegeGraph g){
      initGraph = g;
    }

    public List<College> getOddDegreeVertices(){
      Set<College> colleges = initGraph.getVertices();
      Set<Path> edges = initGraph.getEdges();
      int numVertices = colleges.size();
      HashMap<College, Integer> numNeighbors = new HashMap<>(numVertices);
      Iterator<Path> it = edges.iterator();
      while(it.hasNext()){
        Path next = it.next();
        College src = next.getStart();
        if(numNeighbors.containsKey(src)){
          numNeighbors.replace(src, 1 + numNeighbors.get(src));
        }else{
          numNeighbors.put(src, 1);
        }
      }
      ArrayList<Path> newEdges = new ArrayList<Path>();
      List<College> oddDegreeVertices = new ArrayList<College>();
      for(College c : colleges) {
        if(numNeighbors.get(c) % 2 == 1) {
          oddDegreeVertices.add(c);
        }
      }
      return oddDegreeVertices;
    }


  }

  }


