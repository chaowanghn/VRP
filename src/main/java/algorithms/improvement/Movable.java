package algorithms.improvement;

import io.Visualizable;

public interface Movable extends Comparable<Movable>, Visualizable{
	double cost();
	
	Movable getCopy();
}
