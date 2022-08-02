package edu.institution.asn9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class RetrieveMetricsTest {
	
	private static String PATH = System.getProperty("user.home") + File.separator + "Java2" + File.separator;
	private static String FILE_NAME = "asn9-numbers.txt"; 
	

	 

	@Test
	public void retrieveMetricsTest() throws IOException{
		
		List<Integer> numbers = new ArrayList<>();
		
		
		SortAlgorithmMetrics sortAlgorithmMetrics = new SortAlgorithmMetrics();

		
		
		List<MetricData> metricDataListForTest = new ArrayList<>();   //point to the metricDataList
		
		
		
		metricDataListForTest = sortAlgorithmMetrics.retrieveMetrics(PATH + FILE_NAME);
         
		boolean isArraySortedCorrect = true;
		
		for(int i =0 ; i< 5; i++) {		  //compare the next integer to the previous interger , the next one should larger than the previous one	
			System.out.println(metricDataListForTest.get(i).toString());

			for(int j =0 ; j< 80000-1; j++ ) {		
				if( sortAlgorithmMetrics.getFiveArrays().get(i)[j] >  sortAlgorithmMetrics.getFiveArrays().get(i)[j+1]    ) {
					isArraySortedCorrect = false;   //if found just one case that the next integer is smaller the previous interger,sign false
				}			
			}//inner for
	
		}//outer for
        
		//validate that your metrics list contains the five entries
		Assert.assertEquals(5, metricDataListForTest.size(), 0);
		
		//Check if the array is sorted correctly
		Assert.assertTrue(isArraySortedCorrect );   
		
		//Correct order by executionTime: mergeSort , quickSort , heapSort , insertionSort, BubbleSort		
		Assert.assertEquals(SortAlgorithm.MERGE_SORT, metricDataListForTest.get(0).getSortAlgorithm());
		Assert.assertEquals(SortAlgorithm.QUICK_SORT, metricDataListForTest.get(1).getSortAlgorithm());
		Assert.assertEquals(SortAlgorithm.HEAP_SORT, metricDataListForTest.get(2).getSortAlgorithm());
		Assert.assertEquals(SortAlgorithm.INSERTION_SORT, metricDataListForTest.get(3).getSortAlgorithm());
		Assert.assertEquals(SortAlgorithm.BUBBLE_SORT, metricDataListForTest.get(4).getSortAlgorithm());
		
		//Check if TimeComplexity correct
		Assert.assertEquals(TimeComplexity.LOGARITHMIC, metricDataListForTest.get(0).getTimeComplexity());   //mergeSort LOGARITHMIC
		Assert.assertEquals(TimeComplexity.QUADRATIC, metricDataListForTest.get(1).getTimeComplexity());   //quickSort QUADRATIC
		Assert.assertEquals(TimeComplexity.LOGARITHMIC, metricDataListForTest.get(2).getTimeComplexity());    //heapSort LOGARITHMIC
		Assert.assertEquals(TimeComplexity.QUADRATIC, metricDataListForTest.get(3).getTimeComplexity());  //insertionSort QUADRATIC
		Assert.assertEquals(TimeComplexity.QUADRATIC, metricDataListForTest.get(4).getTimeComplexity());  //BubbleSort QUADRATIC
		
		
	}//linearSearchTest





	

	 
	
	
	
}
