public interface HashMapInterface {
	public final static Integer TABLE_SIZE = 300000;

	public Long H(Long key);
	public String get(Long key);
	public void put(Long key, String value);
}
