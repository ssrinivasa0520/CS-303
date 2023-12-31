public class Node implements Comparable<Node>{
	private Node next = null;
	private Long value;
	private Double key;
	private Node p = null;
	private String color = null;
	
	public Node(Long value) {
		this.value = value;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}


	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Node getP() {
		return p;
	}

	public void setP(Node p) {
		this.p = p;
	}
	
	public Double getKey() {
		return key;
	}

	public void setKey(Double key) {
		this.key = key;
	}

	@Override
	public boolean equals(Object v) {
		
		if (v == this) {
            return true;
        }
 
        if (!(v instanceof Node)) {
            return false;
        }
        
		return this.getValue().equals(((Node)v).getValue());
	}

	@Override
    public int compareTo(Node o) {
        return o.getKey() > this.getKey() ? 1 : -1;
    }
	
	@Override
	public String toString() {
		return this.getValue() + " " + (this.getP() == null ? "null" : this.getP().getValue().toString());
	}
}
