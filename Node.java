//This class represents a node of the graph.
//Author - Marc Alex Crasto
public class Node {
	//private variable to store id of the node.
	private int id;
	//private variable to store the node's mark - true or false.
	private boolean mark;

	//constructor for the node class that creates a node with an id that is specified in the parameter.
	public Node(int id) {
		this.id = id;
	}
	
	//public method that marks the node with either true or false.
	public void markNode(boolean mark) {
		this.mark = mark;
	}
	
	//public method that returns the id of the node.
	public int getId() {
		return this.id;
	}
	
	//public method that returns of the mark of the node.
	public boolean getMark() {
		return this.mark;
	}
}
