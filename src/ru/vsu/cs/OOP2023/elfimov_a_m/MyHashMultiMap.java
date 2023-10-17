package ru.vsu.cs.OOP2023.elfimov_a_m;

import java.security.Key;
import java.util.*;

public class MyHashMultiMap<K, V> {
    private MyHashMap<K, LinkedList<V>> map;

    public MyHashMultiMap() {
        map = new MyHashMap<>();
    }

    public MyHashMultiMap(int initSize) {
        map = new MyHashMap<>(initSize);
    }


    public int size() {
        return map.size();
    }


    public boolean isEmpty() {
        return map.isEmpty();
    }


    public boolean containsKey(Object o) {
        return map.containsKey(o);
    }


    public boolean containsValue(Object o) {
        return false;
    }

    public List<V> get(Object key) {
        return map.get(key);
    }

    public List<V> getOrDefault(Object key, LinkedList<V> defaultValue) {
        return map.getOrDefault(key, defaultValue);
    }

    public void put(K key, V value) {
        LinkedList<V> values = map.get(key);
        if(values == null) values = new LinkedList<>();

        values.add(value);
        map.put(key, values);
    }

    public List<V> remove(Object key) {
        return map.remove(key);
    }

    public boolean removeValue(Object key, Object value) {
        boolean toReturn =  map.get(key).remove(value);
        if(map.get(key).isEmpty()) map.remove(key);
        return toReturn;
    }


    public void clear() {
        map.clear();
    }

    public Set<K> keySet() {
        return map.keySet();
    }

    public Collection<LinkedList<V>> values() {
        return map.values();
    }

    public Set<Map.Entry<K, LinkedList<V>>> entrySet() {
        return map.entrySet();
    }
}
