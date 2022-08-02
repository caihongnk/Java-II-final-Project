package edu.institution.actions.asn7;

import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;
import edu.institution.ApplicationHelper;




public class ListSkillsetAction implements MenuAction{

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		if(userRepository!=null && !userRepository.retrieveAll().isEmpty() && loggedInUser!=null 
				&& loggedInUser.getUsername()!=null) {
			
			
			String loggedInUserName = loggedInUser.getUsername();
			
			boolean isExist = false;
			for(LinkedInUser user:userRepository.retrieveAll()) {
				if(user.getUsername().equals(loggedInUserName)) {
					loggedInUser = user;
					isExist = true;
				}
			} 
			
			if(isExist) {
				System.out.println("Login User:"+loggedInUserName+". Here are your skillsets:\n");

				if(loggedInUser.getSkillsets().isEmpty()) {
					System.out.println("You have not entered any skillsets.");
				}else {
					for(String skill :loggedInUser.getSkillsets()) {
						int numOfUsers = ApplicationHelper.retrieveSkillsetCount(skill);   // numOfUsers represents the number of users that shares that same skillset.
						
						 numOfUsers = numOfUsers ;
						   
						
						System.out.print(skill+" ");
						System.out.print("("+ numOfUsers +" users"+")");
						System.out.println();
					}
				}
			}else {
				System.out.println("loggedInUser does not exist.");
			}

			
			
			
		}//if
			
		
		

		

		
		return true;
	}
	
	
	
}//ListSkillsetAction