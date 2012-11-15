package io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.Files;

import model.Solution;
import model.TTRP;
import model.fleet.*;
import model.nodes.*;
import model.routes.*;

public class SolutionImporter {
	
	private TTRP ttrp;
	
	private double totalCost;
	
	private int numberOfVehicleRoutes;
	private List<CompleteVehicleRoute> completeVehicleRoutes = new ArrayList<CompleteVehicleRoute>();
	
	private int numberOfTruckRoutes;
	private List<PureTruckRoute> pureTruckRoutes = new ArrayList<PureTruckRoute>();
	
	private int numberOfSubTours;
	private List<SubTour> subTours = new ArrayList<SubTour>();
	
	private int numberOfTrucksUsed;
	
	private int numberOfTrailersUsed; 
	
	private List<String> fileLines;
	
	public SolutionImporter(TTRP ttrp, File inputFile){
		this.ttrp = ttrp;
		try {
			this.fileLines = Files.readLines(inputFile, Charsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readTruckRoute(int stops, int lineNumber){
		PureTruckRoute ptr = new PureTruckRoute(ttrp.getNode(getIntFromString(fileLines.get(lineNumber+1)+1)));
		for(int i=lineNumber+6; i<=lineNumber+6+stops-1; i++){
			ptr.addCustomer(ttrp.getCustomer(getIntFromString(fileLines.get(i))));
		}
		this.pureTruckRoutes.add(ptr);
	}
	
	private void readSubTour(int stops, int lineNumber) {
		SubTour st = new SubTour(ttrp.getNode(getIntFromString(fileLines.get(lineNumber+1)+1)));
		for(int i=lineNumber+6; i<=lineNumber+6+stops-1; i++){
			st.addCustomer(ttrp.getCustomer(getIntFromString(fileLines.get(i))));
		}
		this.subTours.add(st);
	}
	
	private void readVehicleRoute(int stops, int lineNumber){
		CompleteVehicleRoute cvr = new CompleteVehicleRoute(ttrp.getDepot());
		for(int i=lineNumber+6; i<=lineNumber+6+stops-1; i++){
			String vehicleCustomerLine = fileLines.get(i);
			cvr.addCustomer((VehicleCustomer) ttrp.getCustomer(getIntFromString(vehicleCustomerLine)));
			if(vehicleCustomerLine.contains("Vehicle Customer root of subtours")) {
				SubTour correspondingSubTour = null;
				for(SubTour st : this.subTours ){
					if(st.getDepot().getId() == (getIntFromString(vehicleCustomerLine))){
						correspondingSubTour = st;
						cvr.addSubTour(correspondingSubTour);
					}
				}
			}
		}
		this.completeVehicleRoutes.add(cvr);
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

	public void read(){
		this.readBasicInfo(this.fileLines.subList(4, 8));	
		for(int lineNumber=0; lineNumber<this.fileLines.size(); lineNumber++){
			String line = fileLines.get(lineNumber);
			if(line.startsWith("Route ")) {
				int stops = getIntFromString(fileLines.get(lineNumber + 3));
				
				if(fileLines.get(lineNumber + 1 ).contains("(SUBTOUR)")) {
					this.readSubTour(stops,lineNumber);
				}
				
				else if(fileLines.get(lineNumber + 1).contains("(TRUCK ROUTE")){
					this.readTruckRoute(stops, lineNumber);
				}
				
				else if(fileLines.get(lineNumber + 1).contains("VEHICLE ROUTE")) {
					this.readVehicleRoute(stops, lineNumber);
				}
			}
			
			else if(line.startsWith(""));
		}
	}

	public double getTotalCost() {
		return totalCost;
	}

	public int getNumberOfVehicleRoutes() {
		return this.completeVehicleRoutes.size();
	}

	public int getNumberOfTrucksUsed() {
		return numberOfTrucksUsed;
	}

	public int getNumberOfTrailersUsed() {
		return numberOfTrailersUsed;
	}
	
	public int getNumberOfSubTours(){
		return this.subTours.size();
	}
	
	public int getNumberOfTruckRoutes(){
		return this.pureTruckRoutes.size();
	}
	
	public List<CompleteVehicleRoute> getCompleteVehicleRoutes() {
		return completeVehicleRoutes;
	}

	public void setCompleteVehicleRoutes(
			List<CompleteVehicleRoute> completeVehicleRoutes) {
		this.completeVehicleRoutes = completeVehicleRoutes;
	}

	public List<PureTruckRoute> getPureTruckRoutes() {
		return pureTruckRoutes;
	}

	public void setPureTruckRoutes(List<PureTruckRoute> pureTruckRoutes) {
		this.pureTruckRoutes = pureTruckRoutes;
	}

	public List<SubTour> getSubTours() {
		return subTours;
	}

	public void setSubTours(List<SubTour> subTours) {
		this.subTours = subTours;
	}

	public Solution getSolution() {
		Solution solution = new Solution(this.ttrp);
		for(CompleteVehicleRoute cvr : this.completeVehicleRoutes){
			solution.add(cvr);
		}
		
		for(PureTruckRoute ptr : this.pureTruckRoutes) {
			solution.add(ptr);
		}
		return solution;
	}

}
