//This class represents an edge(between 2 nodes) of the graph.
//Author - Marc Alex Crasto
public class Edge {
	//private variable to store the source node.
	private Node u;
	//private variable to store the destination node.
	private Node v;
	//private variable to store the type of the edge.
	private String type;
	
	//constructor method that initialises the private variables of the edge with the values specified in the parameters.
	public Edge(Node u,Node v,String type) {
		this.u = u;
		this.v = v;
		this.type = type;
	}
	
	//public method that returns the source node of the edge.
	public Node firstNode() {
		return this.u;
	}
	
	//public method that returns the destination node of the edge.
	public Node secondNode() {
		return this.v;
	}
	
	//public method that returns the type of the edge.
	public String getType() {
		return this.type;
	}
}
