package Lab2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class mergeSort {
	
	public static void merge(int a[], int p, int q, int r) {
		int n1 = q-p+1;
		int n2=r-q;
		int[] L = new int[n1+1];
		int[] R = new int[n2+1];
		
		for (int i=0; i<n1; i++) {
			L[i] = a[p+i];
			
		}
		for(int j=0; j<n2; j++) {
			R[j] = a[q+1+j];
		}
		L[n1] = Integer.MAX_VALUE;
		R[n2] = Integer.MAX_VALUE;
		
		int i = 0;
		int j = 0;
		for (int k=p; k <= r; k++) {
			if (L[i] <= R[j]) {
				a[k] = L[i];
				i++;
			}
			else {
				a[k] = R[j];
				j++;
			}
		}
	}
	
	public static void sort(int a[], int p, int r) {
		if (p<r) {
			int q = (p+r)/2;
			sort(a, p, q);
			sort(a, q+1, r);
			merge(a, p, q, r);
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
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		String fname = "C:\\Users\\Owner\\eclipse-workspace\\ADS\\src\\intBig.txt";
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
		sort(arr, 0, arr.length-1);
		long end = System.nanoTime();
		
		long duration = end - start;
		
		System.out.println("Time taken is: " + duration + "ns");
		
		System.out.println(TestSortingAlgorithms(arr));

	}

}
