package algorithms.moves;

import model.Solution;

import com.google.common.base.Function;

interface Move extends Function<Movable, Neighborhood> {
	MoveConfiguration getConfiguration();
}
