package Test;
import static org.junit.Assert.*;
import org.junit.Test;

import RedBlackTree.BinaryTree;

public class RedBlackTreeTest {
	/**
	 * Test insert one node.
	 */
	@Test
	public void insertOne() {
		BinaryTree<Integer> bt = new BinaryTree<>();
		
		int node = 30;
		bt.insert(node);
		
		String expected = "30B(()())";
		String actual = bt.treeToString(bt);
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Test inserting into left tree.
	 */
	@Test
	public void insertLeft() {
		BinaryTree<Integer> bt = new BinaryTree<>();
		
		int[] node = {30, 20};
		for (int n : node) {
			bt.insert(n);
		}
		
		String expected = "30B(20R(()())())";
		String actual = bt.treeToString(bt);
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Test inserting into right tree.
	 */
	@Test
	public void insertRight() {
		BinaryTree<Integer> bt = new BinaryTree<>();
		
		int[] node = {30, 50};
		for (int n : node) {
			bt.insert(n);
		}
		
		String expected = "30B(()50R(()()))";
		String actual = bt.treeToString(bt);
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Test inserting into left and right tree.
	 */
	@Test
	public void insertLeftRight() {
		BinaryTree<Integer> bt = new BinaryTree<>();
		
		int[] node = {30, 50, 20};
		for (int n : node) {
			bt.insert(n);
		}
		
		String expected = "30B(20R(()())50R(()()))";
		String actual = bt.treeToString(bt);
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Test LL rotation.
	 */
	@Test
	public void insertLL() {
		BinaryTree<Integer> bt = new BinaryTree<>();
		
		int[] node = {10, 20, 30};
		for (int n : node) {
			bt.insert(n);
		}
		
		String expected = "20B(10R(()())30R(()()))";
		String actual = bt.treeToString(bt);
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Test RR rotation.
	 */
	@Test
	public void insertRR() {
		BinaryTree<Integer> bt = new BinaryTree<>();
		
		int[] node = {30, 20, 10};
		for (int n : node) {
			bt.insert(n);
		}
		
		String expected = "20B(10R(()())30R(()()))";
		String actual = bt.treeToString(bt);
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Test RL rotation.
	 */
	@Test
	public void insertRL() {
		BinaryTree<Integer> bt = new BinaryTree<>();
		
		int[] node = {10, 30, 20};
		for (int n : node) {
			bt.insert(n);
		}
		
		String expected = "20B(10R(()())30R(()()))";
		String actual = bt.treeToString(bt);
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Test LR rotation.
	 */
	@Test
	public void insertLR() {
		BinaryTree<Integer> bt = new BinaryTree<>();
		
		int[] node = {30, 10, 20};
		for (int n : node) {
			bt.insert(n);
		}
		
		String expected = "20B(10R(()())30R(()()))";
		String actual = bt.treeToString(bt);
		
		assertEquals(expected, actual);
	}
}
