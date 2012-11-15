package util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.nodes.Node;

import com.google.common.collect.Iterables;

public class Nodes {

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
