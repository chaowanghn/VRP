package io;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Splitter;

import model.TTRP;
import model.fleet.*;
import model.nodes.*;
import model.routes.*;

public class SolutionImporter {
	private TTRP ttrp;
	
	private double totalCost;
	private int numberOfVehicleRoutes;
	private int numberOfTruckRoutes;
	private int numberOfSubTours;
	private int numberOfTrucksUsed;
	private int numberOfTrailersUsed; 
	
	private List<String> fileLines;
	
	

	public static void main(String[] args) {
		
	}
	
	private PureTruckRoute readTruckRoute(List<String> lines){
		return null;
	}
	
	private SubTour readSubTour(List<String> lines) {
		return null;
	}
	
	private PureVehicleRoute readPureVehicleRoue(List<String> lines){
		return null;
	}
	
	private CompleteVehicleRoute readVehicleRouteWithSubTours(List<String> lines){
		return null;
	}

	private void readBasicInfo(List<String> lines){
		this.totalCost = Double.parseDouble(lines.get(0).split(" ")[2]);
		this.numberOfVehicleRoutes = getIntFromString(lines.get(1));
		this.numberOfTruckRoutes = getIntFromString(lines.get(2));
		this.numberOfSubTours = getIntFromString(lines.get(3));		
	}
	
	public static int getIntFromString(String str){
		Pattern intsOnly = Pattern.compile("\\d+");
		Matcher makeMatch = intsOnly.matcher(str);
		makeMatch.find();
		return Integer.parseInt(makeMatch.group());
	}
}
