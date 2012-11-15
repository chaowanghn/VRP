package model.nodes;

import java.awt.geom.Point2D;
import java.util.*;

import com.google.common.collect.Iterables;

public abstract class Node extends Point2D.Double {
	
	private static final long serialVersionUID = 1L;

	public abstract int getId();
	
	public abstract double getX();
	
	public abstract double getY();
	
	public boolean equals(Object obj) {
	   if(obj instanceof Node){
		   final Node other = (Node) obj;
		   return Objects.equals(getId(), other.getId())
				   && Objects.equals(getX(), other.getX())
				   && Objects.equals(getY(), other.getY());
	   }
	   else {
		   return false;
	   }
	 }
	
	public int hashCode() {
		return Objects.hash(getId(),getX(),getY());
	}

	
}
