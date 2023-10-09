package ru.vsu.cs.OOP2023.elfimov_a_m;

import java.util.*;

public class MyHashMap<K, V> implements Map<K, V> {
    private static class Entry<K, V> implements Map.Entry<K, V> {
        protected K key;
        protected V value;
        protected Entry<K, V> next = null;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V v) {
            V valueSave = this.value;
            this.value = v;
            return valueSave;
        }
    }

    private int entryCount = 0;
    private final int START_HASHTABLE_SIZE = 17;
    private Entry<K, V>[] hashTable;

    public MyHashMap() {
        hashTable = new Entry[START_HASHTABLE_SIZE];
    }

    private final Set<K> keySet = new HashSet<>();
    private final Set<V> valueSet = new HashSet<>();

    private void becomeNewSize() {
        Entry<K, V>[] oldList = hashTable;
        int newSize = hashTable.length * 2 + 1;
        hashTable = new Entry[newSize];
        entryCount = 0;
        for (Entry<K, V> entryKV : oldList) {
            while (entryKV != null) {
                this.put(entryKV.key, entryKV.value);
                entryKV = entryKV.next;
            }
        }

    }

    private void recalculateSets() {
        keySet.clear();
        valueSet.clear();

        Entry<K, V> entryKV;
        for (Entry<K, V> kvEntry : hashTable) {
            entryKV = kvEntry;
            while (entryKV != null) {
                keySet.add(entryKV.key);
                valueSet.add(entryKV.value);

                entryKV = entryKV.next;
            }
        }
    }

    private int getHash(Object key) {
        int hash = key.hashCode();
        return hash % hashTable.length;
    }


    @Override
    public int size() {
        return entryCount;
    }

    @Override
    public boolean isEmpty() {
        return entryCount == 0;
    }

    @Override
    public boolean containsKey(Object o) {
        return keySet.contains(o);
    }

    @Override
    public boolean containsValue(Object o) {
        return valueSet.contains(o);
    }

    @Override
    public V get(Object o) {
        int hash = getHash(o);
        Entry<K, V> entryKV = hashTable[hash];
        while (entryKV != null) {
            if (entryKV.key.equals(o)) return entryKV.value;
            entryKV = entryKV.next;
        }
        // throw new IllegalArgumentException("Don't have key: " + o.toString());
        return null;
    }

    @Override
    public V put(K k, V v) {
        assert k != null && v != null;

        if (entryCount == hashTable.length) becomeNewSize();

        int hash = getHash(k);
        Entry<K, V> entryKV = hashTable[hash];

        keySet.add(k);
        valueSet.add(v);
        // First pair
        if (entryKV == null) {
            hashTable[hash] = new Entry<>(k, v);

            entryCount++;
            return null;
        }
        // Search for pair
        while (true) {
            if (entryKV.key.equals(k)) {
                V old = entryKV.value;
                entryKV.value = v;
                return old;
            }
            if (entryKV.next == null) break;
            entryKV = entryKV.next;
        }
        // pairKV is last now
        // add new pair
        entryKV.next = new Entry<>(k, v);
        entryCount++;
        return null;
    }

    @Override
    public V remove(Object o) {
        int hash = getHash(o);
        Entry<K, V> entryKV = hashTable[hash];
        // null check
        if (entryKV == null) return null;
        // check first pair
        if (entryKV.key.equals(o)) {
            hashTable[hash] = entryKV.next;

            entryCount--;
            recalculateSets();
            return entryKV.value;
        }
        // check for next from pairKV
        while (entryKV.next != null) {
            if (entryKV.next.key.equals(o)) {
                entryKV.next = entryKV.next.next;

                entryCount--;
                recalculateSets();
                return entryKV.next.value;
            }
            entryKV = entryKV.next;
        }
        // else
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (K key : map.keySet()) {
            this.put(key, map.get(key));
        }
    }

    @Override
    public void clear() {
        valueSet.clear();
        keySet.clear();
        entryCount = 0;
        hashTable = new Entry[START_HASHTABLE_SIZE];
    }

    @Override
    public Set<K> keySet() {
        return new HashSet<>(keySet);
    }

    @Override
    public Collection<V> values() {
        return new HashSet<>(valueSet);
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for (int i = 0; i <hashTable.length; i++) {
            Entry<K, V> entryKV = hashTable[i];
            while (entryKV != null) {
                set.add(entryKV);
                entryKV = entryKV.next;
            }
        }
        return set;
    }
}

