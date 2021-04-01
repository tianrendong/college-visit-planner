package edu.brown.cs.termproject.router;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * The class for clustering Locatables.
 */
public class Clustering {

  private static final int R = 6371 * 1000;
  private final double maxDistance;

  public Clustering(double maxDistance) {
    this.maxDistance = maxDistance;
  }

  /**
   * Creates clusters from a list of Locatable.
   * @param locations list of Locatable.
   * @return Set of clusters represented as set of Locatable.
   */
  public Map<Locatable, List<Locatable>> makeClusters(List<Locatable> locations) {
    Map<Locatable, List<Locatable>> clusters = new HashMap<>();
    // iterate through location list
    for (Locatable curLoc : locations) {
      if (clusters.isEmpty()) {
        List<Locatable> newList = new ArrayList<>();
        newList.add(curLoc);
        clusters.put(new Point(curLoc.getLat(), curLoc.getLon()), newList);
      } else {
        Locatable newCentroid = null;
        //iterate through cluster map
        for (Map.Entry<Locatable, List<Locatable>> entry : clusters.entrySet()) {
          //if the distance b/w centroid and location is less than the maxDistance, add to cluster
          if (getDistance(curLoc, entry.getKey()) < this.maxDistance) {
            List<Locatable> newCluster = entry.getValue();
            newCluster.add(curLoc);
            newCentroid = getCentroid(newCluster);
            clusters.remove(entry.getKey());
            clusters.put(newCentroid, newCluster);
            break;
          }
        }
        //if the centroid was altered, then check if it's close enough to another
        if (newCentroid != null) {
          //optimization for when two centroids are close, then combine
          Iterator<Map.Entry<Locatable, List<Locatable>>> iter = clusters.entrySet().iterator();
          while (iter.hasNext()) {
            Map.Entry<Locatable, List<Locatable>> curEntry = iter.next();
            if (!curEntry.getKey().equals(newCentroid)
                && getDistance(curEntry.getKey(), newCentroid) < maxDistance) {
              List<Locatable> toMergeCluster = clusters.get(newCentroid);
              curEntry.getValue().addAll(toMergeCluster);
              iter.remove();
            }
          }
        } else { //if the location does not belong to any cluster, then make a new entry in map
          List<Locatable> newList = new ArrayList<>();
          newList.add(curLoc);
          clusters.put(new Point(curLoc.getLat(), curLoc.getLon()), newList);
        }
      }
    }
    return clusters;
  }

  /**
   * Gets the middle centroid point of a cluster given a list of locations.
   * @param locations list of Locatable.
   * @return the centroid point of the cluster.
   */
  public Locatable getCentroid(List<Locatable> locations) {
    double totalLat = 0;
    double totalLon = 0;
    for (Locatable location : locations) {
      totalLat += location.getLat();
      totalLon += location.getLon();
    }
    return new Point(totalLat / locations.size(), totalLon / locations.size());
  }
  /**
   * returns the haversine distance in meters between two nodes.
   * formula from:
   * https://www.igismap.com/haversine-formula-calculate-geographic-distance-earth/
   * @param start The first node.
   * @param end The second node.
   * @return Distance of way in meters.
   */
  public static double getDistance(Locatable start, Locatable end) {
    //    double dLat = Math.toRadians(latEnd - latStart);
    //    double dLon = Math.toRadians(lonEnd - lonStart);
    //
    //    latStart = Math.toRadians(latStart);
    //    latEnd = Math.toRadians(latEnd);
    //
    //    double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2)
    //        * Math.cos(latStart) * Math.cos(latEnd);
    //
    //    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    //    return c * R;
    double latStart = start.getLat();
    double latEnd = end.getLat();
    double lonStart = start.getLon();
    double lonEnd = end.getLon();
    return Math.sqrt(Math.pow(Math.abs(latStart - latEnd), 2)
        + Math.pow(Math.abs(lonStart - lonEnd), 2));
  }

}
