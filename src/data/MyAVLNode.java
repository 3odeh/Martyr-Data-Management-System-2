package data;


//This class for save data
public class MyAVLNode <T extends Comparable<T>> {
	
	// Attributes of node
	private int height;
	private T info ;
	private MyAVLNode<T> left , right;
	
	// Constructor to make objects of node with data
	public MyAVLNode(T info) {
		super();
		this.info = info;
	}
	
	// This method to get info
	public T getInfo() {
		return info;
	}
	
	// This method to get left node
	public MyAVLNode<T> getLeft() {
		return left;
	}
	
	// This method to get right node
	public MyAVLNode<T> getRight() {
		return right;
	}
	
	// This method to set info
	public void setInfo(T info) {
		this.info = info;
	}
	
	// This method to set left node
	public void setLeft(MyAVLNode<T> left) {
		this.left = left;
	}
	
	// This method to set right node
	public void setRight(MyAVLNode<T> right) {
		this.right = right;
	}
	
	// This method to get height
	public int getHeight() {
		return height;
	}
	// This method to set height
	public void setHeight(int height) {
		this.height = height;
	}
	
	
}