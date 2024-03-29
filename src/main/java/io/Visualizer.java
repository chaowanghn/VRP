package io;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

import model.TTRP;
import model.fleet.MovingObject;
import model.nodes.Customer;
import model.nodes.Depot;
import model.nodes.Node;
import model.nodes.VehicleCustomer;
import model.routes.CompleteVehicleRoute;
import model.routes.Edge;
import model.routes.Edge.EdgeType;
import model.routes.Route;
import model.routes.SubTour;

import org.apache.commons.collections15.Transformer;

import util.Routes;

import algorithms.improvement.Movable;
import algorithms.improvement.Neighborhood;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbsoluteCrossoverScalingControl;
import edu.uci.ics.jung.visualization.decorators.EdgeShape.Line;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.BasicVertexLabelRenderer;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.renderers.VertexLabelRenderer;

public class Visualizer {
	
	private static float DEFAULT_SCALING_CONTROL = 10;
	private static Dimension DEFAULT_LAYOUT_SIZE = new Dimension(1000,1000);
	private static Dimension DEFAULT_VV_SIZE = new Dimension(800, 800);
	
	private Graph<Node, Edge> graph;
	private Layout<Node, Edge> layout;
	private VisualizationViewer<Node,Edge> visualViewer;
	private AbsoluteCrossoverScalingControl scalingcontrol;	
	
	private Visualizer() {
		this.graph = new DirectedOrderedSparseMultigraph<Node, Edge>();
		this.layout = new CircleLayout<Node,Edge>(graph);
		this.layout.setSize(DEFAULT_LAYOUT_SIZE);
		this.visualViewer = new VisualizationViewer<Node, Edge>(layout);
		this.visualViewer.setPreferredSize(DEFAULT_VV_SIZE);
		this.scalingcontrol = new AbsoluteCrossoverScalingControl();
		this.scalingcontrol.scale(visualViewer, DEFAULT_SCALING_CONTROL, new Point2D.Double(0,0));
	}
	
	private Visualizer(float scalingControl, Point2D point, Dimension layoutSize) {
		this();
		this.layout.setSize(layoutSize);
		this.scalingcontrol.scale(visualViewer, scalingControl, point);
	}
	
	private <N extends Node> void setNodes(Collection<N> nodes) {
		for (N node : nodes) {
			this.graph.addVertex(node);
			layout.setLocation(node, node);
			layout.lock(node, true);
		} 
	}
	
	private void addEdges(Collection<Edge> edges) {
		for(Edge e : edges) {
			this.graph.addEdge(e, e.getN1(), e.getN2());
		}
	}
	
	private Transformer<Node,Paint> vertexColorTransformer() {
		return new Transformer<Node,Paint>() {
			public Paint transform(Node node) {
						if (node instanceof Depot) {return Color.BLACK;}
						if (node instanceof VehicleCustomer ) {return Color.CYAN;}
						else return Color.RED;
					}
		    };
	}

	public void setStyle(){
		 visualViewer.getRenderContext().setVertexFillPaintTransformer(vertexColorTransformer());
		 visualViewer.getRenderContext().setVertexLabelTransformer(new Transformer<Node, String>() {
				public String transform(Node input) {
					return Integer.toString(input.getId());
				}
			});
		 visualViewer.getRenderContext().setEdgeShapeTransformer(new Line<Node, Edge>());
		 visualViewer.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);
		 visualViewer.getRenderContext().setEdgeDrawPaintTransformer(new Transformer<Edge, Paint>() {
				@Override
				public Paint transform(Edge input) {
					if(input.getType().equals(EdgeType.TRUCK_EDGE)){
						return Color.RED;
					}
					else {
						return Color.BLUE;
					}
				}
			});
	}
	
	private VisualizationViewer<Node, Edge> getVisualizationViewer() {
		return this.visualViewer;
	}
	
	private static void show(String frameTitle, Visualizer visualizer ){
		JFrame frame = new JFrame(frameTitle);
		frame.getContentPane().add(visualizer.getVisualizationViewer());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void visualize(Visualizable visualizable){
		if(visualizable instanceof Neighborhood) {
			visualize(((Neighborhood) visualizable).getInitialMovable());
			for(Movable m : ((Neighborhood) visualizable).getNeighbors()) {
				visualize(m);
			}
		}
		Visualizer visualizer = new Visualizer();
		visualizer.setNodes(visualizable.getNodes());
		visualizer.addEdges(visualizable.getEdges());
		visualizer.setStyle();
		show(visualizable.getVisualizationTitle(),visualizer);
	}

	public static void exportToFile(File outputFile, Visualizer visualizer) throws Exception {
		JFrame frame = new JFrame();
		frame.getContentPane().add(visualizer.getVisualizationViewer());
		frame.pack();
		Image img = frame.getIconImage();
		BufferedImage bi = new BufferedImage(DEFAULT_LAYOUT_SIZE.width,DEFAULT_LAYOUT_SIZE.height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bi.createGraphics();
        g2.drawImage(img, 0, 0, null);

        ImageIO.write(bi, "jpeg", outputFile);
        throw new Exception("Not implemented yet!");
	}
}
