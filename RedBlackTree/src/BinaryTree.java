public class BinaryTree<T extends Comparable<T>> {
	private T root;
	private String color;
	private BinaryTree<T> leftSubtree;
	private BinaryTree<T> rightSubtree;
	
	/** Constructor */
	public BinaryTree() {
		this.root = null;
		this.color = "black";
		this.leftSubtree = null;
		this.rightSubtree = null;
	}
	
	public void insert(BinaryTree<T> tree, T node) {
		BinaryTree<T> newTree = new BinaryTree<>();
		newTree.color = "red";
		newTree.root = node;
		
		if (tree.root == null) { // no tree exists
			tree.root = newTree.root;
			tree.color = "black";
		}
		else if (node.compareTo(tree.root) < 0) { /* node less than current subtree's parent */
			// No left subtree
			if (tree.leftSubtree == null) {
				tree.leftSubtree = newTree;
				if (tree.color.equals("red")) {
					newTree.color = "black";
				}
			}
			// Left subtree exists; keep going down the tree
			else if (this.leftSubtree != null) {
				insert(tree.leftSubtree, node);
			}
			
		}
		else if (node.compareTo(tree.root) > 0) { /* node bigger than current subtree's parent */
			// No right subtree
			if (tree.rightSubtree == null) {
				tree.rightSubtree = newTree;
				if (tree.color.equals("red")) {
					newTree.color = "black";
				}
			}
			// Right subtree exists; keep going down the tree
			else if (tree.rightSubtree != null) {
				insert(tree.rightSubtree, node);
			}
		}
	}
	
	/**
	 * Deletes the node from the tree and replaces it with the maximum value in the left child tree of the node.
	 * @param tree
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
			if (leftCurrentNode == null && rightCurrentNode == null) {
				// Reset
				this.root = null;
				this.color = "black";
				this.leftSubtree = null;
				this.rightSubtree = null;
			}
			// Case 2: Node has a single child on the left
			else if (rightCurrentNode == null) {
				this.root = leftCurrentNode.root;
				this.color = leftCurrentNode.color;
				this.leftSubtree = leftCurrentNode.leftSubtree;
				this.rightSubtree = leftCurrentNode.rightSubtree;
			}
			// 2A: Node has a single child on the right
			else if (leftCurrentNode == null) {
				this.root = rightCurrentNode.root;
				this.color = rightCurrentNode.color;
				this.leftSubtree = rightCurrentNode.leftSubtree;
				this.rightSubtree = rightCurrentNode.rightSubtree;
			}
			// Case 3: Node has two children
			else if (!(leftCurrentNode == null && rightCurrentNode == null)) {
				BinaryTree<T> maxNode = max(leftCurrentNode);
				this.root = maxNode.root;
				this.color = maxNode.color;
				
				leftCurrentNode.delete(maxNode.root);
			}
		}
		else {
			nodeToDelete = searchNode(node);
			if (nodeToDelete != null) {
				nodeToDelete.delete(node);
			}
			else {
				System.out.println("error");
			}
		}
	}
	
	// KEEP
	public BinaryTree<T> max(BinaryTree<T> tree) {
		BinaryTree<T> max = tree;
		
		// Keep going down to the right side of the tree until there is no more right child
		if (tree.rightSubtree != null) {
			max = max(tree.rightSubtree);
		}
		
		return max;
	}
	
	public BinaryTree<T> nodeParent(T node) {
		BinaryTree<T> parent = new BinaryTree<>();
		
		if (this.root.equals(node)) {
			parent = null;
		}
		else if (this.leftSubtree != null && this.leftSubtree.root.equals(node)) {
			parent = this;
		}
		else if (this.rightSubtree != null && this.rightSubtree.root.equals(node)) {
			parent = this;
		}
		else if (this.leftSubtree != null && node.compareTo(this.leftSubtree.root) < 0) {
			parent = this.leftSubtree.nodeParent(node);
		}
		else if (this.rightSubtree != null && node.compareTo(this.rightSubtree.root) > 0) {
			parent = this.rightSubtree.nodeParent(node);
		}
				
		return parent;
	}
	
	public BinaryTree<T> searchNode(T node) {
		// Set the subtree as the current tree we're looking through
		BinaryTree<T> nodeSubtree = this;
		
		if (node.compareTo(this.root) < 0) { // Node is less than parent
			nodeSubtree = this.leftSubtree.searchNode(node);
		}
		else if (node.compareTo(this.root) > 0) { // Node is bigger than parent
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
	        treeAsStringBuilder.append(t.root.toString() + " " + t.color + "(");
	        treeAsStringBuilder.append(treeToString(leftTree));
	        treeAsStringBuilder.append(treeToString(rightTree));
	        treeAsStringBuilder.append(")");
	    }
	    else {
	        treeAsStringBuilder.append("()");
	    }
	    
	    return treeAsStringBuilder.toString();
	}
	
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
