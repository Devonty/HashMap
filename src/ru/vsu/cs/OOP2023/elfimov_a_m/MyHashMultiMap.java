package ru.vsu.cs.OOP2023.elfimov_a_m;

import java.util.*;

public class MyHashMultiMap<K, V> implements Map<K, V> {
    private static class Entry<K, V> {
        protected K key;
        protected List<V> valueList;
        protected Entry<K, V> next = null;

        public Entry(K key, V value) {
            this.key = key;
            this.valueList = new ArrayList<>();
            this.valueList.add(value);
        }

        public K getKey() {
            return key;
        }

        public List<V> getValueList() {
            return valueList;
        }

        public List<V> setValueList(List<V> v) {
            List<V> valueListSave = this.valueList;
            this.valueList = v;
            return valueListSave;
        }
    }

    private int entryCount = 0;
    private final int START_HASHTABLE_SIZE = 17;
    private Entry<K, V>[] hashTable;

    public MyHashMultiMap() {
        hashTable = new MyHashMultiMap.Entry[START_HASHTABLE_SIZE];
    }

    private final Set<K> keySet = new HashSet<>();
    private final Set<V> valueSet = new HashSet<>();

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object o) {
        return false;
    }

    @Override
    public boolean containsValue(Object o) {
        return false;
    }

    @Override
    public V get(Object o) {
        return null;
    }

    @Override
    public V put(K k, V v) {
        return null;
    }

    @Override
    public V remove(Object o) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }
}
