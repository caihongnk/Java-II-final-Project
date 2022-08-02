package edu.institution.actions.asn4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;






public class DegreeOfSeparationAction implements MenuAction{   
    // Using recursion	
	// display the path and the number of connections between the logged in user and the entered user.	
	// gwen -> doug -> sam -> nat -> zane.（3 degrees）
	// doesn’t include the initial user and the end user. 
	
	
	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		String fromUsername =loggedInUser.getUsername();
		
		System.out.println("LoggedInUserName: " + fromUsername); 
	   
		System.out.println("\nPlease enter an user name  to see the Degree of Separation:"); 
		
		String toUsername = scanner.nextLine(); 
		
		List<LinkedInUser> ignoreSet = new ArrayList<>();   //create a arraylist to store users that already searched
		
		List<String> nodes = new ArrayList<>();  //store direct connecton nodes
		
		List<String> pathSet = new ArrayList<>();
		
		int degree=0;
		
	    boolean isFound = false;
		
	    BingGo( fromUsername,userRepository,toUsername,ignoreSet,nodes,degree,loggedInUser,isFound,pathSet );
		

		return true;
		 
	}//process
	
	
	
	public static boolean BingGo( String fromUsername, UserRepository userRepository,String toUsername, List<LinkedInUser> ignoreSet,
			List<String> nodes,int degree,LinkedInUser loggedInUser,boolean isFound,List<String> pathSet ) {
		
		if(degree==0) {
			nodes.clear();
		}
		
		
		//System.out.println("The degree right now is ...." + degree + "... Name is " + fromUsername+ " Entered the BingGo.");	
		

		
		
		degree = degree + 1;
		//-----------------Initialized the fromUser--------------------		
		int index = 0 ; 	 
		for(LinkedInUser linkedInUser : userRepository.retrieveAll()) {
			if(linkedInUser.getUsername().equals( fromUsername )) {
				index = userRepository.retrieveAll().indexOf(linkedInUser);
			}
	    }
		
		LinkedInUser fromUser = userRepository.retrieveAll().get(index); 
		
		//-----------------Initialized the fromUser ---------------------
		

		
		
		
		
		ignoreSet.add(fromUser);
		pathSet.add(fromUser.getUsername());
		
		
 

		
			for(LinkedInUser linkedInUser : fromUser.getConnections() ){
								
                 if(ignoreSet.contains(linkedInUser)) {
	                       //if already searched before, do nothing
                 }else {
                	 
                     if(linkedInUser.getUsername().equals(toUsername)) {
	
                    	ignoreSet.add(linkedInUser); 
                    	isFound = true;
 				    	System.out.println("There is "+ (degree-1) + " degrees of separation between you and " + toUsername+".\n");	
 				    	
 				    	
 				    	for(String str:pathSet) {
 				    		System.out.print(str + " -> ");  
 				    	}
 				    	System.out.print( toUsername );  
 				    	
 				    	break;
 				    	   
                   }else { 
                	   
                 	   ignoreSet.add(linkedInUser); 
                 	   
                 	   boolean trueOrFalse = BingGo(linkedInUser.getUsername(),userRepository,toUsername,ignoreSet,nodes,degree,loggedInUser,isFound,pathSet); 
                 	   
                 	   if(trueOrFalse==false) {
                 		  pathSet.remove(linkedInUser.getUsername());
                 	   }
                 	      
                       
                   }
                 }

			}//for
			
			
			
			
			return isFound;
		 

         
	}//BingGo






}//DegreeOfSeparationAction
