package com.leetcode2.周赛.zs430;

public class Q3 {
    public long numberOfSubsequences(int[] nums) {
        int n = nums.length;
        int p, q, r, s;
        long count = 0;
        for (int i = 0; i < n; i++) {
            p = nums[i];
            for (int j = i + 2; j < n; j++) {
                q = nums[j];
                for (int k = j + 2; k < n; k++) {
                    r = nums[k];
                    for (int l = k + 2; l < n; l++) {
                        s = nums[l];
                        if (p * r == q * s) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Q3 q3 = new Q3();
        int[] nums = {3, 4, 3, 4, 3, 4, 3, 4};

        q3.numberOfSubsequences(nums);
    }
}
