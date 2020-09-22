package Lab2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class assessedExD {
	
	private static void swap(int[] array, int i, int j) {
	    int a = array[i];
	    array[i] = array[j];
	    array[j] = a;
	}
	
	private static void threeWaySort(int[] arr, int start, int end) {
	    if (end <= start)
	        return;

	    int left = start;
	    int right = end;
	    int pivot = arr[end];
	    int i = start;

	    while (i <= right) { // Sort the array into 3 sections
	        if (arr[i] < pivot) // Pivot is right-most selected. 
	            swap(arr, left++, i++); // remaining elements are swapped accordingly
	        else if (arr[i] > pivot) 
	            swap(arr, i, right--);
	        else
	            i++;
	    }
	    quickSortA(arr, 0, arr.length-1); // Proceed with right-most pivot quick sort

	}
	
	
	
	public static void quickSortA(int[] arr, int left, int right){
		 
        int partition = partitionA(arr, left, right);
 
        if(partition-1>left) {
            quickSortA(arr, left, partition - 1);
        }
        if(partition+1<right) {
            quickSortA(arr, partition + 1, right);
        }
    }
    public static int partitionA(int[] arr, int left, int right){
        int pivot = arr[right];
 
        for(int i=left; i<right; i++){
            if(arr[i]<pivot){
                int temp= arr[left];
                arr[left]=arr[i];
                arr[i]=temp;
                left++;
            }
        }
 
        int temp = arr[left];
        arr[left] = pivot;
        arr[right] = temp;
 
        return left;
    }
	
    public static boolean TestSortingAlgorithms(int[] a) {
    	int n = a.length;
    	for (int i=0; i < n-1; i++) {
    		if (a[i] > a[i+1]) {
    			return false;
    		}
    	}
    	return true;
    }
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		String fname = "C:\\Users\\Owner\\eclipse-workspace\\ADS\\src\\dutch.txt";
		String line;
		ArrayList<Integer> arrList = new ArrayList<Integer>();
		
		FileInputStream stream = new FileInputStream(fname);
		DataInputStream inputStream = new DataInputStream(stream);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));		
		
		while ((line = br.readLine()) != null) {
			int a = Integer.valueOf(line);
			
			arrList.add(a);
			//System.out.println(arr);  
		
		}
		br.close();
		
		int[] arr = new int[arrList.size()];
		
		for (int i=0; i < arrList.size(); i++) {
			arr[i] = arrList.get(i);
		}
		//System.out.println(Arrays.toString(arr));
		
		
		
		//System.out.println(java.util.Arrays.toString(a));
		
		
		long start = System.nanoTime();
		threeWaySort(arr, 0, arr.length-1);
		long end = System.nanoTime();
		
		long duration = end - start;
		
		System.out.println("Time taken is: " + duration);
		
		System.out.println(TestSortingAlgorithms(arr));

	}

}
