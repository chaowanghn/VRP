package io;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.util.*;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

public class Visualizer {
	public static void main(String[] args) {
		Graph<Integer,String> graph = new DirectedSparseMultigraph<Integer, String>();
		graph.addVertex(1);
		graph.addVertex(2);
		graph.addVertex(3);
		graph.addEdge("1-2", 1, 2, EdgeType.DIRECTED);
		graph.addEdge("2-3", 2, 3, EdgeType.DIRECTED);
		
		Transformer<Integer, Paint> vertexPaint = new Transformer<Integer, Paint>() {
			public Paint transform(Integer input) {
				return Color.GREEN;
			}
		};
		
		Layout<Integer, String> layout = new CircleLayout<Integer, String>(graph);
		layout.setSize(new Dimension(500, 500));
		BasicVisualizationServer<Integer, String> vv = new BasicVisualizationServer<Integer, String>(layout);
		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
		vv.setSize(600, 600);
		
		JFrame frame = new JFrame("Graph Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
		
	}
}
