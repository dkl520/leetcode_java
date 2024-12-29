package com.leetcode2.org.大厂.barclay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FindSoldierID {
    public static int findSoldierID(String input) {

        String[] lines = input.trim().split("\n");

        int soldierNum = Integer.parseInt(lines[0]);
        String[] actionInfo = lines[1].split(" ");
        int actions = Integer.parseInt(actionInfo[0]);
        int actionsPair = Integer.parseInt(actionInfo[1]);
        List<int[]> positions = new ArrayList<>();
        int target = Integer.parseInt(lines[lines.length - 1]);
        for (int i = 0; i < actions; i++) {
            int[] position = Arrays.stream(lines[i + 2].split(" ")).mapToInt(Integer::parseInt).toArray();
            positions.add(position);
        }

        List<Integer> soldiers = new ArrayList<>();
        for (int i = 0; i < soldierNum; i++) {
            soldiers.add(i + 1);
        }

        for (int[] pos : positions) {
            int left = pos[0];
            int right = pos[1];
            while (left < right) {
                Collections.swap(soldiers, left-1, right-1);
                left++;
                right--;
            }
        }
        return soldiers.get(target-1);
    }

    public static void main(String[] args) {
        String str = """
                10
                2 2
                1 5
                6 10
                1
                """;
        System.out.println( findSoldierID(str));
    }


}
