package com.leetcode2.org.数组;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Solution3397 {
    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
    //此题涉及就是排队问题，。每个单个数值 的取值范围 是【num-k,num+k】
//   为了取得最多的数值。。需要尽可能的把数值分散开来。。从小到大的排序后。。因为后面的数值比较大，为了跟后面的数值不发生冲突，需要
//把前面的数值尽可能取最小。。但是也可能发生踩踏。 前面取最小数值的时候，可能会影响道后面的取值范围。即  前面取值是 last ..
// 那么后面的取值 是  【num-k,num+k】...尽可能取最小值 num-k ...前面的数值是last..如果last 小于 num-k 那就是 取值num-k；
//        如果 last大于num-k 那么就取值 last +1; 前提是  last< num+k; 因为如果前面的数值 已经覆盖了【num-k,num+k】 那么当前就无法取值了。。
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        for (int num : nums) {
            if (dq.isEmpty()) {
                dq.offer(num - k);
            } else {
                int last = dq.getLast();
                if (last + 1 <= num + k) {
                    dq.offer(Math.max(last + 1, num - k));
                }
            }
        }
        return  dq.size();
    }


}
