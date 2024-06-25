//This class represents a node in a linked list storing an element and a pointer to the next node.
//Author - Marc Alex Crastos
public class LinearNode<E> {
	//private variable that stores a pointer to the next node.
    private LinearNode<E> next;
    //private variable that stores an element within the node.
    private E element;
    
    //constructor that createas a node storing no element.
    public LinearNode()
    {
        next = null;
        element = null;
    }
    
    
    //constructor that creates a node storing the specified element.
    public LinearNode (E elem)
    {
        next = null;
        element = elem;
    }
    
    //public method that returns the node that follows this one.
    public LinearNode<E> getNext()
    {
        return next;
    }
    
    //public that sets the node that follows this one.
    public void setNext (LinearNode<E> node)
    {
        next = node;
    }
    
    //public method that returns the element stored in this node.
    public E getElement()
    {
        return element;
    }
    
    //public method that sets the element stored in this node.
    public void setElement (E elem)
    {
        element = elem;
    }
}

