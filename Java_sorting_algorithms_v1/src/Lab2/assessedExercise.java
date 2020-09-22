package Lab2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class assessedExercise {
	
	public static void quickSort(int[] arr, int left, int right){
		 
        int partition = partition(arr, left, right);
 
        if(partition-1>left) {
            quickSort(arr, left, partition - 1);
        }
        if(partition+1<right) {
            quickSort(arr, partition + 1, right);
        }
    }
    public static int partition(int[] arr, int left, int right){
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
		//int[] a = {1, 14, 4, 5, 7, 9, 12};
		//quickSort(a, 0, a.length-1);
		
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
		quickSort(arr, 0, arr.length-1);
		long end = System.nanoTime();
		
		long duration = end - start;
		
		System.out.println("Time taken is: " + duration);
		
		System.out.println(TestSortingAlgorithms(arr));
	}
	
	
	
	

}
