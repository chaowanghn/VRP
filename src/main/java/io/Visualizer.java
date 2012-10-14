package io;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import model.TTRP;
import model.nodes.Depot;
import model.nodes.Node;
import model.nodes.VehicleCustomer;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.util.*;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbsoluteCrossoverScalingControl;

public class Visualizer {
	public static void main(String[] args) throws IOException {
		
		visualize(InstanceImporter.createTTRP(new File("src/test/resources/instances/benchmark/ttrp01.dat")));
		/*Graph<Integer,String> graph = new DirectedSparseMultigraph<Integer, String>();
		Layout<Integer, String> layout = new StaticLayout<Integer, String>(graph);
		
		Transformer<Integer, Paint> vertexPaint = new Transformer<Integer, Paint>() {
			public Paint transform(Integer input) {
				return Color.GREEN;
			}
		};
		
		layout.setSize(new Dimension(1000, 1000));
		VisualizationViewer<Integer, String> vv = new VisualizationViewer<Integer, String>(layout);
		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
		vv.setSize(1000, 1000);
		//AbsoluteCrossoverScalingControl scalingcontrol = new AbsoluteCrossoverScalingControl();
		//scalingcontrol.scale(vv, 10, new Point2D.Double(0.0,0.0));
		 
		Integer vertex = new Integer(0);
		layout.getGraph().addVertex(vertex);
		layout.setLocation(vertex, new Point2D.Double(30.0,40.0));
		 
		
		
		JFrame frame = new JFrame("Graph Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.getContentPane().setLocation(0, 0);
		frame.pack();
		frame.setVisible(true);*/
		
		
		
	}
	
	public static void visualize(TTRP ttrp) {
		Graph<Node,String> graph = new DirectedSparseMultigraph<Node, String>();
		Layout<Node,String> layout = new CircleLayout<Node, String>(graph);
		layout.setSize(new Dimension(1000,1000));
		VisualizationViewer<Node, String> vv = new VisualizationViewer<Node, String>(layout);
		// SCALING CONTROL NOT SET
		AbsoluteCrossoverScalingControl scalingcontrol = new AbsoluteCrossoverScalingControl();
		scalingcontrol.scale(vv, 10, new Point2D.Double(0,0));
		for (Node node : ttrp.getAllNodes()) {
			graph.addVertex(node);
			layout.setLocation(node, node);
			layout.lock(node, true);
		}
		

		Transformer<Node,Paint> vertexColor = new Transformer<Node,Paint>() {
			public Paint transform(Node node) {
				if (node instanceof Depot) {return Color.BLACK;}
				if (node instanceof VehicleCustomer ) {return Color.RED;}
				else return Color.BLUE;
			}
        }; 
        vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);

        vv.setPreferredSize(new Dimension(800,800));
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
        
		
	}
}
