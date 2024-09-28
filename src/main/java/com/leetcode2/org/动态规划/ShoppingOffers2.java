package com.leetcode2.org.动态规划;



import java.util.*;
import java.util.stream.Collectors;

public class ShoppingOffers2 {
    // 主函数，用于计算购物优惠后的最小花费
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        // 使用递归深度优先搜索（DFS）来寻找最小花费，同时使用记忆化来避免重复计算
        return dfs(price, special, needs, new HashMap<>());
    }

    // 递归深度优先搜索函数
    private int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs, Map<List<Integer>, Integer> memo) {
        // 如果当前需求已经在记忆化存储中存在，则直接返回存储的结果
        if (memo.containsKey(needs)) {
            return memo.get(needs);
        }

        int n = price.size(); // 商品的种类数量
        int minPrice = 0; // 最小花费的初始值

        // 计算不使用任何优惠时的花费
        for (int i = 0; i < n; i++) {
            minPrice += needs.get(i) * price.get(i); // 根据每种商品的需求量和单价计算总花费
        }

        // 遍历所有优惠
        for (List<Integer> offer : special) {
            List<Integer> newNeeds = new ArrayList<>(n); // 创建一个新的需求列表，用于存放尝试优惠后的需求
            boolean valid = true; // 标记优惠是否适用

            for (int i = 0; i < n; i++) {
                // 计算尝试优惠后的新需求
                newNeeds.add(needs.get(i) - offer.get(i));
                // 如果优惠后的某种商品需求量为负，则此优惠不适用
                if (newNeeds.get(i) < 0) {
                    valid = false;
                    break;
                }
            }
            // 如果优惠适用
            if (valid) {
                // 递归计算使用此优惠后的最小花费，并加上此优惠的额外费用（如果有）
                int costWithOffer = offer.get(n) + dfs(price, special, newNeeds, memo);
                // 更新最小花费
                minPrice = Math.min(minPrice, costWithOffer);
            }
        }

        // 将当前需求及其对应的最小花费存入记忆化存储
        memo.put(needs, minPrice);
        // 返回计算得到的最小花费
        return minPrice;
    }

    public static void main(String[] args) {
        List<Integer> price = new ArrayList<>(List.of(3, 2));
        int[][] special = {{3, 2, 10}};
        int[] needs = {3, 1};
        ShoppingOffers shoppingOffers = new ShoppingOffers();
        int result = shoppingOffers.shoppingOffers(price, Arrays.stream(special)
                .map(array -> Arrays.stream(array)
                        .boxed()
                        .collect(Collectors.toList()))
                .collect(Collectors.toList()), List.of(3, 1));
        System.out.println(result); // Output should be 10
    }
}
