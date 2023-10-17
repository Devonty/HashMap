package ru.vsu.cs.OOP2023.elfimov_a_m;

import java.util.Map;

public class TestMulriMap {
    public static void main(String[] args) {
        MyHashMultiMap<String, Integer> multimap = new MyHashMultiMap<>();

        multimap.put("nums", 1);
        multimap.put("nums", 2);
        multimap.put("nums", 3);
        multimap.put("nums", 4);
        multimap.put("nums", 5);

        multimap.put("Haha", 1);
        multimap.put("Haha", 2);
        multimap.put("Haha", 3);
        multimap.put("Haha", 4);
        multimap.put("Haha", 5);

        print(multimap);

        System.out.println("multimap.remove(\"Haha\") = " + multimap.remove("Haha"));
        System.out.println("multimap.removeValue(\"nums\", 3) = " + multimap.removeValue("nums", 3));

        print(multimap);

        System.out.println("multimap.removeValue(\"nums\", 1) = " + multimap.removeValue("nums", 1));
        System.out.println("multimap.removeValue(\"nums\", 2) = " + multimap.removeValue("nums", 2));
        System.out.println("multimap.removeValue(\"nums\", 3) = " + multimap.removeValue("nums", 3));
        System.out.println("multimap.removeValue(\"nums\", 4) = " + multimap.removeValue("nums", 4));
        System.out.println("multimap.removeValue(\"nums\", 5) = " + multimap.removeValue("nums", 5));

        print(multimap);

    }

    public static <K, V> void print(MyHashMultiMap<K, V> multimap) {
        System.out.println("-".repeat(20));
        System.out.println("SIZE : " + multimap.size());
        for (K key : multimap.keySet()) {
            System.out.printf("\"%s\" - %s\n", key.toString(), multimap.get(key).toString());
        }
        System.out.println("-".repeat(20));
    }
}
