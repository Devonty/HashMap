package ru.vsu.cs.OOP2023.elfimov_a_m;

import java.security.Key;
import java.util.*;

public class MyHashMultiMap<K, V> {
    private MyHashMap<K, LinkedList<V>> map;
    private int size = 0;

    public MyHashMultiMap() {
        map = new MyHashMap<>();
    }

    public MyHashMultiMap(int initSize) {
        map = new MyHashMap<>(initSize);
    }


    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public boolean containsKey(Object o) {
        return map.containsKey(o);
    }


    public boolean containsValue(Object o) {
        for(K key : map.keySet()){
            if(map.get(key).contains(o)) return true;
        }
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

        size++;
        values.add(value);
        map.put(key, values);
    }

    public List<V> remove(Object key) {
        List<V> values;
        size -= (values = map.get(key)) == null ? 0 : values.size();
        return map.remove(key);
    }


    public boolean removeValue(Object key, Object value) {
        List<V> values = map.get(key);
        boolean toReturn = values != null && values.remove(value);
        if(map.get(key).isEmpty()) map.remove(key);
        if(toReturn) size--;
        return toReturn;
    }


    public void clear() {
        map.clear();
        size = 0;
    }

    public Set<K> keySet() {
        return map.keySet();
    }

    public Collection<LinkedList<V>> valueLists() {
        return map.values();
    }
    public Collection<V> values(){
        Collection<V> col = new ArrayList<>(size);
        for(K key : map.keySet()){
            col.addAll(map.get(key));
        }
        return col;
    }

    public Set<Map.Entry<K, LinkedList<V>>> entrySet() {
        return map.entrySet();
    }
}
