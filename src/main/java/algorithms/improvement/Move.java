package algorithms.improvement;

import com.google.common.base.Function;

/*
 * For an overview of simple moves used in VRPs see [7]
 */
interface Move extends Function<Movable, Neighborhood> {
	MoveConfiguration getConfiguration();
}
