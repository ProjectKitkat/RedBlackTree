package RedBlackTree;
public class BinaryTree<T extends Comparable<T>> {
	private T root;
	private String color;
	private BinaryTree<T> leftSubtree;
	private BinaryTree<T> rightSubtree;
	private BinaryTree<T> rootOfTree;
	
	/** Constructor */
	public BinaryTree() {
		this.root = null;
		this.color = "B";
		this.leftSubtree = null;
		this.rightSubtree = null;
		this.rootOfTree = this;
	}
	
//	public void insert(BinaryTree<T> tree, T node) {
//		BinaryTree<T> newTree = new BinaryTree<>();
//		newTree.color = "R";
//		newTree.root = node;
//		
//		if (tree.root == null) { // no tree exists
//			tree.root = newTree.root;
//			tree.color = "B";
//		}
//		else if (node.compareTo(tree.root) < 0) { /* node less than current subtree's parent */
//			// No left subtree
//			if (tree.leftSubtree == null) {
//				tree.leftSubtree = newTree;
//				if (tree.color.equals("R")) {
//					newTree.color = "B";
//				}
//			}
//			// Left subtree exists; keep going down the tree
//			else if (this.leftSubtree != null) {
//				insert(tree.leftSubtree, node);
//			}
//			
//		}
//		else if (node.compareTo(tree.root) > 0) { /* node bigger than current subtree's parent */
//			// No right subtree
//			if (tree.rightSubtree == null) {
//				tree.rightSubtree = newTree;
//				if (tree.color.equals("R")) {
//					newTree.color = "B";
//				}
//			}
//			// Right subtree exists; keep going down the tree
//			else if (tree.rightSubtree != null) {
//				insert(tree.rightSubtree, node);
//			}
//		}
//	}
	
//	public void insert(T node) {
//		BinaryTree<T> newTree = new BinaryTree<>();
//		newTree.color = "R";
//		newTree.root = node;
//		
//		if (this.root == null) { // no tree exists
//			this.root = newTree.root;
//			this.color = "B";
//		}
//		else if (node.compareTo(this.root) < 0) { /* node less than current subtree's parent */
//			// No left subtree
//			if (this.leftSubtree == null) {
//				this.leftSubtree = newTree;
////				if (this.color.equals("R")) {
////					newTree.color = "B";
////				}
//			}
//			// Left subtree exists; keep going down the tree
//			else if (this.leftSubtree != null) {
//				this.leftSubtree.insert(node);
//			}
//			
//		}
//		else if (node.compareTo(this.root) > 0) { /* node bigger than current subtree's parent */
//			// No right subtree
//			if (this.rightSubtree == null) {
//				this.rightSubtree = newTree;
////				if (this.color.equals("R")) {
////					newTree.color = "B";
////				}
//			}
//			// Right subtree exists; keep going down the tree
//			else if (this.rightSubtree != null) {
//				this.rightSubtree.insert(node);
//			}
//		}
//	}

