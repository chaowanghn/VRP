package algorithms.improvement;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Neighborhood {
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
}
