package data;

//This class to save an data like list but first in last out
public class MyStack<T> {
	
	// Pointer to Linked List.
	private MyLinkedList<T> linkedList;
	
	// Pointer to array.
	private Object[] array;
	
	// counter of top of stack.
	private int top;

	// This constructor to make object and initialize the Linked List object
	public MyStack() {
		linkedList = new MyLinkedList<T>();
	}

	// This constructor to make object and initialize the array object with size
	public MyStack(int size) {
		array = new Object[size];
	}

	// This method to return if there is no data in stack
	public boolean isEmpty() {
		return top == 0;
	}

	// This method to insert data in the top of stack
	public void push(T o) {
		if (linkedList != null) {
			linkedList.addFirst(o);
			top++;
		} else {
			if (!isFull()) {
				array[top] = o;
				top++;
			} else {
				System.out.println("stack is full");
			}
		}
	}

	// This method to get and remove data in the top of stack 
	public T pop() {
		if (!isEmpty()) {
			top--;
			if (linkedList != null) {
				T tmp = linkedList.getFirst();
				linkedList.removeFirst();
				return tmp;
			} else {
			
				return (T) array[top];
			}
		} else {
			System.out.println("stack is empty");
			return null;
		}
	}

	// This method to get data in the top of stack 
	public T top() {
		if (!isEmpty()) {
			if (linkedList != null) {
				return linkedList.getFirst();
			} else
				return (T) array[top - 1];
		} else {
			System.out.println("stack is empty");
			return null;
		}
	}

	// This method to get size of stack
	public int size() {
		return top;
	}

	// This method to return if the stack is full
	public boolean isFull() {
		if (linkedList != null)
			return false;
		return top == array.length;

	}
	





}