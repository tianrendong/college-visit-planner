package edu.brown.cs.termproject.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class for a generic graph.
 * @param <V> type that extends Vertex.
 * @param <E>type that extends Edge.
 */
public class GenericGraph<V extends Vertex, E extends Edge<V>> implements Graph<V, E> {
  private Set<E> edges;
  private final Map<V, List<E>> graph = new HashMap<>(); // graph stored as adjacency list
  private Set<V> vertices;

  /**
   * Constructor of the generic graph class.
   * @param e set of edges to construct the graph from.
   */
  public GenericGraph(Set<E> e) {
    this.edges = new HashSet<>(e);
    this.vertices = new HashSet<>();
    this.buildGraph(this.edges);
  }

  /**
   * Builds the graph.
   * @param edgeSet edge set.
   */
  public void buildGraph(Set<E> edgeSet) {
    for (E edge : edgeSet) {
      this.addEdge(edge);
    }
  }

  /**
   * Adds an edge.
   * @param edge edge to add
   */
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

  /**
   * Gets the vertices of graph.
   * @return set of vertices.
   */
  @Override
  public Set<V> getVertices() {
    return vertices;
  }

  /**
   * Gets the edges of graph.
   * @return set of edges.
   */
  @Override
  public Set<E> getEdges() {
    return edges;
  }


}
