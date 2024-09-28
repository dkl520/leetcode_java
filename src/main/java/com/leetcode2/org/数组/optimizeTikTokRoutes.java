package com.leetcode2.org.数组;

import java.util.*;

public class optimizeTikTokRoutes {


    public static long optimizeTikTokRoutes2(int numServers, List<List<Integer>> disconnectedPairs) {

        disconnectedPairs.sort((a, b) -> a.get(0).compareTo(b.get(0)));

        

        List<String> strsConnectedPairs = new ArrayList<>();
        for (int i = 0; i < disconnectedPairs.size(); i++) {
            String str = disconnectedPairs.get(i).toString();
            strsConnectedPairs.add(str);
        }
        long result = 0;
        for (int i = 1; i <= numServers; i++) {
            result++;
            if (i != numServers) {
                List<Integer> list = List.of(i, i + 1);
                String str = list.toString();
                if (!strsConnectedPairs.contains(str)) {
                    result++;
                }
            }
        }
        return result;

    }

    public static void main(String[] args) {
        int numServers = 4;
        List<List<Integer>> disconnectedPairs = new ArrayList<>();
        disconnectedPairs.add(Arrays.asList(1, 2));
        disconnectedPairs.add(Arrays.asList(2, 3));
        System.out.println(
                optimizeTikTokRoutes2(numServers, disconnectedPairs)
        );
    }


}
