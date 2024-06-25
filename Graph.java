//This class represents an undirected graph. The graph is represented through the use of an adjacency list.
//Author - Marc Alex Crasto
import java.util.Iterator;

public class Graph implements GraphADT {
	//private variable that stores the no. of nodes in the graph.
	private int n;
	//private variable that stores the nodes in an array.
	private Node[] nodeList;
	//private variable that stores the edges in an array that consists of linked lists.
	private LinkedList<Edge>[] edgeList;
	
	//constructor method that creates a graph with the no. of nodes specified in the parameter. The ids' of the nodes are initialised as well and stored in the array. The array that holds the incident edges for a particular node is initialised as well. 
	@SuppressWarnings("unchecked")
	public Graph(int n) {
		this.n = n;
		this.nodeList = new Node[n];
		this.edgeList = new LinkedList[n];
		for(int i = 0;i<n;i++) {
			this.nodeList[i] = new Node(i);
			this.nodeList[i].markNode(false);
		}
		for(int i = 0;i<n;i++) {
			this.edgeList[i] = new LinkedList<Edge>();
		}
	}
	
	//public method that returns the node with the id that is specified in the parameter. Returns null otherwise if node does not exist.
	public Node getNode(int id) throws GraphException {
		Node result = null;
		for(int i=0;i<this.n;i++) {
			if(this.nodeList[i] != null) {
				if(this.nodeList[i].getId() == id) {
					result = this.nodeList[i];
				}
			}
			else {
				//do nothing
			}
		}
		if(result == null) {
			throw new GraphException("Node does not exist");
		}
		else {
			return result;
		}
	}
	
	//public void method that stores the edge connecting two nodes in their corresponding edge lists. First checks if the edge has already been created and stored and also checks if both nodes exist.
	public void addEdge(Node u, Node v, String edgeType) throws GraphException {
		int countU = -1;
		int countV = -1;
		Edge newEdge = new Edge(u,v,edgeType);
		for(int i=0;i<this.n;i++) {
			if(this.nodeList[i] != null) {
				if(this.nodeList[i].getId() == u.getId()) {
					countU = i;
				}
				if(this.nodeList[i].getId() == v.getId()) {
					countV = i;
				}
				
			}
			else {
				//do nothing
			}
		}
		if(countU == -1 || countV == -1) {
			throw new GraphException("One of the nodes does not exist!");
		}
		else {
			LinkedList<Edge> currList = this.edgeList[countU];
			LinearNode<Edge> currNode = currList.getHead();
			while(currNode != null) {
				int currNodeFirstId = currNode.getElement().firstNode().getId();
				int currNodeSecondId = currNode.getElement().secondNode().getId();
				int uId = u.getId();
				int vId = v.getId();
				if(currNodeFirstId == uId && currNodeSecondId == vId) {
					break;
				}
				else if(currNodeFirstId == vId && currNodeSecondId == uId) {
					break;
				}
				else {
					currNode = currNode.getNext();
				}
			}
			if(currNode != null) {
				throw new GraphException("Edge already exists!");
			}
			else {
				currList.insert(newEdge);
				currList = this.edgeList[countV];
				Edge newEdge2 = new Edge(v,u,edgeType);
				currList.insert(newEdge2);
			}
		}
	}
	
	//public method that returns an iterator of all the edges that are incident on the node specified in the parameter. Returns null if node does not have any edges.
	public Iterator<Edge> incidentEdges(Node u) throws GraphException {
		Iterator<Edge> result = null;
		int countU = -1;
		for(int i=0;i<this.n;i++) {
			if(this.nodeList[i] != null) {
				if(this.nodeList[i].getId() == u.getId()) {
					countU = i;
				}
			}
			else {
				//do nothing
			}
		}
		if(countU == -1) {
			throw new GraphException("Node does not exist!");
		}
		else {
			if(!this.edgeList[countU].isEmpty()) {
				result = this.edgeList[countU].iterator();
			}
		}
		return result;
	}
	
	//public method that returns the edge connecting two nodes specified in the parameter. Returns null if no such edge exists.
	public Edge getEdge(Node u, Node v) throws GraphException{
		Edge result = null;
		int countU = -1;
		int countV = -1;
		for(int i=0;i<this.n;i++) {
			if(this.nodeList[i] != null) {
				if(this.nodeList[i].getId() == u.getId()) {
					countU = i;
				}
				if(this.nodeList[i].getId() == v.getId()) {
					countV = i;
				}
				
			}
			else {
				//do nothing
			}
		}
		if(countU == -1 || countV == -1) {
			throw new GraphException("One of the nodes does not exist!");
		}
		else {
			LinkedList<Edge> currList = this.edgeList[countU];
			LinearNode<Edge> currNode = currList.getHead();
			while(currNode != null) {
				int currNodeFirstId = currNode.getElement().firstNode().getId();
				int currNodeSecondId = currNode.getElement().secondNode().getId();
				int uId = u.getId();
				int vId = v.getId();
				if(currNodeFirstId == uId && currNodeSecondId == vId) {
					break;
				}
				else if(currNodeFirstId == vId && currNodeSecondId == uId) {
					break;
				}
				else {
					currNode = currNode.getNext();
				}
			}
			if(currNode != null) {
				result = currNode.getElement();
			}
			else {
				throw new GraphException("Edge does not exist!");
			}
		}
		return result;
	}
	
	//public method that returns true if two nodes(specified in parameter) are adjacent(have an edge connecting them). Returns false otherwise.
	public boolean areAdjacent(Node u, Node v) throws GraphException{
		boolean result = false;
		Edge check = null;
		int countU = -1;
		int countV = -1;
		for(int i=0;i<this.n;i++) {
			if(this.nodeList[i] != null) {
				if(this.nodeList[i].getId() == u.getId()) {
					countU = i;
				}
				if(this.nodeList[i].getId() == v.getId()) {
					countV = i;
				}
				
			}
			else {
				//do nothing
			}
		}
		if(countU == -1 || countV == -1) {
			throw new GraphException("One of the nodes does not exist!");
		}
		else {
			LinkedList<Edge> currList = this.edgeList[countU];
			LinearNode<Edge> currNode = currList.getHead();
			while(currNode != null) {
				int currNodeFirstId = currNode.getElement().firstNode().getId();
				int currNodeSecondId = currNode.getElement().secondNode().getId();
				int uId = u.getId();
				int vId = v.getId();
				if(currNodeFirstId == uId && currNodeSecondId == vId) {
					break;
				}
				else if(currNodeFirstId == vId && currNodeSecondId == uId) {
					break;
				}
				else {
					currNode = currNode.getNext();
				}
			}
			if(currNode != null) {
				check = currNode.getElement();
			}
			else {
				//do nothing
			}
		}
		if(check != null) {
			result = true;
		}
		return result;
	}
	
}
