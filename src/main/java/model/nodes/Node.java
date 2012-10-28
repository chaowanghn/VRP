package model.nodes;

import java.awt.geom.Point2D;
import java.util.*;

public abstract class Node extends Point2D.Double {
	
	private static final long serialVersionUID = 1L;

	public abstract int getId();
	
	public abstract double getX();
	
	public abstract double getY();
	
	public static <O extends Node, T extends Node> List<O> nodesInAscendingDistance(Collection<? extends O> of,final  T to) {
		List<O> nodes = new ArrayList<O>(of);
		Collections.sort(nodes, new Comparator<O>() {
			public int compare(O n1, O n2) {
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

	public static <O extends Node, T extends Node> O nearestNode(Collection<? extends O> of,final T to) {
		return nodesInAscendingDistance(of, to).get(0);
	}
	
	public static <O extends Node, T extends Node> O farthestNode(Collection<? extends O> of,final T to) {
		return nodesInAscendingDistance(of, to).get(nodesInAscendingDistance(of, to).size()-1);
	}
}
