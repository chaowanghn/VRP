package model.routes;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.HashSet;
import java.util.Set;

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
	
	public double cost() {
		double totalCost = 0;
		for(SubTour st : this.subTours) {
			totalCost += st.cost();
		}
		return totalCost + super.cost();
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
			return c.getDemand() <= Customer.totalDemand(customers);
		}
		return false;
	}
	
	public Set<SubTour> getSubTours() {
		return this.subTours;
	}

	public boolean hasSubTours() {
		return !(this.subTours.size() == 0);
	}
}
