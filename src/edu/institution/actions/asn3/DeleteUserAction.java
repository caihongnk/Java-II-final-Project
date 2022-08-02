package edu.institution.actions.asn3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;
import edu.institution.actions.asn10.UndoAction;

public class DeleteUserAction implements MenuAction {	
	//Prompt for the user name to delete.Check if user name exists in the user list.
	//If the user does NOT exist, display an error message to the console and re-display the menu.
	//If the user does exist, prompt for the password of the user being deleted
	//If the password is not correct, display an error message to the console and return out of the action
	//If the password is correct, call the delete method on the user repository passing the user name.
	//If the deleted user was the logged in user, then the action should return false to tell the
	//Application Controller to sign out the current user. Otherwise, return true to keep the user signed in
	
	
	
	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		boolean loginStatus = true;  //true: keep login status;  true also means re-display the menu; false: log out.
		boolean isUserExist = false;      //(false indicate the user does NOT exist )
		boolean isPasswordMatch = false;
		int indexOfUserForDelete=0; 
				
		System.out.println("Enter the username you want to delete");  
		String username =scanner.nextLine();		
				
        if(userRepository.retrieveAll().isEmpty()) {
    		if(username.equalsIgnoreCase(loggedInUser.getUsername())) {
    			System.out.println("Please enter the password to delete:");
    			String password =scanner.nextLine();
    			if(password.equals(loggedInUser.getPassword() )) {
    				loginStatus = false;
    				System.out.println("You are logining out...");
    			}						
    		}else { System.out.println("There is no users in the list, please add users first."); }
        	
 	
        }else {
        	      	
    		for(LinkedInUser linkedInuUer :userRepository.retrieveAll() ) {   
    			if( linkedInuUer.getUsername().equalsIgnoreCase(username)) {   //if the supplied username already exist(can be deleted)
    				isUserExist = true;
    				indexOfUserForDelete = userRepository.retrieveAll().indexOf(linkedInuUer); 
    				
    				if( linkedInuUer.getUsername().equals( loggedInUser.getUsername() )) {   //if the user of the supplied username need to be deleted also is the login user
    					loginStatus =false;
    				}				
    			}			
    		}//for
        	
    		if(isUserExist) {
    			System.out.println("Please enter the password to delete:");
    			String password =scanner.nextLine();
				if( userRepository.retrieveAll().get(indexOfUserForDelete).getPassword().equals(password)) {
					isPasswordMatch = true;
				}
    			   			
    			if(isPasswordMatch) {
    						
    				if(!loginStatus) {
    					userRepository.delete( userRepository.retrieveAll().get(indexOfUserForDelete) );
    					System.out.println("Delete finish.");
    					System.out.println("You are logining out...");				
    				}
    				
    				if(loginStatus) {
    					LinkedInUser userNeedToBeDeled = userRepository.retrieveAll().get(indexOfUserForDelete); 
  
    					userRepository.delete( userNeedToBeDeled );
    					
    					loggedInUser.getDeletedUsersStack().push(userNeedToBeDeled);
    					loggedInUser.getHistory().push(new DeleteUserAction());  //### push this Action instance to Stack of history in UndoAction
    					
    					System.out.println("Delete finish.");
    					

    				}
    			}
    			
    			
    			if(!isPasswordMatch) {
    				System.out.println("The password is not match.");
    				loginStatus =true;
    			}
    		}
    		
    		
    		if(!isUserExist){
    			if(username.equalsIgnoreCase(loggedInUser.getUsername())) {
    				System.out.println("Please enter the password to delete:");
    				String password =scanner.nextLine();
    				if(password.equals( loggedInUser.getPassword() )) {
    					loginStatus =false;   
    					System.out.println("You are logining out...");
    				}
    			}else {
    				System.out.println("The supplied username is not exist.");
    			}
    			
    		}
    		
        }//else (iif users List not empty)
    

		return loginStatus;
		
		
	}
	

}