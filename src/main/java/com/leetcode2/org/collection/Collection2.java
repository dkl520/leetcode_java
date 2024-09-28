package com.leetcode2.org.collection;

import java.util.*;

public class Collection2 {
    public static void main(String[] args) {
        String[] num = {"A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] color = {"方块♦", "梅花♣", "红桃♥", "黑桃♠"};
        ArrayList poker = new ArrayList();
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < color.length; j++) {
                poker.add(color[j] + num[i]);
            }
        }
        System.out.println(poker);

        HashMap<String, Integer> map = new HashMap<>();
        map.put("tom", 56);
        map.put("tom2", 526);
        map.put("tom3", 16);
        map.put("tom4", 346);


//        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
//        Iterator<Map.Entry<String, Integer>> iterator = entrySet.iterator();
        var entrySet = map.entrySet();
        var iterator= entrySet.iterator();
        while (iterator.hasNext()) {
//            iterator.next();
            Map.Entry<String, Integer> entry= iterator.next();
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key+"--->"+value+"iterator");
        }


    }
}
