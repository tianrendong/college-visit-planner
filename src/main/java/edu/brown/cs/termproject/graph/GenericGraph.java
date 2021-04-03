package edu.brown.cs.termproject.graph;

import com.google.maps.errors.ApiException;
import edu.brown.cs.termproject.collegegraph.College;
import edu.brown.cs.termproject.collegegraph.Path;
import edu.brown.cs.termproject.main.GoogleMapAPIManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GenericGraph<V extends Vertex, E extends Edge<V>> implements Graph<V,E>{
  private Set<E> edges;
  private final Map<V, List<E>> graph = new HashMap<>(); // graph stored as adjacency list
  private Set<V> vertices;

  public GenericGraph(Set<E> e){
    this.edges = new HashSet<>(e);
    this.vertices = new HashSet<>();
    this.buildGraph(this.edges);
  }

    public void buildGraph(Set<E> edges) {
      for(E edge : edges){
        this.addEdge(edge);
      }
    }

  @Override
  public void addEdge(E edge) {
    V start = edge.getStart();
    V end = edge.getEnd();
    vertices.add(start);
    vertices.add(end);
    if (graph.containsKey(start)) {
      graph.get(start).add(edge);
    } else {
      graph.put(start, new ArrayList<>(Arrays.asList(edge)));
    }
  }

  @Override
  public Set<V> getVertices() {
    return vertices;
  }

  @Override
  public Set<E> getEdges() {
    return edges;
  }


}
