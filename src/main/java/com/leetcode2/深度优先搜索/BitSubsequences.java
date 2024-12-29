package com.leetcode2.深度优先搜索;

import java.util.*;

public class BitSubsequences {
    public static List<Integer> findSubsequences(int[] A) {
        // dp：使用Map存储可达的键值和对应的最小最大值
        // 键表示通过按位或操作可以构造的子序列
        // 值表示该子序列中的最小最大值限制
        Map<Integer, Integer> dp = new HashMap<>();

        // 初始化：只有0是可达的，最小最大值也是0
        dp.put(0, 0);

        // 遍历输入数组的每个元素
        for (int x : A) {
            // 创建当前dp的快照，避免在迭代中修改原Map
            List<Map.Entry<Integer, Integer>> pairs = new ArrayList<>(dp.entrySet());

            // 遍历当前可达的所有键值对
            for (Map.Entry<Integer, Integer> entry : pairs) {
                int canReach = entry.getKey();    // 当前可达的键
                int limit = entry.getValue();     // 当前可达键的最小最大值限制

                // 如果当前元素x小于等于已有的最小最大值限制，则跳过
                // 这确保新加入的元素严格大于之前的限制
                if (limit >= x) {
                    continue;
                }

                // 计算通过按位或操作可以构造的新键
                int nowReachable = canReach | x;

                // 更新dp：
                // 1. 如果新键不存在
                // 2. 或者新键存在但可以用更小的限制值替换
                if (!dp.containsKey(nowReachable) || dp.get(nowReachable) > x) {
                    dp.put(nowReachable, x);
                }
            }
        }

        // 将所有可达的键转换为列表并排序
        List<Integer> result = new ArrayList<>(dp.keySet());
        Collections.sort(result);

        return result;
    }

    public static void main(String[] args) {
    long startTime1 = System.nanoTime();
    Random random = new Random();
    int[][] testCases = new int[10][100];
    // 生成100个测试用例，每个用例10000个元素
    for (int i = 0; i < testCases.length; i++) {
        for (int j = 0; j < testCases[i].length; j++) {
            // 生成 0-1000 范围内的随机整数
            testCases[i][j] = random.nextInt(1001);
        }
    }
    for (int[] A : testCases) {
        List<Integer> result = findSubsequences(A);
        System.out.println(Arrays.toString(A) + " -> " + result);
    }
    long endTime1 = System.nanoTime();
    System.out.println("别人的代码耗时: " + (endTime1 - startTime1) / 1000000 + "ms");
}
}






//


