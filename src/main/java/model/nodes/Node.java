package model.nodes;

import java.awt.geom.Point2D;
import java.util.*;

import com.google.common.collect.Collections2;


public abstract class Node extends Point2D.Double {
	
	private static final long serialVersionUID = 1L;

	public abstract int getId();
	
	public abstract double getX();
	
	public abstract double getY();
	
	public static List<? extends Node> nodesInAscendingDistance(Collection<? extends Node> of,final Node to) {
		List<? extends Node> nodes = new ArrayList<Node>(of);
		Collections.sort(nodes, new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				if (n1.distance(to) < n2.distance(to)) {
					return -1;
				}
				
				else if (n1.distance(to) > n2.distance(to)) {
					return 1;
				}
				
				else {
					return 0;
				}
			}
			
		});

		return nodes;
	}

	public static Node nearestNode(Collection<? extends Node> of,final Node to) {
		return nodesInAscendingDistance(of, to).get(0);
	}
	
	public static Node farthestNode(Collection<? extends Node> of,final Node to) {
		return nodesInAscendingDistance(of, to).get(nodesInAscendingDistance(of, to).size()-1);
	}
}
