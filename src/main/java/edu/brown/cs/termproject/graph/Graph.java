package edu.brown.cs.termproject.graph;

public interface Graph<V extends Vertex, E extends Edge<V>> {

  void addEdge(E edge);

}