package com.leetcode2.org.数组;

public class Solution2383 {
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        if (experience.length == 1) {
            return Math.max(experience[0] + 1 - initialExperience, 0) + Math.max(energy[0] + 1 - initialEnergy, 0);
        }
        int energySum = 0;
        for (int j : energy) {
            energySum += j;

        }
        int count = 0;
        count += Math.max(energySum + 1 - initialEnergy, 0);

        int exP = Integer.MIN_VALUE;
        for (int i = experience.length - 1; i > 0; i--) {
            int curE = experience[i];
            int pre = experience[i - 1];
            if (pre < curE) {
                experience[i - 1] = Math.max(Math.max(curE  - pre, pre + 1), exP);
            }

        }

        count += Math.max(experience[0]+1 - initialExperience, 0);
        return count;
    }

    public static void main(String[] args) {
        int initialEnergy = 1;
        int initialExperience = 1;
        int[] energy = new int[]{1, 1, 1, 1};
        int[] experience = new int[]{1, 1, 1, 50};
        Solution2383 solution = new Solution2383();
        solution.minNumberOfHours(initialEnergy, initialExperience, energy, experience);
    }
}
