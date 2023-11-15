package data;

//This class to save list of data
public class MyLinkedList<T> {

	// Pointer to first node.
	private MyLinkedListNode<T> first;
	// Pointer to last node.
	private MyLinkedListNode<T> last;
	// size of list
	private int count;

	// This method to return if there is no martyr
	public boolean isEmpty() {
		return (count == 0);
	}

	// This method to returns the first data in this list
	public T getFirst() {
		if (count != 0)
			return first.getElement();
		else
			return null;
	}

	// This method to returns the last data in this list
	public T getLast() {
		if (count != 0)
			return last.getElement();

		else
			return null;
	}

	// This method to add data in the first place in this list.
	public void addFirst(T o) {

		if (count == 0) {
			first = last = new MyLinkedListNode<T>(o);

		} else {
			MyLinkedListNode<T> tmp = new MyLinkedListNode<T>(o);
			tmp.setNext(first);
			first = tmp;
		}
		count++;
	}

	// This method to add data in the last place in this list.
	public void addLast(T o) {
		if (count == 0) {
			first = last = new MyLinkedListNode<T>(o);
		} else {
			MyLinkedListNode<T> tmp = new MyLinkedListNode<T>(o);
			last.setNext(tmp);
			last = tmp;
		}
		count++;
	}
	
	// This method to remove first data in this list.
	public boolean removeFirst() {
		if (count == 0) {
			return false;
		} else {
			if (count == 1) {
				last = first = null;
			}
			else {
				first = first.getNext();
			}
			count--;
			return true;
		}
	}

	// This method to get size 
	public int size() {
		return count;
	}

}