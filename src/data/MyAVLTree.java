package data;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

// This class to save data and sort depend the compare method
public class MyAVLTree<T extends Comparable<T>> {

	// Pointer to root node.
	private MyAVLNode<T> root;

	// This method to invoke the find method
	public MyAVLNode<T> find(T element) {
		return find(element, root);
	}

	// This method to get height of tree
	public int getHeight() {
		return getHeight(root);
	}

	// This method to get maximum value of tree
	public T getMax() {
		if (root == null)
			return null;
		MyAVLNode<T> current = root;
		while (current.getRight() != null) {
			current = current.getRight();
		}
		return current.getInfo();
	}

	// This method to get minimum value of tree
	public T getMin() {
		if (root == null)
			return null;
		MyAVLNode<T> current = root;
		while (current.getLeft() != null) {
			current = current.getLeft();
		}
		return current.getInfo();
	}

	// This method to invoke method to add new value to the tree
	public void insert(T element) {
		if (element == null)
			return;
		root = insert(element, root);
	}

	// This method to invoke method to delete value from the tree
	public boolean delete(T element) {
		if (root == null || element == null)
			return false;

		MyAVLNode<T> tmp = delete(element, root);
		if (tmp != null) {
			root = tmp;
			return true;
		} else
			return false;
	}

	// This method to invoke method to print the tree in order
	// (left -> root -> right)
	public void printInOrder() {
		System.out.println();
		printInOrder(root);
		System.out.println(",");
	}

	// This method to invoke method to print the tree in previous order
	// (root -> left -> right)
	public void printPreOrder() {
		System.out.println();
		printPreOrder(root);
		System.out.println(",");
	}

	// This method to invoke method to print the tree in post order
	// (left -> right -> root)
	public void printPostOrder() {
		System.out.println();
		printPostOrder(root);
		System.out.println(",");
	}

	// This method to print the tree level by level
	public void printLevelByLevel() {
		System.out.println();
		if (root != null) {
			MyQueue<MyAVLNode<T>> tmpQueue = new MyQueue<>();
			tmpQueue.enqueue(root);
			for (int lvl = 0; tmpQueue.isNotEmpty(); lvl++) {
				System.out.println("Level : " + lvl);
				int size = tmpQueue.size();
				System.out.println(size);
				for (int i = 0; i < size; i++) {
					MyAVLNode<T> tmpNode = tmpQueue.dequeue();
					System.out.println("," + tmpNode);
					if (tmpNode.getLeft() != null)
						tmpQueue.enqueue(tmpNode.getLeft());
					if (tmpNode.getRight() != null)
						tmpQueue.enqueue(tmpNode.getRight());
				}
			}
		}
		System.out.println(",");
	}

	// This method to add node of tree level by level in VBox
	public void addDataLevelByLevel(VBox vBox) {

		if (root != null) {
			MyQueue<MyAVLNode<T>> tmpQueue = new MyQueue<>();
			tmpQueue.enqueue(root);
			for (int lvl = 0; tmpQueue.isNotEmpty(); lvl++) {
				Label levelLabel = new Label("Level " + lvl);
				levelLabel.setFont(new Font("Arial", 24));
				vBox.getChildren().add(levelLabel);

				int size = tmpQueue.size();
				for (int i = 0; i < size; i++) {
					MyAVLNode<T> tmpNode = tmpQueue.dequeue();
					Label martyrLabel = new Label(tmpNode.getInfo().toString());
					martyrLabel.setFont(new Font("Arial", 20));
					vBox.getChildren().add(martyrLabel);
					if (tmpNode.getLeft() != null)
						tmpQueue.enqueue(tmpNode.getLeft());
					if (tmpNode.getRight() != null)
						tmpQueue.enqueue(tmpNode.getRight());
				}
			}
		}

	}

	// -------------------------------------------------- Private Methods --------------------------------------------------

	// This method to find a node of input element
	private MyAVLNode<T> find(T element, MyAVLNode<T> root) {

		if (root == null || element == null)
			return null;
		int c = element.compareTo(root.getInfo());
		if (c == 0) {
			return root;
		} else if (c < 0) {
			return find(element, root.getLeft());
		} else {
			return find(element, root.getRight());
		}
	}

	// This method to get minimum value of tree
	private MyAVLNode<T> getMin(MyAVLNode<T> root) {
		if (root == null)
			return null;
		MyAVLNode<T> current = root;
		while (current.getLeft() != null) {
			current = current.getLeft();
		}
		return current;
	}

