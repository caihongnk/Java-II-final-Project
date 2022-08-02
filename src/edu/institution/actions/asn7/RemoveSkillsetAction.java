package edu.institution.actions.asn7;

import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;
import edu.institution.ApplicationHelper;




public class RemoveSkillsetAction implements MenuAction {


	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		
		if(userRepository!=null && !userRepository.retrieveAll().isEmpty() && loggedInUser!=null && loggedInUser.getUsername()!=null) {
			
			String loggedInUserName = loggedInUser.getUsername();
			
			for(LinkedInUser user:userRepository.retrieveAll()) {
				if(user.getUsername().equals(loggedInUserName)) {
					System.out.println("Please enter a skillset that you want to remove: ");
					String skill = scanner.nextLine();
					if(skill!=null ) {
						if(user.getSkillsets().contains(skill)) {
							user.removeSkillset(skill);
							userRepository.saveAll();
							ApplicationHelper.decrementSkillsetCount(skill);
						}
					}
				}
			}//for
			
			
			
			
			
			
		}//1st if
    
        	
       
        
		return true;  
	}
	
}//class
