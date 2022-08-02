package edu.institution.midterm;

import java.util.List;
import java.util.ArrayList;

//hold the properties of a part in the part store.
public class Part {
 // identifies this part in the part store
	private String partNumber;
	  
	// the display name for this part
	private String name;
	
	// the type of part. Valid values are: "ASSEMBLY", "PURCHASE", or "COMPONENT"
	// "ASSEMBLY" parts are the final assemblies that are sold to the customers.
	// "COMPONENT" parts are the sub component parts that make up the final assemblies.
	// "PURCHASE" parts are the external components purchased from a vendor.
	private String partType;
	
	// the price of this part.
	private float price;
	
	// the list of BOM entries one level under this part.
	private List<BomEntry> billOfMaterial = new ArrayList<>(); 
 
	
	
	// add getter/setters
	public List<BomEntry> getBillOfMaterial() { 
		return billOfMaterial;
	}

	public void setBillOfMaterial(List<BomEntry> billOfMaterial) {
		this.billOfMaterial = billOfMaterial;
	}
	
	public String getPartNumber() {
		return partNumber;
	}
   
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPartType() {
		return partType;
	}

	public void setPartType(String partType) {
		this.partType = partType;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	// add equals, hashCode, and toString methods.
	@Override
	public String toString() {
		return "Part [partNumber=" + partNumber + ", name=" + name + ", partType=" + partType + ", price=" + price
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((partNumber == null) ? 0 : partNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Part other = (Part) obj;
		if (partNumber == null) {
			if (other.partNumber != null)
				return false;
		} else if (!partNumber.equals(other.partNumber))
			return false;
		return true;
	}
	
	
	

	
	
	
}
