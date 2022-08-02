package edu.institution.asn2;

import java.util.*;

import edu.institution.actions.MenuAction;





public class LinkedInUser extends UserAccount implements Comparable<LinkedInUser>, java.io.Serializable{
	//Fields
	private String type;
	private List<LinkedInUser> connections = new ArrayList<>();   //Create arrayList named connections
	
	private Set<String> skillsets = new TreeSet<>();  //using TreeSet so the skills be displayed in alphabetical order.
	
	private transient  Stack<MenuAction> history  = new Stack<MenuAction>();   //##To store Actions that can be Undo
	
	private transient Stack<LinkedInUser> addedUsersStack  = new Stack<LinkedInUser>();   //##Create a stack to store added users
	
	private transient Stack<LinkedInUser> deletedUsersStack  = new Stack<LinkedInUser>();   //##Create a stack to store deleted users
	
	private transient Stack<LinkedInUser> addedConnectionsStack  = new Stack<LinkedInUser>();   //##Create a stack to store added connections
	
	private transient Stack<LinkedInUser> removeConnectionsStack  = new Stack<LinkedInUser>();   //##Create a stack to store
	

	public void setConnections(List<LinkedInUser> connections) {
		this.connections = connections;  
	}


	public LinkedInUser(String username, String password) {    //Construct a LinkedInUser instance by pasing username and password
		super(username, password);
		 
	}

		
	//Returns the type of this LinkedIn user.
	public String getType() { 
		return type;
	}
    
     
	
	
	public Stack<MenuAction> getHistory() {
		return history;
	}
	
	
	
	
	
	
	


	public Stack<LinkedInUser> getAddedUsersStack() {
		return addedUsersStack;
	}


	public Stack<LinkedInUser> getDeletedUsersStack() {
		return deletedUsersStack;
	}


	public Stack<LinkedInUser> getAddedConnectionsStack() {
		return addedConnectionsStack;
	}


	public Stack<LinkedInUser> getRemoveConnectionsStack() {
		return removeConnectionsStack;
	}


	/** Returns the skillsets. */
	public Set<String> getSkillsets() {
		return skillsets;
	}

	/** Adds the supplied skillset to this user. */
	public void  addSkillset(String skillset) {
		if(skillset == null || skillset.isBlank() || skillset.isEmpty()) {
			System.out.println("This skillset value is not valid.");
		}else {
			this.skillsets.add(skillset);
		}
	
	}
	
	/** Removes the supplied skillset from this user. */
	public void removeSkillset(String skillset) {
		if(skillset == null || skillset.isBlank() || skillset.isEmpty()) {
			System.out.println("This skillset value is not valid.");
		}else {
			this.skillsets.remove(skillset);
		}
	}
	
	
	/**
	/*Add the supplied LinkedInUser argument to this user’s connections List
	/*If the supplied connection already exists in this user’s connection List, 
	/*then throw a new LinkedInException A message “You are already connected with this user ”
	*/
	public void addConnection(LinkedInUser user) throws NullPointerException, LinkedInException{		
		for(LinkedInUser connection : this.connections) {
			if( connection.getUsername().equals(user.getUsername())) {
				throw new LinkedInException("You are already connected with this user");
			}
		}
		
		this.connections.add(user);
	}
	

	
	



	/**
	* Removes the supplied user from the list of connections for this user.
	* @param user the user to remove.
	* @throws LinkedInException thrown if the supplied user is not connected to
	* this user.
	*/
	public void removeConnection(LinkedInUser user) throws NullPointerException, LinkedInException{
		List<LinkedInUser> usersToDeleteList = new ArrayList<>();  //Built a list to store users that need to be deleted 
		
		for(LinkedInUser linkedInConnections : this.connections) {    //loop through list of connections
			if( linkedInConnections.equals( user )) {    
				usersToDeleteList.add(user);			
			}
		}
		
		if( usersToDeleteList.size()== 0) {
			throw new LinkedInException("You are NOT connected with this user");
			
		}else {
			//pass the usersToDelete list to the removeAll method. 
			//The removeAll method will remove every user from connections that matches the users in the usersToDelete list.
			connections.removeAll(usersToDeleteList);
			System.out.println("The connection was removed successfully.");
		}
			
	}
	
	
	public void removeConnection(String username) throws NullPointerException, LinkedInException{
		List<LinkedInUser> usersToDeleteList = new ArrayList<>();  //Built a list to store users that need to be deleted 
		
		for(LinkedInUser linkedInConnections : this.connections) {
			if(linkedInConnections.getUsername().equals(username)) {
				usersToDeleteList.add(linkedInConnections);
			}
		}
		
		if( usersToDeleteList.size()== 0) {
			throw new LinkedInException("There is no user with that user name.");
		}else {
			connections.removeAll(usersToDeleteList);
			System.out.println("The connection was removed successfully.");
		} 
	}
	
	
	
	/**
	* Returns a list of users that are connected to this user.
	* @return the list or empty list if this user has no connections.
	*/
	//return a copy of this user’s connections – create a new List
	//with the same LinkedInUsers in it as this user’s connections.
	//Do not return the connections List directly, since this violates the principle of encapsulation
	public List<LinkedInUser> getConnections(){
		//URL: https://www.geeksforgeeks.org/initialize-an-arraylist-in-java/ 
		List<LinkedInUser> copyConnections = new ArrayList<> ();
		for(LinkedInUser linkedInConnections : this.connections) {
			copyConnections.add( linkedInConnections );
		}
		
		return copyConnections; 
		
	}
	
	//sort our LinkedIn users,natural order of our LinkedIn users to be
	//ordered alphabetically by their username, ignoring capitalization
	//The compareToIgnoreCase() method compares two strings lexicographically, ignoring lower case and upper case differences
	//0 if the string is equal to the other string
	//< 0 if the string is lexicographically less than the other string
	//> 0 if the string is lexicographically greater than the other string (more characters) 
	@Override
	public int compareTo( LinkedInUser user) {     //This override Comparable<LinkedInUser> interface 		
            int returnValue =0;
            
            if(this.getUsername()==null && user.getUsername()!=null) {
            	returnValue = -1;
            }
            
            if(this.getUsername()!=null && user.getUsername()==null) {
            	returnValue = 1;
            }
            
            if(this.getUsername()==null && user.getUsername()==null) {
            	returnValue = 0;
            }
            
            if(this.getUsername()!=null && user.getUsername()!=null) {
            	returnValue = this.getUsername().compareToIgnoreCase( user.getUsername() ); 
            }
            
            
            return returnValue; 
	
	}
	
	
	@Override
	public void setType(String type) {
	    this.type = type; 
		
	}
	
	

}