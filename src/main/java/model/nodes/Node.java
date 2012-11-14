package model.nodes;

import java.awt.geom.Point2D;
import java.util.*;

import com.google.common.collect.Iterables;

public abstract class Node extends Point2D.Double {
	
	private static final long serialVersionUID = 1L;

	public abstract int getId();
	
	public abstract double getX();
	
	public abstract double getY();
	
	public static <O extends Node, T extends Node> List<O> inAscendingDistance(Collection<? extends O> of,final  T to) {
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

	public static <O extends Node, T extends Node> O nearest(Collection<? extends O> of,final T to) {
		return Iterables.getFirst(inAscendingDistance(of, to), null);
	}
	
	public static <O extends Node, T extends Node> O farthest(Collection<? extends O> of,final T to) {
		return Iterables.getLast(inAscendingDistance(of, to));
	}

	
}
