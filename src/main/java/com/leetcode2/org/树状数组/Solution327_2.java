package com.leetcode2.org.树状数组;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Solution327_2 {
    /**
     * 计算给定范围内的数组的和的数量。
     *
     * @param nums  输入数组
     * @param lower 范围下限
     * @param upper 范围上限
     * @return 给定范围内的和的总数量
     */


    //所谓的区间和 就是 S【i，j】 = Psum[j+1] - Psum[i]  //Psum 为前缀和。
    //[lower upper ] 之间的区间和即    lower<= Psum[j+1] - Psum[i] <= upper
    // 数学推导出 ：：    Psum[i] ===》  Psum[j+1] - upper       <= Psum[i] <= Psum[j+1] - lower
    // 即 通过  Psum[j+1]  求   Psum[i]  的个数。。这里有个问题，为什么是 通过   Psum[j+1] 求    Psum[i]  而不是相反呢。。
    // 因为通过 Psum[i]   无法求 Psum[j+1] 。。。在往树状数组里添加的时候 先添加Psum[i] 。。然后在未来才添加 Psum[j+1]。。无法通过现在的值求未来的值。
    public int countRangeSum(int[] nums, int lower, int upper) {
        long sum = 0;
        // 前置和数组，用于计算任意段的和
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            preSum[i + 1] = sum;
        }

        // 使用树集收集所有需要进行运算的值（含自己和范围跟值）
        Set<Long> allNumbers = new TreeSet<Long>();
        for (long x : preSum) {
            allNumbers.add(x);
            allNumbers.add(x - lower);
            allNumbers.add(x - upper);
        }

        // 利用哈希表实现值的离散化，将值对应为一个索引
        Map<Long, Integer> values = new HashMap<Long, Integer>();
        int idx = 0;
        for (long x : allNumbers) {
            values.put(x, idx);
            idx++;
        }

        int ret = 0;
        // 创建树状数素对象
        BIT bit = new BIT(values.size());
        for (int i = 0; i < preSum.length; i++) {
            // 根据离散化值获得左与右范围对应的索引
            int left = values.get(preSum[i] - upper), right = values.get(preSum[i] - lower);
            // 查询树状数素上在该范围内的素存值总和
            ret += bit.query(right + 1) - bit.query(left);  //统计的是left 到right之间的和
            // 更新当前值到树状数素
            bit.update(values.get(preSum[i]) + 1, 1);
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 5, -1};
        int lower = -2, upper = 2;
        Solution327_2 solution = new Solution327_2();
        System.out.println(solution.countRangeSum(nums, lower, upper));
    }
}

/**
 * 树状数素（Binary Indexed Tree）实现类
 */
class BIT {
    int[] tree;
    int n;

    /**
     * 构造方法，初始化树状数素
     *
     * @param n 树状数素大小
     */
    public BIT(int n) {
        this.n = n;
        this.tree = new int[n + 1];
    }

    /**
     * 返回最低一个有效位，用于计算根节点
     *
     * @param x 输入值
     * @return 最低有效位
     */
    public static int lowbit(int x) {
        return x & (-x);
    }

    /**
     * 更新值，将当前值添加到一定节点上
     *
     * @param x 当前索引
     * @param d 添加的值
     */
    public void update(int x, int d) {
        while (x <= n) {
            tree[x] += d;
            x += lowbit(x);
        }
    }

    /**
     * 查询索引前的值总和
     *
     * @param x 索引值
     * @return 总和
     */
    public int query(int x) {
        int ans = 0;
        while (x != 0) {
            ans += tree[x];
            x -= lowbit(x);
        }
        return ans;
    }
}
