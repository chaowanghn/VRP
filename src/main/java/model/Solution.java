package model;

import java.util.*;

import com.google.common.collect.ImmutableList;

import algorithms.improvement.Movable;

import model.fleet.MovingObject;
import model.routes.*;
import model.nodes.*;

public class Solution implements Movable {
	public static String INPUT_FILE_PATH = "src/test/resources/instances/bestSolutions/sol-ttrp03.txt";

	Set<CompleteVehicleRoute> completeVehicleRoutes = new HashSet<CompleteVehicleRoute>();
	Set<PureTruckRoute> pureTruckRoutes = new HashSet<PureTruckRoute>();
	
	public Solution() {
		
	}

	public void add(CompleteVehicleRoute cvr){
		this.completeVehicleRoutes.add(cvr);
	}
	
	public void add(PureTruckRoute ptr){
		this.pureTruckRoutes.add(ptr);
	}
	
	public int compareTo(Movable o) {
		if (this.cost() < o.cost())
			return -1;	
		else if (this.cost() > o.cost())
			return 1;	
		else 
			return 0;
	}



	@Override
	public double cost() {
		double totalCost=0;
		totalCost += Route.costOfRoutes(completeVehicleRoutes);
		totalCost += Route.costOfRoutes(pureTruckRoutes);
		for(CompleteVehicleRoute cvr : this.completeVehicleRoutes) {
			for (SubTour st : cvr.getSubTours()) {
				totalCost += st.cost();
			}
		}
		return totalCost;
	}

	
	
	
}


