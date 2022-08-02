package edu.institution.actions.asn3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import edu.institution.ApplicationHelper;
import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;




public class SerializedUserRepository implements UserRepository { 

	private static final Exception LinkedInException = null;
	private String filePath;
	private String fileName;
	private List<LinkedInUser> users =  new ArrayList<>();    //initialied it otherwise users value will be NULL !!!!!!
	
	private static Stack<MenuAction> history = new Stack();
  


	@Override
	//Deserialize the data stored at the supplied filePath + fileName location 
	//Into a list of LinkedIn users
	//If there is no previously saved data, then initialize a new list of LinkedIn users.
	
	public void init(String filePath, String fileName) {    //is called by LinkedInCLI		
		this.filePath = filePath;
		this.fileName = fileName;
	 
		File file = new File(filePath + fileName);
		
		if(file.exists()) {  //if not exist do nothing
			try( FileInputStream fileInput = new FileInputStream( file );  ObjectInputStream objectInput = new ObjectInputStream(fileInput) ){
				this. users= (List<LinkedInUser>) objectInput.readObject();
				ApplicationHelper.initSkillsetUsages(this. users);  //initiate the skillset usage counts after the application starts up.
				
				
			}catch(Exception exception) {
				throw new RuntimeException(exception);
			}
		}
				
		}//init

	
	@Override
	public void add(LinkedInUser user) throws LinkedInException {
		
		// add user to ArrayList-users //10:45 (Part 1) - User Repository
		//Need to pass the 3 criterias, the the user is ready to be added.
		//1.if the user name  or user type is not supplied,need to resolved before to be added(in this cas throw a LinkedInException with the message Username & UserType are required to add to the user)
       //2. the user already added. Throw LinkedInException "The user already exist"
	   //3. The type of the user can only be "P" OR "S", if the user enter any other types, Throw Exception"Valid user type"
			
			
			if(user.getUsername().isBlank() || user.getUsername() == null ) {
				   throw new LinkedInException("The username is not valid!"); 
				   
			}else {				
				if( this.users.isEmpty() ) {
									
					if( user.getType().equalsIgnoreCase("P") || user.getType().equalsIgnoreCase("S") ) {
						this.users.add(user);
						this.saveAll();			
					}else{
						throw new LinkedInException("Invalid user type. Valid types are P or S.");
					}

					
				}else {//(if not empty)
					boolean isUserExist = false; 
					for(LinkedInUser linkedInuUer : this.users) {
						if( user.getUsername().equalsIgnoreCase(linkedInuUer.getUsername())) {
							isUserExist = true; 
							throw new LinkedInException("A user already exists with that user name."); 
						}			
					}
					
				    if(isUserExist==false) {
				    	if( user.getType().equalsIgnoreCase("P") || user.getType().equalsIgnoreCase("S") ) { 		    			    		
				    		this.users.add(user);
				    		this.saveAll();		    		
				    	}else {
				    		throw new LinkedInException("Invalid user type. Valid types are P or S.");
				    	}
				    }
					
					
				}//else(not empty)
	
				
			}//else(username is valid)
			
	
	}
    
     //10:57 (Part 1) - User Repository
	//overwrites (serializes) the list of LinkedIn users that was established in the init method to the file system.
	@Override
	public void saveAll() {    
		File file = new File(this.filePath + this.fileName);
		
		if(file.exists()) { file.delete(); }
			
		//ensure the folder in the path exist, will create a folder if it doesn't 
		new File(this.filePath).mkdir(); 
		
		//Try-with-resouces will close the output stream automatically
		try(FileOutputStream fileOutput = new FileOutputStream( file ); 
				ObjectOutputStream objectOutput = new ObjectOutputStream(  fileOutput );){		
			
			objectOutput.writeObject(this.users);
			
		}catch(Exception exception) {
			throw new RuntimeException(exception);
		}
		   
	}

	
    //12:02 (Part 1) - User Repository
	//Remove a user from the ArrayList and after that call the saveAll() method to save it
	@Override
	public void delete(LinkedInUser user){	
				this.users.remove(user);
				this.saveAll();
	}

	
	//This method returns the LinkedIn user associated with the supplied user name or NULL if there is
	//no user associated with the supplied user name. 13:35
	@Override
	public LinkedInUser retrieve(String username) {
		
		if( this.users.isEmpty() ) {
			System.out.println("There is no users in the list, add users first.");
			return null; 
						
		}else {
			boolean exist = false;  
			int objectIndex = 0;   //use for the index of a specific object in the arraylist
			
			for(LinkedInUser linkedInuUer : this.users) {						
				if(linkedInuUer.getUsername().equalsIgnoreCase(username)) {  //if the username match the username of the object in the Arraylist 
					objectIndex = this.users.indexOf(linkedInuUer);  //mark the index since I cannot return the LinkedInUser instance here( inside if )	
					exist=true;
				}						
			}//for
			
			if(exist==true) {			
				return this.users.get(objectIndex) ;
			}else {
				return null; 
			}
						
		}//else(not empty)
		
  
	}
	

	
	
	
	//This method returns all LinkedIn users  in the repository 
	//or an empty list if there are no users in the repository.
	@Override
	public List<LinkedInUser> retrieveAll() {
												
			return this.users;    
		}



	
	
	

}
