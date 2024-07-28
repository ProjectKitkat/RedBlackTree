public class RedBlackTree {

	public static void main(String[] args) {
		BinaryTree<Integer> bt = new BinaryTree<>();
		System.out.println("new tree: " + bt.root());
		
//		bt.insert(bt, 5);
//		bt.insert(bt, 3);
//		bt.insert(bt, 7);
//		bt.insert(bt, 6);
//		bt.insert(bt, 10);
		
		bt.insert(bt, 5);
		bt.insert(bt, 3);
		bt.insert(bt, 20);
		bt.insert(bt, 10);
		bt.insert(bt, 30);
		bt.insert(bt, 6);
		bt.insert(bt, 15);
		bt.insert(bt, 13);
		bt.insert(bt, 14);

		System.out.println(bt.treeToString(bt));
		//System.out.println("maxParent: " + bt.maxParent(bt).root());
		System.out.println("insert, root: " + bt.root());
		System.out.println("insert, left child: " + bt.leftChild().root());
		System.out.println("insert, right child: " + bt.rightChild().root());
		//System.out.println("left, left: " + bt.leftChild().leftChild().root());
		
		System.out.println("max: " + bt.max(bt).root());
		
		System.out.println("search node: " + bt.searchNode(2).root());
		bt.nodeParent(10);
		System.out.println("node parent of: " + bt.nodeParent(10).root());
//		bt.delete(14);
		bt.delete(20);
		System.out.println(bt.treeToString(bt));
	}

}
