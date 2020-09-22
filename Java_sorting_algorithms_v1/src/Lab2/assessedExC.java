package Lab2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class assessedExC {
	public static void quickSortC(int[] arr, int left, int right){
		 
        int partition = partitionC(arr, left, right);
 
        if(partition-1>left) {
            quickSortC(arr, left, partition - 1);
        }
        if(partition+1<right) {
            quickSortC(arr, partition + 1, right);
        }
    }
	
	public static int[] medianOfThree(int[] arr, int left, int right) {
		int[] threeVals = {arr[0], arr[(arr.length-1)/2], arr[arr.length-1]}; // First, middle and last elements for an array
		Arrays.sort(threeVals); // Array is sorted
		arr[0] = threeVals[0];// And values are correspondingly swapped into the original array. 
		arr[(arr.length-1)/2] = threeVals[1];
		arr[(arr.length-1)] = threeVals[2];
		
		return arr;
		

		
		
	}
	
    public static int partitionC(int[] arr, int left, int right){
    	medianOfThree(arr, left, right);
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
	
	public static void main(String[] args) throws IOException {
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
		quickSortC(arr, 0, arr.length-1);
		long end = System.nanoTime();
		
		long duration = end - start;
		
		System.out.println("Time taken is: " + duration);
		
		//quickSortC(arr, 0, arr.length-1);
		
		System.out.println(TestSortingAlgorithms(arr));
	}

}
