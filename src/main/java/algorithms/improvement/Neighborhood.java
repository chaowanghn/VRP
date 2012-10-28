package algorithms.improvement;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Neighborhood {
	Set<Movable> neighbors;
	
	public Neighborhood() {
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
}
