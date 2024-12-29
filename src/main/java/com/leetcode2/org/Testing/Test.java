package com.leetcode2.org.Testing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lists.add(List.of(i, 11));
        }
        List<Integer> set = lists.stream().flatMap(list -> new HashSet<>(list).stream()).toList();
        System.out.println(set);

    }
}
