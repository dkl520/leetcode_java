package com.leetcode2.org.树状数组;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution2907_2 {

    public int maxProfit(int[] prices, int[] profits) {
        // 获取价格数组的长度
        int n = prices.length;
        // 复制价格数组并对其进行排序
        int[] sortedPrices = Arrays.copyOf(prices, n);
        Arrays.sort(sortedPrices);

        // 使用哈希表记录价格与对应索引的映射，用于离散化处理
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(n);
        int pre = 0, index = 1; // `pre`用于记录前一个价格值，`index`为映射的索引值从1开始
        for (int x : sortedPrices) {
            if (x == pre) continue; // 如果价格重复则跳过
            map.put(x, index++); // 将价格与离散化后的索引存入哈希表
            pre = x; // 更新前一个价格值
        }

        // 初始化一个树状数组（BIT）用于前缀区间查询和更新
        BIT bit1 = new BIT(n);
        int[] left = new int[n]; // `left`数组存储左侧利润最大值
        for (int i = 0; i < n - 1; ++i) {
            index = map.get(prices[i]); // 获取当前价格在离散化后的索引
            left[i] = bit1.query(index - 1); // 查询索引`index-1`之前的最大利润
            bit1.update(index, profits[i]); // 更新当前索引的利润值
        }

        // 初始化另一个树状数组（BIT）用于右侧区间查询和更新
        BIT bit2 = new BIT(n);
        int[] right = new int[n]; // `right`数组存储右侧利润最大值
        for (int i = n - 1; i >= 1; --i) {
            index = map.get(prices[i]); // 获取当前价格在离散化后的索引
            index = n - index + 1; // 进行索引翻转，用于从右向左处理
            right[i] = bit2.query(index - 1); // 查询索引`index-1`之前的最大利润
            bit2.update(index, profits[i]); // 更新当前索引的利润值
        }

        // 遍历数组，寻找满足条件的最大利润值
        int ans = -1; // 初始化为-1表示不存在满足条件的情况
        for (int i = 1; i < n - 1; ++i) {
            if (left[i] == 0 || right[i] == 0) continue; // 如果左或右利润为0，跳过
            ans = Math.max(ans, left[i] + profits[i] + right[i]); // 更新最大利润值
        }

        return ans; // 返回结果
    }

    // 定义树状数组类（Binary Indexed Tree, BIT）
    class BIT {
        int[] tree; // 树状数组
        int n; // 树状数组的大小

        // 构造函数，初始化树状数组
        public BIT(int n) {
            this.n = n;
            tree = new int[n + 1]; // 树状数组索引从1开始
        }

        // 计算低位比特（lowbit）
        public int lowbit(int index) {
            return index & (-index); // 获取最低位1的位置
        }

        // 更新树状数组
        public void update(int index, int v) {
            while (index <= n) {
                tree[index] = Math.max(tree[index], v); // 更新当前索引的最大值
                index += lowbit(index); // 跳转到下一个影响范围的索引
            }
        }

        // 查询树状数组
        public int query(int index) {
            int result = 0; // 初始化结果为0
            while (index > 0) {
                result = Math.max(tree[index], result); // 查询最大值
                index -= lowbit(index); // 跳转到影响范围的上一个索引
            }
            return result; // 返回查询结果
        }
    }
}
