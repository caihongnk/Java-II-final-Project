package edu.institution.finalproj;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface AnagramDataReader {

	/**
	* Returns a Set containing all words read from the anagram data text file.
	*
	* @return the Set or empty Set if no words are found.
	 * @throws IOException 
	*/
	Set<String> readData(String filePath) throws IOException;
	
}
