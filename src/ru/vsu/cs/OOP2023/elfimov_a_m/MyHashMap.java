package ru.vsu.cs.OOP2023.elfimov_a_m;

import java.util.*;

public class MyHashMap<K, V> implements Map<K, V> {
    private static class Pair<Kp, Vp> {
        Kp key;
        Vp value;
        Pair<Kp, Vp> next = null;

        public Pair(Kp key, Vp value) {
            this.key = key;
            this.value = value;
        }
    }

    private int pairCount = 0;
    private final int START_HASHTABLE_SIZE = 17;
    private Pair<K, V>[] hashTable;

    public MyHashMap() {

        hashTable = new Pair[START_HASHTABLE_SIZE];
    }

    private final Set<K> keySet = new HashSet<>();
    private final Set<V> valueSet = new HashSet<>();
    private void becomeNewSize() {
        Pair<K, V>[] oldList = hashTable;
        int newSize = hashTable.length * 2 + 1;
        hashTable =  new Pair[newSize];
        pairCount = 0;
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
        return hash % hashTable.length;
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
        Pair<K, V> pairKV = hashTable[hash];
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

        if(pairCount == hashTable.length) becomeNewSize();

        int hash = getHash(k);
        Pair<K, V> pairKV = hashTable[hash];

        keySet.add(k);
        valueSet.add(v);
        // First pair
        if (pairKV == null) {
            hashTable[hash] = new Pair<>(k, v);

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
        Pair<K, V> pairKV = hashTable[hash];
        // null check
        if (pairKV == null) return null;
        // check first pair
        if (pairKV.key.equals(o)) {
            hashTable[hash] = pairKV.next;

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
        hashTable = new Pair[START_HASHTABLE_SIZE];
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