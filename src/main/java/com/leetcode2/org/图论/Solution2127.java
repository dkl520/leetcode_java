package com.leetcode2.org.图论;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution2127 {
    public int maximumInvitations(int[] favorite) {
        Set<Integer> choosedNum = new HashSet<>();
        DFS(choosedNum, favorite, 0);
        return choosedNum.size();
    }

    void DFS(Set<Integer> choosedNum, int[] favorite, int start) {
        if (choosedNum.contains(favorite[start])) return;
        choosedNum.add(favorite[start]);

        DFS(choosedNum, favorite, favorite[start]);

    }

    public static void main(String[] args) {

        int[] favorite = {3, 0, 1, 4, 1};
        Solution2127 solution2127 = new Solution2127();
        System.out.println(solution2127.maximumInvitations(favorite));
    }

}
