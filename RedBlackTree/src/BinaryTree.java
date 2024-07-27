
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
				insert(leftSubtree, node);
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
				insert(rightSubtree, node);
			}
		}
	}
	
	/**
	 * Deletes the node from the tree and replaces it with the maximum value in the left child tree of the node.
	 * @param tree
	 * @param node
	 */
	public void delete(BinaryTree<T> tree, T node) {
		// 1. Search for the node to delete and see if it even exists
		BinaryTree<T> nodeToDelete = tree.searchNode(node);
		
		// 2. Within the nodeToDelete's subtree, search within the left child's subtree for the max
		BinaryTree<T> nodeToReplaceWith = nodeToDelete.max(nodeToDelete);
		// 2A. Replace the node to delete with new node details (value, color)
		nodeToDelete.root = nodeToReplaceWith.root;
		nodeToDelete.color = nodeToReplaceWith.color;
		
		/*
		 * 3. Re-point the max's parent to the max's left child
		 * (There is no right child. If there is, that means this is not the max.)
		 */
		//this.root = nodeToReplaceWith;
		//BinaryTree<T> replacementNodeParent = nodeParent(nodeToReplaceWith);
	}
	
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
		else if (this.leftSubtree.root.equals(node) || this.rightSubtree.root.equals(node)) {
			parent = this;
		}
		else if (node.compareTo(this.leftSubtree.root) < 0) {
			parent = this.leftSubtree.nodeParent(node);
		}
		else if (node.compareTo(this.rightSubtree.root) > 0) {
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
	    
	    if (t != null) {
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
