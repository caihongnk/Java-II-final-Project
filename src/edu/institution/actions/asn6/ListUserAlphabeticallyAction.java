package edu.institution.actions.asn6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class ListUserAlphabeticallyAction implements MenuAction {
	
	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		
	  //Create a Comparetor ------------
        class CompareLinkedInUser implements Comparator<LinkedInUser>{  
        	int returnValue = 0;
        	
        	@Override
        	public int compare(LinkedInUser user1,LinkedInUser user2) {
        		
        		if(user1.getUsername()==null && user2.getUsername()!=null) {
        			
        			 returnValue = -1;
        		}
        		
        		if(user1.getUsername()!=null && user2.getUsername()==null) {
        			
       			     returnValue = 1;
       		}
        		
        		
        		if(user1.getUsername()!=null && user2.getUsername()!=null) {
        			 returnValue = user1.getUsername().compareTo( user2.getUsername() );
          			 
          		}
        		
        		
			  return returnValue; 
        	}
        }//CompareLinkedInUser
        
      //Create a Comparetor ------------
        
        
        
        List<LinkedInUser> usersList = new ArrayList<>(userRepository.retrieveAll());
		
		Collections.sort( usersList, new CompareLinkedInUser());
		
		System.out.println( usersList ); 
		
		return true;
		
	}
	
	
	
	
	

}//ListUserAlphabeticallyAction Class
