package io;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

import model.TTRP;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.util.*;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

public class Visualizer {
	public static void main(String[] args) {
		Graph<Integer,String> graph = new DirectedSparseMultigraph<Integer, String>();
		Layout<Integer, String> layout = new StaticLayout<Integer, String>(graph);
		
		Transformer<Integer, Paint> vertexPaint = new Transformer<Integer, Paint>() {
			public Paint transform(Integer input) {
				return Color.GREEN;
			}
		};
		
		
		Integer vertex = new Integer(0);
		layout.getGraph().addVertex(vertex);
		layout.setLocation(vertex, new Point2D.Double(0.0,0.0));
		
		
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
	
	public static void visualize(TTRP ttrp) {
		
	}
}
