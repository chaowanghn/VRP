package model.routes;

import model.nodes.Node;

public class Edge implements Comparable<Edge>{
	private Node n1;
	private Node n2;
	
	public Edge(Node n1, Node n2){
		this.n1 = n1;
		this.n2 = n2;
		
	}

	public double cost(){
		return n1.distance(n2);
	}
	
	@Override
	public int compareTo(Edge o) {
		if(this.cost() < o.cost()) {
			return -1;
		}
		
		else if (this.cost() > o.cost()) {
			return 1;
		}
		
		else {
			return 0;
		}
	}
}
