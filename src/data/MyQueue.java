package data;

// This class to save an data like list but first in first out
public class MyQueue<T> {

	// size of queue
	private int size;

	// Pointer to Linked List.
	private MyLinkedList<T> linkedList;

	// Pointer to array.
	private Object[] array;

	// This is font of array
	private int font;

	// This is rear of array
	private int rear;

	// This constructor to make object and initialize the Linked List object
	public MyQueue() {
		linkedList = new MyLinkedList<T>();
	}

	// This constructor to make object and initialize the array object with size
	public MyQueue(int size) {
		array = new Object[size + 2];
		rear = size + 1;
	}

	// This method to return if there is no data in queue
	public boolean isEmpty() {
		return size == 0;
	}

	// This method to return if there is data in queue
	public boolean isNotEmpty() {
		return size != 0;
	}

	// This method to get size of queue
	public int size() {
		return size;
	}

	// This method to get and remove the first data
	public T dequeue() {
		if (isEmpty())
			return null;
		size--;
		if (linkedList != null) {
			T o = linkedList.getFirst();
			linkedList.removeFirst();
			return o;
		} else {
			rear = getNext(rear);
			return (T) array[rear];
		}
	}

	// This method to insert data in the last of queue
	public void enqueue(T o) {
		if (!isFull()) {
			size++;
			if (linkedList != null) {
				linkedList.addLast(o);
			} else {
				array[font] = o;
				font = getNext(font);
			}
		} else
			System.out.println("The queue is full");
	}

	// This method to return if the queue is full
	public boolean isFull() {
		if (linkedList != null)
			return false;
		return (rear == getNext(font));
	}
	
	// This method to get the next of input index
	private int getNext(int c) {
		if (c == (array.length - 1))
			return 0;
		return c + 1;
	}

}