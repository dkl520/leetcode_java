package com.leetcode2.org.排序;

import java.util.List;
import java.util.Map;
import java.util.*;

public class TopologicalSort {
    Map<Character, List<Character>> maps;
    Set<Character> set = new HashSet<>();
    Stack<Character> stack = new Stack<>();

    Stack<Character> getSorted(Map<Character, List<Character>> map) {

        maps = map;
        for (char c : map.keySet()) {
            if (!set.contains(c)) {

                set.add(c);
                sort(c);
            }
        }
        List<Character> list = new ArrayList<>(stack);
       Collections.reverse(list);
        Stack<Character> reverseStack = new Stack<>();
        reverseStack.addAll(list);
        return reverseStack;

    }

    void sort(Character vertex) {
        List<Character> curList = maps.get(vertex);
        for (Character c : curList) {
            if (!set.contains(c)) {
                set.add(c);
                sort(c);
            }
        }
        stack.push(vertex);
    }


    public static void main(String[] args) {
        Map<Character, List<Character>> map = new HashMap<Character, List<Character>>();
        map.put('A', List.of('B', 'C'));
        map.put('B', List.of('E', 'D'));
        map.put('C', List.of('F'));
        map.put('D', List.of());
        map.put('E', List.of());
        map.put('F', List.of('G'));
        map.put('G', List.of());
        TopologicalSort ts = new TopologicalSort();
        Stack<Character> stack = ts.getSorted(map);
        System.out.println(stack);
    }


}
