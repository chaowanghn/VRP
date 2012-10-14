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
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbsoluteCrossoverScalingControl;

public class Visualizer {
	
	public static float DEFAULT_SCALING_CONTROL = 10;
	
	public static void main(String[] args) throws IOException {
		Visualizer.visualize(InstanceImporter.createTTRP(new File("src/test/resources/instances/benchmark/ttrp05.dat")));
	}
	
	public static void visualize(TTRP ttrp) {
		Graph<Node,String> graph = new DirectedSparseMultigraph<Node, String>();
		Layout<Node,String> layout = new CircleLayout<Node, String>(graph);
		layout.setSize(new Dimension(1000,1000));
		VisualizationViewer<Node, String> visualViewer = new VisualizationViewer<Node, String>(layout);
		
		//Set scaling control
		AbsoluteCrossoverScalingControl scalingcontrol = new AbsoluteCrossoverScalingControl();
		scalingcontrol.scale(visualViewer, DEFAULT_SCALING_CONTROL, new Point2D.Double(0,0));
		
		
		//Fetch nodes
		for (Node node : ttrp.getAllNodes()) {
			graph.addVertex(node);
			layout.setLocation(node, node);
			layout.lock(node, true);
		}

		//Set nodes colors
		Transformer<Node,Paint> vertexColor = new Transformer<Node,Paint>() {
			public Paint transform(Node node) {
				if (node instanceof Depot) {return Color.BLACK;}
				if (node instanceof VehicleCustomer ) {return Color.RED;}
				else return Color.BLUE;
			}
        };     
        visualViewer.getRenderContext().setVertexFillPaintTransformer(vertexColor);


        visualViewer.setPreferredSize(new Dimension(800,800));
        JFrame frame = new JFrame(ttrp.toString());;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(visualViewer);
        frame.pack();
        frame.setVisible(true);
	}
	
}
