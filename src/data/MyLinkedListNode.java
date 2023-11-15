package data;

//This class for save data and the next node
public class MyLinkedListNode<T> {

	// Attributes of node
	private T element;
	private MyLinkedListNode<T> next;

	// Constructor to make objects of node with data
	public MyLinkedListNode(T o) {
		element = o;
	}

	// This method to get data
	public T getElement() {
		return element;
	}

	// This method to set data
	public void setElement(T element) {
		this.element = element;
	}

	// This method to get next node
	public MyLinkedListNode<T> getNext() {
		return next;
	}

	// This method to set new next node
	public void setNext(MyLinkedListNode<T> next) {
		this.next = next;
	}
	
	
}