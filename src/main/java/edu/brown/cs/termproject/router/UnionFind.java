package edu.brown.cs.termproject.router;

public class UnionFind<T> {

  private UnionFind<T> root;
  private T value;

  UnionFind(T v) {
    root = null;
    value = v;
  }

  public UnionFind<T> getRoot() {
    return root;
  }

  public T getValue() {
    return value;
  }

  public UnionFind<T> find(UnionFind<T> element) {
    if (element.getRoot() != null) {
      return find(element.getRoot());
    }
    return element;
  }

  public void setRoot(UnionFind element) {
    root = element;
  }

  public void union(UnionFind other) {
    UnionFind thisRoot = find(this);
    UnionFind otherRoot = find(other);
    otherRoot.setRoot(thisRoot);
  }
}
