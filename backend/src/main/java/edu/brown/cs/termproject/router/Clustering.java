package edu.brown.cs.termproject.router;

import edu.brown.cs.termproject.iotools.CenterCalculator;
import edu.brown.cs.termproject.iotools.DistanceCalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * The class for clustering Locatables.
 * @param <L> locatable object.
 */
public class Clustering<L extends Locatable> {

  private final double maxDistance; // in kilometers
  private static final int RADIAN = 180;

  /**
   * Constructs a clustering obejct.
   * @param maxDistance maximum distance from each point to the centroid.
   */
  public Clustering(double maxDistance) {
    this.maxDistance = maxDistance;
  }

  /**
   * Creates clusters from a list of Locatable.
   * @param locations list of Locatable.
   * @return Set of clusters represented as set of Locatable.
   */
  public Map<Point, List<L>> makeClusters(List<L> locations) {
    Map<Point, List<L>> clusters = new HashMap<>();
    // iterate through location list
    for (L curLoc : locations) {
      if (clusters.isEmpty()) {
        List<L> newList = new ArrayList<>();
        newList.add(curLoc);
        clusters.put(new Point(curLoc.getLat(), curLoc.getLon()), newList);
      } else {
        Point newCentroid = null;
        //iterate through cluster map
        for (Map.Entry<Point, List<L>> entry : clusters.entrySet()) {
          //if the distance b/w centroid and location is less than the maxDistance, add to cluster
          if (DistanceCalculator.getDistance(curLoc, entry.getKey()) < this.maxDistance) {
            List<L> newCluster = entry.getValue();
            newCluster.add(curLoc);
            newCentroid = CenterCalculator.getCentroid(newCluster);
            clusters.remove(entry.getKey());
            clusters.put(newCentroid, newCluster);
            break;
          }
        }
        //if the centroid was altered, then check if it's close enough to another
        if (newCentroid != null) {
          //optimization for when two centroids are close, then combine
          Iterator<Map.Entry<Point, List<L>>> iter = clusters.entrySet().iterator();
          while (iter.hasNext()) {
            Map.Entry<Point, List<L>> curEntry = iter.next();
            if (!curEntry.getKey().equals(newCentroid)
                && DistanceCalculator.getDistance(curEntry.getKey(), newCentroid) < maxDistance) {
              List<L> toMergeCluster = clusters.get(newCentroid);
              curEntry.getValue().addAll(toMergeCluster);
              iter.remove();
            }
          }
        } else { //if the location does not belong to any cluster, then make a new entry in map
          List<L> newList = new ArrayList<>();
          newList.add(curLoc);
          clusters.put(new Point(curLoc.getLat(), curLoc.getLon()), newList);
        }
      }
    }
    return clusters;
  }
}
