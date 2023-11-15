package data;

import java.util.Date;

//This class for save data with stack
public class MyAVLStackNode {

	// Attributes of node
	private int height;
	private Date date;
	private MyStack<Martyr> stack;
	private MyAVLStackNode left, right;
	private int size = 0;

	// Constructor to make objects of node with date and initialize the stack
	public MyAVLStackNode(Date date) {
		super();
		this.date = date;
		stack = new MyStack<>();
	}

	// This method to get left node
	public MyAVLStackNode getLeft() {
		return left;
	}

	// This method to get right node
	public MyAVLStackNode getRight() {
		return right;
	}

	// This method to set left node
	public void setLeft(MyAVLStackNode left) {
		this.left = left;
	}

	// This method to set right node
	public void setRight(MyAVLStackNode right) {
		this.right = right;
	}

	// This method to get height
	public int getHeight() {
		return height;
	}

	// This method to get date
	public Date getDate() {
		return date;
	}

	// This method to get stack
	public MyStack<Martyr> getStack() {
		return stack;
	}

	// This method to set date
	public void setDate(Date date) {
		this.date = date;
	}

	// This method to set stack
	public void setStack(MyStack<Martyr> stack) {
		this.stack = stack;
	}

	// This method to set height
	public void setHeight(int height) {
		this.height = height;
	}

	// This method to get size
	public int getSize() {
		return size;
	}

	// This method to set size
	public void setSize(int size) {
		this.size = size;
	}

	// This method to increment size
	public void incSize() {
		this.size++;
	}

	// This method to decrement size
	public void decSize() {
		this.size--;
	}

	// This method to get the all data of node and return it as string
	@Override
	public String toString() {
		String value = "Date : " + date + " ,Height : " + height + " ,Size : " + size + " ,Stack [\n";
		MyStack<Martyr> tmp = new MyStack<>();
		while (!stack.isEmpty()) {
			tmp.push(stack.top());
			value = value + "	" + stack.pop().getInfo("L") + "\n";
		}

		while (!tmp.isEmpty())
			stack.push(tmp.pop());

		value = value + "]";
		return value;
	}

}
