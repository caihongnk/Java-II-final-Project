package edu.institution.finalproj;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AnagramDataReaderImpl implements AnagramDataReader{

	@Override
	public Set<String> readData(String filePath) throws IOException {
		
		
		Set<String> anagramDataSet = new HashSet<>();
		
		
		try (BufferedReader bufReader = new BufferedReader(new FileReader(filePath))){
			String line ;
			
			while( (line =bufReader.readLine() ) !=null) {				
				  
				anagramDataSet.add(line);
								
			}
			
		}catch(FileNotFoundException exception) {
			
		}
		
		
		
		
		
		return anagramDataSet;
	}

}
