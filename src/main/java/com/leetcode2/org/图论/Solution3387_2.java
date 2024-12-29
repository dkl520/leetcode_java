package com.leetcode2.org.图论;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution3387_2 {
    /**
     * 计算初始货币能通过两组货币交换对及其汇率转换所能获得的最大金额
     *
     * @param initialCurrency 初始货币的名称 (例如："EUR")
     * @param pairs1          第一组货币交换对，例如：[["EUR", "USD"], ["USD", "JPY"]]
     * @param rates1          第一组货币交换对的对应汇率，例如：[2.0, 3.0]
     * @param pairs2          第二组货币交换对，例如：[["JPY", "USD"], ["USD", "CHF"], ["CHF", "EUR"]]
     * @param rates2          第二组货币交换对的对应汇率，例如：[0.5, 1.5, 0.8]
     * @return 返回经过汇率转换后，初始货币所能获得的最大金额
     */
    public double maxAmount(String initialCurrency, List<List<String>> pairs1, double[] rates1, List<List<String>> pairs2, double[] rates2) {
        // 使用 HashMap 存储每种货币的最大金额，初始货币的金额为 1.0
        Map<String, Double> mp = new HashMap<>();
        mp.put(initialCurrency, 1.0);

        int n = pairs1.size(); // 获取第一组汇率转换对的数量

        // 遍历第一组货币交换对多次，进行松弛操作，类似于 Bellman-Ford 算法
        for (int i = 0; i < n; i++) { // 外层循环进行多轮更新，确保所有货币都能传播最大值
            for (int j = 0; j < n; j++) { // 遍历每一对货币交换对
                // 获取当前货币对
                String fromCurrency = pairs1.get(j).get(0); // 源货币
                String toCurrency = pairs1.get(j).get(1);   // 目标货币
                double rate = rates1[j];                   // 当前货币对的汇率

                // 更新目标货币的最大值：源货币的金额 * 汇率
                mp.put(toCurrency, Math.max(
                        mp.getOrDefault(fromCurrency, 0.0) * rate,
                        mp.getOrDefault(toCurrency, 0.0)
                ));

                // 更新源货币的最大值：目标货币的金额 / 汇率
                mp.put(fromCurrency, Math.max(
                        mp.getOrDefault(toCurrency, 0.0) / rate,
                        mp.getOrDefault(fromCurrency, 0.0)
                ));
            }
        }

        n = pairs2.size(); // 获取第二组汇率转换对的数量

        // 遍历第二组货币交换对多次，进行松弛操作
        for (int i = 0; i < n; i++) { // 外层循环进行多轮更新，确保所有货币都能传播最大值
            for (int j = 0; j < n; j++) { // 遍历每一对货币交换对
                // 获取当前货币对
                String fromCurrency = pairs2.get(j).get(0); // 源货币
                String toCurrency = pairs2.get(j).get(1);   // 目标货币
                double rate = rates2[j];                   // 当前货币对的汇率

                // 更新目标货币的最大值：源货币的金额 * 汇率
                mp.put(toCurrency, Math.max(
                        mp.getOrDefault(fromCurrency, 0.0) * rate,
                        mp.getOrDefault(toCurrency, 0.0)
                ));

                // 更新源货币的最大值：目标货币的金额 / 汇率
                mp.put(fromCurrency, Math.max(
                        mp.getOrDefault(toCurrency, 0.0) / rate,
                        mp.getOrDefault(fromCurrency, 0.0)
                ));
            }
        }

        // 返回初始货币所能获得的最大金额，如果不存在，返回 0.0
        return mp.getOrDefault(initialCurrency, 0.0);
    }
}
