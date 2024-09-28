//package com.leetcode2.深度优先搜索;
//
//import java.util.*;
//
//public class Solution332_4 {
//    boolean isFinished = false;
//
//    public List<String> findItinerary(List<List<String>> tickets) {
//        List<String> res = new ArrayList<>();
//        Map<String, LinkedList<String>> map = new HashMap<>();
//
//        // Sort the tickets first
//        tickets.sort((a, b) -> a.get(1).compareTo(b.get(1)));
//
//        for (List<String> ticket : tickets) {
//            map.computeIfAbsent(ticket.get(0), k -> new LinkedList<>()).add(ticket.get(1));
//        }
//
//        res.add("JFK");
//        trackBack(res, map, "JFK", tickets.size());
//        return res;
//    }
//
//    void trackBack(List<String> res, Map<String, LinkedList<String>> map, String current, int remainingTickets) {
//        if (remainingTickets == 0) {
//            isFinished = true;
//            return;
//        }
//
//        LinkedList<String> destinations = map.get(current);
//        if (destinations == null || destinations.isEmpty()) return;
//
//        for (int i = 0; i < destinations.size(); i++) {
//            String next = destinations.get(i);
//            res.add(next);
//            destinations.remove(i);
//
//            trackBack(res, map, next, remainingTickets - 1);
//
//            if (isFinished) return;
//
//            destinations.add(i, next);
//            res.remove(res.size() - 1);
//        }
//    }
//
//    public static void main(String[] args) {
//        String[][] ticketsArray = {
//                {"MEL", "PER"},
//                {"SYD", "CBR"},
//                {"AUA", "DRW"},
//                {"JFK", "EZE"},
//                {"PER", "AXA"},
//                {"DRW", "AUA"},
//                {"EZE", "SYD"},
//                {"AUA", "MEL"},
//                {"DRW", "AUA"},
//                {"PER", "ANU"},
//                {"CBR", "EZE"},
//                {"EZE", "PER"},
//                {"MEL", "EZE"},
//                {"EZE", "MEL"},
//                {"EZE", "TBI"},
//                {"ADL", "DRW"},
//                {"ANU", "EZE"},
//                {"AXA", "ADL"}
//        };
//
//        List<List<String>> tickets = Arrays.stream(ticketsArray)
//                .map(Arrays::asList)
//                .toList();
//        Solution332_4 solution = new Solution332_4();
//        System.out.println(solution.findItinerary(tickets));
//    }
//}