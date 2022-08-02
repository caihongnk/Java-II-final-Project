package edu.institution.actions.asn6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class ListUserByConnectionAction implements MenuAction {
	
	//displays the LinkedIn users, ordered by their number of connections
	//(from most connections to least connections) and display the number of connections they have
	
	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		
		List<LinkedInUser> usersList = new ArrayList<>(userRepository.retrieveAll());
		
		//Create a Comparetor ------------
		class CompareByConnectionsNum implements Comparator<LinkedInUser>{

			@Override
			public int compare(LinkedInUser user1, LinkedInUser user2) {
				return user1.getConnections().size() - user2.getConnections().size();
			}
		}
		//Create a Comparetor ------------
		
		Collections.sort(usersList, new CompareByConnectionsNum().reversed() );
		
		  for(LinkedInUser user: usersList) {
			  System.out.println(" \" " + user.getUsername() + ";" + " connection size = " + user.getConnections().size() + " \" " );
		  }
		
		
		return true;
	}

}
