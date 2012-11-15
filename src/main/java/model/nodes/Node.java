package model.nodes;

import java.awt.geom.Point2D;
import java.util.*;

import com.google.common.collect.Iterables;

public abstract class Node extends Point2D.Double {
	
	private static final long serialVersionUID = 1L;

	public abstract int getId();
	
	public abstract double getX();
	
	public abstract double getY();
	
	

	
}
