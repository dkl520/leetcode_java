package com.leetcode2.org.图论;
import java.util.*;

public class ShortestSuperstring {

    public String shortestSuperstring(String[] words) {
        int n = words.length;

        // Step 1: Build graph with overlap lengths
        int[][] overlaps = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    overlaps[i][j] = calculateOverlap(words[i], words[j]);
                }
            }
        }

        // Step 2: Traveling Salesman Problem (TSP) with Dynamic Programming (DP)
        // dp[mask][i] represents the shortest superstring ending with words[i] and covers all words in mask
        String[][] dp = new String[1 << n][n];
        int[][] parent = new int[1 << n][n];

        for (int mask = 0; mask < (1 << n); mask++) {
            Arrays.fill(dp[mask], "");
            Arrays.fill(parent[mask], -1);
        }

        for (int mask = 0; mask < (1 << n); mask++) {
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0) continue;
                int prevMask = mask ^ (1 << i);
                if (prevMask == 0) {
                    dp[mask][i] = words[i];
                } else {
                    for (int j = 0; j < n; j++) {
                        if ((prevMask & (1 << j)) != 0) {
                            String candidate = dp[prevMask][j] + words[i].substring(overlaps[j][i]);
                            if (dp[mask][i].isEmpty() || candidate.length() < dp[mask][i].length()) {
                                dp[mask][i] = candidate;
                                parent[mask][i] = j;
                            }
                        }
                    }
                }
            }
        }

        // Step 3: Find the shortest superstring
        int last = 0;
        for (int i = 1; i < n; i++) {
            if (dp[(1 << n) - 1][i].length() < dp[(1 << n) - 1][last].length()) {
                last = i;
            }
        }

        // Step 4: Reconstruct the shortest superstring
        StringBuilder superstring = new StringBuilder(dp[(1 << n) - 1][last]);
        int mask = (1 << n) - 1;
        while (parent[mask][last] != -1) {
            int prev = parent[mask][last];
            superstring.insert(0, dp[mask][last].substring(0, dp[mask][last].length() - overlaps[prev][last]));
            mask ^= (1 << last);
            last = prev;
        }
        superstring.insert(0, words[last]);

        return superstring.toString();
    }

    private int calculateOverlap(String word1, String word2) {
        int maxOverlap = Math.min(word1.length(), word2.length());
        for (int i = maxOverlap; i > 0; i--) {
            if (word1.endsWith(word2.substring(0, i))) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String[] words = {"01", "00", "10", "11"};
        ShortestSuperstring solution = new ShortestSuperstring();
        String shortestSuperstring = solution.shortestSuperstring(words);
        System.out.println("Shortest superstring: " + shortestSuperstring); // Output: "0011001100"
    }
}
