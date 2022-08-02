package edu.institution.midterm;

import edu.institution.asn2.UserAccount;

public class BomEntry {
	
	// identifies the part in the BOM
	private String partNumber;
	
	// the quantity needed for this part entry
	private int quantity;
	
	
	 
	// add getters/setters
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public int getQuantity() {
		return quantity; 
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
		BomEntry other = (BomEntry) obj;
		if (partNumber == null) {
			if (other.partNumber != null)
				return false;
		} else if (!partNumber.equals(other.partNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BomEntry [partNumber=" + partNumber + ", quantity=" + quantity + "]";
	}
	
	
	
	


     
	

}//BomEntry
