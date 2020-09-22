package Lab2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class insertionSort {
	
	public static void insertionSort(int a[]) {
		int n = a.length;
		for (int j = 1; j < n; j++) {
			int key = a[j];
			int i = j - 1;
			while ((i>= 0) && (a[i] > key)) {
				a[i+1] = a[i];
				i--;
			}
			a[i+1] = key;
		}
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
		insertionSort(arr);
		long end = System.nanoTime();
		
		long duration = end - start;
		
		System.out.println("Time taken is: " + duration + "ns");

	}

}
