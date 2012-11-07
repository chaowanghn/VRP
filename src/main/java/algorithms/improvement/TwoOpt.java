package algorithms.improvement;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.nodes.*;
import model.routes.Route;


public class TwoOpt implements Move {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	MoveConfiguration configuration;
	
	public TwoOpt() {
		
	}

	
	public Neighborhood apply(Movable input) {
		return null;
	}

	public Neighborhood apply(Route initialRoute) {
		logger.info("TwoOpt application started for " + initialRoute.toString());
		Neighborhood neighborhood = new Neighborhood(initialRoute);
		
		for(int i=0; i<=initialRoute.getCustomers().size()-2; i++) {
			for(int j=i+2; j<=initialRoute.getCustomers().size(); j++) {
				List<Node> preparedNodes = this.swapAndReverse(new ArrayList<Node>(initialRoute.getNodes()), i, j);
				
				Route neighborRoute = new Route(initialRoute.getDepot());

				for (Node n : preparedNodes) {
					if (n instanceof Customer) {
						neighborRoute.addCustomer((Customer) n);
					}
				}
				logger.info("\tNeighbor: " + neighborRoute.toString());
				neighborhood.addNeighbor(neighborRoute);
			}
			
		}
		logger.info("two opt application finished with neighborhood size "+neighborhood.size() + "\n Best neighbor: " + neighborhood.getBestNeigbor().toString() + " Improvement: "+Math.abs(((initialRoute.cost() - neighborhood.getBestNeigbor().cost()) / initialRoute.cost())) * 100 + " %");
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
