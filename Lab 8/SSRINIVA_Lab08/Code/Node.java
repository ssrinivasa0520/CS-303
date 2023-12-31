public class Node {
	private Node next = null;
	private Long value;
	private Long d = null;
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

	public Long getD() {
		return d;
	}

	public void setD(Long d) {
		this.d = d;
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
}
