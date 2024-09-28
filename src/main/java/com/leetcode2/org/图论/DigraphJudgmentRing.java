package com.leetcode2.org.图论;

import java.util.*;

public class DigraphJudgmentRing {

    boolean digraphJudgmentRing(Map<Character, List<Character>> map) {
        int visited = 0;
        Map<Character, Integer> indegrees = new HashMap<>();
        for (char c : map.keySet()) {
            indegrees.put(c, 0);
        }
        for (List<Character> value : map.values()) {
            value.forEach(character -> indegrees.put(character, indegrees.getOrDefault(character, 0) + 1));
        }
        Queue<Character> visitedList = new ArrayDeque<>();
        for (char c : indegrees.keySet()) {
            if (indegrees.get(c) == 0) {
                visitedList.offer(c);
            }
        }
        while (!visitedList.isEmpty()) {
            char c = visitedList.poll();
            visited++;
            List<Character> list = map.get(c);
            for (Character character : list) {
                indegrees.put(character, indegrees.getOrDefault(character, 0) - 1);
                if (indegrees.get(character) == 0) {
                    visitedList.offer(character);
                }
            }
        }
        System.out.println(visited);
        return visited == indegrees.size();
    }


    public static void main(String[] args) {
        Map<Character, List<Character>> map = new HashMap<>();
        map.put('A', new ArrayList<>(List.of('B', 'C')));
        map.put('B', new ArrayList<>(List.of('D')));
        map.put('C', new ArrayList<>(List.of('D')));
        map.put('D', new ArrayList<>(List.of('E')));
        map.put('E', new ArrayList<>(List.of('B')));
//        map.put('F', new ArrayList<>());
        DigraphJudgmentRing ring = new DigraphJudgmentRing();
        System.out.println(ring.digraphJudgmentRing(map));
    }
}


//有向图判断环。 通过入度判断