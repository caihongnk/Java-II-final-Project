package edu.institution.asn11;

import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import edu.institution.actions.asn5.Utilities;
import edu.institution.asn2.LinkedInUser;
import jdk.internal.joptsimple.internal.Strings;



public class BSTtest {
	
	
	@Test
	public void bstTest(){
		// ----------TEST breadthFirstTraversal ----------
		BST<Integer> bstTree = new BST<>();
		bstTree.insert(50);
		bstTree.insert(48);
		bstTree.insert(30);
		bstTree.insert(20);
		bstTree.insert(15);
		bstTree.insert(70);
		bstTree.insert(65);
		bstTree.insert(32);
		bstTree.insert(31);
		bstTree.insert(25);
		bstTree.insert(90);
		bstTree.insert(67);
		bstTree.insert(66);
		bstTree.insert(35);
		bstTree.insert(98);
		bstTree.insert(69);
		bstTree.insert(94); 
		bstTree.insert(99);
	    
		List<Integer> breadthFirstTraversal_List = new ArrayList<>();
		
		breadthFirstTraversal_List= bstTree.breadthFirstTraversal();
		
		ArrayList compareList_A = new ArrayList<>(Arrays.asList( 50,48,70,30,65,90,20,32,67,98,15,25,31,35,66,69,94,99));  
		
		ArrayList compareList_B = new ArrayList<>();
		
		for(Integer e :breadthFirstTraversal_List) {
			compareList_B.add(e);
		}
		//System.out.print(e.element + ",");
		Assert.assertTrue(  compareList_A.equals(compareList_B)  );
       
		BST<String> bstTree2 = new BST<>();
		bstTree2.insert("S");
		bstTree2.insert("D");
		bstTree2.insert("V");
		bstTree2.insert("F");
		bstTree2.insert("A");
		bstTree2.insert("B");
		bstTree2.insert("K");
		bstTree2.insert("N");
		bstTree2.insert("M");
		
		List<String> breadthFirstTraversal_List2 = new ArrayList<>();
		breadthFirstTraversal_List2 = bstTree2.breadthFirstTraversal();
		ArrayList compareList_A2 = new ArrayList<>(Arrays.asList( "S","D","V","A","F","B","K","N","M"));
		ArrayList compareList_B2 = new ArrayList<>();
		for(String e :breadthFirstTraversal_List2) {
			compareList_B2.add(e);
			//System.out.print(e.element + ",");
		}
		Assert.assertTrue(  compareList_A2.equals(compareList_B2)  );
		// ----------TEST breadthFirstTraversal ----------
		
		
		// ----------TEST getHeight method ----------
		
		Assert.assertEquals(4, bstTree.getHeight());
		
		Assert.assertEquals(5, bstTree2.getHeight());
		

		
		
		
		// ----------TEST nonRecursiveInorder traversal method ----------
		
		List<Integer> listFornonRecursiveInorder = new ArrayList<>();
		listFornonRecursiveInorder = bstTree.nonRecursiveInorder();
		ArrayList compareList_listFornonRecursiveInorderA = new ArrayList<>(Arrays.asList(15,20,25,30,31,32,35,48,50,65,66,67,69,70,90,94,98,99));
		ArrayList compareList_listFornonRecursiveInorderB  = new ArrayList<>();
		for(Integer e :listFornonRecursiveInorder) {
			compareList_listFornonRecursiveInorderB.add( e );
			
		}
		Assert.assertTrue(  compareList_listFornonRecursiveInorderA.equals(compareList_listFornonRecursiveInorderB)  );
		
		
		List<String> listFornonRecursiveInorder2 = new ArrayList<>();
		listFornonRecursiveInorder2 = bstTree2.nonRecursiveInorder();
		ArrayList compareList_listFornonRecursiveInorderA2 = new ArrayList<>(Arrays.asList(
				"A","B","D","F","K","M","N","S","V"));
		ArrayList compareList_listFornonRecursiveInorderB2  = new ArrayList<>();
		for(String e :listFornonRecursiveInorder2) {
			compareList_listFornonRecursiveInorderB2.add( e );
			
		}
		Assert.assertTrue(  compareList_listFornonRecursiveInorderA2.equals(compareList_listFornonRecursiveInorderB2)  );




	}//BSTtest
    

}
