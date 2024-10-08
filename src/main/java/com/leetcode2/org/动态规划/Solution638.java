package com.leetcode2.org.动态规划;

import java.util.Arrays;
import java.util.List;

public class Solution638 {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int n = price.size(); // 物品的种类数
        int[] g = new int[n + 1]; // 状态压缩中每个物品的权重
        g[0] = 1;
        // 计算每个物品的状态权重，g[i] 表示第 i 个物品的权重
        for (int i = 1; i <= n; i++) {
            g[i] = g[i - 1] * (needs.get(i - 1) + 1);
        }
        int mask = g[n]; // 所有物品的总状态数
        int[] f = new int[mask]; // 动态规划数组，f[state] 表示达到 state 状态的最小花费
        int[] cnt = new int[n]; // 临时数组，用于存储当前状态下每种物品的数量

        // 遍历所有可能的状态
        for (int state = 1; state < mask; state++) {
            f[state] = 0x3f3f3f3f; // 初始化为一个很大的值，表示初始状态下的花费
            Arrays.fill(cnt, 0);   // 初始化 cnt 数组，用于记录当前状态下每个物品的数量

            for (int i = 0; i < n; i++) {
                cnt[i] = (state % g[i + 1]) / g[i]; // 计算当前状态下每个物品的数量
                System.out.println(cnt[i]);
            }
            System.out.println(cnt);
            // 不使用大礼包，逐个购买物品
            for (int i = 0; i < n; i++) {
                if (cnt[i] > 0) {
                    f[state] = Math.min(f[state], f[state - g[i]] + price.get(i)); // 更新当前状态的最小花费
                }
            }

            // 使用大礼包
            out:
            for (List<Integer> x : special) {
                int cur = state; // 当前状态
                for (int i = 0; i < n; i++) {
                    if (cnt[i] < x.get(i)) continue out; // 如果当前状态下某个物品的数量小于大礼包中该物品的数量，则跳过该大礼包
                    cur = cur - x.get(i) * g[i]; // 更新状态，减去大礼包中的物品数量
                }
                f[state] = Math.min(f[state], f[cur] + x.get(n)); // 更新当前状态的最小花费，加上使用大礼包的花费
            }
        }
        return f[mask - 1]; // 返回满足所有需求的最小花费
    }

    public static void main(String[] args) {
        List<Integer> price = List.of(2, 5); // 每种物品的单价
        List<List<Integer>> special = List.of(List.of(3, 0, 5), List.of(1, 2, 10)); // 大礼包的组合和价格
        List<Integer> needs = List.of(3, 2); // 需要购买的每种物品的数量

        Solution638 solution = new Solution638();

        System.out.println(
                solution.shoppingOffers(price, special, needs)
        );
    }
}

//
//假设我们有 2 种物品，需求分别是 2 和 1。
//
//g[0] = 1 (初始值)
//g[1] = 1 * (2 + 1) = 3 (第一种物品可能的状态：0, 1, 2)
//g[2] = 3 * (1 + 1) = 6 (第二种物品可能的状态：0, 1)
//总状态数 mask = g[2] = 6，这正好对应了所有可能的组合：
//        (0,0), (0,1), (1,0), (1,1), (2,0), (2,1)
//
//这种编码方式允许我们使用一个整数来表示多维的状态，大大简化了状态的表示和转换。每个状态可以通过对这个整数进行除法和取模运算来解码，得到每种物品的具体数量。
//
//这种技巧在动态规划中经常被用来处理多维状态的问题，它可以有效地减少内存使用并简化状态转移的过程。


//这行代码的目的是从 state 这个整数中提取出第 i 种物品的数量。让我们逐步分解这个计算过程：
//
//先计算：state % g[i + 1]
//这个操作会得到一个小于 g[i+1] 的数。
//它去掉了所有比第 i 种物品更高位的信息。
//后计算：(...) / g[i]
//这个除法操作会去掉所有比第 i 种物品更低位的信息。
//让我们通过一个例子来说明这个过程：
//
//假设我们有 3 种物品，它们的需求量分别是 2, 1, 2。
//那么 g 数组会是这样的：
//
//g[0] = 1
//g[1] = 1 * (2 + 1) = 3
//g[2] = 3 * (1 + 1) = 6
//g[3] = 6 * (2 + 1) = 18
//现在，假设当前的 state 是 11。我们来看看如何解码每种物品的数量：
//
//对于第一种物品 (i = 0):
//cnt[0] = 11 % 3 / 1 = 2
//对于第二种物品 (i = 1):
//cnt[1] = 11 % 6 / 3 = 1
//对于第三种物品 (i = 2):
//cnt[2] = 11 % 18 / 6 = 1
//所以，state = 11 表示的状态是 (2, 1, 1)，即第一种物品有 2 个，第二种物品有 1 个，第三种物品有 1 个。
//
//这种编码和解码方式的巧妙之处在于：
//
//每种物品的数量都被编码在特定的"位"上。
//取模操作 (%) 可以去掉高位的信息。
//除法操作 (/) 可以去掉低位的信息。
//通过这种方式，我们可以在一个整数中存储多维的信息，并且可以方便地提取出每一维的值。
//这在动态规划中经常被用来压缩状态空间，使得我们可以用一维数组来解决多维问题。


//我理解您的困惑。让我们更深入地解释一下 g[i] 的含义和作用。
//
//g[i] 是一个用于状态编码的权重值。它的作用是在一个整数中编码多维状态（即多种物品的数量）。让我们通过一个具体的例子来理解：
//
//假设我们有3种物品，它们的最大需求量分别是2, 1, 2。
//
//首先，我们计算 g 数组：
//g[0] = 1 (这是基础值)
//g[1] = 1 * (2 + 1) = 3 (第一种物品有3种可能：0, 1, 2)
//g[2] = 3 * (1 + 1) = 6 (第二种物品有2种可能：0, 1)
//g[3] = 6 * (2 + 1) = 18 (第三种物品有3种可能：0, 1, 2)
//现在，我们可以用一个整数来表示任何可能的状态。例如：
//状态 11 表示 (2, 1, 1)，即第一种物品2个，第二种物品1个，第三种物品1个。
//当我们说 state - g[i] 时，我们实际上是在减少第 i 种物品的数量：
//如果 state = 11，表示 (2, 1, 1)
//state - g[1] = 11 - 3 = 8，表示 (1, 1, 1)，即第一种物品减少了1个
//state - g[2] = 11 - 6 = 5，表示 (2, 0, 1)，即第二种物品减少了1个
//state - g[3] = 11 - 18 = -7，这是一个无效状态，因为我们不能有负数的物品
//这就是为什么 g[i] 被称为"状态权重"。它代表了在我们的状态编码中，第 i 种物品的"权重"或"价值"。
//
//当我们在动态规划中使用 f[state - g[i]] 时，我们实际上是在寻找"如果我们少买一个第 i 种物品，会是什么状态，" +
//        "那个状态的最小花费是多少"。
//
//
//
//
//这种编码方式允许我们使用一维数组来表示和处理多维状态，大大简化了问题的表示和求解过程。但确实，这种方法可能初看起来不太直观。
//希望这个更详细的解释能帮助您更好地理解 g[i] 的概念和作用。