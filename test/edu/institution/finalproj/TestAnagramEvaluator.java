package edu.institution.finalproj;

import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

import edu.institution.actions.asn5.Utilities;
import edu.institution.asn2.LinkedInUser;
import jdk.internal.joptsimple.internal.Strings;
 


public class TestAnagramEvaluator {
	
	
	
	@Test
	public void anagramDataReaderTest(){
		
		
               

		//List<String> list = new ArrayList<>();
		
		List<String> list = null;
		List<String> list0 = null;
		List<String> list1 = null;
		

		AnagramEvaluator evaluator = new AnagramEvaluatorImpl();
		list = evaluator.evaluate("magma", "nf");   
		int lengthOfList = list.size();
	 
		/**
		* "magma" has 5 character, 5!=120; because the method exclude duplcates, so it should be 30 results without filter.
		*/
		
		//To check duplication
		boolean duplicateExist = false;
		
		for (int i = 0; i < lengthOfList-1; i++) {
			for(int j= i+1; j<lengthOfList ; j++) {
				if( list.get(i).equalsIgnoreCase( list.get(j) )) {
					duplicateExist = true;
				}
			}
		}
		Assert.assertFalse(duplicateExist);
		
		Assert.assertEquals(30, lengthOfList);
		

		/**
		* "magma" has 5 character, 5!=120; because the method exclude duplcates, so it should be 30 results without filter.
		* But after use filter, it only have 2 words(GAMMA MAGMA) left fit the dictionary
		*/
		list1 = evaluator.evaluate("magma", "words"); 
			    	    
        List<String> compareList1 = new ArrayList(Arrays.asList("GAMMA","MAGMA"));
		
        Assert.assertEquals(compareList1, list1);
			

	}
    
	
	





	

	 
	
	
	
}
