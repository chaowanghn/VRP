package io;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public static int getIntFromString(String str){
		Pattern intsOnly = Pattern.compile("\\d+");
		Matcher makeMatch = intsOnly.matcher(str);
		makeMatch.find();
		return Integer.parseInt(makeMatch.group());
	}
}
