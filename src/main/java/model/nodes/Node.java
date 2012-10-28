package model.nodes;

import java.awt.geom.Point2D;
import java.util.*;

import com.google.common.collect.Collections2;


public abstract class Node extends Point2D.Double {
	
	private static final long serialVersionUID = 1L;

	public abstract int getId();
	
	public abstract double getX();
	
	public abstract double getY();
	
	public static <N extends Node> List<N> nodesInAscendingDistance(Collection<? extends N> of,final  Node to) {
		List<N> nodes = new ArrayList<N>(of);
		Collections.sort(nodes, new Comparator<N>() {
			public int compare(N n1, N n2) {
				if (n1.distance(to) < n2.distance(to))
					return -1;
				else if (n1.distance(to) > n2.distance(to))
					return 1;
				else
					return 0;
			}
			
		});

		return nodes;
	}

	public static <N extends Node> N nearestNode(Collection<? extends N> of,final Node to) {
		return nodesInAscendingDistance(of, to).get(0);
	}
	
	public static <N extends Node> N farthestNode(Collection<? extends N> of,final Node to) {
		return nodesInAscendingDistance(of, to).get(nodesInAscendingDistance(of, to).size()-1);
	}
}
