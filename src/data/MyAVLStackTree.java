package data;

import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

// This class to save data with sort depend on date of martyr
public class MyAVLStackTree {

	// Pointer to root node.
	private MyAVLStackNode root;

	// For size of martyr in this tree
	private int size;

	// this method for check if the tree is empty
	public boolean isEmpty() {
		return root == null;
	}

	// this method for check if the tree is not empty
	public boolean isNotEmpty() {
		// TODO Auto-generated method stub
		return root != null;
	}

	// This method to get height of tree
	public int getHeight() {
		return getHeight(root);
	}

	// This method to get maximum value of input root
	public MyAVLStackNode getMin(MyAVLStackNode root) {
		if (root == null)
			return null;
		MyAVLStackNode current = root;
		while (current.getLeft() != null) {
			current = current.getLeft();
		}
		return current;
	}

	// This method to invoke method to add new value to the tree
	public MyAVLStackNode insert(Martyr element) {
		if (element == null)
			return null;

		root = insert(element, root);
		size++;
		return root;
	}

	// This method to delete martyr
	public boolean delete(Martyr element) {
		if (root == null || element == null)
			return false;
		MyAVLStackNode tmpNode = find(element, root);
		if (tmpNode != null) {
			MyStack<Martyr> tmp = new MyStack<>();
			while (!tmpNode.getStack().isEmpty() && !tmpNode.getStack().top().equals(element)) {
				tmp.push(tmpNode.getStack().pop());
			}
			if (tmpNode.getStack().isEmpty()) {
				while (!tmp.isEmpty()) {
					tmpNode.getStack().push(tmp.pop());
				}
				return false;
			} else {
				tmpNode.getStack().pop();
				while (!tmp.isEmpty()) {
					tmpNode.getStack().push(tmp.pop());
				}
				tmpNode.decSize();
				if (tmpNode.getStack().isEmpty()) {
					root = delete(tmpNode.getDate(), root);
				}
				size--;
				return true;
			}
		} else {
			return false;
		}
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
			MyQueue<MyAVLStackNode> tmpQueue = new MyQueue<>();
			tmpQueue.enqueue(root);
			for (int lvl = 0; tmpQueue.isNotEmpty(); lvl++) {
				System.out.println("Level : " + lvl);
				int size = tmpQueue.size();
				for (int i = 0; i < size; i++) {
					MyAVLStackNode tmpNode = tmpQueue.dequeue();
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
			MyQueue<MyAVLStackNode> tmpQueue = new MyQueue<>();
			tmpQueue.enqueue(root);
			for (int lvl = 0; tmpQueue.isNotEmpty(); lvl++) {
				Label levelLabel = new Label("Level " + lvl);
				levelLabel.setFont(new Font("Arial", 24));
				vBox.getChildren().add(levelLabel);

				int size = tmpQueue.size();
				for (int i = 0; i < size; i++) {
					MyAVLStackNode tmpNode = tmpQueue.dequeue();

					MyStack<Martyr> stack = tmpNode.getStack();
					MyStack<Martyr> tmpStack = new MyStack<>();
					while (!stack.isEmpty()) {

						Martyr tmpMartyr = stack.pop();
						tmpStack.push(tmpMartyr);

						Label martyrLabel = new Label(tmpMartyr.toString());
						martyrLabel.setFont(new Font("Arial", 20));
						vBox.getChildren().add(martyrLabel);
					}

					while (!tmpStack.isEmpty())
						stack.push(tmpStack.pop());

					if (tmpNode.getLeft() != null)
						tmpQueue.enqueue(tmpNode.getLeft());
					if (tmpNode.getRight() != null)
						tmpQueue.enqueue(tmpNode.getRight());
				}
			}
		}
	}

	// This method to invoke method to add all node of tree in TableView
	public void addDataToTableView(TableView<Martyr> tv) {
		addDataToTableView(tv, root);
	}

	// This method to invoke method to add all node of tree in TableView with reverse
	public void addDataRevToTableView(TableView<Martyr> tv) {
		addDataRevToTableView(tv, root);
	}

	// This method to invoke method to add all node of tree in TableView input with search
	public void addDataToTableView(TableView<Martyr> tv, String search) {
		addDataToTableView(tv, search, root);
	}

	// This method to invoke method to add all node of tree in TableView with input location
	public void addAllDataToTableView(TableView<AllData> tv, String location) {
		addAllDataToTableView(tv, location, root);
	}

	// This method to invoke method to add all node of tree in TableView with input search and
	// location
	public void addAllDataToTableView(TableView<AllData> tv, String location, String search) {
		addAllDataToTableView(tv, location, search, root);
	}

	// This method to invoke method to get date that have maximum number of martyr
	public Date getDateOfMaxNum() {
		if (root == null)
			return null;
		return getDateOfMaxNum(root).getDate();
	}

	// This method to get size
	public int getSize() {
		return size;
	}

	// This method to invoke method to print all node of tree in input file with input location
	public void printListToFile(PrintWriter pw, String location) {
		printListToFile(pw, location, root);
	}

	// --------------------------------------------------  Private Methods  --------------------------------------------------
	
	// This method to insert new value
	private MyAVLStackNode insert(Martyr element, MyAVLStackNode root) {
		if (root == null) {
			MyAVLStackNode insertNode = new MyAVLStackNode(element.getDateOfDeath());
			insertNode.getStack().push(element);
			insertNode.incSize();
			return insertNode;
		}
		int c = element.compareTo(root.getDate());
		if (c == 0) {
			root.getStack().push(element);
			root.incSize();
			return root;
		}
		if (c < 0) {
			root.setLeft(insert(element, root.getLeft()));
			if (Math.abs(getHeight(root.getLeft()) - getHeight(root.getRight())) > 1) {
				if (element.compareTo(root.getLeft().getDate()) < 0)
					root = singleLeftRotate(root);
				else
					root = doubleLeftRotate(root);
			}

		} else if (c > 0) {
			root.setRight(insert(element, root.getRight()));
			if (Math.abs(getHeight(root.getRight()) - getHeight(root.getLeft())) > 1) {
				if (element.compareTo(root.getRight().getDate()) >= 0)
					root = singleRightRotate(root);
				else
					root = doubleRightRotate(root);
			}
		}
		changeHeight(root);
		return root;
	}

	// This method to delete node of input value
	private MyAVLStackNode delete(Date element, MyAVLStackNode root) {
		if (root == null)
			return null;
		int c = 0;

		if (root.getDate() != null && element != null)
			c = element.compareTo(root.getDate());
		else if (root.getDate() == null && element == null) {
			c = 0;
		} else if (root.getDate() == null) {
			c = 1;
		} else {
			c = -1;
		}
		if (c < 0) {
			root.setLeft(delete(element, root.getLeft()));
		} else if (c > 0) {
			root.setRight(delete(element, root.getRight()));
		} else {

			if (root.getLeft() != null && root.getRight() != null) {
				MyAVLStackNode node = getMin(root.getRight());
				root.setDate(node.getDate());
				while (!node.getStack().isEmpty())
					root.getStack().push(node.getStack().pop());
				root.setRight(delete(root.getDate(), root.getRight()));
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

	// This method to find a node of input element
	private MyAVLStackNode find(Martyr element, MyAVLStackNode root) {

		if (root == null || element == null)
			return null;
		int c = 0;
		if (root.getDate() != null && element.getDateOfDeath() != null)
			c = element.getDateOfDeath().compareTo(root.getDate());
		else if (root.getDate() == null && element.getDateOfDeath() == null) {
			c = 0;
		} else if (root.getDate() == null) {
			c = 1;
		} else {
			c = -1;
		}

		if (c == 0) {
			return root;
		} else if (c < 0) {
			return find(element, root.getLeft());
		} else {
			return find(element, root.getRight());
		}
	}

	// This method to print the tree in order
	// (left -> root -> right)
	private void printInOrder(MyAVLStackNode root) {
		if (root != null) {
			printInOrder(root.getLeft());
			System.out.print("," + root);
			printInOrder(root.getRight());
		}
	}
	// This method to print the tree in previous order
	// (root -> left -> right)
	private void printPreOrder(MyAVLStackNode root) {
		if (root != null) {
			System.out.print("," + root);
			printPreOrder(root.getLeft());
			printPreOrder(root.getRight());
		}
	}

	// This method to print the tree in post order
	// (left -> right -> root)
	private void printPostOrder(MyAVLStackNode root) {
		if (root != null) {
			printPostOrder(root.getLeft());
			printPostOrder(root.getRight());
			System.out.print("," + root);
		}
	}

	// This method to do single rotate from left to right to make balance
	private MyAVLStackNode singleLeftRotate(MyAVLStackNode k2) {
		MyAVLStackNode k1 = k2.getLeft();
		k2.setLeft(k1.getRight());
		k1.setRight(k2);
		changeHeight(k2);
		changeHeight(k1);
		return k1;
	}

	// This method to do single rotate from right to left to make balance
	private MyAVLStackNode singleRightRotate(MyAVLStackNode k1) {
		MyAVLStackNode k2 = k1.getRight();
		k1.setRight(k2.getLeft());
		k2.setLeft(k1);
		changeHeight(k2);
		changeHeight(k1);
		return k2;
	}

	// This method to do double rotate from left to right to make balance
	private MyAVLStackNode doubleLeftRotate(MyAVLStackNode k1) {
		k1.setLeft(singleRightRotate(k1.getLeft()));
		return singleLeftRotate(k1);
	}

	// This method to do double rotate from right to left to make balance
	private MyAVLStackNode doubleRightRotate(MyAVLStackNode k1) {
		k1.setRight(singleLeftRotate(k1.getRight()));
		return singleRightRotate(k1);
	}

	// This method to change the height of node depend in children
	private void changeHeight(MyAVLStackNode node) {
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
	private int getHeight(MyAVLStackNode node) {
		if (node == null)
			return -1;

		return node.getHeight();

	}

	// This method to add invoke method to all node of tree in TableView
	private void addDataToTableView(TableView<Martyr> tv, MyAVLStackNode root) {
		if (root != null) {
			addDataToTableView(tv, root.getRight());

			MyStack<Martyr> tmp = new MyStack<>();
			while (!root.getStack().isEmpty()) {

				Martyr m = root.getStack().pop();
				tmp.push(m);
				tv.getItems().add(m);

			}
			while (!tmp.isEmpty())
				root.getStack().push(tmp.pop());

			addDataToTableView(tv, root.getLeft());
		}

	}

	// This method to add all node of tree in TableView with reverse
	private void addDataRevToTableView(TableView<Martyr> tv, MyAVLStackNode root) {

		if (root != null) {

			addDataRevToTableView(tv, root.getRight());

			MyStack<Martyr> tmp = new MyStack<>();
			while (!root.getStack().isEmpty()) {

				Martyr m = root.getStack().pop();
				tmp.push(m);
				tv.getItems().add(m);

			}
			while (!tmp.isEmpty())
				root.getStack().push(tmp.pop());

			addDataRevToTableView(tv, root.getLeft());
		}

	}

	// This method to add all node of tree in TableView input with search
	private void addDataToTableView(TableView<Martyr> tv, String search, MyAVLStackNode root) {
		if (root != null) {
			addDataToTableView(tv, search, root.getLeft());

			MyStack<Martyr> tmp = new MyStack<>();
			while (!root.getStack().isEmpty()) {
				if (root.getStack().top().getName().contains(search))
					tv.getItems().add(root.getStack().top());
				tmp.push(root.getStack().pop());
			}
			while (!tmp.isEmpty())
				root.getStack().push(tmp.pop());

			addDataToTableView(tv, search, root.getRight());
		}

	}

	// This method to add all node of tree in TableView with input location
	private void addAllDataToTableView(TableView<AllData> tv, String location, MyAVLStackNode root) {
		if (root != null) {
			addAllDataToTableView(tv, location, root.getLeft());

			MyStack<Martyr> tmp = new MyStack<>();
			while (!root.getStack().isEmpty()) {

				tv.getItems().add(new AllData(root.getStack().top(), location));
				tmp.push(root.getStack().pop());
			}
			while (!tmp.isEmpty())
				root.getStack().push(tmp.pop());

			addAllDataToTableView(tv, location, root.getRight());
		}

	}

	// This method to add all node of tree in TableView with input location and search
	private void addAllDataToTableView(TableView<AllData> tv, String location, String search, MyAVLStackNode root) {
		if (root != null) {
			addAllDataToTableView(tv, location, search, root.getLeft());

			MyStack<Martyr> tmp = new MyStack<>();
			while (!root.getStack().isEmpty()) {
				if (root.getStack().top().getName().contains(search))
					tv.getItems().add(new AllData(root.getStack().top(), location));
				tmp.push(root.getStack().pop());
			}
			while (!tmp.isEmpty())
				root.getStack().push(tmp.pop());

			addAllDataToTableView(tv, location, search, root.getRight());
		}

	}

	// This method to get date that have maximum number of martyr
	private MyAVLStackNode getDateOfMaxNum(MyAVLStackNode root) {

		if (root.getLeft() == null && root.getRight() == null)
			return root;
		if (root.getLeft() == null)
			return (root.getSize() >= root.getRight().getSize()) ? root : root.getRight();
		if (root.getRight() == null)
			return (root.getLeft().getDate() == null || root.getSize() >= root.getLeft().getSize()) ? root
					: root.getLeft();

		MyAVLStackNode left = getDateOfMaxNum(root.getLeft());
		MyAVLStackNode right = getDateOfMaxNum(root.getRight());

		if (right.getSize() > root.getSize() && (left.getDate() == null || right.getSize() > left.getSize()))
			return right;
		if (root.getSize() >= right.getSize() && (left.getDate() == null || root.getSize() >= left.getSize()))
			return root;

		return left;

	}

	// This method to print all node of tree in input file with input location
	private void printListToFile(PrintWriter pw, String location, MyAVLStackNode root) {
		if (root != null) {
			printListToFile(pw, location, root.getRight());
			MyStack<Martyr> tmp = new MyStack<>();
			while (!root.getStack().isEmpty()) {
				Martyr m = root.getStack().pop();
				tmp.push(m);
				pw.println(m.getInfo(location));
			}
			while (!tmp.isEmpty())
				root.getStack().push(tmp.pop());
			printListToFile(pw, location, root.getLeft());
		}

	}

}