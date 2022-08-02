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
 


public class TestDataReader {
	
	
	private static String PATH = System.getProperty("user.home") + File.separator + "Java2" + File.separator;
	private static String FILE_NAME = "anagram_data.txt"; 
	
	
	@Test
	public void anagramDataReaderTest(){

			
		Set<String> anagramDataSet = new HashSet<>();
		List<String> list = new ArrayList<>();
		
		
		
		
		AnagramDataReader anagramDataReader =new AnagramDataReaderImpl();
		
		
		try {
			anagramDataSet = anagramDataReader.readData(PATH + FILE_NAME);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
        
		int sizeOfElements = anagramDataSet.size(); 
		
		/**
		* Test DataReader
		*/
		Assert.assertEquals(373295, sizeOfElements );
		
		
		
//		for(String str:anagramDataSet) {  
//			list.add(str);
//		}
//		
//		
//       	//test if have duplicate or not  :  It take too much time to run so I delete it.
//		boolean duplicateExist = false;
//		for(int i =0; i < sizeOfElements-1; i++) {
//			
//		for(int j= i+1; j<sizeOfElements ; j++) {
//				
//				if( list.get(i).equalsIgnoreCase( list.get(j) )) {
//					duplicateExist = true;
//				}
//			}
//			
//		}
//		
//		Assert.assertFalse(duplicateExist);
		

	}
    
	
	





	

	 
	
	
	
}
