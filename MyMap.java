//This class represents a roadmap. A graph will be used to store the roadmap and try to find a path from the source node to the destination node following the conditions specified in the input file.
//Author - Marc Alex Crasto
import java.io.*;
import java.util.Iterator;

public class MyMap {
	//private variable to store the input filename.
	private String inputFile;
	//private variable that stores the no. of privates specified allowed to be used in the file.
	private int PRIVATE_ROADS;
	//private variable that stores the no. of construction roads allowed to be used in the file.
	private int CONSTRUCTION_ROADS;
	//private variable that stores the no. of horizontal roads in each column.
	private int LENGTH;
	//private variable that stores the no. of vertical roads in each row.
	private int WIDTH;
	//private variable that stores the graph.
	private Graph G;
	//private variable that stores the no. of nodes in the graph.
	private int noOfNodes;
	//private variable that stores the source node.
	private Node START;
	//private variable that stores the destination node.
	private Node END;
	//private variable that stores the nodes that will be visited from the source node to the destination node.
	private LinkedList<Node> nodesInPath;
	//private variable that stores the iterator of all the nodes visited.
	private Iterator<Node> path;
	
	//constructor method that initialises all the private variables using the input file mentioned in the parameter. It also creates the graph, with its nodes and their respective incident edges.
	public MyMap(String inputFile) throws MapException{
		this.inputFile = inputFile;
		this.nodesInPath = new LinkedList<Node>();
		File file = new File(this.inputFile);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {;
			for(int i = 0;i<7;i++) {
				String str = br.readLine();
				if(i == 1) {
					Integer number = Integer.valueOf(str);
					this.START = new Node(number);
				}
				if(i == 2) {
					Integer number = Integer.valueOf(str);
					this.END = new Node(number);
				}
				if(i == 3) {
					Integer number = Integer.valueOf(str);
					this.WIDTH = number;
				}
				if(i == 4) {
					Integer number = Integer.valueOf(str);
					this.LENGTH = number;
				}
				if(i == 5) {
					Integer number = Integer.valueOf(str);
					this.PRIVATE_ROADS = number;
				}
				if(i == 6) {
					Integer number = Integer.valueOf(str);
					this.CONSTRUCTION_ROADS = number;
				}
			}
			this.noOfNodes = this.LENGTH*this.WIDTH;
			this.G = new Graph(this.noOfNodes);
			String str;
			int lineCount = 0;
			int horizontalCount = -1;
			int verticalCount = -1;
			while((str = br.readLine()) != null) {
				if(lineCount%2 == 0) {
					for(int i = 0;i<str.length();i++) {
						if(i%2 == 0) {
							horizontalCount++;
						}
						if(i%2 == 1) {
							if(str.charAt(i) == 'V') {
								try {
									Node u = G.getNode(horizontalCount);
									Node v = G.getNode(horizontalCount+1);
									G.addEdge(u, v, "private");
								} catch (GraphException e) {
									System.out.println("Graph Exception!");
								}
							}
							else if(str.charAt(i) == 'P') {
								try {
									Node u = G.getNode(horizontalCount);
									Node v = G.getNode(horizontalCount+1);
									G.addEdge(u, v, "public");
								} catch (GraphException e) {
									System.out.println("Graph Exception!");
								}
							}
							else if(str.charAt(i) == 'C') {
								try {
									Node u = G.getNode(horizontalCount);
									Node v = G.getNode(horizontalCount+1);
									G.addEdge(u, v, "construction");
								} catch (GraphException e) {
									System.out.println("Graph Exception!");
								}
							}
							else {
								//do nothing
							}
						}
					}
				}
				if(lineCount%2 == 1) {
					for(int i=0;i<str.length();i++) {
						if(i%2 == 0) {
							verticalCount++;
							if(str.charAt(i) == 'V') {
								try {
									Node u = G.getNode(verticalCount);
									Node v = G.getNode(verticalCount+this.WIDTH);
									G.addEdge(u, v, "private");
								} catch (GraphException e) {
									System.out.println("Graph Exception!");
								}
							}
							else if(str.charAt(i) == 'P') {
								try {
									Node u = G.getNode(verticalCount);
									Node v = G.getNode(verticalCount+this.WIDTH);
									G.addEdge(u, v, "public");
								} catch (GraphException e) {
									System.out.println("Graph Exception!");
								}
							}
							else if(str.charAt(i) == 'C') {
								try {
									Node u = G.getNode(verticalCount);
									Node v = G.getNode(verticalCount+this.WIDTH);
									G.addEdge(u, v, "construction");
								} catch (GraphException e) {
									System.out.println("Graph Exception!");
								}
							}
							else {
								//do nothing
							}
						}
					}
				}
				lineCount++;
			}
		} catch (IOException e) {
			throw new MapException();
		}
	}
	
	//public method that returns the graph storing the roadmap.
	public Graph getGraph() {
		return this.G;
	}
	
	//public method that returns the id of the source node.
	public int getStartingNode() {
		return this.START.getId();
	}
	
	//public method that returns the id of the destination node.
	public int getDestinationNode() {
		return this.END.getId();
	}
	
	//public method that returns the no. of private roads allowed to be used.
	public int maxPrivateRoads() {
		return this.PRIVATE_ROADS;
	}
	
	//public method that returns the no. of construction roads allowed to be used.
	public int maxConstructionRoads() {
		return this.CONSTRUCTION_ROADS;
	}
	
	//public method that returns an iterator all the nodes visited from the source node to the destination node while following all the conditions specified in the input file. If no path exists, returns null.
	public Iterator<Node> findPath(int start,int destination, int maxPrivate, int maxConstruction) throws GraphException {
		Node begin = G.getNode(start);
		begin.markNode(true);
		this.nodesInPath.insert(begin);
		if(begin.getId() == destination) {
			this.path = this.nodesInPath.iterator();
			return this.path;
		}
		else {
			Iterator<Edge> options = G.incidentEdges(begin);
			while(options.hasNext()) {
				Edge route = options.next();
				Node routeNode = route.secondNode();
				String routeType = route.getType();
				if(routeNode.getMark() == false) {
					if(routeType.equals("private")) {
						if(maxPrivate == 0) {
							//can't go that way
						}
						else {
							if(findPath(routeNode.getId(),destination,maxPrivate-1,maxConstruction) != null) {
								return this.path;
							}
						}
					}
					else if(routeType.equals("construction")) {
						if(maxConstruction == 0) {
							//can't go that way
						}
						else {
							if(findPath(routeNode.getId(),destination,maxPrivate,maxConstruction-1) != null) {
								return this.path;
							}
						}
					}
					else {
						if(findPath(routeNode.getId(),destination,maxPrivate,maxConstruction) != null) {
							return this.path;
						}
					}
				}
				else {
					//do nothing
				}
			}
			begin.markNode(false);
			this.nodesInPath.removeLast();
		}
		return this.path;
	}
}
