package com.leetcode2.org.动态规划;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution486_2 {

    public boolean predictTheWinner(int[] nums) {
        // 将数组转换为 LinkedList
        LinkedList<Integer> list = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));
        List<Integer> playerOne = new ArrayList<>();
        List<Integer> playerTwo = new ArrayList<>();
        int count = 1;
        List<Integer> currentPlayer;
        while (!list.isEmpty()) {
            currentPlayer = count % 2 == 1 ? playerOne : playerTwo;
            int first = list.getFirst();
            int firstNext = list.size() > 1 ? Math.max(list.get(1), list.getLast()) : list.getLast();
            int last = list.getLast();
            int lastNext = list.size() > 1 ? Math.max(first, list.get(list.size() - 2)) : first;
            if ((first - firstNext) >= (last - lastNext)) {
                currentPlayer.add(first);
                list.removeFirst();
            } else {
                currentPlayer.add(last);
                list.removeLast();
            }
            count++;
        }
        int sumOne = playerOne.stream().reduce(0, Integer::sum);
        int sumTwo = playerTwo.stream().reduce(0, Integer::sum);
        return sumOne >= sumTwo;
    }

    public static void main(String[] args) {
        Solution486_2 solution486 = new Solution486_2();
        int[] nums = {3606449, 6, 5, 9, 452429, 7, 9580316, 9857582, 8514433, 9, 6, 6614512, 753594, 5474165, 4, 2697293, 8, 7, 1};
        System.out.println(solution486.predictTheWinner(nums));
    }
}
