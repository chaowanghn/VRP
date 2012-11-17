package model;

import io.SolutionImporter;
import io.Visualizable;
import io.Visualizer;

import java.io.File;
import java.util.*;

import util.Routes;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;

import algorithms.improvement.Movable;

import model.fleet.MovingObject;
import model.routes.*;
import model.nodes.*;

public class Solution implements Movable,Visualizable{
	public static String TTRP_03_BEST_KNOWN_SOLUTION = "src/test/resources/instances/bestSolutions/sol-ttrp03.txt";

	private TTRP ttrp;
	Set<CompleteVehicleRoute> completeVehicleRoutes = new HashSet<CompleteVehicleRoute>();
	Set<PureTruckRoute> pureTruckRoutes = new HashSet<PureTruckRoute>();
	
	public Solution(TTRP ttrp) {
		this.ttrp = ttrp;
	}

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
		totalCost += Routes.costOfRoutes(completeVehicleRoutes);
		totalCost += Routes.costOfRoutes(pureTruckRoutes);
		for(CompleteVehicleRoute cvr : this.completeVehicleRoutes) {
			for (SubTour st : cvr.getSubTours()) {
				totalCost += st.cost();
			}
		}
		return totalCost;
	}
	
	public static Solution createSolutionFromFile(TTRP ttrp, String solutionFilePath){
		SolutionImporter solutionImporter = new SolutionImporter(ttrp, new File(solutionFilePath));
		solutionImporter.read();
		return solutionImporter.getSolution();
	}
	
	public static Solution createSolutionFromFile(String ttrpInstanceFilePath, String solutionFilePath){
		return createSolutionFromFile(TTRP.createInstanceFromFile(ttrpInstanceFilePath), solutionFilePath);
	}

	@Override
	public Collection<Node> getNodes() {
		return Sets.union(Routes.getAllNodes(pureTruckRoutes), Routes.getAllNodes(completeVehicleRoutes));
	}

	@Override
	public Collection<Edge> getEdges() {
		return Sets.union(Routes.getAllEdges(pureTruckRoutes), Routes.getAllEdges(completeVehicleRoutes));
	}

	@Override
	public String getVisualizationTitle() {
		return new StringBuilder().append("Solution Cost: " + this.cost()).toString();
	}
}


