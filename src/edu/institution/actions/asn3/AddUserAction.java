package edu.institution.actions.asn3;

import java.util.Scanner;
import java.util.Stack;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;
import edu.institution.actions.asn10.UndoAction;

public class AddUserAction implements MenuAction {
	
	

	//Use the supplied Scanner instance to prompt the user for information. 
	//Prompt the user for the user name to add.
	//prompt the user to enter a password and what type of user he/she is.
	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		
        	
    		try {
    			System.out.println("Enter a username.");
    			String username = scanner.nextLine();
    			System.out.println("Enter a password.");
    			String password = scanner.nextLine();
    			System.out.println("Enter the type of user(P or S).");
    			String type = scanner.nextLine();
    			
    			LinkedInUser user = new LinkedInUser(username,password);
    			user.setType(type);
    			
    			loggedInUser.getHistory().push(new AddUserAction()); //### push this Action instance to Stack of history in UndoAction
    			loggedInUser.getAddedUsersStack().push(user);
    			
    			userRepository.add(user);	
    			
    			
    			
    			
    			
    			
    		} catch (LinkedInException exception) {
    			    			
    		}
        	
        	
       
        
		return true;  //Return true keep signed in status
	}
	
}//class