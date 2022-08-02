package edu.institution.asn9;

/**
 * This class represents a metrics entry for a sorting algorithm. 
 * 
 * The MetricData class will be stored in a List created in the SortAlgorithmMetrics class
 * to hold the performance metrics of each sorting algorithm.
 */
public class MetricData implements Comparable<MetricData>{
	
	// the sort algorithm used to sort the data
	private SortAlgorithm sortAlgorithm;
	
	// the time complexity for the sort algorithm
	private TimeComplexity timeComplexity;
	
	// the time (in milliseconds) that it took to sort the data
	private long executionTime;
	
	MetricData(SortAlgorithm sortAlgorithm){
		this.sortAlgorithm = sortAlgorithm;
	}
	
	//Setter & Getter
	public TimeComplexity getTimeComplexity() {
		return timeComplexity;
	}

	public void setTimeComplexity(TimeComplexity timeComplexity) {
		this.timeComplexity = timeComplexity;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}

	public SortAlgorithm getSortAlgorithm() {
		return sortAlgorithm;
	}

	// hashCode and equals method using the SortAlgorithm property as the unique key 
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sortAlgorithm == null) ? 0 : sortAlgorithm.hashCode());
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
		MetricData other = (MetricData) obj;
		if (sortAlgorithm != other.sortAlgorithm)
			return false;
		return true;
	}



	
	//Generate a toString method for debugging purposes
	@Override
	public String toString() {
		return "MetricData [sortAlgorithm=" + sortAlgorithm + ", timeComplexity=" + timeComplexity + ", executionTime="
				+ executionTime + "]";
	}

	
    //compareTo method to order the entries by their executionTime (shortest time to longest time)
	@Override
	public int compareTo(MetricData object) {
		int returnValue = 0;
		
		if(object==null) {
			 returnValue = -1;
			 
		}else {
			if(this.executionTime>object.getExecutionTime()) {
				returnValue = 1;
			}
			
			if(this.executionTime<object.getExecutionTime()) {
				returnValue = -1;
			}
			
			if(this.executionTime==object.getExecutionTime()) {
				returnValue = 0;
			}
			
		}
		
		return returnValue;
	}
	
	
}// MetricData
