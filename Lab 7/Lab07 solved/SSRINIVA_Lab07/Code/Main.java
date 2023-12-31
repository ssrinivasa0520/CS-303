import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
	public static void test1() {
		//HashMap hashMap = new HashMap();
		//LinearProbeHashMap hashMap = new LinearProbeHashMap();
		QuadraticProbeHashMap hashMap = new QuadraticProbeHashMap();
		hashMap.put(100L, "Hello");
		hashMap.put(200L, "World");
		hashMap.put(300L, "Three Hundred");
		hashMap.put(300L, "Now Four Hundred");
		hashMap.put(50000L, "50K");
		//hashMap.put(2, "Zero");
		System.out.println(hashMap.get(100L));
		System.out.println(hashMap.get(200L));
		System.out.println(hashMap.get(300L));
		System.out.println(hashMap.get(50000L));
		System.out.println(hashMap.get(50001L));
		System.out.println(hashMap.get(100L));
		System.out.println(hashMap.get(200L));
	}
	
	public static void test2() {
		HashMapInterface hashMap = new HashMap(1500000);
		//HashMapInterface hashMap = new LinearProbeHashMap();
		//HashMapInterface hashMap = new QuadraticProbeHashMap();
		upcMap(hashMap);
		
        System.out.println(hashMap.get(28785103105L));
        System.out.println(hashMap.get(28400085168L));
        System.out.println(hashMap.get(26297107420L));
        System.out.println(hashMap.get(15400029186L));
        System.out.println(hashMap.get(11545338367L));
        
	}
	
	public static void upcMap(HashMapInterface hashMap) {
		Integer lineCount = 0;
		final Integer totalLines = 177650;
		try (Scanner fileScanner = new Scanner(Paths.get("UPC.csv"))) {
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String elements[] = line.split(",");
				Long key = Long.valueOf(elements[0]);
				String data = elements[1] + ", " + elements[2];
				hashMap.put(key, data);
				lineCount++;
				System.out.format("%f\n", (lineCount * 1.0 / totalLines) * 100f);
			}
			fileScanner.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
	}
	
	public static List<Long> getSearchKeys() {
		List<Long> keys = new ArrayList<>();
		try (Scanner fileScanner = new Scanner(Paths.get("input.dat"))) {
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String elements[] = line.split(",");
				Long key = Long.valueOf(elements[0]);
				keys.add(key);
			}
			fileScanner.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
			System.exit(1);
		}
		return keys;
	}
	
	public static void driver() {
		Instant start, finish;
		long timeElapsed1, timeElapsed2, timeElapsed3;
		String value;
		List<Long> searchKeys = getSearchKeys();
		
		HashMapInterface hashMap1 = new HashMap(1500000);
		HashMapInterface hashMap2 = new LinearProbeHashMap();
		HashMapInterface hashMap3 = new QuadraticProbeHashMap();
		
		upcMap(hashMap1);
		upcMap(hashMap2);
		upcMap(hashMap3);
		
		for(Long key: searchKeys) {
			System.out.format("Key := %d\n", key);
			start = Instant.now();
			System.out.format("Normal := %s\n", hashMap1.get(key));
			finish = Instant.now();
			timeElapsed1 = Duration.between(start, finish).toNanos();
			
			start = Instant.now();
			System.out.format("Linear Probe := %s\n", hashMap2.get(key));
			finish = Instant.now();
			timeElapsed2 = Duration.between(start, finish).toNanos();
			
			start = Instant.now();
			System.out.format("Quadratic Probe := %s\n", hashMap3.get(key));
			finish = Instant.now();
			timeElapsed3 = Duration.between(start, finish).toNanos();
			
			System.out.println("Time taken := ");
			System.out.format("Normal := %d ns Linear Probe := %d ns Quadratic Probe := %d ns\n", timeElapsed1, timeElapsed2, timeElapsed3);
		}
	}
	
	public static void main(String args[]) {
		driver();
	}
}
