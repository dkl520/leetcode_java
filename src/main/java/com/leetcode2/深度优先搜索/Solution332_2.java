package com.leetcode2.深度优先搜索;

import java.util.*;
import java.util.stream.Collectors;

public class Solution332_2 {

    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (List<String> ticket : tickets) {
            map.computeIfAbsent(ticket.get(0), k -> new ArrayList<>()).add(ticket.get(1));
        }
        int size = tickets.size();
        res.add("JFK");
        List<List<String>> resAll = new ArrayList<>();
        trackBack(res, resAll, map, map.get("JFK"), size);
        resAll.sort(new Comparator<List<String>>() {
            @Override
            public int compare(List<String> list1, List<String> list2) {
                int minLength = Math.min(list1.size(), list2.size());
                for (int i = 0; i < minLength; i++) {
                    int comp = list1.get(i).compareTo(list2.get(i));
                    if (comp != 0) {
                        return comp;
                    }
                }
                return Integer.compare(list1.size(), list2.size());
            }
        });
        return resAll.get(0);

    }

    void trackBack(List<String> res, List<List<String>> resAll, Map<String, List<String>> map, List<String> list, int size) {
        if (size == 0) {
            resAll.add(new ArrayList<>(res));
            return;
        }
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s == null) continue;
            res.add(s);
            list.set(i, null);
            trackBack(res, resAll, map, map.get(s), size - 1);
            list.set(i, s);
            res.remove(res.size() - 1);
        }
    }

    public static void main(String[] args) {
//        String[][] ticketsArray = {
//                {"JFK", "SFO"},
//                {"JFK", "ATL"},
//                {"SFO", "ATL"},
//                {"ATL", "JFK"},
//                {"ATL", "SFO"}
//        };
        String[][] ticketsArray = {
                {"JFK", "KUL"},
                {"JFK", "NRT"},
                {"NRT", "JFK"}
        };


        List<List<String>> tickets = Arrays.stream(ticketsArray)
                .map(Arrays::asList)
                .toList();
        Solution332_2 solution = new Solution332_2();
        System.out.println(solution.findItinerary(tickets));
    }
}
