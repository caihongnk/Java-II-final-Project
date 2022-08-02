package edu.institution.asn11;

import java.util.*;


public class BST<E extends Comparable<E>>  {
 
	protected TreeNode<E> root;
	protected int size = 0;
	
	public BST() { }
	
	public BST(E[] objects) {
		for (int i=0; i<objects.length; i++) {
			insert(objects[i]);
		}
	}
	 
	public boolean search(E e) {
		TreeNode<E> current = root;
		
		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				current = current.right;
			} else {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean insert(E e) {
		if (root == null) {
			root = createNewNode(e);
		} else {
			TreeNode<E> parent = null;
			TreeNode<E> current = root;
			while (current != null) {
				if (e.compareTo(current.element) < 0) {
					parent = current;
					current = current.left;
				} else if (e.compareTo(current.element) > 0) {
					parent = current;
					current = current.right;
				} else {
					return false;
				}
			}
				
			if (e.compareTo(parent.element) < 0) {
				parent.left = createNewNode(e);
			} else {
				parent.right = createNewNode(e);
			}
		}
		
		size++;
		return true;
	}
	
	protected TreeNode<E> createNewNode(E e) {
		return new TreeNode<E>(e);
	}
	
	public void inorder() {
		inorder(root);
	}
	
	protected void inorder(TreeNode<E> root) {
		if (root == null) return;
		inorder(root.left);
		System.out.print(root.element + " ");
		inorder(root.right);
	}
	
	public void postorder() {
		postorder(root);
	}
	
	protected void postorder(TreeNode<E> root) {
		if (root == null) return;
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.element + " ");
	}
	
	public void preorder() {
		preorder(root);
	}
	
	protected void preorder(TreeNode<E> root) {
		if (root == null) return;
		System.out.print(root.element + " ");
		preorder(root.left);
		preorder(root.right);
	}
	
	public int getSize() {
		return size;
	}
	
	public TreeNode<E> getRoot() {
		return root;
	}
	
	public ArrayList<TreeNode<E>> path(E e) {
		ArrayList<TreeNode<E>> list = new ArrayList<>();
		TreeNode<E> current = root;
		
		while (current != null) {
			list.add(current);
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				current = current.right;
			} else {
				break;
			}
		}
		
		return list;
	}
	
	public boolean delete(E e) {
		TreeNode<E> parent = null;
		TreeNode<E> current = root;
		
		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				parent = current;
				current = current.left;
			} else if (e.compareTo(current.element)> 0) {
				parent = current;
				current = current.right;
			} else {
				break;
			}
		}
		
		if (current == null) {
			return false;
		}
		
		if (current.left == null) {
			if (parent == null) {
				root = current.right;
			} else {
				if (e.compareTo(parent.element) < 0) {
					parent.left = current.right;
				} else {
					parent.right = current.right;
				}
			}
		} else {
			TreeNode<E> parentOfRightMost = current;
			TreeNode<E> rightMost = current.left;
			
			while (rightMost.right != null) {
				parentOfRightMost = rightMost;
				rightMost = rightMost.right;
			}
			
			current.element = rightMost.element;
			
			if (parentOfRightMost.right == rightMost) {
				parentOfRightMost.right = rightMost.left;
			} else {
				parentOfRightMost.left = rightMost.left;
			}
		}
		
		size--;
		return true;
	}//delete
	
	/**
	* Traverses the nodes using the breadth-first traversal algorithm and
	* and returns a list of elements in the correct order.
	 * @return  
	* @return the elements in the order that reflects a breadth-first traversal.
	*/
	public List<E> breadthFirstTraversal(){
		return this.breadthFirstTraversal(root);
	}
	
	public List<E> breadthFirstTraversal(TreeNode<E> root){    //URL: https://gist.github.com/thmain/22fd068398ed990166413c874e549814
		ArrayList<TreeNode<E>> list = new ArrayList<>();
		List<E> returnList = new ArrayList<>();
		Queue<TreeNode<E>>	que = new LinkedList<>();
		
		
		if (root == null) return returnList;
		

		que.add(root);
		while(!que.isEmpty()) {
			
		TreeNode<E>	node = que.remove();
		list.add(node);
		if(list.size()==size) {}
			
			if(node.left!=null) {
				que.add(node.left);
			}
			
			if(node.right!=null) {
				que.add(node.right);
			}	
		}
		
		for(TreeNode<E> e :list) {
			returnList.add(e.element);
		}
		
		
		
		
		return returnList;
		
	}//breadthFirstTraversal
	
	
	/**
	* Returns the number of edges between the tree's root and its furthest leaf.
	* @return the height.
	* 
	*/
	public int getHeight() {
		return getHeight(root);
	}
	
	
	public int getHeight(TreeNode<E> root) {
		
		int height =0;
		ArrayList<TreeNode<E>> list = new ArrayList<>();
		Queue<TreeNode<E>>	que = new LinkedList<>();
		que.add(root);
		while(!que.isEmpty()) {
			
		TreeNode<E>	node = que.remove();
		
		list.add(node);
		if(list.size()==size && list.size()!=0) {        //find the last node
			TreeNode<E>	lastNode = node;
			ArrayList<TreeNode<E>> path = this.path(lastNode.element);
			height =  path.size() - 1;              //get the height
		}
			
			if(node.left!=null) {
				que.add(node.left);
			}
			
			if(node.right!=null) {
				que.add(node.right);
			}	
		}//while
		

		return height;
		
	}
	
	
	public List<E> nonRecursiveInorder(){
		return this.nonRecursiveInorder(root);
	}
	/**
	* Returns the results of an in-order traversal without the use of recursion.
	* @return the elements in the order that reflects an in-order traversal.
	*/
	//Re-implement the inorder method in a new method , using a stack instead of recursion.
	public List<E> nonRecursiveInorder( TreeNode<E> root ){     //URL: https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
		TreeNode<E>  current;
		Stack<TreeNode<E>> stack = new Stack();
		List<TreeNode<E>> nodesList = new ArrayList<>();
		List<E> returnList = new ArrayList();
		
		if (root == null) return returnList;
		
		
		current = root;
		while(current!=null || !stack.isEmpty()) {
			
			while(current!=null) {												
				stack.push(current);	
				current=current.left;				
			}
			current= stack.pop();
			nodesList.add(current);
			current = current.right;

		}//1st
		
		
		for(TreeNode<E> e : nodesList) {
			returnList.add(e.element);
		}
		


		return returnList;
		
	}
	
//---------------------------------------------------------------------
	
	public Iterator<E> iterator() {
		return new InorderIterator();
	}
	
	public void clear() {
		root = null;
		size = 0;
	}
	
	
	private class InorderIterator implements Iterator<E> {
		
		private ArrayList<E> list = new ArrayList<>();
		private int current = 0;
		
		public InorderIterator() {
			inorder();
		}
		
		private void inorder() {
			inorder(root);
		}
		
		private void inorder(TreeNode<E> root) {
			if (root == null) return;
			inorder(root.left);
			list.add(root.element);
			inorder(root.right);
		}
		
		public boolean hasNext() {
			return current < list.size();
		}
		
		public E next() {
			return list.get(current++);
		}
		
		public void remove() {
			delete(list.get(current));
			list.clear();
			inorder();
		}
	}
}

