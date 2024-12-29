package com.leetcode2.周赛.zs430;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Q2 {
    public String answerString(String word, int numFriends) {
        String maxStr = "";
        int n = word.length();
        char curMax = 'a';
        if (numFriends==1)return  word;
        for (int i = 0; i < n; i++) {
            if (curMax < word.charAt(i)) {
                curMax = word.charAt(i);
            }
        }
        if (numFriends >= word.length()) {
            maxStr = "" + curMax;
            return maxStr;
        }
        List<Integer> curMaxList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (curMax == word.charAt(i)) {
                curMaxList.add(i);
            }
        }
        int len = n - (numFriends - 1);
        List<String> results = new ArrayList<>();
        for (int index : curMaxList) {
            results.add(word.substring(index, Math.min(index + len, n)));
        }
        results.sort(Comparator.reverseOrder());

        return results.get(0);

    }

    public static void main(String[] args) {
        Q2 q2 = new Q2();
        String word = "bif";
        int numFriends = 2;
        q2.answerString(word, numFriends);
    }

}
