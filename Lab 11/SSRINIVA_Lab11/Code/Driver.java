import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
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
                g.insert(Integer.valueOf(elements[0]), Integer.valueOf(elements[1]), Double.valueOf(elements[2]),
                        directed);
            }
            fileScanner.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            System.exit(1);
        }
        return g;
    }

    public static void writeShortestPathOutputToFile(Node s, Graph g, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            for (Node v : g.getAllVertices()) {
                writer.write(String.format("The minimum distance between %d to %d = %f", s.getValue(), v.getValue(), v.getD()));
                writer.write(g.printShortestPath(s, v));
                writer.write("\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            System.exit(1);
        }
    }

    public static void driver1(String filename, boolean directed) {

        Graph g = getGraphFromFile(filename, directed);
        // g.print();

        Node s = g.get(0);

        g.dijkstra(s);

        writeShortestPathOutputToFile(s, g, String.format("%s-Output", filename));

    }

    public static void driver2(String filename, boolean directed) {
        Instant start, finish;
        long timeElapsed;

        Graph g = getGraphFromFile(filename, directed);
        Node s = g.get(0);

        start = Instant.now();
        g.dijkstra(s);
        finish = Instant.now();
        timeElapsed = Duration.between(start, finish).toNanos();

        writeShortestPathOutputToFile(s, g, String.format("%s-Output", filename));

        System.out.format("%s: Time taken for completion:= %d ns\n", filename, timeElapsed);

    }

    public static void main(String[] args) {
        // driver1("tinyDG.txt", true);
        // driver1("mediumDG.txt", true);
        // driver1("test.txt", false);
        driver2("tinyDG.txt", true);
        driver2("mediumDG.txt", true);
        driver2("largeDG.txt", true);
    }
}
