import java.util.*;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;

public class Driver {
    public static Graph getGraphFromFile(String filename, boolean directed) {
        Graph g = null;
        try (Scanner fileScanner = new Scanner(Paths.get(filename))) {
            Integer vertices = fileScanner.nextInt();
            g = new Graph(vertices);
            Integer edges = fileScanner.nextInt();
            fileScanner.nextLine();
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().strip();
                String elements[] = line.split("\\s+");
                g.insert(Integer.valueOf(elements[0]), Integer.valueOf(elements[1]), Double.valueOf(elements[2]), directed);
            }
            fileScanner.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            System.exit(1);
        }
        return g;
    }

    public static void driver1(String filename, boolean directed) {

        Graph g = getGraphFromFile(filename, directed);
        g.print();

        g.mstPrim(g.get(0));

        //g.printMst();

    }

    public static void driver2(String filename, boolean directed) {
		Instant start, finish;
		long timeElapsed;
		
		Graph g = getGraphFromFile(filename, directed);
				
		start = Instant.now();
        g.mstPrim(g.get(0));
		finish = Instant.now();
		timeElapsed = Duration.between(start, finish).toNanos();
		
		System.out.format("%s: Time taken for completion:= %d ns\n", filename, timeElapsed);
		

	}

    public static void main(String[] args) {
        //driver1("tinyDG.txt", true);
        driver1("mediumDG.txt", true);
        //driver1("test.txt", false);
        driver2("mediumDG.txt", true);
        driver2("largeDG.txt", true);
        driver2("XtraLargeDG.txt", true);
    }
}
