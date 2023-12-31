import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class BST {

	public static Node treeInsert(Node root, Node z) {
		Node y = null;
		Node x = root;

		while (x != null) {
			y = x;
			if (z.key < x.key)
				x = x.left;
			else
				x = x.right;
		}
		if (y == null)
			return z;
		else if (z.key < y.key)
			y.left = z;
		else
			y.right = z;
		return root;
	}

	public static void inorderTreeWalk(Node root) {
		if (root != null) {
			inorderTreeWalk(root.left);
			System.out.println(root);
			inorderTreeWalk(root.right);
		}
	}

	public static void inorderTreeWalkIterative(Node root) {
		if (root == null)
			return;

		Stack<Node> s = new Stack<Node>();
		Node x = root;

		while (x != null || s.size() > 0) {
			while (x != null) {
				s.push(x);
				x = x.left;
			}
			x = s.pop();
			System.out.println(x);
			x = x.right;
		}
	}

	public static Node treeSearch(Node root, Long key) {
		if (root == null || root.key.equals(key)) {
			return root;
		}
		if (key < root.key) {
			return treeSearch(root.left, key);
		} else
			return treeSearch(root.right, key);
	}
	
	public static Node iterativeTreeSearch(Node root, Long key) {
		while(root != null && !root.key.equals(key)) {
			if(key < root.key) root = root.left;
			else root = root.right;
		}
		return root;
	}

	public static Node upcTree() {
		Node root = null;
		Integer lineCount = 0;
		final Integer totalLines = 177650;
		try (Scanner fileScanner = new Scanner(Paths.get("UPC.csv"))) {
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String elements[] = line.split(",");
				Long key = Long.valueOf(elements[0]);
				String data = elements[1] + ", " + elements[2];
				root = treeInsert(root, new Node(key, data));
				lineCount++;
				System.out.format("%f\n", (lineCount * 1.0 / totalLines) * 100f);
			}
			fileScanner.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		return root;
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
	
	public static void test1() {
		Node root = treeInsert(null, new Node(10L, ""));
		treeInsert(root, new Node(14234L, ""));
		treeInsert(root, new Node(5L, ""));
		treeInsert(root, new Node(1000L, "Data"));
		treeInsert(root, new Node(10L, ""));
		treeInsert(root, new Node(10000000L, ""));
		inorderTreeWalkIterative(root);
		Node x = iterativeTreeSearch(root, 1000L);
		System.out.println(x);
	}
	
	public static void test2() {
		Node upc = upcTree();
		System.out.println(iterativeTreeSearch(upc, 28800143178L));
		System.out.println(iterativeTreeSearch(upc, 18949005918L));
		System.out.println(iterativeTreeSearch(upc, 13286452029L));
	}
	
	public static void driver() {
		Instant start, finish;
		long timeElapsed;
		List<Long> searchKeys = getSearchKeys();

		start = Instant.now();
		Node upc = upcTree();
		finish = Instant.now();
		timeElapsed = Duration.between(start, finish).toMillis();
		System.out.format("Time taken for UPC tree formation:= %d ms\n", timeElapsed);
		
		for(Long key: searchKeys) {
			start = Instant.now();
			System.out.println(iterativeTreeSearch(upc, key));
			finish = Instant.now();
			timeElapsed = Duration.between(start, finish).toMillis();
			System.out.format("Time taken to search for key %d:= %d ms\n", key, timeElapsed);
		}
	}


	public static void main(String args[]) {
		//test1();
		//test2();
		driver();
	}
}
