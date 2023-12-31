import java.util.*;

public class Graph {
	private ArrayList<LinkedList<Node>> graph;
	private Node vertices[];
	private List<Map<Long, Double>> weights;

	public Graph(int size) {
		this.graph = new ArrayList<>(size);
		this.weights = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			graph.add(new LinkedList<>());
			weights.add(new HashMap<>());
		}
		this.vertices = new Node[size];
	}

	public Node get(int i) {
		return vertices[i];
	}

	public void insert(int v1, int v2, double weight, boolean directed) {

		if (vertices[v1] == null)
			vertices[v1] = new Node((long) v1);
		if (vertices[v2] == null)
			vertices[v2] = new Node((long) v2);

		graph.get(v1).add(vertices[v2]);
		if (!directed)
			graph.get(v2).add(vertices[v1]);
		weights.get(v1).put((long) v2, weight);
		weights.get(v2).put((long) v1, weight);

	}

	public void print() {
		for (int i = 0; i < graph.size(); i++) {
			System.out.format("%d : ", i);
			for (Node v : graph.get(i)) {
				System.out.format("(%d, %f), ", v.getValue().intValue(),
						weights.get(i).get(v.getValue()));
			}
			System.out.print("/ \n");
		}
	}

	public Double w(Node u, Node v) {
		return weights.get(u.getValue().intValue()).get(v.getValue());
	}

	public void initializeSingleSource(Node s) {
		for (Node v : vertices) {
			v.setD(Double.MAX_VALUE);
			v.setP(null);
		}
		s.setD(0D);
	}

	public void relax(Node u, Node v) {
		// System.out.println(u + " " + v);
		// System.out.println(v.getD() + " " + u.getD() + w(u, v));
		if (v.getD() > u.getD() + w(u, v)) {
			v.setD(u.getD() + w(u, v));
			v.setP(u);
			// System.out.println(u + " " + v);
		}
	}

	public void dijkstra(Node s) {
		initializeSingleSource(s);
		// System.out.println(Arrays.toString(vertices));
		Set<Node> S = new HashSet<>();
		PriorityQueue<Node> Q = new PriorityQueue<>(Arrays.asList(vertices));
		while (Q.size() > 0) {
			Node u = Q.poll();
			S.add(u);
			for (Node v : graph.get(u.getValue().intValue())) {
				relax(u, v);
				if (Q.contains(v)) {
					Q.remove(v);
					Q.add(v);
				}
			}
		}
	}

	public String printShortestPath(Node s, Node v) {
		if (v.equals(s)) {
			return String.format("\n" + s.getValue());
		} else if (v.getP() == null) {
			return String.format("\n no path from %d to %d exists", s.getValue(), v.getValue());
		} else {
			return printShortestPath(s, v.getP()) + String.format("-> %d", v.getValue());

		}
	}

	public Node[] getAllVertices() {
		return vertices;
	}
}
