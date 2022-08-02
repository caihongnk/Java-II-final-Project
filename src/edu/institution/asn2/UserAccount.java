package edu.institution.asn2;

public abstract class UserAccount implements  java.io.Serializable {
	
	private String username;
	private String password;
	

	
	
	// a constructor accepting two Strings; one to initiate the user name and the other to
	// initiate the password
	public UserAccount(String username, String password) {
		this.username = username;
		this.password = password;
	}

	
	// returns the user name supplied on the constructor
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	
	// return true if the argument is the same as this account’s password,false otherwise
	//URL: https://www.educative.io/edpresso/how-to-resolve-the-javalangnullpointerexception
	public boolean isPasswordCorrect(String password) {
         try{
        	 if(this.password.equals(password)) {
        		 return true;
        	 }else {
        		 return false;
        	 }
         }
         catch(NullPointerException e ){
        	 System.out.println("NullPointerException error");
        	 return false;       	 
         }



	}
	

	//displays the username for the account.
	@Override
	public String toString() {
		return username;
	}
	
	
	/**
	* Sets the type of this user.
	* @param type the type.
	*/
	public abstract void setType(String type);
	
	
	
	

	   
	// uses the username as the unique identifier of an account
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAccount other = (UserAccount) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
	
	

}//class
