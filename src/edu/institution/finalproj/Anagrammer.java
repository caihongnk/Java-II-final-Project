package edu.institution.finalproj;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.*;
import java.util.Set;

import org.apache.commons.cli.*;
import org.apache.commons.cli.*;


public class Anagrammer {

	public static void main(String[] args)  {
		
		//How to use Apache Commons CLI: 
		//URL: https://commons.apache.org/proper/commons-cli/usage.html                 Auther:commons.apache.org
		//URL: https://www.tutorialspoint.com/commons_cli/commons_cli_quick_guide.htm   Auther:www.tutorialspoint.com
		
		
		          String suppliedString = null;
		          
		          String suppledFilter = null;
		          
		          List<String> resultList = new ArrayList<>(); 
		          
		          boolean showHelp = false;
			     
			      // create Options object
			      Options options = new Options();
			      
			  
			      options.addOption(Option.builder("h").longOpt("help") //!!!Need to use Option.builder otherwise "--help" dont work !!
                          .desc("displays the help for this program")
                          .hasArg(false)
                          .build());
			      
			      
			      
			      options.addOption(Option.builder("a").longOpt("anagram") 
                          .desc("supplies the anagram to evaluate")
                          .hasArg(true)
                          .build());
			      			      
			      
			      //options.addOption("-no-filter", false, "output all anagram values with no filter"); Not working because of "-" 
			      options.addOption(Option.builder("nf").longOpt("no-filter") //!!!Need to use Option.builder otherwise it will said "-" is illegal
                          .desc("output all anagram values with no filter")
                          .hasArg(false)
                          .build());
			      
			      
			      options.addOption(Option.builder("words").longOpt("filter-words") 
                          .desc("output only values that are known words")
                          .hasArg(false)
                          .build());
	

			      //Create a parser
			      CommandLineParser parser = new DefaultParser();
			      
			      
			      //parse the options passed as command line arguments
			      //CommandLine cmd; 
				try {
					CommandLine cmd = parser.parse(options, args);

					
					if(cmd.hasOption("h")||cmd.hasOption("help")) {
						showHelp = true;
						System.out.println("Usage: Anagrammer ");
						System.out.println("Options: ");				
						///URL: https://stackoverflow.com/questions/4418308/java-output-formatting-for-strings    About how to lineup println
						System.out.println(String.format("%-40s %s" , "-a,-anagram <word>", "supplies the anagram to evaluate" )); 
						System.out.println(String.format("%-40s %s" , "-h,-help", "displays this help textdisplays the help for this program" ));
						System.out.println(String.format("%-40s %s" , "-nf,-no-filter","output all anagram values with no filter" ));
						System.out.println(String.format("%-40s %s" , "-words,-filter-words", "output only values that are known words" ));						
						System.out.println(); 
					}
					
					if(cmd.hasOption("a")||cmd.hasOption("anagram")) {
						if(cmd.getOptionValue("a")!=null ) {
							suppliedString = cmd.getOptionValue("a");
						}
                        if( cmd.getOptionValue("anagram")!= null) {
                        	suppliedString = cmd.getOptionValue("anagram");
                        }

					}
					
					
					if(cmd.hasOption("nf")||cmd.hasOption("no-filter")) {
						//System.out.println("output all anagram values with no filter"); 
						suppledFilter = "nf";
					}
					
					if(cmd.hasOption("words")||cmd.hasOption("--filte_words")) {
						//System.out.println("output all anagram values with no filter");
						suppledFilter = "words";
					}
					
					if(suppledFilter==null) {   //if user dont provide/ type in any filter, then default filter is "words"
						suppledFilter = "words";
					}
					
				} catch (ParseException e) { 
					System.out.println("Wrong Option!");
					
				}
                
				if(showHelp == false) {
					//System.out.println("Suppled Filter is: "  +  suppledFilter );
					//System.out.println("Supplied String is: "  +  suppliedString );
					//System.out.println("");
					
					AnagramEvaluator anagramEvaluator = new AnagramEvaluatorImpl();
					
				    if( suppliedString != null &&  suppledFilter != null ) {   //if supplied anagram exist, but didnt provide filter, default use filter"word"			    	 
				    	resultList = anagramEvaluator.evaluate(suppliedString, suppledFilter);               
				    }else {
				    	//do nothing
				    }
				    
					
				    
				    //print the result
				    System.out.println("Result: ");
				    
		            for(String str : resultList) {
		            	System.out.println(str);
		            } 
			        
		          
		            System.out.println("--- " + resultList.size() + " value(s) found.");	
				}

			    
			    

	}//main

}
