package edu.brown.cs.termproject.router;

/**
 * Class for union find.
 * @param <T> Type of value the UnionFind stores.
 */
public class UnionFind<T> {

  private UnionFind<T> root;
  private T value;

  /**
   * Constructs a union find with value v.
   * @param v value of the UnionFind object.
   */
  public UnionFind(T v) {
    root = null;
    value = v;
  }

  /**
   * Gets the root.
   * @return root.
   */
  public UnionFind<T> getRoot() {
    return root;
  }

  /**
   * Gets the value.
   * @return value.
   */
  public T getValue() {
    return value;
  }

  /**
   * Finds the final root of the object.
   * @return ultimate root.
   */
  public UnionFind<T> find() {
    if (getRoot() != null) {
      return getRoot().find();
    }
    return this;
  }

  /**
   * Sets the root to another element.
   * @param element new root to set.
   */
  public void setRoot(UnionFind element) {
    root = element;
  }

  /**
   * Unions the object with other by making
   * this as other's root.
   * @param other other UnionFind object.
   */
  public void union(UnionFind other) {
    UnionFind thisRoot = this.find();
    UnionFind otherRoot = other.find();
    otherRoot.setRoot(thisRoot);
  }
}
