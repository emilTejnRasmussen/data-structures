package datastructures.hashing;

import datastructures.interfaces.Map;

public class HashTable<K, V> implements Map<K, V>
{
    private static final int DEFAULT_CAPACITY = 16;

    private Entry<K, V>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTable()
    {
        buckets = (Entry<K, V>[]) new Entry[DEFAULT_CAPACITY];
    }


    @Override
    public void put(K key, V value)
    {

    }

    @Override
    public V get(K key)
    {
        return null;
    }

    @Override
    public V remove(K key)
    {
        return null;
    }

    @Override
    public boolean containsKey(K key)
    {
        return false;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public void clear()
    {

    }

    private int bucketIndex(K key)
    {
        return Math.floorMod(key.hashCode(), buckets.length);
    }

    private static class Entry<K, V>
    {
        private K key;
        private V value;
        private Entry<K, V> next;

        private Entry(K key, V value)
        {
            this.key = key;
            this.value = value;
        }
    }
}