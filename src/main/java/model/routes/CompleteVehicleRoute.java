package model.routes;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashSet;
import java.util.Set;

import util.Customers;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import model.fleet.CompleteVehicle;
import model.nodes.Customer;
import model.nodes.Depot;
import model.nodes.TruckCustomer;
import model.nodes.VehicleCustomer;

public class CompleteVehicleRoute extends PureVehicleRoute {

	private Set<SubTour> subTours = new HashSet<SubTour>();
	
	public CompleteVehicleRoute(Depot d, CompleteVehicle vehicle) {
		super(d, vehicle);
	}

	public CompleteVehicleRoute(Depot d) {
		super(d);
	}

	public double totalDemand(){
		double totalDemandInSubTours=0;
		for(SubTour st : this.subTours) {
			totalDemandInSubTours += st.totalDemand();
		}
		return super.totalDemand() + totalDemandInSubTours;
	}
	
	public void addSubTour(SubTour st) {
		this.subTours.add(st);
	}
	
	public void addToMainTour(VehicleCustomer vCustomer) {
		super.addCustomer(vCustomer);
	}
	
	public <T extends Customer> boolean feasibleInsertion(final T c) {
		if(c instanceof TruckCustomer) {
			Iterables.any(this.subTours, new Predicate<SubTour>() {
				public boolean apply(SubTour st) {
					return st.feasibleInsertion(c);
				}
			});
		}
		else {
			checkArgument(c instanceof VehicleCustomer);
			return c.getDemand() <= Customers.totalDemand(customers);
		}
		return false;
	}
	
	public Set<SubTour> getSubTours() {
		return this.subTours;
	}

	public boolean hasSubTours() {
		return !(this.subTours.isEmpty());
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getDepot().getId());
		for (Customer c : customers)
			sb.append("-"+c.getId());
		sb.append("-"+this.getDepot().getId());
		sb.append("  Cost: " + this.cost());
		if(hasSubTours()) {
			sb.append("\nSUBTOURS: ");
			for (SubTour st : this.subTours) {
				checkNotNull(st);
				checkNotNull(st.toString());
				sb.append("{ "+st.toString()+" }");
			}
		}
		return sb.toString();
	}
}
