//This class represents a linked list storing multiple nodes.
//Author - Marc Alex Crasto
import java.util.Iterator;

public class LinkedList<T> {
	//private variable that stores the first node(head) in the list.
	private LinearNode<T> head;
	
	//constructor that creates a linked list and initialises the head node to point to null.
	public LinkedList() {
		head = null;
	}
	
	//public method that creates a node, stores data into the node, and stores the node into the linked list.
	public void insert(T data) {
		LinearNode<T> newNode = new LinearNode<T>(data);
		newNode.setNext(null);
		
		if(this.head == null) {
			newNode.setNext(head);
			head = newNode;
		}
		else {
			LinearNode<T> currNode = head;
			while(currNode.getNext() != null) {
				currNode = currNode.getNext();
			}
			currNode.setNext(newNode);
		}
	}
	
	//public method that removes a node that contains the data specified in the parameter from the linked list.
	public void remove(T data) {
		LinearNode<T> currNode = this.head;
		LinearNode<T> prev = null;
		
		if(currNode != null && currNode.getElement() == data) {
			this.head = currNode.getNext();
		}
		else {
			while(currNode != null && currNode.getElement() != data) {
				prev = currNode;
				currNode = currNode.getNext();
			}
			if(currNode != null) {
				prev.setNext(currNode.getNext());
			}
			else {
				//do nothing
			}
		}
	}
	
	//public method that removes the last node in the list.
	public void removeLast() {
		if(this.head == null) {
			//do nothing
		}
		else {
			LinearNode<T> currNode = this.head;
			LinearNode<T> prev = null;
			if(currNode.getNext() == null) {
				this.head = currNode.getNext();
			}
			else {
				while(currNode.getNext() != null) {
					prev = currNode;
					currNode = currNode.getNext();
				}
				prev.setNext(currNode.getNext());
			}
		}	
	}
	
	//public method that returns the head node of the list.
	public LinearNode<T> getHead() {
		return this.head;
	}
	
	//public method that returns true if the list is empty. Returns false otherwise.
	public boolean isEmpty() {
		return this.head == null;
	}
	
	//public method that clears the list.
	public void clear() {
		this.head = null;
	}
	
	//public method that returns an iterator of this list.
	public Iterator<T> iterator() {
		return new LinkedListIterator<T>(this.head);
	}
}
