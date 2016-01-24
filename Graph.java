package colorgraph;

import java.util.*;

public class Graph{
	
	private Vertex[] vertices;
	private ArrayList<Vertex> cycle;
	public Graph(int numVerts){
		if(numVerts == -1){			//will recieve -1 if using graph object for storing cycle
			vertices = null;
		}
		else{
			vertices = new Vertex[numVerts+1];
		}
		cycle = new ArrayList<Vertex>();
	}
	public void addToGraph(Vertex vertex){	//vertices are stored at the index equal to vertex number
		vertices[vertex.getVertexNum()] = vertex;
	}
	public Vertex[] getVertices(){
		return vertices;
	}
	public int getNumVerts(){
		return vertices.length;
	}
	public Graph isTwoColorable(){

		Graph returnGraph;
		for(Vertex vertex : vertices){									//cycle through all vertices to make sure we found every part of graph
			if(vertex != null && vertex.getVisitColor() == "white"){	//only dfs on nodes that have not been visited	

				//initial source vertex setup
				Stack<Vertex> visitingStack = new Stack<Vertex>();
				visitingStack.push(vertex);
				vertex.setVisitColor("grey");

				//dfs
				while(visitingStack.empty() == false){
					vertex = visitingStack.peek();
					//color vertices as we dfs - not to confused with visitation color
					vertex.colorVertex();

					//if grey child that isnt parent - backedge found
					Vertex v = vertex.hasGreyNonParent();
					if( v != null ){
						int pos = visitingStack.search(v);
						//odd length cycle - invalid
						if( (pos % 2) != 0 ){System.out.println("invalid cycle");
							returnGraph = getCycle(visitingStack, pos);
							System.out.println("returnGraph");
							return returnGraph;
						}
					}

					//if node has white child - go to white child
					Vertex vw = vertex.getWhiteChild();
					if( vw != null ){
						vw.setParent(vertex);
						vw.setVisitColor("grey");
						visitingStack.push(vw);
					}
					//no invalid cycle found, no child to go to - done visiting
					else{
						vertex.setVisitColor("black");
						visitingStack.pop();
					}		
				}
			}
		}
		//end of looking at all nodes and no invalid cycles found - must be 2 colorable
		returnGraph = this;
		return returnGraph;	
	}
	public ArrayList<Vertex> getCycle(){
		return cycle;
	}
	public void addToCycle(Vertex v){
		cycle.add(v);
	}
	public Graph getCycle(Stack<Vertex> stack, int cycleSize){
		System.out.println(cycleSize);
		Graph returnGraph = new Graph(-1);
		while(cycleSize > 0){
			returnGraph.addToCycle(stack.pop());
			cycleSize--;
		}
		return returnGraph;
	}
}
