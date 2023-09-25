package ru.vsu.cs.OOP2023.elfimov_a_m;

import java.util.Map;


public class Main {
    public static void main(String[] args) {
        Map<String, Integer> map = new MyHashMap<>();
        print(map);

        System.out.println("\nadd--");
        map.put("Bottle", 10);
        map.put("Pen", 15);
        map.put("Ball", 100);
        map.put("Cake", 23);
        print(map);

        System.out.println("\nremove--");
        map.remove("Bottle");
        map.remove("Bottle"); // will be ignored
        print(map);


    }

    public static void print(Map<String , Integer> map) {
        System.out.println("Size: " + map.size());
        for (Object key : map.keySet()) {
            System.out.println(key + " - " + map.get(key));
        }
    }
}
