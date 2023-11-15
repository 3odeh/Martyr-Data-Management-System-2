package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import application.GeneralPanes;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
//This class to save list of brand data
public class MyDoubleLinkedList {
	
	// To know if data change
	public boolean ifDataChanges;

	// Pointer to first double node.
	private MyDoubleNode first;
	// Pointer to last double node.
	private MyDoubleNode last;
	// size of list
	private int count;

	public MyDoubleNode getFirstMyDoubleNode() {
		return first;
	}

	// This method to add location in the first place in this list.
	public void addFirst(String location) {
		if (location == null || location.isEmpty())
			return;
		if (count == 0) {
			first = last = new MyDoubleNode(location);
			 last.setNext(first);
			 first.setPre(last);
		} else {
			MyDoubleNode tmp = new MyDoubleNode(location);
			tmp.setNext(first);
			tmp.setPre(last);
			first.setPre(tmp);
			first = tmp;
			last.setNext(first);
		}
		count++;
	}

	// This method to return if their is no location
	public boolean isEmpty() {
		return count == 0;
	}

	// This method to add location in the last place in this list.
	public void addLast(String location) {
		if (location == null || location.isEmpty())
			return;
		if (count == 0) {
			first = last = new MyDoubleNode(location);
			last.setNext(first);
			first.setPre(last);
		} else {
			MyDoubleNode tmp = new MyDoubleNode(location);
			last.setNext(tmp);
			tmp.setPre(last);
			last = tmp;
			last.setNext(first);
			first.setPre(last);
		}
		count++;
	}

	/*
	 * This method add the location in the place dependent on location name in this
	 * list and martyr in the place dependent on date of death in link list of the
	 * same location.
	 */
	public void add(Martyr martyr, String location) {
		if (location == null || location.isEmpty() || martyr == null)
			return;
		location = location.trim();

		if (isEmpty()) {
			addFirst(location);
			first.getDateTree().insert(martyr);
			first.getMartyrTree().insert(martyr);
			return;
		} else {
			int compare = location.compareToIgnoreCase(first.getLocation());
			if (compare < 0) {
				addFirst(location);
				first.getDateTree().insert(martyr);
				first.getMartyrTree().insert(martyr);
				return;
			} else if (compare == 0) {
				first.getDateTree().insert(martyr);
				first.getMartyrTree().insert(martyr);
				return;
			}

			compare = location.compareToIgnoreCase(last.getLocation());
			if (compare > 0) {
				addLast(location);
				last.getDateTree().insert(martyr);
				last.getMartyrTree().insert(martyr);
				return;
			} else if (compare == 0) {
				last.getDateTree().insert(martyr);
				last.getMartyrTree().insert(martyr);
				return;
			}

			MyDoubleNode current = first;
			for (int i = 0 ; i < count ; i++) {
				compare = location.compareToIgnoreCase(current.getLocation());
				if (compare > 0 && location.compareToIgnoreCase(current.getNext().getLocation()) < 0) {
					MyDoubleNode node = new MyDoubleNode(location);
					node.getDateTree().insert(martyr);
					node.getMartyrTree().insert(martyr);
					node.setNext(current.getNext());
					node.setPre(current);
					current.getNext().setPre(node);
					current.setNext(node);
					count++;
					return;
				} else if (compare == 0) {
					current.getDateTree().insert(martyr);
					current.getMartyrTree().insert(martyr);
					return;
				}
				current = current.getNext();
			}
		}
	}

	// This method to add location in the place dependent on location in this list.
	public boolean add(String location) {
		if (location == null || location.isEmpty())
			return false;
		location = location.trim();

		if (count == 0) {
			addFirst(location);
			return true;
		} else {
			int compare = location.compareToIgnoreCase(first.getLocation());
			if (compare < 0) {

				addFirst(location);
				return true;
			} else if (compare == 0) {

				return false;
			}

			compare = location.compareToIgnoreCase(last.getLocation());
			if (compare > 0) {
				addLast(location);
				return true;
			} else if (compare == 0) {
				return false;
			}

			MyDoubleNode current = first;
			for (int i = 0 ; i < count ; i++) {
				compare = location.compareToIgnoreCase(current.getLocation());
				if (compare > 0 && location.compareToIgnoreCase(current.getNext().getLocation()) < 0) {
					MyDoubleNode node = new MyDoubleNode(location);

					node.setNext(current.getNext());
					node.setPre(current);
					current.getNext().setPre(node);
					current.setNext(node);
					count++;
					return true;
				} else if (compare == 0) {
					return false;
				}
				current = current.getNext();
			}
		}
		return false;
	}

