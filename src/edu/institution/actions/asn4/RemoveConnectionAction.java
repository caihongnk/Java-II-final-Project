package edu.institution.actions.asn4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;






public class RemoveConnectionAction implements MenuAction{ 
	
	

	
    //remove a user from the logged in user’s connection list.
	//remove a user from the logged in user’s connection list.
	//If the user does NOT exist, display the message, “There is no user with that user name”
	//If the user does exist,remove the user from the logged in user’s connection list 
	

	
	
	
	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {		
		
		
		if(userRepository.retrieveAll().isEmpty() || userRepository.retrieveAll()==null) {
			System.out.println("The User List is empty or it is null.");
		}else {
			int index = 0 ;
			boolean isLoggedInUserExist= false;
			for(LinkedInUser linkedInUser : userRepository.retrieveAll()) {
				if(linkedInUser.getUsername().equals(loggedInUser.getUsername())) {
					System.out.println("LoginUser  name is: " + linkedInUser.getUsername());
					index = userRepository.retrieveAll().indexOf(linkedInUser);
					isLoggedInUserExist =true; 
				}
		    }
			
			System.out.println("Please enter username need to Remove:");
			String userName = scanner.nextLine(); 
			
			if(isLoggedInUserExist) {  //isLoggedInUserExist True		
					
				if( userRepository.retrieveAll().get(index).getConnections().isEmpty() || userRepository.retrieveAll().get(index).getConnections()==null ) {
					System.out.println("You have no connetions or fail to retrive your connections.");
					
				}else {
					List<LinkedInUser> connections = userRepository.retrieveAll().get(index).getConnections();   //retrive data and asign value to connections list
					loggedInUser.setConnections(connections);    //initialized loggedInUser connections
					//Check if the connection exist in the Users List
					boolean isConnectionExist= false;
					LinkedInUser connectionNeedToRemove = null ;
					for(LinkedInUser linkedInUser : userRepository.retrieveAll()) {
						if(linkedInUser.getUsername().equals(userName)) {
							connectionNeedToRemove = linkedInUser;
							isConnectionExist = true;
						}
					}
					if(isConnectionExist) { // if the connection exist in the Users List
						
						if( loggedInUser.getConnections().contains(connectionNeedToRemove)  ) {
							try { 
								loggedInUser.removeConnection(connectionNeedToRemove);	
								userRepository.retrieveAll().set(index, loggedInUser);  // update in the users list
								userRepository.saveAll();
								loggedInUser.getRemoveConnectionsStack().push(connectionNeedToRemove);
								loggedInUser.getHistory().push(new RemoveConnectionAction()); //### push this  Stack of history in UndoAction
								//System.out.println("The connection REMOVE sucessfully!");
							} catch (NullPointerException e) {
								System.out.println("NullPointerException"); 
							} catch (LinkedInException e) {}	
							
						}else {
							System.out.println("Your connection doesn't have this user."); 
						}
								
					}else { // if the connection does not exist in the Users List
						     System.out.println("The username of the connection does not exist in the Users List!");
					}
				}

			}else {  System.out.println("The LoggedInUser is not exist !!!"); }

		}//User List is empty else
   		

			

		return true;
	}



}


		
	
