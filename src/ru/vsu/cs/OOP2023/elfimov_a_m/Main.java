package ru.vsu.cs.OOP2023.elfimov_a_m;

import java.util.Map;


public class Main {
    public static void main(String[] args) {
        Map<String, Integer> map = new MyHashMap<>();
        print(map);

        System.out.println("\nadd--");
        System.out.println("map.put(\"Bottle\", 10): " + map.put("Bottle", 10));
        System.out.println("map.put(\"Pen\", 15): " + map.put("Pen", 15));
        System.out.println("map.put(\"Ball\", 100): " + map.put("Ball", 100));
        System.out.println("map.put(\"Cake\", 23): " + map.put("Cake", 23));
        System.out.println("map.put(\"Cake\", 46): " + map.put("Cake", 46)); // вернет старое значение
        print(map);

        System.out.println("\nremove--");
        System.out.println("map.remove(\"Bottle\"): " + map.remove("Bottle"));
        System.out.println("map.remove(\"Bottle\"): " + map.remove("Bottle")); // вернет null, т.к. ключ уже удален
        print(map);


    }

    public static <K, V> void print(Map<K , V> map) {
        System.out.println("Size: " + map.size());
        for (K key : map.keySet()) {
            System.out.println(key + " - " + map.get(key));
        }
    }
}
