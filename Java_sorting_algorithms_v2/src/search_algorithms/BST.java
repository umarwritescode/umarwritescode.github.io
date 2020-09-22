package coursework2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

public class BST {
	public Node root;
	
	public class Node {
		public int data;
		public Node left, right;
		
		public Node (int data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}
	
	
	public BST() {
		root = null;
	}
	
	public boolean isELEMENT(double number){
		Node current = root;
		while(current!=null){
			if(current.data==number){
				return true;
			}else if(current.data>number){
				current = current.left;
			}else{
				current = current.right;
			}
		}
		return false;
	}
	public boolean REMOVE(int id){
		Node parent = root;
		Node current = root;
		boolean isLeftBranch = false;
		while(current.data!=id){
			parent = current;
			if(current.data>id){
				isLeftBranch = true;
				current = current.left;
			}else{
				isLeftBranch = false;
				current = current.right;
			}
			if(current ==null){
				return false;
			}
		}
		//Following if block to b executed if node to be deleted has
		//no branches/children.
		if(current.left==null && current.right==null){
			if(current==root){
				root = null;
			}
			if(isLeftBranch ==true){
				parent.left = null;
			}else{
				parent.right = null;
			}
		}
		//If node to delete has only one branch/child
		else if(current.right==null){
			if(current==root){ 
				root = current.left;
			}else if(isLeftBranch){
				parent.left = current.left;
			}else{
				parent.right = current.left;
			}
		}
		else if(current.left==null){
			if(current==root){
				root = current.right;
			}else if(isLeftBranch){
				parent.left = current.right;
			}else{
				parent.right = current.right;
			}
		}else if(current.left!=null && current.right!=null){
			
			//now we have found the minimum element in the right sub tree
			Node successor	 = getSucceeding(current);
			if(current==root){
				root = successor;
			}else if(isLeftBranch){
				parent.left = successor;
			}else{
				parent.right = successor;
			}			
			successor.left = current.left;
		}		
		return true;		
	}
	
	public Node getSucceeding(Node deleleNode){
		Node successsor =null;
		Node successsorParent =null;
		Node current = deleleNode.right;
		while(current!=null){
			successsorParent = successsor;
			successsor = current;
			current = current.left;
		}
		//check if successor has the right child, it cannot have left child for sure
		// if it does have the right child, add it to the left of successorParent.
//		successsorParent
		if(successsor!=deleleNode.right){
			successsorParent.left = successsor.right;
			successsor.right = deleleNode.right;
		}
		return successsor;
	}
	public void ADD(int id){
		Node newNode = new Node(id);
		if(root==null){
			root = newNode;
			return;
		}
		Node current = root;
		Node parent = null;
		while(true){
			parent = current;
			if(id<current.data){				
				current = current.left;
				if(current==null){
					parent.left = newNode;
					return;
				}
			}else{
				current = current.right;
				if(current==null){
					parent.right = newNode;
					return;
				}
			}
		}
	}
	public void display(Node root){ // Prints the nodes on a line 
		if(root!=null){
			display(root.left);
			System.out.print(" " + root.data);
			display(root.right);
		}
	}
	
	public boolean setEMPTY(Node root) {
		if (root == null) {
			return true;
		}
		else {
			return false;
		}
	}
	/*
	int count;
	
	public int size(Node root) {
		if(root != null) {
			size(root.left);
			count++;
			size(root.right);
		}
		return count;
	}
	*/

	
	
	public BST UNION(BST T) {
		
		recursiveUnion(T.root, this);
		
		display(this.root);
		return this;
		
		
	}
	
	/*
	static void recursiveUnion(BST c) {
		recursiveUnion(c.root, c);
	}*/
	
	static void recursiveUnion(Node root, BST c) {
		if (root != null) {
			recursiveUnion(root.left, c);
			c.ADD(root.data);
			recursiveUnion(root.right, c);
		}
	}
	
	public BST INTERSECTION(BST T) {
		BST c = new BST();
		
		recursiveIntersection(T.root, c, this);
		display(c.root);
		return c;
	}
	
