package com.leetcode2.深度优先搜索;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution638 {
    /**
     * 记忆化缓存
     * key: 当前的需求列表
     * value: 满足该需求列表的最小花费
     * 使用Map避免重复计算相同的子问题
     */
    private Map<List<Integer>, Integer> memo = new HashMap<>();

    /**
     * 主入口方法
     * @param price 每件物品的单价列表
     * @param special 大礼包列表，每个大礼包包含物品数量和总价
     * @param needs 需要购买的物品数量列表
     * @return 满足购物清单的最低价格
     */
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return dfs(price, special, needs);
    }

    /**
     * 深度优先搜索方法
     * 使用回溯和记忆化搜索来找到最优解
     * @param price 物品单价列表
     * @param special 大礼包列表
     * @param needs 当前还需要购买的物品数量
     * @return 满足当前needs的最小花费
     */
    private int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        // 1. 检查记忆化缓存，如果已经计算过，直接返回结果
        if (memo.containsKey(needs)) {
            return memo.get(needs);
        }

        // 2. 计算不使用大礼包，只用原价购买的总花费（这是一个基准值）
        int totalCost = 0;
        for (int i = 0; i < needs.size(); i++) {
            totalCost += needs.get(i) * price.get(i);
        }

        // 3. 尝试使用每一个大礼包
        for (List<Integer> pack : special) {
            // 创建一个新的需求列表，用于计算使用当前大礼包后的剩余需求
            List<Integer> remainNeeds = new ArrayList<>(needs);
            boolean canUseSpecial = true;

            // 4. 检查当前大礼包是否可用
            // 遍历每种物品，确保使用大礼包不会超出需求
            for (int i = 0; i < needs.size(); i++) {
                int remain = remainNeeds.get(i) - pack.get(i);
                // 如果某个物品数量会变成负数，说明这个大礼包不能使用
                if (remain < 0) {
                    canUseSpecial = false;
                    break;
                }
                // 更新剩余需求
                remainNeeds.set(i, remain);
            }

            // 5. 如果大礼包可用，递归计算使用这个大礼包后的最小花费
            if (canUseSpecial) {
                // pack.get(pack.size() - 1)是大礼包的价格
                // dfs(price, special, remainNeeds)是剩余需求的最小花费
                totalCost = Math.min(totalCost,
                        pack.get(pack.size() - 1) + dfs(price, special, remainNeeds));
            }
        }

        // 6. 将计算结果存入记忆化缓存
        memo.put(needs, totalCost);
        return totalCost;
    }

    /**
     * 判断大礼包是否值得购买
     * 通过比较大礼包价格和原价购买的价格来判断
     * @param price 物品单价列表
     * @param special 待判断的大礼包
     * @return 是否值得购买
     */
    private boolean isWorthSpecial(List<Integer> price, List<Integer> special) {
        int normalPrice = 0;
        // 计算不使用大礼包时的原价总和
        for (int i = 0; i < price.size(); i++) {
            normalPrice += special.get(i) * price.get(i);
        }
        // 比较大礼包价格和原价
        return special.get(special.size() - 1) < normalPrice;
    }

    /**
     * 优化版本的购物方法
     * 通过预处理过滤掉不值得购买的大礼包，减少搜索空间
     */
    public int shoppingOffersOptimized(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        // 使用Stream API过滤出值得购买的大礼包
        List<List<Integer>> worthSpecials = special.stream()
                .filter(s -> isWorthSpecial(price, s))
                .collect(Collectors.toList());

        return dfs(price, worthSpecials, needs);
    }
}