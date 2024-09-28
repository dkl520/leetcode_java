package com.leetcode2.org.排序;

import java.util.*;
import java.util.stream.Collectors;

public class CountingSort {
    class Alphabet {
        char c;
        int f;

        public Alphabet(char c, int f) {
            this.c = c;
            this.f = f;
        }
    }


    String sort(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = str.toCharArray();
        List<Alphabet> list = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : chars) {
            if (c >= 'A' && c <= 'z') {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
        }
        for (char c : map.keySet()) {
            Alphabet alphabet = new Alphabet(c, map.get(c));
            list.add(alphabet);
        }
        list.sort((a, b) -> {
            if (a.f == b.f) {
                return a.c - b.c;
            }
            return b.f - a.f;
        });
      for (Alphabet alphabet : list) {
          String cc= String.valueOf(alphabet.c);
          stringBuilder.append( cc.repeat(alphabet.f) );
      }
      return  stringBuilder.toString();
    }

    public static void main(String[] args) {
        String str = "This is a sample string";
        CountingSort countingSort = new CountingSort();
        System.out.println(
                countingSort.sort(str)
        );
    }
}
