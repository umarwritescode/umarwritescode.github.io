package Lab2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class assessedExB {

	// perform insertion sort on arr[]
	public static void insertionSort(int arr[]) {
		int n = arr.length;
		for (int j = 1; j < n; j++) {
			int key = arr[j];
			int i = j - 1;
			while ((i>= 0) && (arr[i] > key)) {
				arr[i+1] = arr[i];
				i--;
			}
			arr[i+1] = key;
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

	public static void hybridQuickSort(int[] A, int low, int high, int k) {
		while (low < high) { // do insertion sort if <= k 
			if(high - low < k) {
				insertionSort(A);
				break;
			}
			else { // Else proceed with reg. quick sort
				int pivot = partition(A, low, high);


				if (pivot - low < high - pivot) {
					hybridQuickSort(A, low, pivot - 1, k);
					low = pivot + 1;
				} else {
					hybridQuickSort(A, pivot + 1, high, k);
					high = pivot - 1;
				}
			}
		}
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
		// TODO Auto-generated method stub
		String fname = "C:\\Users\\Owner\\eclipse-workspace\\ADS\\src\\int10.txt";
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
		hybridQuickSort(arr, 0, arr.length-1, 5);
		long end = System.nanoTime();
		
		long duration = end - start;
		
		System.out.println("Time taken is: " + duration);
		
		System.out.println(TestSortingAlgorithms(arr));

	}

}
