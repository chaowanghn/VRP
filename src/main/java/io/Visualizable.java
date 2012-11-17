package io;

import java.util.Collection;
import model.nodes.Node;
import model.routes.Edge;

public interface Visualizable {
	Collection<Node> getNodes();
	Collection<Edge> getEdges();
	String getVisualizationTitle();
}
