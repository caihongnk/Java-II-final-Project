package edu.institution.actions.asn10;

import java.util.*;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.actions.asn3.AddUserAction;
import edu.institution.actions.asn3.DeleteUserAction;
import edu.institution.actions.asn4.AddConnectionAction;
import edu.institution.actions.asn4.RemoveConnectionAction;
import edu.institution.asn2.LinkedInUser;

public class UndoAction implements MenuAction{
	
	//public static Stack<MenuAction> history ;

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		

		
		if(loggedInUser.getHistory().isEmpty() || loggedInUser.getHistory().equals(null)) {
			System.out.println("\nThere are no UndoAction left.");	
		}else {
						
			if( userRepository.retrieveAll()==null ) {
				System.out.println("There is no users in the List or retrive user list fail.");
				
			}else {			
				//Initialized logginUser -----------------  
				int indexOfLoggedInUser = 0 ;				
				for(LinkedInUser linkedInUser : userRepository.retrieveAll()) {
					if(linkedInUser.getUsername().equals(loggedInUser.getUsername())) {
						System.out.println("\nLoginUser  name is: " + linkedInUser.getUsername());
						indexOfLoggedInUser = userRepository.retrieveAll().indexOf(linkedInUser);
						 
					}
			    }
			
				//Initialized user List ------------------
				List<LinkedInUser> usersList = userRepository.retrieveAll();  //***
								

				Class classNeedToBeChecked = loggedInUser.getHistory().pop().getClass();
				
						if( classNeedToBeChecked.equals(new AddUserAction().getClass()) ) {    //if history pop a AddUserAction Class
							
								if(loggedInUser.getAddedUsersStack().size()!=0 && loggedInUser.getAddedUsersStack()!=null) {
									LinkedInUser userNeedToBeDeleted = loggedInUser.getAddedUsersStack().pop();
									String userName =  userNeedToBeDeleted.getUsername() ;
									boolean trueOrFalse = UndoAction.dialog("\nDo you want to Undo AddUserAction", userName);
									if(trueOrFalse) {
										usersList.remove(userNeedToBeDeleted);
										userRepository.saveAll();
										System.out.println(userName + " has been deleted from the user List.");

									}
								}

												
						}
						//-----------------------------------------------------
						if( classNeedToBeChecked.equals(new DeleteUserAction().getClass()) ) {   //if history pop a DeleteUserAction Class
							
								if(loggedInUser.getDeletedUsersStack().size()!=0 && loggedInUser.getDeletedUsersStack()!=null) {
									LinkedInUser userNeedToBeRecovered = loggedInUser.getDeletedUsersStack().pop();
									String userName =  userNeedToBeRecovered.getUsername() ;
									boolean trueOrFalse = UndoAction.dialog("\nDo you want to Undo DeleteUserAction", userName );
									if(trueOrFalse) {
										usersList.add(userNeedToBeRecovered);
										userRepository.saveAll();
										System.out.println(userName + " has been added back to the user List.");

									}							
								}
							
						}
						//-----------------------------------------------------
						if( classNeedToBeChecked.equals(new AddConnectionAction().getClass()) ) {  //if history pop a AddConnectionAction Class
							if(loggedInUser.getAddedConnectionsStack().size()!=0 && !loggedInUser.getAddedConnectionsStack().equals(null)) {
								LinkedInUser connectionNeedToBeDeled = loggedInUser.getAddedConnectionsStack().pop();
								String userName =  connectionNeedToBeDeled.getUsername() ;  
								boolean trueOrFalse = UndoAction.dialog("\nDo you want to Undo AddConnectionAction",userName );  // delete the connection
								if(trueOrFalse) {
									List<LinkedInUser> updatedConnectionList = userRepository.retrieveAll().get(indexOfLoggedInUser).getConnections();
									updatedConnectionList.remove(connectionNeedToBeDeled);									
									userRepository.retrieveAll().get(indexOfLoggedInUser).setConnections(updatedConnectionList);
									userRepository.saveAll();
									System.out.println(userName + " has been deleted from your connection List.");
									

								}
							} 
						}
						//-----------------------------------------------------
						if( classNeedToBeChecked.equals(new RemoveConnectionAction().getClass()) ) {   //if history pop a RemoveConnection Class				
							if(loggedInUser.getRemoveConnectionsStack().size()!=0 && !loggedInUser.getRemoveConnectionsStack().equals(null)) {
								LinkedInUser connectionNeedToAddBack =  loggedInUser.getRemoveConnectionsStack().pop();
								String userName =  connectionNeedToAddBack.getUsername() ;
								boolean trueOrFalse = UndoAction.dialog("\nDo you want to Undo RemoveConnection",userName );  // delete the connection
								if(trueOrFalse) {
									List<LinkedInUser> updatedConnectionList = userRepository.retrieveAll().get(indexOfLoggedInUser).getConnections();									
									updatedConnectionList.add(connectionNeedToAddBack);
									userRepository.retrieveAll().get(indexOfLoggedInUser).setConnections(updatedConnectionList);																		
									userRepository.saveAll();
									System.out.println(userName + " has been added back to your connection List.");
			
								}
							}
							
						}
					 
					 
				 
				 

				
				
			}//userRepository.retrieveAll().isEmpty() || userRepository.retrieveAll()==null)
			
			
			

				
		}//loggedInUser.getHistory().isEmpty() || loggedInUser.getHistory().equals(null)
		
	   
		
		

		
			
				    
				
               

				
				
				
				
	
		
		

		
		
		return true;
	}//process
	
	
	public static boolean dialog(String str, String username) {
		System.out.println("\n" + str + " involving " + username + "." + " Undo (Y/N)? ");
		
		Scanner input = new Scanner(System.in);
		String yesOrNo = input.nextLine();
		if(yesOrNo.equalsIgnoreCase("Y")) {
			return true;
		}else {
			return false;
		}
	}
	
}
