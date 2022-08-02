package edu.institution.midterm;

import java.util.List;

import edu.institution.asn2.LinkedInUser;

//this interface which defines a family of classes that manage parts in a part store.
public interface PartManager {
	
	
	/**
	 * Imports the part store from an external file.
	 * Accepts the file path to the file which contains the parts to import.
	 * Returns the number of parts imported.
	 */
	int importPartStore(String filePath);
	
	
	/**
	 * Computes the cost to manufacture the part associated with the supplied part number.
	 * Accepts a part number which identifies the part to compute the cost for.
	 */
	Part costPart(String partNumber);
	
	/**
	 * Retrieves the part associated with the supplied part number from the part store.
	 * Accepts a part number which identifies the part to retrieve.
	 * Returns the Part instance associated with the supplied part number or null if not found
	 */
	Part retrievePart(String partNumber);
	
	
	/**
	 * Returns all final assembly parts sorted alphabetically by their part number
	 * Final assemblies have a part type of “ASSEMBLY”.
	 */
	List<Part> getFinalAssemblies();
	
	
	/**
	 * Returns all purchased parts sorted by their price, highest price to lowest.
	 * Purchase parts have a part type of “PURCHASE”.
	 */
	List<Part> getPurchasePartsByPrice();
}
