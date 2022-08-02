package edu.institution.finalproj;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class AnagramEvaluatorImpl implements AnagramEvaluator {
	
	

	@Override
	public List<String> evaluate(String anagram, String option) {
		
		
		
		List<String> list = new ArrayList<>();//To store finished List
		
		Set<String> set = new TreeSet<>();//To store all permutation (No duplicate. in aphabet order) 
		
		Set<String> anagramDataSet = new HashSet<>();  // to store the Dictionary
		
		anagram = anagram.toUpperCase();
		
		set = AnagramEvaluatorImpl.printPermutn(anagram, "",set);
		

	
		
		//If use filter:		
		if(option.equalsIgnoreCase("words")) {   
			
		    //Let memory load a list with anagram_data, and use it for filter 
			String PATH = System.getProperty("user.home") + File.separator + "Java2" + File.separator;
			String FILE_NAME = "anagram_data.txt";

			AnagramDataReader anagramDataReader =new AnagramDataReaderImpl();
			
			try {
				anagramDataSet = anagramDataReader.readData(PATH + FILE_NAME); //The Dictionary is ready!!
				System.out.println("The Dictionary is ready! Total " + anagramDataSet.size() + " words!");
				System.out.println();
			} catch (IOException e) {			
				System.out.println("Not able to open file!");
			}
			

			for(String str :set) {   //go through the set
				if(anagramDataSet.contains(str)) {   //if the dictionary contain given string
					list.add(str);  //then add to the final list
				}
			}
		}
		
		//If Not use filter:
		if(option.equalsIgnoreCase("nf")) {
			for(String str: set) {
				list.add(str);  //add every element in the set to the List
			}
		}
		
		
		return list;
	}
    
	
	
	//Print all permutations of a string in Java
	//URL: https://www.geeksforgeeks.org/print-all-permutations-of-a-string-in-java/
	//Auther : dekay
	
	/**
	*My understanding about this Method:
	*
	* To understand it, we need to think about a base case, say "AB", it put A out first, and with the A as 
	* head character,there is only one permutation which is B. 
	* Then it put B first, and with B as head character, there is only one permutation which is B.
	*
	* For longer words say "ABC", the method will use each character as head, then permutate the rest, just like the base case "AB".
	* 
	* At each permutation, the leltover string length will be equal to 0, at this point add the result to the provided list.
	*/
	
	
	static Set<String> printPermutn(String str, String ans,Set<String> set) {  //I change the argument: pass a Set to store each permutation
		
		
        // If string is empty
        if (str.length() == 0) {    //base case,if supplied string is empty string.
            //System.out.print(ans + " ");
        	set.add(ans);
         
        }
        
        for (int i = 0; i < str.length(); i++) {
            // ith character of str
            char ch = str.charAt(i);
  
            // Rest of the string after excluding the ith character
            String ros = str.substring(0, i) + 
                         str.substring(i + 1);
  
            // Recurvise call
            printPermutn(ros, ans + ch,set);
        }
        
        return set;
	}//printPermutn
}
