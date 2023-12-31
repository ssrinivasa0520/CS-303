import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Driver {
	
	public static Graph getGraphFromFile(String filename) {
		Graph g = null;
		try (Scanner fileScanner = new Scanner(Paths.get(filename))) {
			Integer vertices = fileScanner.nextInt();
			g = new Graph(vertices);
			Integer edges = fileScanner.nextInt();
			fileScanner.nextLine();
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String elements[] = line.split(" ");
				g.insert(Integer.valueOf(elements[0]), Integer.valueOf(elements[1]));
			}
			fileScanner.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
			System.exit(1);
		}
		return g;
	}
	
	public static void driver(String filename) {
		Instant start, finish;
		long timeElapsed;
		
		Graph g = getGraphFromFile(filename);
		g.print();
		
		start = Instant.now();
		g.bfs(g.get(0));
		finish = Instant.now();
		timeElapsed = Duration.between(start, finish).toNanos();
		
		System.out.format("Time taken for BFS:= %d\n", timeElapsed);
		
		for(Node v: g.getAllVertices()) {
			g.printPath(g.get(0), v);
		}
	}
	

	public static void main(String[] args) {
		driver("mediumG.txt");
	}

}
