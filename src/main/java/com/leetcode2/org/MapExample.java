package com.leetcode2.org;

import java.util.HashMap;
import java.util.Map;

public class MapExample {
    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>();
        map.put("Alice", 23);
        map.put("Bob", 11);
        map.put("Charile", 23);
        System.out.println(map);
        Integer age = map.get("Alice");
        System.out.println(age);
        boolean containsKey = map.containsKey("Alice");
        System.out.println(containsKey);
        boolean containsValue = map.containsValue(11);
        System.out.println(containsValue);
        for (Map.Entry<String, Integer> entry : map.entrySet()
        ) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value);
        }
        ;
        for (String key : map.keySet()
        ) {
            Integer value = map.get(key);
            System.out.println("Key: " + key + ", Value: " + value);
        }
        map.forEach((key,value)->{
            System.out.println("key"+key+"value"+value);
        });
        map.remove("Alice");
        System.out.println(map);
//  System.out.println(  map.hashCode());
      Map<String, Integer> map2= new HashMap<>();
        map2.putAll(map);
        System.out.println(map2);
        System.out.println(map2.equals(map));

    }
}
