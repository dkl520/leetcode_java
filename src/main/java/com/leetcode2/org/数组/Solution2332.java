package com.leetcode2.org.数组;

import java.util.Arrays;

public class Solution2332 {
    /**
     * 计算乘客可以搭上最后一班公交车的最晚时间。
     *
     * @param buses      公交车的出发时间数组
     * @param passengers 乘客的到达时间数组
     * @param capacity   每辆公交车的最大载客量
     * @return 最晚的乘车时间
     */
    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        // 将公交车和乘客的时间排序，确保按时间顺序处理
        Arrays.sort(buses);
        Arrays.sort(passengers);

        int pos = 0;  // 乘客的位置索引，指向当前未上车的乘客
        int space = 0;  // 每辆车的剩余座位数

        // 遍历每一辆公交车的出发时间
        for (int arrive : buses) {
            space = capacity;  // 初始化每辆车的座位容量

            // 只要座位还有空位，并且当前乘客的到达时间不晚于公交车的出发时间，就让乘客上车
            while (space > 0 && pos < passengers.length && passengers[pos] <= arrive) {
                space--;  // 剩余空位减一
                pos++;    // 当前乘客上车，指向下一个乘客
            }
        }

        // 回退一个乘客的位置，指向最后一个可能上车的乘客
        pos--;
        // 如果最后一辆车还有空位，则最后的乘车时间可以是公交车出发的时间，否则就是最后一个上车的乘客的时间
        int lastCatchTime = space > 0 ? buses[buses.length - 1] : passengers[pos];

        // 如果乘客到达的时间恰好是最后乘车的时间，继续往前寻找最晚可乘车的时间
        while (pos >= 0 && passengers[pos] == lastCatchTime) {
            pos--;  // 回退到前一个乘客
            lastCatchTime--;  // 时间回退到前一个可用的时间
        }

        return lastCatchTime;  // 返回最晚的可乘车时间
    }

    public static void main(String[] args) {
        int[] buses = {10, 20};
        int[] passengers = {2, 17, 18, 19};
        int capacity = 2;
        Solution2332 solution = new Solution2332();

        System.out.println(
                solution.latestTimeCatchTheBus(buses, passengers, capacity)
        );
    }
}
