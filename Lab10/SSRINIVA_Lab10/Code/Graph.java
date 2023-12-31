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
		if(!directed) graph.get(v2).add(vertices[v1]);
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

	public void mstPrim(Node r) {
		for (Node u : vertices) {
			u.setKey(Double.MAX_VALUE);
			u.setP(null);
		}
		r.setKey(0.0);
		PriorityQueue<Node> Q = new PriorityQueue<>(Arrays.asList(vertices));
		while (Q.size() > 0) {
			Node u = Q.poll();
			for (Node v : graph.get(u.getValue().intValue())) {
				Double weight = weights.get(u.getValue().intValue()).get(v.getValue());
				if (Q.contains(v) && weight < v.getKey()) {
					Q.remove(v);
					v.setP(u);
					v.setKey(weight);
					Q.add(v);
				}
			}
		}
	}

	public void printMst() {
		for (Node v : vertices) {
			if (v.getP() != null) {
				System.out.format("%-10d %-10d %-10f\n", v.getValue().intValue(), v.getP().getValue().intValue(),
						weights.get(v.getValue().intValue()).get(v.getP().getValue()));
			}
		}
	}

	public Node[] getAllVertices() {
		return vertices;
	}
}
