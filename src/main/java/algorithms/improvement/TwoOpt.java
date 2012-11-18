package algorithms.improvement;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.fleet.MovingObject;
import model.nodes.*;
import model.routes.Route;


public class TwoOpt implements Move {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	MoveConfiguration configuration;
	
	public TwoOpt() {
		
	}

	public TwoOpt(Movable m){
		
	}
	
	public Neighborhood apply(Movable m) {
		return null;
	}
	
	public Neighborhood apply(Route<? extends Node,? extends Customer,? extends MovingObject> initialRoute) {
		logger.info("TwoOpt application started for " + initialRoute.toString());
		checkNotNull(initialRoute);
		checkNotNull(initialRoute.getVehicle());
		
		Neighborhood neighborhood = new Neighborhood(initialRoute);
		
		int numberOfCustomers = initialRoute.getCustomers().size();
		for(int i=0; i<=numberOfCustomers-2; i++) {
			for(int j=i+2; j<=numberOfCustomers; j++) {
				List<Node> preparedNodes = this.swapAndReverse(new ArrayList<Node>(initialRoute.getNodes()), i, j);
				
				Route<Node,Customer,MovingObject> neighborRoute = (Route<Node, Customer, MovingObject>) initialRoute.getCopy();

				for (Node n : preparedNodes) {
					if (n instanceof Customer) {
						neighborRoute.addCustomer((Customer) n);
					}
				}
				logger.info("\tNeighbor: " + neighborRoute.toString());
				neighborhood.addNeighbor(neighborRoute);
			}
			
		}
		logger.info("two opt application finished with neighborhood size "+neighborhood.size() + "\n Best neighbor: " + neighborhood.getBestNeigbor().toString() + " Improvement: "+(((initialRoute.cost() - neighborhood.getBestNeigbor().cost()) / initialRoute.cost())) * 100 + " %");
		
		checkNotNull(neighborhood);
		return neighborhood;
	}
	
	private List<Node> swapAndReverse(List<Node> nodes, int i, int j) {
		Collections.swap(nodes, i+1, j);
		Collections.reverse(nodes.subList(i+2, j-1+1));
		return nodes;
	}
	
	public void setConfiguration(MoveConfiguration config) {
		this.configuration = config;
	}
	
	public MoveConfiguration getConfiguration() {
		return this.configuration;
	}

}
