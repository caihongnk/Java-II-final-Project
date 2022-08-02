package edu.institution.actions.asn7;

import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;
import edu.institution.ApplicationHelper;



public class AddSkillsetAction implements MenuAction {

    //Add the skillset to the logged in user’s skillsets
	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		
		if(userRepository!=null && !userRepository.retrieveAll().isEmpty() && loggedInUser!=null && loggedInUser.getUsername()!=null) {
			
			String loggedInUserName = loggedInUser.getUsername();
			
            
				for(LinkedInUser user:userRepository.retrieveAll()){
					if(user.getUsername().equals(loggedInUserName)) {
						System.out.println("Please enter a skillset: ");
						String skill = scanner.nextLine();
						if(skill!=null && !skill.isBlank() && !skill.isEmpty()) {
							user.addSkillset(skill);
							userRepository.saveAll();
							System.out.println(skill + " has been added to your skillsets.");
							ApplicationHelper.incrementSkillsetCount(skill);
						}	
					}	
				}//for

		}//1st if
				

        	
        	
       
        
		return true;  
	}
	
}//class
