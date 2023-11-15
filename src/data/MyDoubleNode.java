package data;



//This class for save data of brand with linked list of car and the next and pre node
public class MyDoubleNode {

	// Attributes of double node
	private String location;
	private MyAVLStackTree dateTree;
	private MyAVLTree<Martyr> martyrTree;
	private MyDoubleNode pre, next;

	// Constructor to make objects of double node with location data and initialize
	// the trees
	public MyDoubleNode(String location) {
		super();
		this.location = location;
		this.dateTree = new MyAVLStackTree();
		this.martyrTree = new MyAVLTree<>();
	}

	// This method to get location
	public String getLocation() {
		return location;
	}

	// This method to get date tree
	public MyAVLStackTree getDateTree() {
		return dateTree;
	}

	// This method to get martyr tree
	public MyAVLTree<Martyr> getMartyrTree() {
		return martyrTree;
	}

	// This method to get previous double node
	public MyDoubleNode getPre() {
		return pre;
	}

	// This method to get next double node
	public MyDoubleNode getNext() {
		return next;
	}

	// This method to set update location
	public void setLocation(String location) {
		this.location = location;
	}

	// This method to set set new date tree
	public void setDateTree(MyAVLStackTree dateTree) {
		this.dateTree = dateTree;
	}

	// This method to set set new martyr tree
	public void setMartyrTree(MyAVLTree<Martyr> martyrTree) {
		this.martyrTree = martyrTree;
	}

	// This method to set new previous double node
	public void setPre(MyDoubleNode pre) {
		this.pre = pre;
	}

	// This method to set new next double node
	public void setNext(MyDoubleNode next) {
		this.next = next;
	}

}
