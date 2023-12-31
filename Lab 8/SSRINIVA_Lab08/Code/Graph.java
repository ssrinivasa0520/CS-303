import java.util.*;

public class Graph {
	private ArrayList<LinkedList<Node>> graph;
	private Node vertices[];

	public Graph(int size) {
		this.graph = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			graph.add(new LinkedList<>());
		}
		this.vertices = new Node[size];
	}

	public Node get(int i) {
		return vertices[i];
	}

	public void insert(int v1, int v2) {

		if (vertices[v1] == null)
			vertices[v1] = new Node((long) v1);
		if (vertices[v2] == null)
			vertices[v2] = new Node((long) v2);

		graph.get(v1).add(vertices[v2]);

	}

	public void print() {
		for (int i = 0; i < graph.size(); i++) {
			System.out.format("%d -> ", i);
			for (Node v : graph.get(i)) {
				System.out.format("%d -> ", v.getValue());
			}
			System.out.print("/ \n");
		}
	}

	public void bfs(Node s) {
		for (Node u : vertices) {
			Node v = u;
			if (!v.equals(s)) {
				v.setColor("WHITE");
				v.setD(null);
				v.setP(null);
			}
		}
		s.setColor("GRAY");
		s.setD(0l);
		s.setP(null);
		Queue<Node> Q = new ArrayDeque<>();
		Q.add(s);
		while (Q.size() > 0) {
			Node u = Q.remove();
			for (Node v: graph.get(Math.toIntExact(u.getValue()))) {
				if (v.getColor().equalsIgnoreCase("WHITE")) {
					v.setColor("GRAY");
					v.setD(u.getD() + 1);
					v.setP(u);
					Q.add(v);
				}
			}
			u.setColor("BLACK");
		}
	}

	public void printPath(Node s, Node v) {
		if (v.equals(s)) {
			System.out.print("\n" + s.getValue());
		} else if (v.getP() == null) {
			System.out.format("\n%d X %d", s.getValue(), v.getValue());
		} else {
			printPath(s, v.getP());
			System.out.format(" -> %d", v.getValue());
		}
	}

	public Node[] getAllVertices() {
		return vertices;
	}
}
