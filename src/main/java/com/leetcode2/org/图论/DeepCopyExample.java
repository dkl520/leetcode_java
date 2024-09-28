package com.leetcode2.org.图论;

import java.io.*;
import java.util.*;

public class DeepCopyExample {

    public static <T> T deepCopy(T object) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            oos.flush();
            oos.close();

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            @SuppressWarnings("unchecked")
            T copy = (T) ois.readObject();
            ois.close();
            return copy;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        List<Map<String, Object>> original = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("key1", "value1");
        map1.put("key2", 123);
        original.add(map1);

        List<Map<String, Object>> copy = deepCopy(original);

        // 验证深拷贝
        System.out.println("Original: " + original);
        System.out.println("Copy: " + copy);
        System.out.println("Is same object: " + (original == copy));
        System.out.println("Is first map same object: " + (original.get(0) == copy.get(0)));
    }
}