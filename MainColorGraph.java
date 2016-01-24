package colorgraph;
import java.io.*;
import java.util.*;
public class MainColorGraph{
	public static void main(String [] args){

		//create file and set to file sent from args array
		File adjList = null;
		if(args.length > 0){
			adjList = new File(args[0]);
		}
		//start reading file
		try(Scanner sc = new Scanner(adjList)){
			
			//read in total number of nodes
			int totVerts = sc.nextInt();

			//create graph
			Graph graph = new Graph(totVerts);

			//populate graph
			while(sc.hasNext()){
				int vert1 = sc.nextInt();
				int vert2 = sc.nextInt();

				//vert1 has not been made nor vert2
				Vertex[] vertices = graph.getVertices();
				Vertex v1;
				Vertex v2;
				if( (vertices[vert1] == null) && (vertices[vert2] == null) ){
					v1 = new Vertex(vert1);
					v2 = new Vertex(vert2);
					v1.addEdge(v2);
					v2.addEdge(v1);
					graph.addToGraph(v1);
					graph.addToGraph(v2);
				}
				//v1 doesn't exsist - v2 does
				else if( (vertices[vert1] == null) && (vertices[vert2] != null) ){
					v1 = new Vertex(vert1);
					v1.addEdge(vertices[vert2]);
					vertices[vert2].addEdge(v1);
					graph.addToGraph(v1);
				}
				//v1 exsists - v2 does not
				else if( (vertices[vert1] != null) && (vertices[vert2] == null) ){
					v2 = new Vertex(vert2);
					v2.addEdge(vertices[vert1]);
					vertices[vert1].addEdge(v2);
					graph.addToGraph(v2);
				}
				//both v1 and v2 exsist
				else{
					vertices[vert1].addEdge(vertices[vert2]);
					vertices[vert2].addEdge(vertices[vert1]);
				}
			}
			sc.close();

			//check two colorable
			graph = graph.isTwoColorable();
			
			//write to file
			PrintWriter pw = new PrintWriter(new FileWriter("graphoutput.txt"));

			if(graph.getVertices() == null){//print a cycle
				pw.println("no");
				for(Vertex v : graph.getCycle()){
					pw.println("Vertex "+v.getVertexNum()+"\n");
				}
			}
			else{//print a colored graph
				pw.println("yes");
				for(Vertex v : graph.getVertices()){
					if(v != null){
						pw.println("Vertex "+v.getVertexNum() +" "+v.getColor()+"\n");
					}
				}
			}
			pw.close();
			

		}
		catch(Exception e){
			System.out.println(e);
		}

	}
}