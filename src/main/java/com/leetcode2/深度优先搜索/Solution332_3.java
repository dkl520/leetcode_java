package com.leetcode2.深度优先搜索;

import java.util.*;
import java.util.stream.Collectors;

public class Solution332_3 {
    boolean isFinished = false;

    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (List<String> ticket : tickets) {
            map.computeIfAbsent(ticket.get(0), k -> new ArrayList<>()).add(ticket.get(1));
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String from = entry.getKey();
            List<String> to = entry.getValue();
            to.sort(String::compareTo);
        }
        int size = tickets.size();
        res.add("JFK");
        trackBack(res, map, map.get("JFK"), size);
        return res;
    }

    void trackBack(List<String> res, Map<String, List<String>> map, List<String> list, int size) {
        if (size == 0) {
            isFinished = true;
            return;
        }
        if (list == null) return;
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s == null) continue;
            res.add(s);
            list.set(i, null);
            trackBack(res, map, map.get(s), size - 1);
            if (isFinished) return;
            list.set(i, s);
            res.remove(res.size() - 1);
        }
        return;
    }

    public static void main(String[] args) {
//        String[][] ticketsArray = {
//                {"JFK", "SFO"},
//                {"JFK", "ATL"},
//                {"SFO", "ATL"},
//                {"ATL", "JFK"},
//                {"ATL", "SFO"}
//        };
//        String[][] ticketsArray = {
//                {"JFK", "KUL"},
//                {"JFK", "NRT"},
//                {"NRT", "JFK"}
//        };
        String[][] ticketsArray  = {
                {"JFK", "SFO"},
                {"JFK", "ATL"},
                {"SFO", "JFK"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"}
        };


        List<List<String>> tickets = Arrays.stream(ticketsArray)
                .map(Arrays::asList)
                .toList();
        Solution332_3 solution = new Solution332_3();
        System.out.println(solution.findItinerary(tickets));
    }
}
