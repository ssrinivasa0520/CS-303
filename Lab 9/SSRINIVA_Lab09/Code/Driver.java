import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Driver {
	public static Graph getGraphFromFile(String filename, boolean directed) {
		Graph g = null;
		try (Scanner fileScanner = new Scanner(Paths.get(filename))) {
			Integer vertices = fileScanner.nextInt();
			g = new Graph(vertices);
			Integer edges = fileScanner.nextInt();
			fileScanner.nextLine();
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String elements[] = line.split(" ");
				g.insert(Integer.valueOf(elements[0]), Integer.valueOf(elements[1]), directed);
			}
			fileScanner.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
			System.exit(1);
		}
		return g;
	}
	
	public static void driver1(String filename) {
		Instant start, finish;
		long timeElapsed;
		
		Graph g = getGraphFromFile(filename, false);
		g.print();
		
		
		start = Instant.now();
		g.dfs();
		finish = Instant.now();
		timeElapsed = Duration.between(start, finish).toNanos();
		
		System.out.format("Time taken for DFS:= %d\n", timeElapsed);
		
		for(Node v: g.getAllVertices()) {
			System.out.format("\nPath from Source Vertex %d to Vertex %d", g.get(0).getValue(), v.getValue());
			g.printPath(g.get(0), v);
		}
		
//		for(Node v: g.getAllVertices()) {
//			System.out.println(v);
//		}
	}
	
	public static void driver2(String filename) {
		Instant start, finish;
		long timeElapsed;
		
		Graph g = getGraphFromFile(filename, true);
		g.print();
		
		
		start = Instant.now();
		g.dfs();
		finish = Instant.now();
		timeElapsed = Duration.between(start, finish).toNanos();
		
		System.out.format("Time taken for DFS:= %d\n", timeElapsed);
		
//		for(Node v: g.getAllVertices()) {
//			g.printPath(g.get(0), v);
//		}
		
		System.out.println("Topological Sort");
		for(Node v: g.getSortedVertices()) {
			System.out.print(v.getValue() + ", ");
		}
		
//		for(Node v: g.getAllVertices()) {
//			System.out.println(v);
//		}
	}
	
	public static void main(String[] args) {
		//driver1("tinyG.txt");
		driver1("tinyG.txt");
		System.out.println();
		System.out.println();
		driver2("tinyDG.txt");
	}
}
