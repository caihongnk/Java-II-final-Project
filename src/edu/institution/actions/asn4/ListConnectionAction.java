package edu.institution.actions.asn4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;




public class ListConnectionAction implements MenuAction{
    //This action will loop through the logged in user’s connections 
	//Display the user name of each LinkedIn user in the connections list.
	//If the logged in user has no connections, display the message, “You have no connections” to the console. 
	//Return true to keep the user logged in.
	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		
		int index = 0 ;  
		boolean isExist= false;  
		boolean isEmptyList = false; 
		for(LinkedInUser linkedInUser : userRepository.retrieveAll()) {
			if(linkedInUser.getUsername().equals(loggedInUser.getUsername())) {
				System.out.println("LoginUser name is: " + linkedInUser.getUsername());
				index = userRepository.retrieveAll().indexOf(linkedInUser);
				isEmptyList=linkedInUser.getConnections().isEmpty();
				isExist =true; 
			}
	    }
		
		if(isExist) {  //loggedInUser in The Users list	
			if(isEmptyList) {
				System.out.println("You have no connections!");
							
			}else {
				List<LinkedInUser> connections = new ArrayList<>();			
				connections = userRepository.retrieveAll().get(index).getConnections();   //retrive data and asign value to connections list
				loggedInUser.setConnections(connections);    //initialized loggedInUser connections
				
				for(LinkedInUser connection : loggedInUser.getConnections()) { //print
					System.out.println(connection.getUsername());			
				}
			}			
		}else {  //loggedInUser not in The Users list	
			if(loggedInUser.getConnections().isEmpty()) {
				System.out.println("You have no connections!");
			}else {
				System.out.println("\nThe connections of "+ loggedInUser.getUsername() + ":");
				
				for(LinkedInUser connection : loggedInUser.getConnections()) { //print
					System.out.println(connection.getUsername());			
				}
			}
						
		}

		return true; 
		
	}
	
}