//This class represents an iterator for a linked list.
//Author - Marc Alex Crasto
import java.util.Iterator;

public class LinkedListIterator<T> implements Iterator<T> {
	//private variable that stores the current node that the iterator is on.
	private LinearNode<T> currNode;
	
	//constructor method that creates an iterator an initialises the current node to the node in a linked list(specified in the parameter).
	public LinkedListIterator(LinearNode<T> start) {
		this.currNode = start;
	}
	
	//public method that returns true if the iterator's current node is not null. Returns false otherwise.
	public boolean hasNext() {
		return currNode != null;
	}
	
	//public method that returns the current node that the iterator is on if it is there. Returns null otherwise.
	public T next() {
		if(hasNext()) {
			LinearNode<T> temp = currNode;
			currNode = currNode.getNext();
			return temp.getElement();
		}
		else {
			return null;
		}
	}
	
}
