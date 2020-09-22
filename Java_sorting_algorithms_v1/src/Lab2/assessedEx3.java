package Lab2;

import java.util.Arrays;
import java.util.Random;

public class assessedEx3 {
	
	// To create a pathological input for the quick sort median of three algorithm
	// Median of 3 must be 2nd smallest/largest
	// We must create an input, already ordered, and shift the sequence by one.
	// This forces the algo to continuously call 'med of three' method
	public static int[] pathologicalInput(int length, int max) {
		Random dice = new Random();
		
		int[] arr = new int[length];
		int firstDig = dice.nextInt(max);
		
		arr[1] = firstDig; // Assign 2nd pos. in the pathological array to the generated digit.
		
		for (int i = 2; i < length; i++) { // Add one to the remaining positions in the array of the val in the pos. preceding it
			arr[i] = arr[i-1] + 1;
		}
		
		arr[0] = arr[arr.length - 1] + 1; // Assign the first pos to be the value of the last one plus one. 
											// Thus results in a rotated sequence. 
		
		
		return arr;
	
		
	}
	
	
	public static void main(String[] args) {

		System.out.println(Arrays.toString(pathologicalInput(50,100)));
	}

}
