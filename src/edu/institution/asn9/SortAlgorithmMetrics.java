package edu.institution.asn9;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

/**
 * This class will execute  Bubble Sort, Merge Sort, Quick Sort, Heap Sort, and Insertion Sort
 * against an array of 80000 integers and return a List of MetricData instances representing the
 * appropriate time complexity and the length of time (in milliseconds) it took to sort the data
 * 
 * 
 */

public class SortAlgorithmMetrics {
	 
	

	//five instances of the MetricData class contained within the List 
	//one for each sort algorithm. Call the Collections.sort method on the list to sort the metric entries
	//shortest time to longest time, and return the list
	
	private List<MetricData> metricDataList = new ArrayList<>();   
	
	private List<Integer> numbers = new ArrayList<>();  //Store 8000 integers
	
	private ArrayList<Integer[]> fiveArrays = new ArrayList<>();
	
	
	public ArrayList<Integer[]> getFiveArrays() {
		return fiveArrays;
	}



	
	public List<MetricData> retrieveMetrics(String filePath) throws IOException {
		
		
		try(BufferedReader bufReader = new BufferedReader(new FileReader(filePath))){        //URL: https://www.java67.com/2016/07/how-to-read-text-file-into-arraylist-in-java.html  
			
			
			
			String line = bufReader.readLine();
			
			while(line != null) {				
				int i = Integer.parseInt(line);   //URL: https://www.javatpoint.com/java-string-to-int
				numbers.add(i);
				line = bufReader.readLine();				
			}
			
		}catch(FileNotFoundException exception) {
			
		}
		int length = numbers.size();
		
		Integer[] array1 = new Integer[length]; //for BubbleSort
		Integer[] array2 = new Integer[length]; //for MergeSort
		Integer[] array3 = new Integer[length];//for QuickSort
		Integer[] array4 = new Integer[length];//for HeapSort
		Integer[] array5 = new Integer[length];//for InsertionSort
		
		
		fiveArrays.add(array1); 
		fiveArrays.add(array2);
		fiveArrays.add(array3);
		fiveArrays.add(array4);
		fiveArrays.add(array5);
		
		
		
		

		
		//Shuffle 5 time(each time should get different arrange of numbers) then copy to 5 arrays 
		for(int i =0; i < 5; i++) {
			Collections.shuffle(numbers);
			Integer[] template = {}; // tells toArray what type of Array to generate
			Integer[] list = numbers.toArray(template);  //ArrayList convert to array
			
			Integer[] tempArray = new Integer[length]; 
			System.arraycopy(list, 0, tempArray, 0, length);  //public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)   URL:https://www.tutorialspoint.com/java/lang/system_arraycopy.htm
			System.arraycopy(tempArray,0,fiveArrays.get(i),0, length);	
		}
		
		//Create an instance of the MetricsData class representing the Bubble Sort algorithm nad calculate the time 
		MetricData bubbleSortMetricData = new MetricData(SortAlgorithm.BUBBLE_SORT);
		LocalTime start1 = LocalTime.now();
		BubbleSort.bubbleSort(array1);
		LocalTime end1 = LocalTime.now();
		long elapsedMilliseconds1 = Duration.between(start1, end1).toMillis();
		bubbleSortMetricData.setExecutionTime(elapsedMilliseconds1);
		bubbleSortMetricData.setTimeComplexity(TimeComplexity.QUADRATIC);
		
		//Create an instance of the MetricsData class representing the Merge Sort algorithm nad calculate the time
		MetricData mergeSortMetricData = new MetricData(SortAlgorithm.MERGE_SORT);
		LocalTime start2 = LocalTime.now();
		MergeSort.mergeSort(array2);
		LocalTime end2 = LocalTime.now();
		long elapsedMilliseconds2 = Duration.between(start2, end2).toMillis();
		mergeSortMetricData.setExecutionTime(elapsedMilliseconds2);
		mergeSortMetricData.setTimeComplexity(TimeComplexity.LOGARITHMIC);
		
		//Create an instance of the MetricsData class representing the QuickSort algorithm nad calculate the time
		MetricData quickSortMetricData = new MetricData(SortAlgorithm.QUICK_SORT);
		LocalTime start3 = LocalTime.now();
		QuickSort.quickSort(array3);
		LocalTime end3 = LocalTime.now();
		long elapsedMilliseconds3 = Duration.between(start3, end3).toMillis();
		quickSortMetricData.setExecutionTime(elapsedMilliseconds3);
		quickSortMetricData.setTimeComplexity(TimeComplexity.QUADRATIC);   //O(n2) worst-case time
		
		//Create an instance of the MetricsData class representing the HeapSort algorithm nad calculate the time
		MetricData heapSortMetricData = new MetricData(SortAlgorithm.HEAP_SORT);
		LocalTime start4 = LocalTime.now();
		HeapSort.heapSort(array4);
		LocalTime end4 = LocalTime.now();
		long elapsedMilliseconds4 = Duration.between(start4, end4).toMillis();
		heapSortMetricData.setExecutionTime(elapsedMilliseconds4);
		heapSortMetricData.setTimeComplexity(TimeComplexity.LOGARITHMIC);
		
		//Create an instance of the MetricsData class representing the InsertionSort algorithm nad calculate the time
		MetricData insertionSortMetricData = new MetricData(SortAlgorithm.INSERTION_SORT);
		LocalTime start5 = LocalTime.now();
		InsertionSort.insertionSort(array5);
		LocalTime end5 = LocalTime.now();
		long elapsedMilliseconds5 = Duration.between(start5, end5).toMillis();
		insertionSortMetricData.setExecutionTime(elapsedMilliseconds5);
		insertionSortMetricData.setTimeComplexity(TimeComplexity.QUADRATIC);
		
		
		metricDataList.add(bubbleSortMetricData);
		metricDataList.add(mergeSortMetricData);
		metricDataList.add(quickSortMetricData);
		metricDataList.add(heapSortMetricData);
		metricDataList.add(insertionSortMetricData);
		
		Collections.sort(metricDataList);
		
         
		

		
		return metricDataList;
		
	}



	


}
