import java.util.Arrays; //imports arrays
import java.util.Collections; //imports sort method
import java.util.List; //imports list class

public class Algorithms {
	
	public static int linearSearch(List<Integer> arr, Integer key) { //method that implements linear search. Taking in two parameters: key and array. Key is the value that I am going to search for.
		for(int i = 0; i < arr.size(); i++) { //go through the entire array and checks whether it is equal to the key. Searches sequentially.
			if(arr.get(i).equals(key)) return i;
		} 
		return -1;
	}
	
	public static int binarySearch(List<Integer> arr, int l, int u, Integer key) { //assumes array is sorted. l is lower limit, u is upper limit and key is key. It is a recursive method and it calls itself. At each level of recursion, I am going to calculate the middle value and then compare it to the key. If it is greater than the key or lesser than the key, we divide the search space by half by changing the values of the limits.
		if(l > u) return -1;
		int m = (l + u) / 2;
		if(arr.get(m) > key) { //get the value that is at index m of the array.
			return binarySearch(arr, l, m - 1, key); //if m is greater than the key, u becomes m-1
		} else if(arr.get(m) < key) {
			return binarySearch(arr, m + 1, u, key); //if m is lesser than the key, l becomes m+1
		} else return m;
	}
	
	public static void driver1() { //main method
		List<Integer> arr = Arrays.asList(22, 45, 65, 3, 1, 903, 43, 5); //hard code array 
		int n1 = 45, n2 = 75, n3 = 65, n4 = 5; //random numbers to check if searching algorithm is working correctly
		int linearResult1 = linearSearch(arr, n1); //returns index of the element if it is present, if it is absent it returns -1.
		int linearResult2 = linearSearch(arr, n2); //returns index of the element if it is present, if it is absent it returns -1.
		Collections.sort(arr); //sorts the array in ascending order
		System.out.println(arr); //printing sorted array
		int binaryResult1 = binarySearch(arr, 0, arr.size() - 1, n3); //returns index of the element if it is present, if it is absent it returns -1.
		int binaryResult2 = binarySearch(arr, 0, arr.size() - 1, n4); //returns index of the element if it is present, if it is absent it returns -1.
		
		System.out.format("%d found at %d and %d found at %d\n", n1, linearResult1, n2, linearResult2); // at %d program has to substitute an integer(4 values given) and prints the string
		System.out.format("%d found at %d and %d found at %d\n", n3, binaryResult1, n4, binaryResult2); 
		
	}
	
	public static void main(String args[]) {
		driver1();
	}
}
