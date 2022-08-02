package edu.institution.actions.asn4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.actions.asn3.AddUserAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;




public class AddConnectionAction implements MenuAction{ 
	
	

	
	
	//This action will add a LinkedIn user to the logged in user’s connection list	
	//Prompt for the user name of the user to connect with.
	//If the user does NOT exist, display the message, “There is no user with that user name”, return true to keep the user signed in.
	//If the user does exist, add the user to the logged in user’s connection list
	//If the user is already in the logged in user’s connection list, 
	//then catch the LinkedInException that was thrown in the second assignment and display the message
	//Return true to keep the user signed in
	
	

	
	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {		
   		
		if(userRepository.retrieveAll().isEmpty() || userRepository.retrieveAll()==null) {
			System.out.println("The User List is empty or it is null.");
		}else {
			int index = 0 ;
			boolean isLoggedInUserExist= false;
			for(LinkedInUser linkedInUser : userRepository.retrieveAll()) {
				if(linkedInUser.getUsername().equals(loggedInUser.getUsername())) {
					System.out.println("\nLoginUser  name is: " + linkedInUser.getUsername());
					index = userRepository.retrieveAll().indexOf(linkedInUser);
					isLoggedInUserExist =true; 
				}
		    }
			

			
			if(isLoggedInUserExist) {  //isLoggedInUserExist True
				
				System.out.println("Please enter username need to Add:");
				String userName = scanner.nextLine(); 
					
				List<LinkedInUser> connections = null;
				
				if(  userRepository.retrieveAll().get(index).getConnections()==null ) {
					System.out.println("Fail to retrive your connections.");
					
				}else if(userRepository.retrieveAll().get(index).getConnections().isEmpty()) {
					
					connections = new ArrayList<>();	
		
				}else {
					connections = userRepository.retrieveAll().get(index).getConnections();   //retrive data and asign value to connections list
				}	
					
				loggedInUser.setConnections(connections);    //initialized loggedInUser connections
				//Check if the connection exist in the Users List
					boolean isConnectionExist= false;
					LinkedInUser connectionNeedToAdd = null ;
					for(LinkedInUser linkedInUser : userRepository.retrieveAll()) {
						if(linkedInUser.getUsername().equals(userName)) {
							connectionNeedToAdd = linkedInUser;
							isConnectionExist = true;
						}
					}
					
					if(isConnectionExist) { // if the connection exist in the Users List
						//-------------------------------
						if( !connections.contains( connectionNeedToAdd ) ) {
							try { 
								loggedInUser.addConnection(connectionNeedToAdd);	
								userRepository.retrieveAll().set(index, loggedInUser);  // update in the users list
								userRepository.saveAll();
								loggedInUser.getAddedConnectionsStack().push(connectionNeedToAdd);
								loggedInUser.getHistory().push(new AddConnectionAction()); //### push this  Stack of history in UndoAction
								System.out.println("The connection ADD sucessfully!");
							} catch (NullPointerException e) {
								System.out.println("NullPointerException"); 
							} catch (LinkedInException e) {}

						}else {
							System.out.println("The connection already in your connection List."); 
						}
			
					}else { // if the connection does not exist in the Users List
						     System.out.println("The username of the connection does not exist in the Users List!");
					}
					
				


			}else {  System.out.println("The LoggedInUser is not exist !!!"); }

		}
			

		return true;
	}




	
	
}