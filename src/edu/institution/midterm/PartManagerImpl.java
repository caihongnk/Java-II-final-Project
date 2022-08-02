package edu.institution.midterm;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;



public class PartManagerImpl implements PartManager{
	
	static Map<String,Part> partsMap = new LinkedHashMap<>();//Create a LinkedHashMap to hold partnumber as Key and Part instance  as value 

	@Override 
	public int importPartStore(String filePath) {
	 
	Set<Part> partsSet = new LinkedHashSet<>(); //Create a temporary Set to hold info from JSON file(Using Set to elemenate duplicate)
	int numberOfParts =0; //number of imported parts
	
	//URL:Parse Large Json Files in Java using Gson Streaming   https://www.amitph.com/java-parse-large-json-files/#Read_Large_JSON_File_by_Streaming
    try(
    		InputStream inputStream = Files.newInputStream(Path.of(filePath));  //Creates a FileInputStream and then wraps it in an InputStreamReader  
    		                                                                   //URL: InputStreamReader:  http://tutorials.jenkov.com/java-io/inputstreamreader.html
    		JsonReader reader = new JsonReader(new InputStreamReader(inputStream));  //Instantiate JsonReader wrapper and use it to parse the JSON file
    		
    		 
    ){      
    	    //com.google.gson.stream Class JsonReader  Url: https://www.javadoc.io/doc/com.google.code.gson/gson/2.8.0/com/google/gson/stream/JsonReader.html
    	    reader.beginArray();  
    	    while (reader.hasNext()) {
    	    	 Part part = new Gson().fromJson(reader, Part.class);
    	    	 partsSet.add(part);
    	     }
    	    reader.endArray();  
    	    
    		
    	  //fill in data for the partsMap
    	    for(Part part : partsSet ) {
    	    	partsMap.put( part.getPartNumber(), part );
    	    } 
    	    
    	   //partsMap.forEach((key,value) ->System.out.println(key+ ": "+ value + " "));
    	    
    	   numberOfParts = partsMap.size(); 

    }catch(IOException e) {
    	System.out.println("IOException");
    }

    return  numberOfParts;

		
	}

			
	

	@Override
	//computes the cost of manufacturing the part associated with the supplied part number.
	//this method also computes the cost of each sub component part, and their sub component parts
	//until all parts under the supplied part have been costed.
	
	public Part costPart(String partNumber) {
		if(partNumber==null) {
			System.out.println("The supplied partNumber is null");
			return null ;
			
		}else {
			if(partsMap.get(partNumber)==null) {   //To avoid NullPointerException exception 
				System.out.println("The supplied partNumber is not exist!");
				return null ;
			}else {
				Part part =  partsMap.get(partNumber); 
				
				float total = 0;
		        
				float finalPrice = new BigDecimal(  priceCalculator(part,total)  ).setScale(2, RoundingMode.HALF_UP).floatValue();
				//System.out.println("The finalPrice is :" + finalPrice);
				
				part.setPrice(finalPrice);

				return part ;
			}
			

		}
		



	}

	@Override
	//Return the Part instance from the part store that is related to the supplied part number
	//Part instance is found for the supplied part number
	public Part retrievePart(String partNumber) {
		Part part;
		
		if(partNumber==null) {
			return null;
		}else if(partsMap.containsKey(partNumber)) {
			return partsMap.get(partNumber);
		}else {
			return null;
		}
	
	}

	@Override
	//Scan all parts imported in the part store and return only the final assembly parts(Type: ASSEMBLY)
	//The returned list of final assembly parts should be sorted in ascending order by their part number
	public List<Part> getFinalAssemblies() {

		//Create a Comparetor ------------
		class ComparePartsByPartNumber implements Comparator<Part>{
			@Override
			public int compare(Part o1, Part o2) {
				if(o1.getPartNumber()==null || o2.getPartNumber()==null) {
					return -1 ;
				}else {
					return o1.getPartNumber().compareTo(o2.getPartNumber());
				}
			}
				
		}
		//Create a Comparetor ------------

		Set<Part> partsSet = new TreeSet<>( new ComparePartsByPartNumber() );     //Textbook Listing 21.5   
		partsMap.forEach( (key,value) -> {
			 if(value.getPartType().equalsIgnoreCase("ASSEMBLY")) {
				 partsSet.add(value);  //add the part instance if partType is ASSEMBLY
			 }
		 } );
				 
				 
		//Create the List that need to be return
		List<Part> partsList = new LinkedList<>();
				 
		for(Part part : partsSet ) {
			partsList.add(part);
		}	 
		 
		
		return partsList;
	}

	
	
	@Override
	//return only the purchased parts(Type: PURCHASE)
	//The returned list of purchased parts should be sorted in descending order by their price
	public List<Part> getPurchasePartsByPrice() {
		
		//Create a Comparetor ------------
		class ComparePartsByPrice implements Comparator<Part>{
			
			@Override
			public int compare(Part o1, Part o2) {
					if(o1.getPrice()==o2.getPrice()) {
						return 0;
						
					}else if(o1.getPrice()>o2.getPrice()) {
						return 1;
					}else {
						return -1;
					}
			}
				
		}
		//Create a Comparetor ------------
		
		Set<Part> partsSet = new TreeSet<>( new ComparePartsByPrice().reversed() );     //I use TreeSet because I want to exclude out the duplicate parts.
		partsMap.forEach((key,value)->{                                                 //But the assignment require me to display all parts that is "PURCHASE", so I shouldn't use Set !!!!
			if(value.getPartType().equalsIgnoreCase("PURCHASE")) {                        
				partsSet.add(value); 
			}
		});
		//Create the List that need to be return
		List<Part> partsList = new LinkedList<>();
		
		for(Part part : partsSet ) {
			partsList.add(part);
		}	
		

		return partsList;
	}
	
	
	
	//new Method to calculate individual price for a part
	public static Float priceCalculator(Part part ,Float total) {
		

		if(part.getPrice() == 0) {
			float subTotal =0;
			
			
			for(BomEntry bom : part.getBillOfMaterial()) {
				String partNumber = bom.getPartNumber();
				int quantity = bom.getQuantity();
				
				if( partsMap.get(  partNumber  ).getPrice()>0 ) {
					subTotal =  subTotal + partsMap.get(partNumber).getPrice()*quantity;
				}else {
					subTotal =  subTotal + priceCalculator(partsMap.get(partNumber),total)*quantity ;
				}
	
			}//for
			

			return subTotal+total;
	
		}else {
			return part.getPrice();	
		}
		   

	}//priceCalculator()
	
	
	
	

}//class PartManagerImpl 
