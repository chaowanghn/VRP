package algorithms.improvement;

import io.Visualizable;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import model.nodes.Node;
import model.routes.Edge;

public class Neighborhood implements Visualizable{
	Movable initial;
	Set<Movable> neighbors;
	
	public Neighborhood(Movable initial) {
		this.initial = initial;
		this.neighbors = new HashSet<Movable>();
	}
	
	public Movable getBestNeigbor() {
		return Collections.min(neighbors);
	}
	
	public void addNeighbor(Movable neighbor) {
		this.neighbors.add(neighbor);
	}
	
	public Set<Movable> getNeighbors() {
		return this.neighbors;
	}

	public boolean contains(Movable movable) {
		return neighbors.contains(movable);
	}
	
	public Movable getInitialMovable() {
		return initial;
	}
	
	public int size() {
		return neighbors.size();
	}

	public boolean hasNeighbors() {
		return !this.neighbors.isEmpty();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Initial: \n" + this.initial.toString());
		sb.append("\n Neighbors");
		for (Movable m : neighbors) {
			sb.append("\n"+m.toString());
		}
		return sb.toString();
	}

	@Override
	public Collection<Node> getNodes() {
		return Collections.emptySet();
	}

	@Override
	public Collection<Edge> getEdges() {
		return Collections.emptySet();
	}

	@Override
	public String getVisualizationTitle() {
		return "";
	}
}
