import java.nio.file.Paths; //imports paths class for providing a path for the files.
import java.util.*; //imports every class inside util package.
import java.util.stream.Collectors; //It's too complicated bro.
import java.time.*; //imports every class in time package that contains utilities for time related work in java.

public class Tester {
	public static List<Integer> getNRandomNos(int n) { //generate n random numbers between 0 to 999
		Random rand = new Random();
		List<Integer> numbers = new ArrayList<>(); //initializes a new list (from collections framework if he asks)
		for(int i = 0; i < n; i++) {
			numbers.add(rand.nextInt(1000)); //gives you a number between 0 and 999 and adds it to the list
		}
		return numbers; //returns numbers that are in the list
	}
	
	public static Map<Integer, List<Integer>> generate2NDatasets(int N) { //Map is collection of buckets where i can put in and remove buckets, as many as I want (for my understanding). Key is the name of the bucket, value is what goes into the bucket. Integer and List<Integer> are data types of the value. N is taking in integer. Map stuff is return type.
		Map<Integer, List<Integer>> datasets = new HashMap<>(); //
		for(int i = 4; i <= N; i++) {  //they want is to start from 2^4
			Integer n = (int)Math.pow(2, i);
			datasets.put(i, getNRandomNos(n)); //calculates 2^N and passes into getNRandomNos (gives random list of N values) and pass it into the Map bucket.
		}
		return datasets; //returns the Map stuff (buckets)
	}
	
	public static List<Integer> readKeysFromFile() { //reads the 1000.txt
		List<Integer> keys = new ArrayList<Integer>(); //making another list that takes all the values from the file
		
		try (Scanner fileScanner = new Scanner(Paths.get("input_1000.txt"))) {
			while(fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				keys.addAll(Arrays.asList(line.split(",")).stream().map(Integer::valueOf).collect(Collectors.toList())); //reads each line from the file and spilts a single line using commas. Integer::valueOf it converts the strings to numbers. keys.addAll takes a list and adds all the elements into the list.
			}
			fileScanner.close(); //closes the file
		} catch (Exception e) {
			System.out.println("Error: " + e.toString()); //handle errors
		}
		return keys; //returns the new list into which keys.addAll added all the elements.
	}
	
	public static void driver2() {  //method which is used to call everything
		List<Integer> keys = readKeysFromFile(); //calling readkeysfromfile method
		Map<Integer, List<Integer>> datasets = generate2NDatasets(20); //calling Map thing and storing in datasets. 2^4 to 2^20.
		Instant start, finish; //just two variables. start and finish are the variables.
		
		for(Integer N = 4; N <= 20; N++) { //goes from 2^4 to 2^20 and it will get the lists from the Map and store it in a dataset variable. Just gets the list of random numbers.
			List<Integer> dataset = datasets.get(N); //gets list of random numbers
			long timeElapsed; //another variable
			
			System.out.format("2^%-5d", N); //prints 2^4.... 
			
			start = Instant.now(); //records the time at which the statement is executed
			for(Integer key: keys) { //goes through the keys list and executes the algorithms for each key.
				Algorithms.linearSearch(dataset, key); //class and method previously defined.
			}
			finish = Instant.now(); //time at which the previous for loop finsihes execution.
			timeElapsed = Duration.between(start, finish).toNanos() / 1000; //calculates the time elapsed and dividing by 1000 to get an average time for each key.
			System.out.format("%12d", timeElapsed); //prints the time elapsed
			
			Collections.sort(dataset); //will sort the data set 
			start = Instant.now(); ////records the time at which the statement is executed
			for(Integer key: keys) { //goes through the keys list and executes the algorithms for each key.
				Algorithms.binarySearch(dataset, 0, dataset.size() - 1, key); //-1 because last index is always 1 lesser than the length.
			}
			finish = Instant.now(); //time at which the previous for loop finsihes execution.
			timeElapsed = Duration.between(start, finish).toNanos() / 1000; //calculates the time elapsed and dividing by 1000 to get an average time for each key
			System.out.format("%12d", timeElapsed); ////prints the time elapsed
			
			System.out.println();
		}
	}
	
	public static void main(String args[]) { //if you execute this both the programs will execute. Algorithms and this HW.
		driver2();
	}
}
