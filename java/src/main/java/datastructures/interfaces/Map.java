package datastructures.interfaces;

public interface Map<K, V>
{
    void put(K key, V value);

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);

    int size();

    default boolean isEmpty()
    {
        return size() == 0;
    }

    void clear();
}