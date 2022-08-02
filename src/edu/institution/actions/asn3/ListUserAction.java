package edu.institution.actions.asn3;

import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;
import java.util.List;


//This action should retrieve all of the LinkedIn users from the user repository 
//and print each user’s user name to the console. 
//The process method for the action should return true to keep the user signed in

public class ListUserAction implements MenuAction {

	//retrieve all of the LinkedIn users from the user repository and print each user’s user name to the console.	
	//The process method for the action should return true to keep the user signed in.
	
	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		
		
		//Make sure the LinkedInUser users ArrayList is not empty 
        if(userRepository.retrieveAll().isEmpty()) {
        	System.out.println("There is no users in the list, please add users first.");
        }else {
        	
    		for(LinkedInUser linkedInuUer : userRepository.retrieveAll()) {
    			System.out.println(linkedInuUer.getUsername());
    		}
        	
        }//else if List is not empty
		
		return true;
	}

}//class