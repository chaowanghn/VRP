package io;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import model.TTRP;
import model.nodes.Depot;
import model.nodes.Node;
import model.nodes.VehicleCustomer;
import model.routes.Route;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbsoluteCrossoverScalingControl;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

public class Visualizer {
	public static String INPUT_FILE_PATH = "src/test/resources/instances/benchmark/ttrp06.dat";
	
	public static void main(String[] args) throws IOException {
		TTRP ttrp = InstanceImporter.createTTRP(INPUT_FILE_PATH);
		ttrp.visualize();
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
	
	private void setNodes(Collection<? extends Node> nodes) {
		for (Node node : nodes) {
			this.graph.addVertex(node);
			layout.setLocation(node, node);
			layout.lock(node, true);
		}
				    
		 visualViewer.getRenderContext().setVertexFillPaintTransformer(vertexColorTransformer());
		 visualViewer.getRenderContext().setVertexLabelTransformer(new Transformer<Node, String>() {
				
				public String transform(Node input) {
					return Integer.toString(input.getId());
				}
			});
		
	}
	
	private void setNodes(List<? extends Node> nodes) {
		for (Node node : nodes) {
			this.graph.addVertex(node);
			layout.setLocation(node, node);
			layout.lock(node, true);
		}
		
		for (int i=1; i<=nodes.size()-1; i++) {
			this.graph.addEdge(Integer.toString(i), nodes.get(i-1), nodes.get(i));
		}
		
		visualViewer.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<String>());
		visualViewer.getRenderContext().setVertexFillPaintTransformer(vertexColorTransformer());
		visualViewer.getRenderContext().setVertexLabelTransformer(new Transformer<Node, String>() {
			
			public String transform(Node input) {
				return Integer.toString(input.getId());
			}
		});
	}
	
	private Transformer<Node,Paint> vertexColorTransformer() {
		return new Transformer<Node,Paint>() {
			public Paint transform(Node node) {
						if (node instanceof Depot) {return Color.BLACK;}
						if (node instanceof VehicleCustomer ) {return Color.BLUE;}
						else return Color.RED;
					}
		    };
	}

	private VisualizationViewer<Node, String> getVisualizationViewer() {
		return this.visualViewer;
	}
	
	public static void visualizeTTRP(TTRP ttrp) {
		Visualizer visualizer = new Visualizer();
		visualizer.setNodes(ttrp.getAllNodes());
		JFrame frame = new JFrame(ttrp.toString());
		frame.getContentPane().add(visualizer.getVisualizationViewer());
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void visualizeNodesSequence(List<? extends Node> nodes) {
		Visualizer visualizer = new Visualizer();
		JFrame frame = new JFrame();
		visualizer.setNodes(nodes);
		frame.getContentPane().add(visualizer.getVisualizationViewer());
		frame.pack();
		frame.setVisible(true);
	}

	public static void visualizeRoute(Route r) {
		visualizeNodesSequence(r.getNodes());
	}
}
