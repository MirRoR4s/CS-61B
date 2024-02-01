public class ArrayMap<K, V> {
    private K[] Keys;
    private V[] Values;
    private int size;

    public ArrayMap() {
        Keys = (K[]) new Object[100];
        Values = (V[]) new Object[100];
        size = 0;
    }

    /**
     * 建立 key 和 value 的映射关系
     * 若 key 不在 Keys 中，则将其加入
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        int index = getKeyIndex(key);
        if (index != -1) {
            Values[index] = value;
            return;
        }
        Keys[size] = key;
        Values[size] = value;
        size++;
    }

    public int getKeyIndex(K key) {
        for (int i = 0; i < size; i++) {
            if (Keys[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public boolean containsKey(K key) {
        return !(getKeyIndex(key) == -1);
    }

    public V get(K key) {
        return Values[getKeyIndex(key)];
    }

    public K[] keys() {
        return Keys;
    }

    public int size() {
        return size;
    }
}
