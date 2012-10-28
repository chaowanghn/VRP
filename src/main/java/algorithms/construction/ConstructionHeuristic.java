package algorithms.construction;

import model.Solution;
import model.TTRP;

import com.google.common.base.Function;

interface ConstructionHeuristic extends Function<TTRP, Solution> {

	Solution apply(TTRP input);

}
