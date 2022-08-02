package edu.institution.asn9;

public class QuickSort {
	public static void quickSort(Integer[] list) {
		quickSort(list, 0, list.length - 1);
	}

	private static void quickSort(Integer[] list, Integer first, Integer last) {
		if (last > first) {
			Integer pivotIndex = partition(list, first, last);
			quickSort(list, first, pivotIndex - 1);
			quickSort(list, pivotIndex + 1, last);
		}
	}

	/** Partition the array list[first..last] */
	private static Integer partition(Integer[] list, Integer first, Integer last) {
		Integer pivot = list[first]; // Choose the first element as the pivot
		Integer low = first + 1; // Index for forward search
		Integer high = last; // Index for backward search

		while (high > low) {
			// Search forward from left
			while (low <= high && list[low] <= pivot)
				low++;

			// Search backward from right
			while (low <= high && list[high] > pivot)
				high--;

			// Swap two elements in the list
			if (high > low) {
				Integer temp = list[high];
				list[high] = list[low];
				list[low] = temp;
			}
		}

		while (high > first && list[high] >= pivot)
			high--;

		// Swap pivot with list[high]
		if (pivot > list[high]) {
			list[first] = list[high];
			list[high] = pivot;
			return high;
		} else {
			return first;
		}
	}
}

