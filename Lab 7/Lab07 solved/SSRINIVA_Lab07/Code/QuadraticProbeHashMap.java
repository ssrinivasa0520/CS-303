public class QuadraticProbeHashMap implements HashMapInterface {

	HashEntry[] table;

	public QuadraticProbeHashMap() {
		this.table = new HashEntry[TABLE_SIZE];
	}

	@Override
	public Long H(Long key) {
		return key % TABLE_SIZE;
	}
	
	public Long hashKey(Long key) {
		Long hashKey = H(key);
		Long initialHashKey = hashKey;
		int count = 1;

		do {
			if (table[Math.toIntExact(hashKey)] != null) {
				if (table[Math.toIntExact(hashKey)].getKey().equals(key)) {
					return hashKey;
				} else {
					hashKey = H(initialHashKey + (count * count));
				}
			} else {
				return hashKey;
			}
			count++;
		} while (hashKey != initialHashKey);

		return null;
	}

	@Override
	public String get(Long key) {
		Long hashKey = hashKey(key);
		if (hashKey != null && table[Math.toIntExact(hashKey)] != null)
			return table[Math.toIntExact(hashKey)].getValue();
		else
			return null;
	}

	@Override
	public void put(Long key, String value) {
		Long hashKey = hashKey(key);
		if (hashKey != null) {
			table[Math.toIntExact(hashKey)] = new HashEntry(key, value);
		} else {
			System.out.println("HashMap at capacity");
		}
	}

}
