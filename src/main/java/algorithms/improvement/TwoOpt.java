package algorithms.improvement;

import java.util.*;

import model.nodes.Node;
import model.routes.Route;


public class TwoOpt implements Move {
	
	MoveConfiguration configuration;
	
	public TwoOpt() {
		
	}

	
	public Neighborhood apply(Movable input) {
		return null;
	}

	public Neighborhood apply(Route initialRoute) {
		Neighborhood neighborhood = new Neighborhood(initialRoute);
		for(int i=0; i<=initialRoute.getCustomers().size()-2; i++) {
			for(int j=i+2; j<=initialRoute.getCustomers().size(); j++) {
				//System.out.println(i + " " + j);
				List<Node> nodes = new ArrayList<Node>(initialRoute.getNodes());
				Collections.swap(nodes, i+1, j);
				Collections.reverse(nodes.subList(i+2, j-1+1));
				for(Node n : nodes) {
					System.out.print(" "+n.getId());
				}
				System.out.println();
			}
			
		}
		
		return neighborhood;
	}
	
	public void setConfiguration(MoveConfiguration config) {
		this.configuration = config;
	}
	
	public MoveConfiguration getConfiguration() {
		return this.configuration;
	}

}
