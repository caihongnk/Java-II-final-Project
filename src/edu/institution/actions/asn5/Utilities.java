package edu.institution.actions.asn5;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Utilities {

	
	
	
	
	
	//excepts a list of items and removes the duplicates from the list.
	//After this method completes, the supplied list should not contain any duplicate items
	public <T> void removeDuplicates(List<T> items){
		
		   

            if(items==null||items.isEmpty()) {
            	return;
            }
             
			List<T> deleteElements = new ArrayList<>(); //to store index need to be deleted
			
			 
			
	        for(int i=0,index =1; i<items.size()-1; i++,index++) {
   	                
	        	for(int x = index; x< items.size(); x++ ) {
	                   if(items.get(i).equals( items.get(x) )) {
	                	   deleteElements.add(items.get(x));
	                   }
	        	}

	        }//for

	        items.removeAll(deleteElements);
	     
	        
	        //System.out.println(items);
	       
	        
	
	}// removeDuplicates
	
	
	
	
	//return the record associated with the supplied key or 
	//null if the key does not exist in the supplied list.
	public <E> E linearSearch(List<E> list, E key) {

        if(list==null) {  
    	 
        	return null;  
	
        }else if(list.isEmpty()||key==null) {
        	return null;  
        	
        }else {
        	for(int i=0; i <list.size(); i ++) {
        		
        		if(list.get(i).equals(key)) {
        			return key;
        		}
        			
        	}//for
        	
        	return null; // not found
 
        }

	}//linearSearch
	
	
	
	
	

	
	
}//Utilities
