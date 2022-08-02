/*
 Copyright (C) 2020. Doug Estep -- All Rights Reserved.
 Copyright Registration Number: TXU002159309.

 This file is part of the Tag My Code application.

 This application is protected under copyright laws and cannot be used, distributed, or copied without prior written
 consent from Doug Estep.  Unauthorized distribution or use is strictly prohibited and punishable by domestic and
 international law.

 Proprietary and confidential.
 */
package edu.institution;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.institution.asn2.LinkedInUser;



/**
 * Contains static helper methods to aid with the command line user interface.
 */
public class ApplicationHelper {
	/**
	* The key is the skillset and the value is the number of users
	* Use LinkedHashMap so the elements order will be sorted in order of the keys
	*/
	private static Map<String,Integer> skillsMap = new TreeMap<>();  
	
	

	/**
	 * Displays the supplied message to the console.
	 * 
	 * @param message the message.
	 */
	public static void showMessage(String message) {
		System.out.println("\n" + message);
		//System.out.println(skillsMap.toString());
		
	}
	
	
	
	/**
	* Increments the number of users associated with the supplied skillset.
	* If this is the first occurrence of the supplied skillset, then add
	* the skillset to your collection and default the count to one.
	*
	* @param skillset the skillset to increment.
	*/
	public static void incrementSkillsetCount(String skillset) {
        if(skillset!=null && !skillset.isBlank() && !skillset.isEmpty()) {
    		if( skillsMap.containsKey(skillset) ) {           
    			skillsMap.put(skillset, skillsMap.get(skillset) + 1);     
    		}else {
    			skillsMap.put(skillset, 1);
    		}       	
        }else {
        	//do nothing
        } 
	
        
        //System.out.println(skillsMap.toString());
	}//incrementSkillsetCount
	
	
	/**
	* Decrements the number of users associated with the supplied skillset.
	* If the number of users associated with the supplied skillset is zero,
	* then remove the skillset from your collection.
	*
	* @param skillset the skillset to decrement.
	*/
	public static void decrementSkillsetCount(String skillset) {
		
        if(skillset!=null && !skillset.isBlank() && !skillset.isEmpty() ) {
        	       	
            if(!skillsMap.containsKey(skillset)) {
            	System.out.println("The supplied skillset is not in the Collection.");
            }else {
            	if(skillsMap.get(skillset)!=0) {
            		skillsMap.put(skillset, skillsMap.get(skillset) - 1);
            	}else {
            		skillsMap.remove(skillset);
            	}
            	
            	
            }
               
        }
      
        
     
	}//decrementSkillsetCount
	
	/**
	* Retrieves the number of users associated with the supplied skillset.
	* If the skillset is not in the collection, return -1.
	*
	* @param skillset the skillset to lookup.
	*/
	public static int retrieveSkillsetCount(String skillset) {
       
		if(skillset==null) {
			return -1;
		}else {
			if( skillsMap.containsKey(skillset) ) {
				int returnValue = skillsMap.get(skillset);
				return returnValue; //When you call the get method on a Map and the provided key does not exist, the Map implementation returns null
			}
			
				return -1;
			
		}
		
		
	}
	
	
	/**
	* Loops through each user and increments the global skillset usage count for
	* each skillset associated with the user.
	*
	* @param users the list of users.
	*/
	//This method accepts the list of LinkedInUser’s loaded from the file system. 
	//For each user, loop through their skillset’s and call the incrementSkillsetCount method – passing the skillset.
	public static void initSkillsetUsages(List<LinkedInUser> users) {
		
		for(LinkedInUser user: users) {
			for(String skillset: user.getSkillsets() ) {
				if(skillset!=null && !skillset.isBlank() && !skillset.isEmpty() ) {
					incrementSkillsetCount(skillset.toLowerCase());
				}
				
			}
			
		}
		
		//System.out.println(skillsMap.toString());
		
	}
	
}//ApplicationHelper