	public void insert(T node) {
		BinaryTree<T> newTree = new BinaryTree<>(), grandpa = new BinaryTree<>(), 
				uncle = new BinaryTree<>(), dad = new BinaryTree<>();
		newTree.color = "R";
		newTree.root = node;
		newTree.leftSubtree = new BinaryTree<>();
		newTree.rightSubtree = new BinaryTree<>();
		
		if (this.root == null) { // no tree exists
			this.root = newTree.root;
			this.color = "B";
			this.leftSubtree = newTree.leftSubtree;
			this.rightSubtree = newTree.rightSubtree;
		}
		else if (node.compareTo(this.root) < 0) { /* node less than current subtree's parent */
			// No left subtree
			if (this.leftSubtree.root == null) {
				this.leftSubtree = newTree;
			}
			// Left subtree exists; keep going down the tree
			else if (this.leftSubtree.root != null) {
				//BinaryTree<T> grandpa = this, dad = this.leftSubtree;
				this.leftSubtree.insert(node);
				grandpa = this;
				dad = this.leftSubtree;
				uncle = this.rightSubtree;
				
				if (uncle == null) {
					uncle = new BinaryTree<>();
				}
			}
			
		}
		else if (node.compareTo(this.root) > 0) { /* node bigger than current subtree's parent */
			// No right subtree
			if (this.rightSubtree.root == null) {
				this.rightSubtree = newTree;
			}
			// Right subtree exists; keep going down the tree
			else if (this.rightSubtree.root != null) {
				this.rightSubtree.insert(node);
				grandpa = this;
				dad = this.rightSubtree;
				uncle = this.leftSubtree;
				
//				if (uncle == null) {
//					uncle = new BinaryTree<>();
//				}
			}
		}
		
		// If parent/dad is black, do nothing. If red, check uncle
		if (dad.root != null && dad.color == "R") {
			// If uncle is red, change parent to and uncle to black and grandpa to red
			if (uncle != null && uncle.color == "R") { //?????? grandpa should not equal root in order to enter this if statement
				dad.color = "B";
				uncle.color = "B";

				// But if the grandpa is the root, don't change grandpa's color to red
				if (!grandpa.equals(this.rootOfTree)) {
					grandpa.color = "R";
				}
			}
			/*
			 * If uncle is black, 4 possible scenarios:
			 * 1. LL - node is the left of the parent who is the left of the grandparent
			 *         Right rotate on grandparent
			 * 2. LR - Node is the right of the parent who is the left of the grandparent
			 *         Left rotate on parent, right rotate on grandparent
			 * 3. RR - Node is the right of the parent who is the right of the grandparent
			 *         Left rotate on grandparent
			 * 4. RL - Node is the left of the parent who is the right of the grandparent
			 *         Right rotate on parent, left rotate on grandparent
			 */
			else if (uncle != null && uncle.color == "B") {
				// LL
				if (newTree.root.compareTo(dad.root) <= 0 && dad.root.compareTo(grandpa.root) <= 0) {
					String grandpaColor = grandpa.color;
					String dadColor = dad.color;
					grandpa.color = dadColor;
					dad.color = grandpaColor;
					grandpa.rightRotate();
				}
				// LR
				else if (newTree.root.compareTo(dad.root) > 0 && dad.root.compareTo(grandpa.root) <= 0) {
					String grandpaColor = grandpa.color;
					String newNodeColor = newTree.color;
					grandpa.color = newNodeColor;
					newTree.color = grandpaColor;
					dad.leftRotate();
					grandpa.rightRotate();
				}
				// RR
				else if (newTree.root.compareTo(dad.root) > 0 && dad.root.compareTo(grandpa.root) >  0) {
					String grandpaColor = grandpa.color;
					String dadColor = dad.color;
					grandpa.color = dadColor;
					dad.color = grandpaColor;
					grandpa.leftRotate();
				}
				// RL
				else if (newTree.root.compareTo(dad.root) <= 0 && dad.root.compareTo(grandpa.root) >  0) {
					String grandpaColor = grandpa.color;
					String newNodeColor = newTree.color;
					grandpa.color = newNodeColor;
					newTree.color = grandpaColor;
					dad.rightRotate();
					grandpa.leftRotate();
				}
			}
		}
	}
	
	public void leftRotate() {
		BinaryTree<T> leftTree = new BinaryTree<>();
		leftTree.root = this.root;
		leftTree.color = this.color;
		leftTree.leftSubtree = this.leftSubtree;
		leftTree.rightSubtree = new BinaryTree<>();
		
		this.root = this.rightSubtree.root;
		this.color = this.rightSubtree.color;
		this.leftSubtree = leftTree;
		this.rightSubtree = this.rightSubtree.rightSubtree;
	}
	
	public void rightRotate() {
		BinaryTree<T> rightTree = new BinaryTree<>();
		rightTree.root = this.root;
		rightTree.color = this.color;
		rightTree.rightSubtree = this.rightSubtree;
		rightTree.leftSubtree = new BinaryTree<>();
		
		this.root = this.leftSubtree.root;
		this.color = this.leftSubtree.color;
		this.leftSubtree = this.leftSubtree.leftSubtree;
		this.rightSubtree = rightTree;
	}
	
