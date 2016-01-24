package colorgraph;

import java.util.*;

public class Vertex{
	
	private ArrayList<Vertex> edges;
	private String visitColor;
	private String color;
	private Vertex parent;
	private int vertexNum;

	public Vertex(int num){
		edges = new ArrayList<Vertex>();
		parent = null;
		color = null;
		visitColor = "white";
		vertexNum = num;
	}
	public void addEdge(Vertex vert){
		edges.add(vert);
	}
	public ArrayList getEdges(){
		return edges;
	}
	public String getVisitColor(){
		return visitColor;
	}
	public int getVertexNum(){
		return vertexNum;
	}
	public Vertex getParent(){
		return parent;
	}
	public String getColor(){
		return color;
	}
	public void setVisitColor(String newColor){
		visitColor = newColor;
	}
	public void setColor(String newColor){
		color = newColor;
	}
	public void colorVertex(){
		if(parent == null){
			color = "red";
		}
		else if(parent.getColor() == "red"){
			color = "green";
		}
		else{
			color = "red";
		}
	}
	public void setParent(Vertex v){
		parent = v;
	}
	public Vertex getWhiteChild(){
		for(Vertex v : edges){
			if(v.getVisitColor() == "white"){
				return v;
			}
		}
		return null;
	}
	public Vertex hasGreyNonParent(){
		for(Vertex v : edges){
			if(v.getVisitColor() == "grey" && this.getParent() != v){
				return v;
			}
		}
		return null;
	}

}