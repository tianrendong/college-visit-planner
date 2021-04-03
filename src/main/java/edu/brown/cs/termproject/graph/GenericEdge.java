package edu.brown.cs.termproject.graph;

import edu.brown.cs.termproject.collegegraph.College;

public class GenericEdge<V extends Vertex> implements Edge<V>{

    private V start;
    private V end;
    private double weight;

    /**
     * Constructor for Path between colleges.
     * @param start start College.
     * @param end end College.
     * @param weight weight of the Path.
     */
    public GenericEdge(V start, V end, double weight) {
      this.start = start;
      this.end = end;
      this.weight = weight;
    }

    @Override
    public V getStart() {
      return start;
    }

    @Override
    public V getEnd() {
      return end;
    }

    @Override
    public double getWeight() {
      return weight;
    }

    /**
     * Sets the weight of the Path to w.
     * @param w new weight of the Path.
     */
    public void setWeight(double w) {
      weight = w;
    }
}
