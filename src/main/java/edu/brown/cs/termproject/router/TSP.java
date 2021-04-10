package edu.brown.cs.termproject.router;

import com.google.maps.errors.ApiException;
import edu.brown.cs.termproject.collegegraph.Path;
import edu.brown.cs.termproject.graph.*;
import edu.brown.cs.termproject.main.GoogleMapAPIManager;

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
 */
public class TSP<V extends Vertex, E extends Edge<V>> {
  public TSP() {}

  /**
   * Finds the route that visits all the Locatables in locations once.
   * @param g complete CollegeGraph
   * @return List of Locatable in the optimal visiting order.
   */
  public List<V> findRoute(Graph<V, E> g) throws InterruptedException, ApiException, IOException {
    //List<Locatable> route = new ArrayList<>();
    Comparator<E> comp = new Comparator<E>() {
      @Override
      public int compare(E o1, E o2) {
        return Double.compare(o1.getWeight(), o2.getWeight());
      }
    };
    Set<E> mst = MST.mst(g, comp);
    GenericGraph<V, E> mstGraph = new GenericGraph<>(mst);
    TSPGraph<V, E> tsp = new TSPGraph<>(mstGraph);
    Set<GenericEdge<V>> matches = tsp.perfectMatches(g.getVertices());
    GenericGraph<V, GenericEdge<V>> graph = new GenericGraph<V, GenericEdge<V>>(matches);
    TSPGraph<V, GenericEdge<V>> tspMatches = new TSPGraph<V, GenericEdge<V>>(graph);
    return tspMatches.createEulerCircuit();
  }

  private class TSPGraph<V extends Vertex, E extends Edge<V>> {
    private GenericGraph initGraph;
    private ArrayList<V> eulerianCircuit = new ArrayList<>();
    private HashMap<V, ArrayList<V>> adj;
    private HashMap<V, Boolean> isVisited = new HashMap<V, Boolean>();

    private TSPGraph(GenericGraph<V, E> g) {
      this.initGraph = g;
      Set<V> verts = g.getVertices();
      int V = verts.size();
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

    public int numVertices() {
      return this.initGraph.getVertices().size();
    }

    public int numEdges() {
      return this.initGraph.getEdges().size();
    }

    public List<V> getOddDegreeVertices() {
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

    public HashMap<V, Integer> numNeighbors() {
      Set<E> edges = this.initGraph.getEdges();
      int numVertices = this.numVertices();
      HashMap<V, Integer> numNeighbors = new HashMap<>(numVertices);
      Iterator<E> it = edges.iterator();
      while (it.hasNext()) {
        E next = it.next();
        V src = next.getStart();
        if (numNeighbors.containsKey(src)) {
          numNeighbors.replace(src, 1 + numNeighbors.get(src));
        } else {
          numNeighbors.put(src, 1);
        }
      }
      return numNeighbors;
    }

    Set<GenericEdge<V>> perfectMatches(Set<V> allVerts) throws InterruptedException, ApiException, IOException {
      ArrayList<GenericEdge<V>> newEdges = new ArrayList<>();
      List<V> odd = this.getOddDegreeVertices();
      Set<E> mst = this.initGraph.getEdges();
      ArrayList<GenericEdge<V>> newedges = findMatches(odd, newEdges);

      ArrayList<GenericEdge<V>> result = new ArrayList<>();
      for (GenericEdge<V> edge : newedges) {
        result.add(edge);
      }
      for (E edge : mst) {
        GenericEdge<V> e = new GenericEdge(edge.getStart(), edge.getEnd(), edge.getWeight());
        result.add(e);
      }

      HashMap<V, Integer> numNeighbors = new HashMap<>();
      Set<GenericEdge<V>> r = new HashSet<>();
      Iterator<GenericEdge<V>> it = result.iterator();
      while (it.hasNext()) {
        GenericEdge<V> next = it.next();
        r.add(next);
        V src = next.getStart();
        if (numNeighbors.containsKey(src)) {
          numNeighbors.replace(src, 1 + numNeighbors.get(src));
        } else {
          numNeighbors.put(src, 1);
        }
      }
      for (GenericEdge<V> e : result) {
        r.add(e);
      }
      return r;
    }


    public ArrayList<GenericEdge<V>> findMatches(List<V> oddDegreeVertices, ArrayList<GenericEdge<V>> newEdges) throws InterruptedException, ApiException, IOException {
      double distance = 0.0, min = Double.MAX_VALUE;
      int nextIndex = 0, indexForRemove = 0;
      GenericEdge currEdge;
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

    void removeEdge(V u, V v) {
      ArrayList<V> arr = adj.get(u);
      arr.remove(v);
      adj.replace(u, arr);
      ArrayList<V> arr2 = adj.get(v);
      arr2.remove(u);
      adj.replace(v, arr2);
    }

    void addEdge(V u, V v) {
      ArrayList<V> arr = adj.get(u);
      arr.add(v);
      adj.replace(u, arr);
      ArrayList<V> arr2 = adj.get(v);
      arr2.add(u);
      adj.replace(u, arr2);
    }


    public ArrayList<V> createEulerCircuit() {
      HashMap<V, Integer> neighbors = this.numNeighbors();
      V first_odd = null;
      for (V key : neighbors.keySet()) {
        if (neighbors.get(key) % 2 == 1) {
          first_odd = key;
          break;
        }
      }
      eulerUtil(first_odd);
      ArrayList<V> result = this.clearRepeats();
      return result;
    }

    void eulerUtil(V vert) {
      for (int i = 0; i < this.adj.get(vert).size(); i++) {
        V v = this.adj.get(vert).get(i);
        if (isValidNextEdge(vert, v)) {
          eulerianCircuit.add(vert);
          eulerianCircuit.add(v);
          removeEdge(vert, v);
          eulerUtil(v);
        }
      }
    }

    private boolean isValidNextEdge(V u, V v) {

      if (this.adj.get(u).size() == 1) {
        return true;
      }
      int count1 = dfsCount(u, this.isVisited);

      removeEdge(u, v);
      int count2 = dfsCount(u, this.isVisited);

      addEdge(u, v);
      return (count1 > count2) ? false : true;
    }

    int dfsCount(V s, HashMap<V, Boolean> isVisited) {
      int count=0;
      Stack<V> stack = new Stack<>();

      stack.push(s);

      while(stack.empty() == false)
      {
        s = stack.peek();
        stack.pop();

        if(isVisited.get(s) == false)
        {
          isVisited.put(s, true);
          count++;
        }

        Iterator<V> itr = adj.get(s).iterator();

        while (itr.hasNext())
        {
          V v = itr.next();
          if(!isVisited.get(v)) {
            stack.push(v);
          }
        }
      }
      return count;
    }

    ArrayList<V> clearRepeats() {
      ArrayList<V> verts = this.eulerianCircuit;
      HashMap<V, Integer> vertsArray = new HashMap<>();
      ArrayList<V> resultCircuit = new ArrayList<V>();
      for(int i=0; i<verts.size(); i++) {
        int v = vertsArray.get(verts.get(i));
        v++;
        vertsArray.replace(verts.get(i), v);
        if(vertsArray.get(verts.get(i)) == 1) {
          resultCircuit.add(verts.get(i));
        }
      }
      resultCircuit.add(resultCircuit.get(0));
      return resultCircuit;
    }
  }

}

