package model.routes;

import com.google.common.base.Objects;

import model.nodes.Node;

public class Edge implements Comparable<Edge>{
	private Node n1;
	private Node n2;
	private EdgeType type = EdgeType.COMPLETE_VEHICLE_EDGE;
	
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
	
	@Override
	public int hashCode(){
		return Objects.hashCode(n1,n2);
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Node) {
			Edge other = (Edge) obj;
			return other.n1.equals(this.n1) && other.n2.equals(this.n2);
		}
		return false;
		
	}

	
	public Node getN1() {
		return n1;
	}

	public void setN1(Node n1) {
		this.n1 = n1;
	}

	public Node getN2() {
		return n2;
	}

	public void setN2(Node n2) {
		this.n2 = n2;
	}

	public String toString(){
		return new StringBuilder().append("[").append(n1.getId()).append(",").append(n2.getId()).append("] ").append(this.cost()).toString();
	}

	
	public EdgeType getType() {
		return type;
	}

	public void setType(EdgeType type) {
		this.type = type;
	}



	public static enum EdgeType {
		COMPLETE_VEHICLE_EDGE,TRUCK_EDGE
	}


}