	/*
	 * This method to read martyr data from input file with format
	 * (name,age,location,date(month/day/year),gender(M,F)) then add the location in
	 * the place dependent on location name in this list and martyr in the place
	 * dependent on date of death in link list of the same location.
	 */
	public int read(File f) throws Exception {
		int errorCount = 0;
		try {
			Scanner scanner = new Scanner(f);
			SimpleDateFormat dateFor = new SimpleDateFormat("MM/dd/yyyy");
			while (scanner.hasNext()) {
				String[] line = scanner.nextLine().split(",");
				if (line.length != 5) {
					errorCount++;
					continue;
				}
				Martyr m;
				try {

					m = new Martyr(line[0], Byte.valueOf(line[1]), dateFor.parse(line[3]), line[4].equals("M"));
					add(m, line[2]);

				} catch (NumberFormatException e) {

					try {
						m = new Martyr(line[0], Byte.valueOf(line[1]),null, line[4].equals("M"));
						add(m, line[2]);
					} catch (Exception exception) {
						m = new Martyr(line[0], (byte) -1, null, line[4].equals("M"));
						add(m, line[2]);
					}
				} catch (ParseException e) {
					try {
						m = new Martyr(line[0], (byte) -1, dateFor.parse(line[3]), line[4].equals("M"));
						add(m, line[2]);
					} catch (Exception exception) {
						m = new Martyr(line[0], (byte) -1, null, line[4].equals("M"));
						add(m, line[2]);
					}
				} catch (Exception e) {
					errorCount++;
				}

			}
			scanner.close();
		} catch (Exception e) {
			throw e;

		}
		return errorCount;
	}

	// This method to add all location data in the grid pane
	public void addLocationToGP(GridPane gp) {

		MyDoubleNode current = first;
		gp.add(GeneralPanes.locationPane( this), 1, 0);
		for (int x = 2; x < count + 2; x++) {
			gp.add(GeneralPanes.locationPane(current, this), x % 5, x / 5);
			current = current.getNext();
		}
	}

	// This method to add search location data in the grid pane
	public void addLocationToGP(GridPane gp, String search) {
		MyDoubleNode current = first;
		for (int x = 2, pos = 2; x < count + 2; x++) {
			if (current.getLocation().toLowerCase().startsWith(search.toLowerCase())) {
				gp.add(GeneralPanes.locationPane(current, this), pos % 5, pos / 5);
				pos++;
			}
			current = current.getNext();
		}
	}

	// This method to print all location and martyr data to the input file
	public boolean printListToFile(File f) throws Exception {
		try {
			PrintWriter pw = new PrintWriter(f);
			MyDoubleNode current = first;
			for (int i = 0 ; i < count ; i++) {
				current.getDateTree().printListToFile(pw,current.getLocation());
				current = current.getNext();
			}
			pw.close();
			return true;
		} catch (FileNotFoundException e) {
			throw e;
		}

	}

	// This method to remove the input node
	public boolean removeMyDoubleNode(MyDoubleNode current) {
		if (current == null) {
			return false;
		}
		if (current.getPre() == last) {
			return removeFirst();
		} else if (current.getNext() == first) {
			return removeLast();
		} else {
			MyDoubleNode pre = current.getPre();
			MyDoubleNode next = current.getNext();
			pre.setNext(next);
			next.setPre(pre);
			count--;
			return true;
		}
	}

	// This method to remove the searched node by location and martyr
	public boolean removeNode(String location, Martyr martyr) {
		MyDoubleNode current = first;
		for (int i = 0 ; i < count ; i++) {
			if (current.getLocation().equals(location)) {
				return current.getDateTree().delete(martyr) && current.getMartyrTree().delete(martyr);
			}
			current = current.getNext();
		}
		return false;
	}

	// This method to get search node by location
	public MyDoubleNode getMyDoubleNode(String location) {
		MyDoubleNode current = first;
		for (int i = 0 ; i < count ; i++) {
			if (current.getLocation().equals(location))
				return current;
			current = current.getNext();
		}
		return current;
	}

	// This method to remove first location in this list.
	public boolean removeFirst() {
		if (count == 0) {
			return false;
		} else {
			if (count == 1) {
				last = first = null;
			} else {
				first = first.getNext();
				first.setPre(last);
				last.setNext(first);
			}
			count--;
			return true;
		}

	}

	// This method to remove last location in this list.
	public boolean removeLast() {
		if (count == 0) {
			return false;
		} else {
			if (count == 1) {
				last = first = null;
			} else {
				last = last.getPre();
				last.setNext(first);
				first.setPre(last);
			}
			count--;
			return true;
		}
	}

