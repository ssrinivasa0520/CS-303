import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
	private ArrayList<LinkedList<Node>> graph;
	private Node vertices[];
	private ArrayList<Node> sortedVertices;
	
	private static Long time = 0l;

	public Graph(int size) {
		this.graph = new ArrayList<>(size);
		this.sortedVertices = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			graph.add(new LinkedList<>());
		}
		this.vertices = new Node[size];
	}

	public Node get(int i) {
		return vertices[i];
	}

	public void insert(int v1, int v2, boolean directed) {

		if (vertices[v1] == null)
			vertices[v1] = new Node((long) v1);
		if (vertices[v2] == null)
			vertices[v2] = new Node((long) v2);

		graph.get(v1).add(vertices[v2]);
		if(!directed) graph.get(v2).add(vertices[v1]);

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

	public void dfs() {
		for (Node v : vertices) {
			v.setColor("WHITE");
			v.setP(null);
		}
		time = 0l;
		for(Node v: vertices) {
			if(v.getColor().equals("WHITE")) {
				dfsVisit(v);
			}
		}
	}
	
	public void dfsVisit(Node u) {
		time += 1;
		u.setD(time);
		u.setColor("GRAY");
		for(Node v: graph.get(Math.toIntExact(u.getValue()))) {
			if(v.getColor().equals("WHITE")) {
				v.setP(u);
				dfsVisit(v);
			}
		}
		u.setColor("BLACK");
		time += 1;
		u.setF(time);
		sortedVertices.add(0, u);
	}

	public void printPath(Node s, Node v) {
		if (v.equals(s)) {
			System.out.print("\n" + s.getValue());
		} else if (v.getP() == null) {
			System.out.format("\n no path from %d to %d exists", s.getValue(), v.getValue());
		} else {
			printPath(s, v.getP());
			System.out.format(" , %d", v.getValue());
		}
	}
	
	
	public ArrayList<Node> getSortedVertices() {
		return sortedVertices;
	}

	public Node[] getAllVertices() {
		return vertices;
	}
}
