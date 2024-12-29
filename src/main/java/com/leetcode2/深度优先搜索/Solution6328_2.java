package com.leetcode2.深度优先搜索;

import com.leetcode2.org.大厂.optiver.Solution;

import java.util.*;

public class Solution6328_2 {
    private Map<List<Integer>, Integer> memo = new HashMap<>();

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return dfs(price, special, needs);

    }

    private int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        if (memo.containsKey(needs)) {
            return memo.get(needs);
        }
        int totalCost = 0;
        for (int i = 0; i < needs.size(); i++) {
            totalCost += needs.get(i) * price.get(i);
        }
        for (List<Integer> pack : special) {
            List<Integer> remainNeeds = new ArrayList<>(needs);
            boolean canUseSpecial = true;

            for (int i = 0; i < needs.size(); i++) {
                int remain = remainNeeds.get(i) - pack.get(i);
                if (remain < 0) {
                    canUseSpecial = false;
                    break;
                }
                remainNeeds.set(i, remain);
            }
            if (canUseSpecial) {
                totalCost = Math.min(totalCost, pack.get(pack.size() - 1) + dfs(price, special, remainNeeds));
            }
        }

        memo.put(needs, totalCost);
        return  totalCost;

    }
    public static void main(String[] args) {
        Solution6328_2 solution = new Solution6328_2();

        // 测试用例1：基本场景
        System.out.println("=== 测试用例1：基本场景 ===");
        List<Integer> price1 = Arrays.asList(2, 5);
        List<List<Integer>> special1 = Arrays.asList(
                Arrays.asList(3, 3, 13),  // 3个物品1，0个物品2，价格5
                Arrays.asList(1, 2, 10)  // 1个物品1，2个物品2，价格10
        );
        List<Integer> needs1 = Arrays.asList(3, 2);
        System.out.println("单个物品价格: " + price1);
        System.out.println("大礼包选项: " + special1);
        System.out.println("购物需求: " + needs1);
        System.out.println("最低花费: " + solution.shoppingOffers(price1, special1, needs1));  // 预期输出：14
        System.out.println("购买方案解释: 购买一个大礼包2 (花费10) + 购买2个物品1 (花费4)");

        // 测试用例2：多种物品
        System.out.println("\n=== 测试用例2：多种物品 ===");
        List<Integer> price2 = Arrays.asList(2, 3, 4);
        List<List<Integer>> special2 = Arrays.asList(
                Arrays.asList(1, 1, 0, 4),   // 1个物品1，1个物品2，0个物品3，价格4
                Arrays.asList(2, 2, 1, 9)    // 2个物品1，2个物品2，1个物品3，价格9
        );
        List<Integer> needs2 = Arrays.asList(1, 2, 1);
        System.out.println("单个物品价格: " + price2);
        System.out.println("大礼包选项: " + special2);
        System.out.println("购物需求: " + needs2);
        System.out.println("最低花费: " + solution.shoppingOffers(price2, special2, needs2));  // 预期输出：11
        System.out.println("购买方案解释: 购买一个大礼包1 (花费4) + 购买1个物品2 (花费3) + 购买1个物品3 (花费4)");

        // 测试用例3：不使用大礼包更便宜的情况
        System.out.println("\n=== 测试用例3：不使用大礼包更便宜 ===");
        List<Integer> price3 = Arrays.asList(1, 1);
        List<List<Integer>> special3 = Arrays.asList(
                Arrays.asList(1, 1, 3)    // 1个物品1，1个物品2，价格3
        );
        List<Integer> needs3 = Arrays.asList(1, 1);
        System.out.println("单个物品价格: " + price3);
        System.out.println("大礼包选项: " + special3);
        System.out.println("购物需求: " + needs3);
        System.out.println("最低花费: " + solution.shoppingOffers(price3, special3, needs3));  // 预期输出：2
        System.out.println("购买方案解释: 分别购买1个物品1和1个物品2 (总花费2)，不使用大礼包更便宜");

//        // 测试用例4：使用优化版本
//        System.out.println("\n=== 测试用例4：优化版本测试 ===");
//        List<Integer> price4 = Arrays.asList(2, 3);
//        List<List<Integer>> special4 = Arrays.asList(
//                Arrays.asList(1, 1, 4),    // 值得购买：原价5，特价4
//                Arrays.asList(2, 2, 10)    // 不值得购买：原价10，特价10
//        );
//        List<Integer> needs4 = Arrays.asList(2, 2);
//        System.out.println("单个物品价格: " + price4);
//        System.out.println("大礼包选项: " + special4);
//        System.out.println("购物需求: " + needs4);
//        int normalResult = solution.shoppingOffers(price4, special4, needs4);
//        int optimizedResult = solution.shoppingOffersOptimized(price4, special4, needs4);
//        System.out.println("常规方法最低花费: " + normalResult);
//        System.out.println("优化方法最低花费: " + optimizedResult);
//        System.out.println("两种方法结果是否相同: " + (normalResult == optimizedResult));
    }
}
