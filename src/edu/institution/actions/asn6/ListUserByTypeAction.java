package edu.institution.actions.asn6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class ListUserByTypeAction implements MenuAction{     //???Your sort by type fails when a LinkedInUser has a null user name.
   
	// ordered first by their type and second alphabetically by their user name
	//first on their type and second on their user name
	//(both alphabetically). Display the user name along with the type.
	//Url: https://howtodoinjava.com/java/sort/sort-on-multiple-fields/
	
	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {

		  List<LinkedInUser> usersList = new ArrayList<>( userRepository.retrieveAll() );
		
		  
		  //Create a Comparetor ------------
		  class  CompareByTypeThenByName implements Comparator<LinkedInUser>{
			  
			  int different =0;
			  
			  @Override 
			  public int compare(LinkedInUser user1, LinkedInUser user2) {
                  if(user1.getUsername()==null && user2.getUsername()!=null) {
                	  different =-1;
                  }      

                  if(user1.getUsername()!=null && user2.getUsername()==null) {
                	  different = 1;
                  }  
                  
                  if(user1.getUsername()==null && user2.getUsername()==null) {
                	  different = 0;
                  }  
                  
                  if(user1.getUsername()!=null && user2.getUsername()!=null) {
                	  different = 2;
                  }
                  
                  if(different == 2  ) {
                	  different = user1.getType().compareToIgnoreCase( user1.getType());  	  
                  }

                  return  different;
			   }//compare
			  
			  
		     }//CompareByTypeThenByName 
		  
		  //Create a Comparetor ------------
		  

		  Collections.sort( usersList, new CompareByTypeThenByName());
		  
		
		  
		  for(LinkedInUser user: usersList) {
			  System.out.println(" \" " + user.getUsername() + ";" + " type = " + user.getType() + " \" " );
		  }
		  
		  
	
		  
		  return true;
		
	}

	
	
}//Class
