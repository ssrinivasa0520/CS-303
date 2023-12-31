public class HashMap implements HashMapInterface {

	HashEntry[] table;
	Integer maxSize = TABLE_SIZE;

	public HashMap() {
		this.table = new HashEntry[maxSize];
	}
	
	public HashMap(Integer size) {
		this.table = new HashEntry[size];
		maxSize = size;
	}

	public Long H(Long key) {
		return key % maxSize;
	}

	public Long hashKey(Long key) {
		Long hashKey = H(key);
		Long initialHashKey = hashKey;

		do {
			if (table[Math.toIntExact(hashKey)] != null) {
				if (table[Math.toIntExact(hashKey)].getKey().equals(key)) {
					return hashKey;
				} else {
					hashKey = H(7 * hashKey + 1);
				}
			} else {
				return hashKey;
			}
		} while (hashKey != initialHashKey);

		return null;
	}

	public String get(Long key) {
		Long hashKey = hashKey(key);
		if (hashKey != null && table[Math.toIntExact(hashKey)] != null)
			return table[Math.toIntExact(hashKey)].getValue();
		else
			return null;
	}

	public void put(Long key, String value) {
		Long hashKey = hashKey(key);
		if (hashKey != null) {
			table[Math.toIntExact(hashKey)] = new HashEntry(key, value);
		} else {
			System.out.println("HashMap at capacity");
		}
	}
	
}
