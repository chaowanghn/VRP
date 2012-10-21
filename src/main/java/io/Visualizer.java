package io;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JLabel;

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
	
	public static void main(String[] args) throws IOException {
		Visualizer.visualize(InstanceImporter.createTTRP(new File("src/test/resources/instances/benchmark/ttrp05.dat")));
	}
	
	public static float DEFAULT_SCALING_CONTROL = 10;
	public static Dimension DEFAULT_LAYOUT_SIZE = new Dimension(1000,1000);
	public static Dimension DEFAULT_VV_SIZE = new Dimension(800, 800);
	
	private Graph<Node, String> graph;
	private Layout<Node, String> layout;
	private VisualizationViewer<Node,String> visualViewer;
	private AbsoluteCrossoverScalingControl scalingcontrol;	
	
	private Visualizer() {
		this.graph = new DirectedOrderedSparseMultigraph<Node, String>();
		this.layout = new CircleLayout<Node,String>(graph);
		this.layout.setSize(DEFAULT_LAYOUT_SIZE);
		this.visualViewer = new VisualizationViewer<Node, String>(layout);
		this.visualViewer.setPreferredSize(DEFAULT_VV_SIZE);
		this.scalingcontrol = new AbsoluteCrossoverScalingControl();
		this.scalingcontrol.scale(visualViewer, DEFAULT_SCALING_CONTROL, new Point2D.Double(0,0));
	}
	
	private Visualizer(float scalingControl, Point2D point, Dimension layoutSize) {
		this();
		this.layout.setSize(layoutSize);
		this.scalingcontrol.scale(visualViewer, scalingControl, point);
	}
	
	private void setNodes(Collection<Node> nodes) {
		for (Node node : nodes) {
			this.graph.addVertex(node);
			layout.setLocation(node, node);
			layout.lock(node, true);
		}
				    
		 visualViewer.getRenderContext().setVertexFillPaintTransformer(vertexColorTransformer());
		
	}
	
	private Transformer<Node,Paint> vertexColorTransformer() {
		return new Transformer<Node,Paint>() {
			public Paint transform(Node node) {
						if (node instanceof Depot) {return Color.BLACK;}
						if (node instanceof VehicleCustomer ) {return Color.RED;}
						else return Color.BLUE;
					}
		    };
	}

	private VisualizationViewer<Node, String> getVisualizationViewer() {
		return this.visualViewer;
	}
	
	public static void visualize(TTRP ttrp) {
		Visualizer visualizer = new Visualizer();
		visualizer.setNodes(ttrp.getAllNodes());
		JFrame frame = new JFrame(ttrp.toString());
		frame.getContentPane().add(visualizer.getVisualizationViewer());
		frame.pack();
		frame.setVisible(true);
	}
	

}
