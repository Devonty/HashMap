package ru.vsu.cs.OOP2023.elfimov_a_m;

import java.util.*;

public class MyHashMap<K, V> implements Map<K, V> {
    private static class Pair<K, V> {
        K key;
        V value;
        Pair<K, V> next = null;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int pairCount = 0;
    private final int startSizeHashTable = 17;
    private List<Pair<K, V>> hashTable;

    public MyHashMap() {
        hashTable = new ArrayList<>();
        for (int i = 0; i < startSizeHashTable; i++) {
            hashTable.add(null);
        }
    }

    private final Set<K> keySet = new HashSet<>();
    private final Set<V> valueSet = new HashSet<>();
    private void becomeNewSize() {
        List<Pair<K, V>> oldList = hashTable;
        int newSize = hashTable.size() * 2 + 1;
        hashTable =  new ArrayList<>(newSize);
        pairCount = 0;

        for (int i = 0; i < newSize; i++) {
            hashTable.add(null);
        }
        for (Pair<K, V> pairKV : oldList) {
            while (pairKV != null) {
                this.put(pairKV.key, pairKV.value);
                pairKV = pairKV.next;
            }
        }

    }

    private void recalculateSets() {
        keySet.clear();
        valueSet.clear();

        Pair<K, V> pairKV;
        for (Pair<K, V> kvPair : hashTable) {
            pairKV = kvPair;
            while (pairKV != null) {
                keySet.add(pairKV.key);
                valueSet.add(pairKV.value);

                pairKV = pairKV.next;
            }
        }
    }

    private int getHash(Object key) {
        int hash = key.hashCode();
        return hash % hashTable.size();
    }


    @Override
    public int size() {
        return pairCount;
    }

    @Override
    public boolean isEmpty() {
        return pairCount == 0;
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
        Pair<K, V> pairKV = hashTable.get(hash);
        while (pairKV != null) {
            if (pairKV.key.equals(o)) return pairKV.value;
            pairKV = pairKV.next;
        }
        // throw new IllegalArgumentException("Don't have key: " + o.toString());
        return null;
    }

    @Override
    public V put(K k, V v) {
        assert k != null && v != null;

        if(pairCount == hashTable.size()) becomeNewSize();

        int hash = getHash(k);
        Pair<K, V> pairKV = hashTable.get(hash);

        keySet.add(k);
        valueSet.add(v);
        // First pair
        if (pairKV == null) {
            hashTable.set(hash, new Pair<>(k, v));

            pairCount++;
            return null;
        }
        // Search for pair
        while (true) {
            if (pairKV.key.equals(k)) {
                V old = pairKV.value;
                pairKV.value = v;
                return old;
            }
            if (pairKV.next == null) break;
            pairKV = pairKV.next;
        }
        // pairKV is last now
        // add new pair
        pairKV.next = new Pair<>(k, v);
        pairCount++;
        return null;
    }

    @Override
    public V remove(Object o) {
        int hash = getHash(o);
        Pair<K, V> pairKV = hashTable.get(hash);
        // null check
        if (pairKV == null) return null;
        // check first pair
        if (pairKV.key.equals(o)) {
            hashTable.set(hash, pairKV.next);

            pairCount--;
            recalculateSets();
            return pairKV.value;
        }
        // check for next from pairKV
        while (pairKV.next != null) {
            if (pairKV.next.key.equals(o)) {
                pairKV.next = pairKV.next.next;

                pairCount--;
                recalculateSets();
                return pairKV.next.value;
            }
            pairKV = pairKV.next;
        }
        // else
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {

    }

    @Override
    public void clear() {
        valueSet.clear();
        keySet.clear();
        pairCount = 0;
        hashTable = new ArrayList<>(startSizeHashTable);
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
    public Set<Entry<K, V>> entrySet() {
        return new HashSet<>();
    }
}
