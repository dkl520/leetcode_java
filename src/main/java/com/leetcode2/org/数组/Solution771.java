package com.leetcode2.org.数组;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution771 {
    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> set = jewels.chars()  // Convert the string to an IntStream of char values
                .mapToObj(c -> (char) c)  // Map each int value to a Character
                .collect(Collectors.toSet());  // Collect the characters into a Set

        return (int) stones.chars()  // Use chars() to get an IntStream of character codes
                .filter(c -> set.contains((char) c))  // Filter by checking presence in the set
                .count();  // Count the number of matching jewels
    }

    public static void main(String[] args) {
        Solution771 solution771 = new Solution771();
        System.out.println(solution771.numJewelsInStones("aA", "aAAbbbb"));
    }
}
