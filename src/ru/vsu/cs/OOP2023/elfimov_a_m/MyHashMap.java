package ru.vsu.cs.OOP2023.elfimov_a_m;

import java.util.*;

public class MyHashMap<K, V> implements Map<K, V> {
    private static class Entry<K, V> implements Map.Entry<K, V> {
        protected K key;
        protected V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Entry(Entry<K, V> entryKV) {
            this.key = entryKV.key;
            this.value = entryKV.value;
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

        @Override
        public boolean equals(Object obj) {
            try {
                Entry<K,V> entryKV = (Entry<K, V>) obj;
                return entryKV.value == this.value && entryKV.key == this.key;
            } catch (ClassCastException e) {
                return false;
            }
        }
    }

    private int entryCount = 0;
    private final int START_HASHTABLE_SIZE = 17;
    private List<Entry<K, V>>[] hashTable;

    public MyHashMap() {
        initHashTable(START_HASHTABLE_SIZE);
    }
    public MyHashMap(int initSize) {
        initHashTable(initSize);
    }

    private void initHashTable(int size){
        hashTable = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            hashTable[i] = new LinkedList<Entry<K,V>>();
        }
    }
    private void becomeNewSize() {
        List<Entry<K, V>>[] oldList = hashTable;

        int newSize = hashTable.length * 2 + 1;
        initHashTable(newSize);

        entryCount = 0;

        for (Map.Entry<K, V> entryKV : this.entrySet()) {
            this.put(entryKV.getKey(), entryKV.getValue());
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
        return false;
    }

    @Override
    public boolean containsValue(Object o) {
        return false;
    }

    private Entry<K, V> getNode(Object key) {
        for (Entry<K, V> entryKV : hashTable[getHash(key)])
            if (entryKV.key.equals(key)) return entryKV;
        return null;
    }

    @Override
    public V get(Object key) {
        Entry<K, V> e;
        return (e = this.getNode(key)) == null ? null : e.value;
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        Entry<K, V> e;
        return (e = this.getNode(key)) == null ? defaultValue : e.value;
    }

    @Override
    public V put(K k, V v) {
        if (entryCount == hashTable.length) becomeNewSize();
        Entry<K, V> entryKV = getNode(k);

        if (entryKV == null) {
            entryCount++;
            hashTable[getHash(k)].add(new Entry<>(k, v));
            return null;
        }

        V oldValue = entryKV.value;
        entryKV.value = v;
        return oldValue;
    }

    @Override
    public V remove(Object o) {
        Entry<K, V> entryKV = getNode(o);
        if(entryKV == null) return null;
        
        entryCount--;
        hashTable[getHash(o)].remove(entryKV);
        return entryKV.value;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (K key : map.keySet()) {
            this.put(key, map.get(key));
        }
    }

    @Override
    public void clear() {
        entryCount = 0;
        initHashTable(START_HASHTABLE_SIZE);
    }

    @Override
    public Set<K> keySet() {
        Set<K> toReturn = new HashSet<>(entryCount);

        for (List<Entry<K, V>> entries : hashTable)
            for (Entry<K, V> entryKV : entries)
                toReturn.add(entryKV.key);

        return toReturn;
    }

    @Override
    public Collection<V> values() {
        List<V> toReturn = new ArrayList<>(entryCount);

        for (List<Entry<K, V>> entries : hashTable)
            for (Entry<K, V> entryKV : entries)
                toReturn.add(entryKV.value);

        return toReturn;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> toReturn = new HashSet<>(entryCount);

        for (List<Entry<K, V>> entries : hashTable)
            for (Entry<K, V> entryKV : entries)
                toReturn.add((Map.Entry<K, V>)entryKV);
        return toReturn;
    }
}