	// This method to update location name and place of node in this list.
	public boolean changeName(String text, MyDoubleNode node) {
		if (text == null || text.isEmpty() || node == null)
			return false;
		text = text.trim();
		int compare = text.compareToIgnoreCase(node.getLocation());

		if (compare > 0) {
			compare = text.compareToIgnoreCase(last.getLocation());
			if (compare > 0) {
				if (node.getNext() == first) {
					// no need to change the place of node
					node.setLocation(text);
					return true;
				} else if (node.getPre() == last) {
					MyDoubleNode current = node;

					// put the first node to the second node
					first = node.getNext();
					

					// put node in the last
					last.setNext(current);
					current.setNext(first);
					current.setPre(last);
					last = current;
					node.setLocation(text);
					first.setPre(last);
					return true;
				} else {
					MyDoubleNode current = node;

					// connect the pre node to the next node
					node.getPre().setNext(node.getNext());
					node.getNext().setPre(node.getPre());

					// put node in the last
					last.setNext(current);
					current.setNext(first);
					current.setPre(last);
					last = current;
					first.setPre(last); 

					node.setLocation(text);
					return true;
				}

			} else if (compare == 0) {
				return false;
			}

			if (text.compareToIgnoreCase(node.getLocation()) > 0
					&& text.compareToIgnoreCase(node.getNext().getLocation()) < 0) {
				node.setLocation(text);
				return true;
			}
			MyDoubleNode current = node;
			for (int i = 0 ; i < count ; i++) {
				compare = text.compareToIgnoreCase(current.getLocation());
				if (compare > 0 && text.compareToIgnoreCase(current.getNext().getLocation()) < 0) {

					MyDoubleNode tmp = node;
					// connect the pre node to the next node
					if (node.getPre() != null) {
						node.getPre().setNext(node.getNext());
					}

					if (tmp.getNext() != null) {
						tmp.getNext().setPre(tmp.getPre());
					}

					tmp.setNext(current.getNext());
					tmp.setPre(current);
					current.getNext().setPre(tmp);
					current.setNext(tmp);

					tmp.setLocation(text);
					return true;
				} else if (compare == 0) {
					return false;
				}

				current = current.getNext();

			}

		} else if (compare < 0) {
			compare = text.compareToIgnoreCase(first.getLocation());
			if (compare < 0) {
				if (node.getPre() == last) {
					// no need to change the place of node
					node.setLocation(text);
					return true;
				} else if (node.getNext() == first) {
					MyDoubleNode current = node;

					// put the Last node to the pre last node
					last = last.getPre();
				

					// put node in the first
					first.setPre(current);
					current.setPre(last);
					current.setNext(first);
					first = current;
					last.setNext(first);
					node.setLocation(text);
					return true;
				} else {
					MyDoubleNode current = node;

					// connect the pre node to the next node
					node.getPre().setNext(node.getNext());
					node.getNext().setPre(node.getPre());

					// put node in the first
					first.setPre(current);
					current.setPre(last);
					current.setNext(first);
					first = current;
					last.setNext(first);
					node.setLocation(text);

					return true;
				}

			} else if (compare == 0) {
				return false;
			}

			if (text.compareToIgnoreCase(node.getLocation()) < 0
					&& text.compareToIgnoreCase(node.getPre().getLocation()) > 0) {
				node.setLocation(text);
				return true;
			}
			MyDoubleNode current = node;
			for (int i = 0 ; i < count ; i++) {
				compare = text.compareToIgnoreCase(current.getLocation());
				if (compare < 0 && text.compareToIgnoreCase(current.getPre().getLocation()) > 0) {

					MyDoubleNode tmp = node;
					// connect the pre node to the next node
					if (node.getPre() != null) {
						node.getPre().setNext(node.getNext());
					}

					if (node.getNext() != null) {
						node.getNext().setPre(node.getPre());
					}

					tmp.setPre(current.getPre());
					tmp.setNext(current);
					current.getPre().setNext(tmp);
					current.setPre(tmp);

					node.setLocation(text);
					return true;
				} else if (compare == 0) {
					return false;
				}

				current = current.getPre();

			}
		}

		return false;

	}

	
	// This method to add all martyr in all location in this list to the table view.
	public void addAllDataToTableView(TableView<AllData> tv) {
		
		MyDoubleNode current = first;
		current = first;
		for (int i = 0 ; i < count ; i++) {
			current.getDateTree().addAllDataToTableView(tv, current.getLocation());
			current = current.getNext();
		}

	}

	// This method to add martyr searched with name in all location in this list to
	// the table view.
	public void addAllDataToTableView(TableView<AllData> tv, String search) {
		MyDoubleNode current = first;
		current = first;
		for (int i = 0 ; i < count ; i++) {
			current.getDateTree().addAllDataToTableView(tv, current.getLocation(), search);
			current = current.getNext();
		}

	}

	// This method to print all data 
	public void print() {

		MyDoubleNode current = first;
		current = first;
		for (int i = 0 ; i < count ; i++) {
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------");
			System.out.println("Location : " + current.getLocation());
			current.getDateTree().printInOrder();
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------");
			current = current.getNext();
		}
	}
}