	void recursiveIntersection(Node root, BST c, BST d) {
		if (root != null) {
			recursiveIntersection(root.left, c, d);
			if (d.isELEMENT(root.data)) {
				c.ADD(root.data);
			}
			recursiveIntersection(root.right, c, d);
		}
	}
	
	public BST DIFFERENCE(BST T) {
		BST c = new BST();
		
		recDifference(T.root, c, this);
		display(c.root);
		return c;
	}
	
	void recDifference(Node root, BST c, BST d) {
		if (root != null) {
			recDifference(root.left, c, d);
			if (!(d.isELEMENT(root.data))) {
				c.ADD(root.data);
			}
			recDifference(root.right, c, d);
		}
	}
	
	public boolean flag = true;
	
	public boolean subSET(BST T) {
		Node current = T.root;
		
		
		recSubset(current, this);
		return flag;
	}
	
	void recSubset(Node current, BST containerBST) {
		if (current != null) {
			recSubset(current.left, containerBST);
			if (!(containerBST.isELEMENT(current.data))) {
				flag = false;
			}
			recSubset(current.right, containerBST);
		}
	}
	
	public int setSIZE(Node root) {

		if (root == null) {
			return 0;
		}
		int heightLeft = setSIZE(root.left);
		int heightRight = setSIZE(root.right);
		return Math.max(heightLeft, heightRight) + 1;
	}
	
	public static void main(String arg[]) throws NumberFormatException, IOException{
		
		/*
		BST b = new BST();
		b.ADD(3);b.ADD(8);
		b.ADD(1);b.ADD(4);b.ADD(6);b.ADD(2);b.ADD(10);b.ADD(9);
		
		BST x = new BST();
		x.ADD(5);x.ADD(12);x.ADD(6);x.ADD(2);x.ADD(3);
		System.out.println("Original Tree : ");
		b.display(b.root);		
		System.out.println(" \n Size of our tree: ");
		System.out.println("\n Size of b is: " + b.setSIZE(b.root));
		//x.display(x.root);
		//b.union(x);
		System.out.println("\n Intersection of the two sets: ");
		b.INTERSECTION(x);
		System.out.println("\n Difference of sets: ");
		b.DIFFERENCE(x);
		System.out.println("\n Does b contain x: " + b.subSET(x));
		System.out.println("\n Does x contain 3: " + x.isELEMENT(3)); 
		
		System.out.println("Printing out sample bst: "); 
		b.display(b.root);
	*/
		int data[] = new int[100];

		BST bst = new BST();
		
		String fname = "C:\\Users\\Owner\\eclipse-workspace\\ADS\\src\\int20k.txt";
		
		String line;
		FileInputStream stream = new FileInputStream(fname);
		DataInputStream inputStream = new DataInputStream(stream);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		
		while ((line = br.readLine()) != null) {
			int a = Integer.valueOf(line);
			
			bst.ADD(a);
			
		
		}
		br.close();
		
		//System.out.println("Displaying doubly linked list: ");
		//DLL.print();
		
		for (double number : data) {
			number = Math.floor(Math.random()*50000);
		}
		
		double isElementBST[] = new double[100];
		
		for (int i=0; i<100; i++) {
			for (double number : data) {
				long startDLL = System.nanoTime();
				bst.isELEMENT(number);
				long endDLL = System.nanoTime();
	
				isElementBST[i] = endDLL - startDLL;		
			}
		}
		
		System.out.println("Printing out the 'isElement' times: ");
		
		for (double no : isElementBST) {
			System.out.println(no);
		}
		
		// Finding the average
		
		double sum = 0;
				
		for (double no : isElementBST) {
			sum = sum + no;
			
		}
				
		double avg = sum/(isElementBST.length);
				
		System.out.println("The average time taken is: " + avg);
		
		System.out.println("Height of tree is: " + bst.setSIZE(bst.root));
		System.out.println("Size of tree is: " + bst.setSIZE(bst.root));
		
	}
	

}
