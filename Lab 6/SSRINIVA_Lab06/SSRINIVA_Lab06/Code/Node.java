class Node {
	Long key;
	String data;
	Node left = null;
	Node right = null;

	Node(Long key, String data) {
		this.key = key;
		this.data = data;
	}

	@Override
	public String toString() {
		return String.format("%d %s", key, data);
	}
}
