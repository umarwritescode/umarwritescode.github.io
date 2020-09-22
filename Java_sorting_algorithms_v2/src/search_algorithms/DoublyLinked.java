/**
 * 
 */
package coursework2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

// Dynamic set is an ADT that stores UNIQUE elements in NO PARTICULAR order
// We want to:
// Add element x to S, if not present: ADD(S,x)
// Remove x from S if present, REMOVE(S,x)
//Check if an element, x, is in a set S: IS-ELEMENT(S,x)
// Return number of elements in set S, SET-SIZE(S)

//UNION


/**
 * @author Umar
 *
 */
public class DoublyLinked<X> {
	
	private DoublyLinked<X>.Node head; // We have a doubly linked list defined by the
	private  DoublyLinked<X>.Node tail; // head (first) and tail (last) element,



	/**
	 * @return 
	 * 
	 */
	public int getHeadElement(DoublyLinked<Integer> S) {
		return (S.head).element; // Returns head int of the list
	}
	
	public DoublyLinked<Integer>.Node getHeadNode(DoublyLinked<Integer> S) {
		return S.head;
	}
	
	private class Node // Each element in the list is added by
	{					 // constructing it via the value, next 
		int  element;
		DoublyLinked<X>.Node next;
		DoublyLinked<X>.Node prev;
		
		public Node(int x, DoublyLinked<X>.Node next, DoublyLinked<X>.Node prev) {
			this.element = x;
			this.next = next;
			this.prev = prev;
		}
		
	}
	
	public void add(int x) {
		DoublyLinked<X>.Node temp = new Node(x, null, tail);
		if (this.tail != null) {
			this.tail.next = temp;
		}
		this.tail = temp;
		if (this.head == null) {
			this.head = temp;
		}

		
	}
	
	public void remove(double del) { // Specify head node
		
		Node delete = this.head;
		
		
		while (delete.element != del) { // Find the node containing 
			delete = delete.next; // The int to delete
			
		}
		
		if (this.head == null) {
			return;
		}
		if (this.head == delete) {
			this.head = delete.next;
		}
		
		if (delete.next != null) {
			delete.next.prev = delete.prev;
		}
		if (delete.prev != null) {
			delete.prev.next = delete.next;
		}
		return;
	}
	
	public boolean isElement(double number) {
		DoublyLinked<X>.Node z = this.head;
		
		boolean flag = false;
		
		while (z != null) {
			if (z.element == number) {
				flag = true;
			}
			z = z.next;
		}
		
		return flag;

	}
	
	public boolean setEmpty() {
		// Can only be the case if a 'head' does not exist.
		// Hence:
		if (this.head == null) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public int setSize() {
		DoublyLinked<X>.Node z = this.head;
		int count = 0;
		while (z != null) {
			count++;
			z = z.next;
		}
		return count;
	}
	
	public DoublyLinked<X> union(DoublyLinked<X> T) {
		
		DoublyLinked<X>.Node y = T.head;
		
		
		while (y != null) {
			if (!(this.isElement(y.element))) {
				this.add(y.element);
			}
			y = y.next;
		}
		
		return this;
		
	}
	
	public String intersection(DoublyLinked<X> T) {

		DoublyLinked<X> intersection = new DoublyLinked<>();

		DoublyLinked<X>.Node y = T.head;
		

		while (y != null) {
			if (this.isElement(y.element)) {
				intersection.add(y.element);
			}
 			y = y.next;
		}
		
		
		return intersection.toString();
	}
	
	public String difference(DoublyLinked<X> T) {
		DoublyLinked<X> difference = new DoublyLinked<>();

		DoublyLinked<X>.Node y = T.head;
		

		while (y != null) {
			if (!(this.isElement(y.element))) {
				difference.add(y.element);
			}
 			y = y.next;
		}
		
		
		return difference.toString();
	}
	
	public boolean subset(DoublyLinked<X> T) {

		DoublyLinked<X>.Node y = T.head;
		
		boolean flag = true;
		
		while (y != null) {
			if (!(this.isElement(y.element))) {
				flag = false;
			}
			y = y.next;
		}
		
		return flag;
	}
	
	
	public void print() { // PRINTS ELEMENTS ON THEIR OWN LINE
		DoublyLinked<X>.Node x = this.head;
		
		while (x != null) {
			System.out.println(x.element);
			x = x.next;
		}
	}
	
	
	
	
	public static void main(String arg[]) throws IOException {
		/*
		DoublyLinked<Integer> test = new DoublyLinked<Integer>();
		System.out.println("DLL Test is empty: " + test.setEmptyCheck());
		test.add(5);
		System.out.println("DLL Test is empty: " + test.setEmptyCheck());
		test.add(11);test.add(21);test.add(3);test.add(16);
		DoublyLinked<Integer> test2 = new DoublyLinked<Integer>();
		test2.add(11);test2.add(52);test2.add(16);
		test.union(test2);
		test.print();
		test.remove(21);
		System.out.println("Printing after deleteing: ");
		test.print();
		*/
		
		
		
		int data[] = new int[100];
		DoublyLinked<Double> DLL = new DoublyLinked<Double>();
		
		String fname = "C:\\Users\\Owner\\eclipse-workspace\\ADS\\src\\int20k.txt";
		
		String line;
		FileInputStream stream = new FileInputStream(fname);
		DataInputStream inputStream = new DataInputStream(stream);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		
		while ((line = br.readLine()) != null) {
			int a = Integer.valueOf(line);
			
			DLL.add(a);
			
		
		}
		br.close();
		
		//System.out.println("Displaying doubly linked list: ");
		//DLL.print();
		
		for (double number : data) {
			number = Math.floor(Math.random()*50000);
		}
		
		double isElementDLL[] = new double[100];
		for (int i=0; i<100; i++) {
			for (double number : data) {
				long startDLL = System.nanoTime();
				DLL.isElement(number);
				long endDLL = System.nanoTime();
	
				isElementDLL[i] = endDLL - startDLL;		
			}
		}
		
		System.out.println("Printing out the 'isElement' times: ");
		
		for (double no : isElementDLL) {
			System.out.println(no);
		}
		
		// Finding the average
		
		double sum = 0;
		
		for (double no : isElementDLL) {
			sum = sum + no;
		}
		
		double avg = sum/(isElementDLL.length);
		
		System.out.println("The average time taken is: " + avg);
		
		System.out.println("Size of set is: " + DLL.setSize()); 
		
	}
	
	
	
	
}