	/**
	 * Deletes the node from the tree and replaces it with the maximum value in the left child tree of the node.
	 * @param node
	 */
	public void delete(T node) {
		/*
		 * Case 1: node to delete is a leaf
		 * Case 2: node to delete has a single child
		 * Case 3: node to delete has two children
		 */
		
		BinaryTree<T> nodeToDelete = new BinaryTree<>();
		
		BinaryTree<T> leftCurrentNode = this.leftSubtree;
		BinaryTree<T> rightCurrentNode = this.rightSubtree;
		
		if (this.root.equals(node)) {
			// Case 1: Node is leaf
			if (leftCurrentNode.root == null && rightCurrentNode.root == null) {
				// Reset
				this.root = null;
				this.color = "B";
				this.leftSubtree = new BinaryTree<>();
				this.rightSubtree = new BinaryTree<>();
			}
			// Case 2: Node has a single child on the left
			else if (rightCurrentNode.root == null) {
				this.root = leftCurrentNode.root;
				this.color = leftCurrentNode.color;
				this.leftSubtree = leftCurrentNode.leftSubtree;
				this.rightSubtree = leftCurrentNode.rightSubtree;
			}
			// 2A: Node has a single child on the right
			else if (leftCurrentNode.root == null) {
				this.root = rightCurrentNode.root;
				this.color = rightCurrentNode.color;
				this.leftSubtree = rightCurrentNode.leftSubtree;
				this.rightSubtree = rightCurrentNode.rightSubtree;
			}
			// Case 3: Node has two children
			else if (!(leftCurrentNode.root == null && rightCurrentNode.root == null)) {
				BinaryTree<T> maxNode = leftCurrentNode.max();
				this.root = maxNode.root;
				this.color = maxNode.color;
				
				leftCurrentNode.delete(maxNode.root);
			}
		}
		else {
			nodeToDelete = searchNode(node);
			if (nodeToDelete.root != null) {
				nodeToDelete.delete(node);
			}
			else {
				System.out.println("error");
			}
		}
	}
	
	public BinaryTree<T> max() {
		BinaryTree<T> max = this;
		
		// Keep going down to the right side of the tree until there is no more right child
		if (this.rightSubtree != null) {
			max = this.rightSubtree.max();
		}
		
		return max;
	}
	
	public BinaryTree<T> searchNode(T node) {
		// Set the subtree as the current tree we're looking through
		BinaryTree<T> nodeSubtree = this; // this?
		
		if (node.compareTo(this.root) < 0 && this.leftSubtree != null) { // Node is less than parent
			nodeSubtree = this.leftSubtree.searchNode(node);
		}
		else if (node.compareTo(this.root) > 0 && this.rightSubtree != null) { // Node is bigger than parent
			nodeSubtree = this.rightSubtree.searchNode(node);
		}
		// If the node is neither smaller or bigger than the parent, the node IS the parent
		
		return nodeSubtree;
	}
	
	public String treeToString(BinaryTree<T> t) {
	    StringBuilder treeAsStringBuilder = new StringBuilder();
	    
	    if (t != null && t.root != null) {
	        BinaryTree<T> leftTree = t.leftChild();
	        BinaryTree<T> rightTree = t.rightChild();
	        
	        //treeAsStringBuilder.append(t.root.toString() + "(");
	        treeAsStringBuilder.append(t.root.toString() + "" + t.color + "(");
	        treeAsStringBuilder.append(treeToString(leftTree));
	        treeAsStringBuilder.append(treeToString(rightTree));
	        treeAsStringBuilder.append(")");
	    }
	    else {
	        treeAsStringBuilder.append("()");
	    }
	    
	    return treeAsStringBuilder.toString();
	}
	
//	public String treeToString() {
//	    StringBuilder treeAsStringBuilder = new StringBuilder();
//	    
//	    if (this != null && this.root != null) {
//	        BinaryTree<T> leftTree = this.leftChild();
//	        BinaryTree<T> rightTree = this.rightChild();
//	        
//	        //treeAsStringBuilder.append(t.root.toString() + "(");
//	        treeAsStringBuilder.append(this.root.toString() + "" + this.color + "(");
//	        treeAsStringBuilder.append(leftTree.treeToString());
//	        treeAsStringBuilder.append(rightTree.treeToString());
//	        treeAsStringBuilder.append(")");
//	    }
//	    else {
//	        treeAsStringBuilder.append("()");
//	    }
//	    
//	    return treeAsStringBuilder.toString();
//	}
	
	public T root() {
		return this.root;
	}
	
	public BinaryTree<T> leftChild() {
		return this.leftSubtree;
	}
	
	public BinaryTree<T> rightChild() {
		return this.rightSubtree;
	}
}