	// This method to insert new value
	private MyAVLNode<T> insert(T element, MyAVLNode<T> root) {
		if (root == null) {
			return new MyAVLNode<T>(element);
		}
		int c = element.compareTo(root.getInfo());
		if (c < 0) {
			root.setLeft(insert(element, root.getLeft()));
			if (Math.abs(getHeight(root.getLeft()) - getHeight(root.getRight())) > 1) {
				if (element.compareTo(root.getLeft().getInfo()) < 0)
					root = singleLeftRotate(root);
				else
					root = doubleLeftRotate(root);
			}

		} else if (c >= 0) {
			root.setRight(insert(element, root.getRight()));
			if (Math.abs(getHeight(root.getRight()) - getHeight(root.getLeft())) > 1) {
				if (element.compareTo(root.getRight().getInfo()) >= 0)
					root = singleRightRotate(root);
				else
					root = doubleRightRotate(root);
			}
		}
		changeHeight(root);

		return root;
	}

	// This method to delete node of input value
	private MyAVLNode<T> delete(T element, MyAVLNode<T> root) {
		if (root == null)
			return null;
		int c = element.compareTo(root.getInfo());
		if (c < 0) {
			root.setLeft(delete(element, root.getLeft()));
		} else if (c > 0) {
			root.setRight(delete(element, root.getRight()));
		} else {
			if (root.getLeft() != null && root.getRight() != null) {
				MyAVLNode<T> node = getMin(root.getRight());
				root.setInfo(node.getInfo());
				root.setRight(delete(root.getInfo(), root.getRight()));
			} else {
				if (root.getLeft() == null)
					return root.getRight();
				if (root.getRight() == null)
					return root.getLeft();
			}
		}
		changeHeight(root);
		return root;
	}

	// This method to print the tree in order
	// (left -> root -> right)
	private void printInOrder(MyAVLNode<T> root) {
		if (root != null) {
			printInOrder(root.getLeft());
			System.out.print("," + root.getInfo() + " : H =" + root.getHeight());
			printInOrder(root.getRight());
		}
	}

	// This method to print the tree in previous order
	// (root -> left -> right)
	private void printPreOrder(MyAVLNode<T> root) {
		if (root != null) {
			System.out.print("," + root.getInfo());
			printPreOrder(root.getLeft());
			printPreOrder(root.getRight());
		}
	}

	// This method to print the tree in post order
	// (left -> right -> root)
	private void printPostOrder(MyAVLNode<T> root) {
		if (root != null) {
			printPostOrder(root.getLeft());
			printPostOrder(root.getRight());
			System.out.print("," + root.getInfo());
		}
	}

	// This method to do single rotate from left to right to make balance
	private MyAVLNode<T> singleLeftRotate(MyAVLNode<T> k2) {
		MyAVLNode<T> k1 = k2.getLeft();
		k2.setLeft(k1.getRight());
		k1.setRight(k2);
		changeHeight(k2);
		changeHeight(k1);
		return k1;
	}

	// This method to do single rotate from right to left to make balance
	private MyAVLNode<T> singleRightRotate(MyAVLNode<T> k1) {
		MyAVLNode<T> k2 = k1.getRight();
		k1.setRight(k2.getLeft());
		k2.setLeft(k1);
		changeHeight(k2);
		changeHeight(k1);
		return k2;
	}

	// This method to do double rotate from left to right to make balance
	private MyAVLNode<T> doubleLeftRotate(MyAVLNode<T> k1) {
		k1.setLeft(singleRightRotate(k1.getLeft()));
		return singleLeftRotate(k1);
	}

	// This method to do double rotate from right to left to make balance
	private MyAVLNode<T> doubleRightRotate(MyAVLNode<T> k1) {
		k1.setRight(singleLeftRotate(k1.getRight()));
		return singleRightRotate(k1);
	}

	// This method to change the height of node depend in children
	private void changeHeight(MyAVLNode<T> node) {
		if (node.getRight() == null && node.getLeft() == null)
			node.setHeight(0);
		else if (node.getRight() == null)
			node.setHeight(node.getLeft().getHeight() + 1);
		else if (node.getLeft() == null)
			node.setHeight(node.getRight().getHeight() + 1);
		else
			node.setHeight(((node.getRight().getHeight() > node.getLeft().getHeight()) ? node.getRight().getHeight()
					: node.getLeft().getHeight()) + 1);
	}

	// This method to get height of input node
	private int getHeight(MyAVLNode<T> node) {
		if (node == null)
			return -1;

		return node.getHeight();

	}
}