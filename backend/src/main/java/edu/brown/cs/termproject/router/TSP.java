package edu.brown.cs.termproject.router;

import com.google.maps.errors.ApiException;
import edu.brown.cs.termproject.graph.Edge;
import edu.brown.cs.termproject.graph.GenericEdge;
import edu.brown.cs.termproject.graph.GenericGraph;
import edu.brown.cs.termproject.graph.Graph;
import edu.brown.cs.termproject.graph.Vertex;
import edu.brown.cs.termproject.iotools.GoogleMapAPIManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * The class for Traveling Salesman Problem algorithm.
 * @param <V> type that extends Vertex.
 * @param <E> type that extends Edge.
 */
public class TSP<V extends Vertex, E extends Edge<V>> {
  /**
   * Constructor for TSP.
   */
  public TSP() {
  }

  /**
   * Finds the route that visits all the Locatables in locations once.
   *
   * @param g complete CollegeGraph
   * @return List of Locatable in the optimal visiting order.
   * @throws InterruptedException when interrupted.
   * @throws ApiException when google maps api errored.
   * @throws IOException when errored.
   */
  public List<V> findRoute(Graph<V, E> g) throws InterruptedException, ApiException, IOException {
    Comparator<E> comp = new Comparator<E>() {
      @Override
      public int compare(E o1, E o2) {
        return Double.compare(o1.getWeight(), o2.getWeight());
      }
    };
    if (g.getVertices().size() == 1) {
      List<V> list = new ArrayList<>();
      Set<V> verts = g.getVertices();
      for (V v : verts) {
        list.add(v);
      }
      return list;
    }
    Set<E> mst = MST.mst(g, comp);
    // Generate MST
    GenericGraph<V, E> mstGraph = new GenericGraph<>(mst);
    TSPGraph<V, E> tsp = new TSPGraph<>(mstGraph);
    // Get the set of odd degree vertices from MST
    // & form a minimum-weight perfect matching graph
    // Combine the edges of the MST with the minimum-weight
    // perfect matching graph
    Set<GenericEdge<V>> matches = tsp.perfectMatches();
    GenericGraph<V, GenericEdge<V>> graph = new GenericGraph<V, GenericEdge<V>>(matches);
    TSPGraph<V, GenericEdge<V>> tspMatches = new TSPGraph<V, GenericEdge<V>>(graph);
    ArrayList<GenericEdge<V>> list = new ArrayList<>();
    for (GenericEdge e : matches) {
      list.add(e);
    }
    for (int i = 1; i < matches.size(); i++) {
      tspMatches.addEdge(list.get(i).getStart(), list.get(i).getEnd());
    }
    // Form a Eulerian circuit
    // Skip repeated vertices to form a Hamiltonian circuit
    return tspMatches.createEulerCircuit();
  }

  /**
   * Class representing a graph of TSP.
   * @param <V> type that extends Vertex.
   * @param <E> type that extends Edge.
   */
  private static final class TSPGraph<V extends Vertex, E extends Edge<V>> {
    private GenericGraph initGraph;
    private ArrayList<V> eulerianCircuit = new ArrayList<>();
    private HashMap<V, ArrayList<V>> adj;
    private HashMap<V, Boolean> isVisited;

    private TSPGraph(GenericGraph<V, E> g) {
      this.initGraph = g;
      Set<V> verts = g.getVertices();
      this.adj = new HashMap<>();
      Set<E> edges = g.getEdges();
      for (V v : verts) {
        this.adj.put(v, new ArrayList<V>());
      }
      for (E edge : edges) {
        V start = edge.getStart();
        V end = edge.getEnd();
        ArrayList<V> curr1 = this.adj.get(start);
        ArrayList<V> curr2 = this.adj.get(end);
        curr1.add(end);
        curr2.add(start);
        this.adj.replace(start, curr1);
        this.adj.replace(end, curr2);
      }
    }

    private int numVertices() {
      return this.initGraph.getVertices().size();
    }


    private List<V> getOddDegreeVertices() {
      Set<V> verts = this.initGraph.getVertices();
      HashMap<V, Integer> numNeighbors = this.numNeighbors();
      List<V> oddDegreeVertices = new ArrayList<>();
      for (V v : verts) {
        if (numNeighbors.get(v) % 2 == 1) {
          oddDegreeVertices.add(v);
        }
      }
      return oddDegreeVertices;
    }

    private HashMap<V, Integer> numNeighbors() {
      Set<E> edges = this.initGraph.getEdges();
      int numVertices = this.numVertices();
      HashMap<V, Integer> numNeighbors = new HashMap<>(numVertices);
      Iterator<E> it = edges.iterator();
      while (it.hasNext()) {
        E next = it.next();
        V src = next.getStart();
        V dst = next.getEnd();
        if (numNeighbors.containsKey(src)) {
          numNeighbors.replace(src, 1 + numNeighbors.get(src));
        } else {
          numNeighbors.put(src, 1);
        }
        if (numNeighbors.containsKey(dst)) {
          numNeighbors.replace(dst, 1 + numNeighbors.get(dst));
        } else {
          numNeighbors.put(dst, 1);
        }
      }
      return numNeighbors;
    }

