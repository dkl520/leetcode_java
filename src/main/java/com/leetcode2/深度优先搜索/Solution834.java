package com.leetcode2.深度优先搜索;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution834 {
    // 计算树中每个节点到其他所有节点的距离之和
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        if (n <= 1) {
            return new int[]{0}; // 如果节点数小于等于1，则距离为0
        }
        // 构建树的邻接表表示
        List<Set<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new HashSet<>());
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            tree.get(u).add(v);
            tree.get(v).add(u); // 无向图，两边都要添加
        }

        int[] res = new int[n]; // 存储每个节点到其他节点的距离之和
        int[] count = new int[n]; // 存储每个节点的子节点数量（包括自己）
        Arrays.fill(count, 1); // 初始化，每个节点至少包含自己
        System.out.println(111111);
        // 第一次DFS，计算每个节点的子节点数量和距离总和
        dfs(0, -1, tree, res, count);
        System.out.println(22222);
        // 第二次DFS，根据根节点的结果计算其他节点的结果
        dfs2(0, -1, tree, res, count, n);

        return res;
    }

    // 第一次DFS，从根节点开始遍历，计算子树信息和节点到子节点的距离总和
    private void dfs(int node, int parent, List<Set<Integer>> tree, int[] res, int[] count) {
        for (int neighbor : tree.get(node)) {
            if (neighbor == parent) continue; // 避免回到父节点
            dfs(neighbor, node, tree, res, count); // 递归遍历子节点
            count[node] += count[neighbor]; // 更新当前节点的子节点总数
            res[node] += res[neighbor] + count[neighbor]; // 更新当前节点到所有节点的距离之和
            // res[neighbor] 所有 neighbor的子元素到neighbor的和，+  count[neighbor]; 包括  neighbor在内的子节点的个数  即为
//            node 到其 下面所有子元素的距离之和。
//           假设  res[node] = distance[neighbor1,neighbor2,,....n].sum().. 表示 每个节点到node 的距离数组求和。
//            distance[neighbor1,neighbor2,,....n].sum() 就等于
//            (res(neighbor1)+count(neighbor1)+res(neighbor2)+ +count(neighbor2)).sum();

            System.out.println(res);
        }
    }

    // 第二次DFS，根据第一次DFS的结果，计算每个节点到其他节点的距离之和
    // 通过子节点的信息反向计算父节点的情况，减少重复计算
    private void dfs2(int node, int parent, List<Set<Integer>> tree, int[] res, int[] count, int n) {
        for (int neighbor : tree.get(node)) {
            if (neighbor == parent) continue; // 避免回到父节点
            // 父节点到其他节点的距离 = 当前节点到其他节点的距离 - 当前节点的子节点数量 + 其他节点的数量
            res[neighbor] = res[node] - count[neighbor] + (n - count[neighbor]);
//           假设  sum[neighbor ] 表示所有neighbor子元素到它本身的距离之和。
//           remain[node] = res[node]-  remain[neighbor]
//            res[node]= reamin[neighbor1]+ reamin[neighbor2].....all;

//      那么 res[node]=    sum[neighbor1 ] +count[neighbor1]+  sum[neighbor2 ] +count[neighbor2].。。。。。reamin.
//            那么   res[node] - count[neighbor]== sum[neighbor]+ sum[remainneighbor..]+ count[remainneighbor2]
//                 res[node] - count[neighbor]== sum[neighbor]+ reamin[remainneighborall]
//              count[remain]= (n - count[neighbor]); 剩余元素的个数
//            那么 res[neighbor]= sum[neighbor]+  reamin[remainneighborall]+ count[remain]


            dfs2(neighbor, node, tree, res, count, n); // 递归遍历子节点
        }
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {
                {0, 1},
                {0, 2},
                {2, 3},
                {2, 4},
                {2, 5}
        };
        System.out.println(Arrays.toString(new Solution834().sumOfDistancesInTree(n, edges)));
    }
}