package edu.institution.asn5;

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



public class UtilitiesTest {
	
	// uses an array of Integers, an array of Strings,and an array of LinkedInUser’s. 
	@Test
	public void removeDuplicatesTest(){
        Utilities utilities = new Utilities();  

	        //Test if pass null as argument
	boolean ExceptionIsNotThrown= true;
	
	try {
		utilities.removeDuplicates(null);	
	}catch(Exception e) {
		ExceptionIsNotThrown = false;
	}         
	Assert.assertTrue(ExceptionIsNotThrown);
	
	
	//test if is empty List
	List<Integer> emptyList = new ArrayList<>();
	utilities.removeDuplicates(emptyList);	
	Assert.assertTrue(emptyList.isEmpty());	 
	
	
	//test with integer List
	List<Integer> integersList = new ArrayList<>();
	integersList.add(1);
	integersList.add(1);
	integersList.add(1); 
	integersList.add(2); 
	integersList.add(2); 
	integersList.add(2);
	integersList.add(3);
	integersList.add(4);
	utilities.removeDuplicates(integersList);
	
	List<Integer> expectIntList = new ArrayList<>();
	
	expectIntList.add(3);	
	expectIntList.add(4);
	expectIntList.add(1);
	expectIntList.add(2);
	Assert.assertEquals(expectIntList, integersList);
	
	
	
	//test with  Strings List
	List<String> stringsList = new ArrayList<>();
	stringsList.add("one");
	stringsList.add("one");
	stringsList.add("one");
	stringsList.add("two");
	stringsList.add("two");
	stringsList.add("two");
	stringsList.add("three");
	stringsList.add("four");
	utilities.removeDuplicates(stringsList);
	
	List<String> expectStringList = new ArrayList<>();
	
	expectStringList.add("three");	
	expectStringList.add("four");
	expectStringList.add("one");
	expectStringList.add("two");
	Assert.assertEquals(expectStringList, stringsList);
	
	
	
	//test with array of LinkedInUser’s
	List<LinkedInUser> linkedInUserList = new ArrayList<>();
	LinkedInUser user1 = new LinkedInUser("user1","123");
	LinkedInUser user2 = new LinkedInUser("user2","123");
	LinkedInUser user3 = new LinkedInUser("user3","123");
	LinkedInUser user4 = new LinkedInUser("user4","123");
	linkedInUserList.add(user1);
	linkedInUserList.add(user1); 
	linkedInUserList.add(user1);
	linkedInUserList.add(user2);
	linkedInUserList.add(user2);
	linkedInUserList.add(user2);
	linkedInUserList.add(user3);
	linkedInUserList.add(user4);
	utilities.removeDuplicates(linkedInUserList);
	
	List<LinkedInUser> expectUserList = new ArrayList<>();
	expectUserList.add(user3);
	expectUserList.add(user4);
	expectUserList.add(user1);
	expectUserList.add(user2);
	Assert.assertEquals(expectUserList, linkedInUserList);
	
	
	
	
	// Test contains only one item
	List<LinkedInUser> onlyOneUserList = new ArrayList<>();
	LinkedInUser onlyme = new LinkedInUser("onlyme","123");
	onlyOneUserList.add(onlyme);
	utilities.removeDuplicates(onlyOneUserList);
	Assert.assertEquals(1, onlyOneUserList.size());
	Assert.assertEquals(onlyme, onlyOneUserList.get(0));
	
	
	
	// Test identical Element List
	List<Integer> identicalElementList = new ArrayList<>();
	identicalElementList.add(1);
	identicalElementList.add(1);
	identicalElementList.add(1); 
	identicalElementList.add(1); 
	utilities.removeDuplicates(identicalElementList);		
	List<Integer> identicalElementExpectList = new ArrayList<>();		
	identicalElementExpectList.add(1);		
	Assert.assertEquals(identicalElementExpectList, identicalElementList);
			
			
			

		
	}//removeDuplicatesTest 
    
	
	
	@Test
	public void  linearSearchTest(){
		   Utilities utilities = new Utilities(); 

			//test with integer List
			List<Integer> integersList = new ArrayList<>();
			integersList.add(1);
			integersList.add(2);
			integersList.add(3);
			integersList.add(4);							
			Assert.assertEquals(Integer.valueOf(4), utilities.linearSearch(integersList,4) );
			Assert.assertEquals(null, utilities.linearSearch(integersList,null) );
			Assert.assertEquals(null, utilities.linearSearch(null,null) );
			Assert.assertEquals(null, utilities.linearSearch(integersList,100) );

		    
			//test with  Strings List
			List<String> stringsList = new ArrayList<>();
			stringsList.add("one");
			stringsList.add("two");
			stringsList.add("three");
			stringsList.add("four");
			Assert.assertEquals("four", utilities.linearSearch( stringsList,"four") );
			Assert.assertEquals(null, utilities.linearSearch( stringsList,null) );
			Assert.assertEquals(null, utilities.linearSearch(null,null) );
			Assert.assertEquals(null, utilities.linearSearch(stringsList,"xxxx") );
			
						
			//test with array of LinkedInUser’s
			List<LinkedInUser> linkedInUserList = new ArrayList<>();
			LinkedInUser user1 = new LinkedInUser("user1","123");
			LinkedInUser user2 = new LinkedInUser("user2","123");
			LinkedInUser user3 = new LinkedInUser("user3","123");
			LinkedInUser user4 = new LinkedInUser("user4","123");
			linkedInUserList.add(user1);
			linkedInUserList.add(user2);
			linkedInUserList.add(user3);
			linkedInUserList.add(user4);
			
			LinkedInUser user = new LinkedInUser("user1","123");
			LinkedInUser userNotExist = new LinkedInUser("userNotExist","123");
					
			Assert.assertEquals( user, utilities.linearSearch( linkedInUserList, user) );
			Assert.assertEquals(null, utilities.linearSearch( linkedInUserList,null) );
			Assert.assertEquals(null, utilities.linearSearch(null,null) );
			Assert.assertEquals(null, utilities.linearSearch(linkedInUserList,userNotExist) );
			
			
			
			// Test identical Element List
			List<Integer> identicalElementList = new ArrayList<>();
			identicalElementList.add(1);
			identicalElementList.add(1);
			identicalElementList.add(1); 
			identicalElementList.add(1); 
					
			List<Integer> identicalElementExpectList = new ArrayList<>();		
			identicalElementExpectList.add(1);		
			Assert.assertEquals(Integer.valueOf(1), utilities.linearSearch(identicalElementList,Integer.valueOf(1)));
					
			
	       	//test if is empty List
			List<Integer> emptyList = new ArrayList<>();				
			Assert.assertEquals(null,utilities.linearSearch(emptyList,Integer.valueOf(1)));
			
			
		
	}//linearSearchTest





	

	 
	
	
	
}