    /**
     * Generates a graph where every vertex has an even degree.
     * First gets the set of odd degree vertices from MST
     * & fors a minimum-weight perfect matching graph.
     * Then combines the edges of the MST with the minimum-weight
     * perfect matching graph
     * @return Set of edges representing perfect matching graph
     * @throws InterruptedException
     * @throws ApiException
     * @throws IOException
     */
    public Set<GenericEdge<V>> perfectMatches()
        throws InterruptedException, ApiException, IOException {
      ArrayList<GenericEdge<V>> newEdges = new ArrayList<>();
      List<V> odd = this.getOddDegreeVertices();
      Set<E> mst = this.initGraph.getEdges();
      ArrayList<GenericEdge<V>> newedges = findMatches(odd, newEdges);
      Set<GenericEdge<V>> result = new HashSet<>();
      for (GenericEdge<V> edge : newedges) {
        result.add(edge);
      }
      for (E edge : mst) {
        GenericEdge<V> e = new GenericEdge(edge.getStart(), edge.getEnd(), edge.getWeight());
        result.add(e);
      }
      return result;
    }

    private ArrayList<GenericEdge<V>> findMatches(
        List<V> oddDegreeVertices, ArrayList<GenericEdge<V>> newEdges)
        throws InterruptedException, ApiException, IOException {
      double distance = 0.0, min = Double.MAX_VALUE;
      int nextIndex = 0, indexForRemove = 0;
      GenericEdge<V> currEdge;
      V curr, curr2;
      for (int i = 0; i < oddDegreeVertices.size(); i = nextIndex) {
        curr = oddDegreeVertices.get(i);
        oddDegreeVertices.remove(i);

        for (int k = 0; k < oddDegreeVertices.size(); k++) {
          curr2 = oddDegreeVertices.get(k);

          distance = GoogleMapAPIManager.getTravelDistance(
              curr.getLat(), curr.getLon(), curr2.getLat(), curr2.getLon());

          if (distance < min) {
            min = distance;
            nextIndex = 0;
            indexForRemove = k;
          }
        }

        curr2 = oddDegreeVertices.get(indexForRemove);
        currEdge = new GenericEdge<V>(curr, curr2, GoogleMapAPIManager.getTravelDistance(
            curr.getLat(), curr.getLon(), curr2.getLat(), curr2.getLon()));
        newEdges.add(currEdge);

        min = Integer.MAX_VALUE;
        oddDegreeVertices.remove(indexForRemove);

        if (oddDegreeVertices.size() == 2) {
          V one = oddDegreeVertices.get(0);
          V two = oddDegreeVertices.get(1);
          currEdge = new GenericEdge(one, two, GoogleMapAPIManager.getTravelDistance(
              one.getLat(), one.getLon(), two.getLat(), two.getLon()));
          newEdges.add(currEdge);
          break;
        }
      }
      return newEdges;
    }

    private void removeEdge(V u, V v) {
      ArrayList<V> arr = adj.get(u);
      arr.remove(v);
      adj.replace(u, arr);
      ArrayList<V> arr2 = adj.get(v);
      arr2.remove(u);
      adj.replace(v, arr2);
    }

    private void addEdge(V u, V v) {
      ArrayList<V> arr = adj.get(u);
      arr.add(v);
      adj.replace(u, arr);
      ArrayList<V> arr2 = adj.get(v);
      arr2.add(u);
      adj.replace(v, arr2);
    }

    /**
     * Creates a Eulerian circuit from
     * the existing graph. This visits every
     * edge once, but has repeat vertices
     * @return list of vertices
     */
    public ArrayList<V> createEulerCircuit() {
      HashMap<V, Integer> neighbors = this.numNeighbors();
      V firstOdd = null;
      for (V key : neighbors.keySet()) {
        if (adj.get(key).size() % 2 == 1) {
          firstOdd = key;
          break;
        }
      }
      this.eulerUtil(firstOdd);
      ArrayList<V> result = this.clearRepeats();
      return result;
    }

    private void eulerUtil(V vert) {
      for (int i = 0; i < this.adj.get(vert).size(); i++) {
        V v = this.adj.get(vert).get(i);
        if (isValidNextEdge(vert, v)) {
          this.eulerianCircuit.add(vert);
          this.eulerianCircuit.add(v);
          removeEdge(vert, v);
          this.eulerUtil(v);
        }
      }
    }

    private boolean isValidNextEdge(V u, V v) {

      if (this.adj.get(u).size() == 1) {
        return true;
      }
      this.isVisited = new HashMap<V, Boolean>();
      int count1 = dfsCount(u);
      this.removeEdge(u, v);
      this.isVisited = new HashMap<V, Boolean>();
      int count2 = dfsCount(u);
      this.addEdge(u, v);
      return (count1 > count2) ? false : true;
    }

    private int dfsCount(V s) {
      int count = 0;
      Stack<V> stack = new Stack<>();

      stack.push(s);

      while (!stack.empty()) {
        s = stack.pop();


        if (!this.isVisited.containsKey(s)) {
          this.isVisited.put(s, true);
          count++;
        }

        Iterator<V> itr = adj.get(s).iterator();

        while (itr.hasNext()) {
          V v = itr.next();
          if (!this.isVisited.containsKey(v)) {
            stack.push(v);
          }
        }
      }
      return count;
    }

    private ArrayList<V> clearRepeats() {
      ArrayList<V> verts = this.eulerianCircuit;
      HashMap<V, Integer> vertsArray = new HashMap<>();
      ArrayList<V> resultCircuit = new ArrayList<V>();
      for (int i = 0; i < verts.size(); i++) {
        if (!vertsArray.containsKey(verts.get(i))) {
          vertsArray.put(verts.get(i), 1);
          resultCircuit.add(verts.get(i));
        }
      }
      return resultCircuit;
    }
  }

}

