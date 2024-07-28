
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
	public BinaryTree<T> delete(T node) {
		/*
		 * Case 1: node to delete is a leaf
		 * Case 2: node to delete has a single child
		 * Case 3: node to delete has two children
		 */
		
		BinaryTree<T> nodeToDelete = new BinaryTree<>();
		
		BinaryTree<T> leftCurrentNode = this.leftSubtree;
		BinaryTree<T> rightCurrentNode = this.rightSubtree;
		
		if (leftCurrentNode.root.equals(node) && leftCurrentNode.leftSubtree.equals(null) && rightCurrentNode.rightSubtree.equals(null)) {
			this.leftSubtree = null;
		}
		
		
//		if (this.root.equals(node) && this.leftSubtree.equals(null) && this.rightSubtree.equals(null)) {
//			nodeToDelete = this;
//		}
//		else if (this.leftSubtree.equals(null) && this.rightSubtree.equals(null)) {
//			System.out.println("Node not found");
//		}
//		else if (this.leftSubtree.equals(null)) { // single child, right child
//			nodeToDelete = this.rightSubtree;
//		}
//		else if (this.rightSubtree.equals(null)) {
//			// single child, left child
//			nodeToDelete = this.leftSubtree;
//		}
//		else if ((!this.leftSubtree.equals(null) && this.rightSubtree.equals(null))) {
//			nodeToDelete = this.delete(node);
//		}
//		else {
//			System.out.println("Something went wrong somewhere");
//		}
//		
//		return nodeToDelete;
		
//		// 1. Search for the node to delete and see if it even exists
//		BinaryTree<T> nodeToDelete = searchNode(node);
//		
//		if (nodeToDelete != null) { // Node to delete exists in the tree
//			BinaryTree<T> parentNode = nodeParent(node);
//			
//			// Case 1: node is a leaf
//			if (nodeToDelete.leftSubtree.equals(null) && nodeToDelete.rightSubtree.equals(null)) {
//				// Un-adopt the node from the node's parent
//				/*
//				 * If node is in the left child, re-parent the left child to a null node.
//				 * Vice versa
//				 */
//				if (parentNode.leftSubtree.root.equals(node)) {
//					parentNode.leftSubtree = null;
//				}
//				else if (parentNode.rightSubtree.root.equals(node)) {
//					parentNode.rightSubtree = null;
//				}
//				else {
//					System.out.println("there's an issue with the parenting");
//				}
//			}
//			// Case 2: node to delete has a single child
//			// 2A: Child is on the left
//			else if (!nodeToDelete.leftSubtree.equals(null) && nodeToDelete.rightSubtree.equals(null)) {
//				
//			}
//		}
		 
//		// 2. Within the nodeToDelete's subtree, search within the left child's subtree for the max as long as it exists
//		BinaryTree<T> nodeToReplaceWith = new BinaryTree<>();
////		if (nodeToDelete.leftSubtree != null) {
////			nodeToReplaceWith = max(nodeToDelete.leftSubtree);
////			
////			// 2A. Replace the node to delete with new node details (value, color)
////			nodeToDelete.root = nodeToReplaceWith.root;
////			nodeToDelete.color = nodeToReplaceWith.color;
////		}
//		
//		/*
//		 * 3. Re-point the max's parent to the max's left child
//		 * (There is no right child. If there is, that means this is not the max.)
//		 */
//		BinaryTree<T> maxParent = new BinaryTree<>();
//		
//		
//		if (nodeToDelete.leftSubtree != null) {
//			nodeToReplaceWith = max(nodeToDelete.leftSubtree);
//			maxParent = nodeParent(nodeToReplaceWith.root);
//			
//			// 2A. Replace the node to delete with new node details (value, color)
//			nodeToDelete.root = nodeToReplaceWith.root;
//			nodeToDelete.color = nodeToReplaceWith.color;
//			
//			if (maxParent != null) { // If this is not the root of the tree
//				maxParent.rightSubtree = nodeToReplaceWith.leftSubtree;
//			}
//		}
//		else {
//			maxParent.rightSubtree = nodeToReplaceWith;
//		}
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
