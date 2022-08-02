package edu.institution.asn2;

public class LinkedInException extends Exception{
	private static final long serialVersionUID = -4354894027723180456L;
	
	// URL: java.lang.Exception(5 constructors):https://docs.oracle.com/javase/8/docs/api/java/lang/Exception.html
	private String message;
	
	LinkedInException(){
		super();
	}

	public LinkedInException(String message) {	
		this.message = message; 
		System.out.println(this.message);
		
	}

	 
	
	LinkedInException(String message, Throwable cause) {

		super(message, cause);

	}
	
	
	LinkedInException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	
	LinkedInException(Throwable cause){
		super(cause);
	}
	
	
	
	

	public String getMessage() {
		return message;
	}


	
	
	
}//class
