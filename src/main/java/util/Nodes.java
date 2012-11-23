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

	public static List<List<Node>> partition(List<Node> nodes, int[] indices){
		/*
		 * Input: 1-2-3-0-4-0-5-6-0-7-8-9
		 * 		int[] indices = {3,5,8};
		 * Output:
		 * 1-2-3
		 * 4
		 * 5-6
		 */
		List<List<Node>> lists = new ArrayList<List<Node>>();
		for(int i=0; i<indices.length; i++) {
			if(i==0) {
				lists.add(nodes.subList(i, indices[i]));
			}
			else {
				lists.add(nodes.subList(indices[i-1]+1, indices[i]));
			}
		}
		return lists;
	}

	public static String toString(Iterable<Node> nodes){
		StringBuilder sb = new StringBuilder();
		for(Node n : nodes){
			sb.append("-"+n.getId());
		}
		sb.deleteCharAt(0);
		return sb.toString();
		
	}
}
