package com.leetcode2.org.哈希表;

import com.leetcode2.广度优先搜索.Solution694;

import java.util.HashSet;
import java.util.Set;

public class Solution694_2 {
    // 网格的行数
    private int m;
    // 网格的列数
    private int n;
    // 网格数据
    private int[][] grid;
    // 用于记录每个岛屿的路径（通过方向编码）
    private StringBuilder path = new StringBuilder();

    /**
     * 计算不同岛屿的数量
     * @param grid 二维网格，其中 1 表示陆地，0 表示海洋
     * @return 不同岛屿的数量
     */
    public int numDistinctIslands(int[][] grid) {
        m = grid.length; // 获取网格的行数
        n = grid[0].length; // 获取网格的列数
        this.grid = grid; // 保存网格数据
        Set<String> paths = new HashSet<>(); // 使用 HashSet 存储不同岛屿的路径编码，自动去重

        // 遍历网格中的每个点
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) { // 发现陆地（岛屿的起点）
                    dfs(i, j, 0); // 深度优先搜索岛屿，并记录路径
                    paths.add(path.toString()); // 将岛屿的路径编码添加到集合中
                    path.setLength(0); // 重置路径，以便记录下一个岛屿的路径
                }
            }
        }
        System.out.println(paths);
        // 返回不同岛屿的数量，即不同路径编码的数量
        return paths.size();
    }

    /**
     * 深度优先搜索岛屿并记录路径
     * @param i 当前点的行索引
     * @param j 当前点的列索引
     * @param k 当前移动的方向编码（0 表示起点，1-4 分别表示右、下、左、上）
     */
//    1  2  3  4
//    右 下 左  上
    private void dfs(int i, int j, int k) {
        grid[i][j] = 0; // 标记当前点为已访问
        path.append(k); // 将当前移动方向编码添加到路径中

        // 定义四个方向的行列偏移量
        int[] dirs = {-1, 0, 1, 0, -1}; // 实际上 dirs[0] 未使用，但为了与 k 的对应关系保持一致而保留

        // 遍历四个相邻方向
        for (int h = 1; h < 5; ++h) {
            int x = i + dirs[h - 1]; // 行偏移
            int y = j + dirs[h]; // 列偏移

            // 检查相邻点是否在网格范围内且为陆地
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                dfs(x, y, h); // 继续深度优先搜索
            }
        }

        // 注意：这里不再添加 k 到 path 中，因为每个岛屿的路径只应在结束时包含一次方向编码（用于区分不同的岛屿形状）
        // path.append(k); // 这行代码在原实现中可能是多余的，取决于路径编码的具体定义
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}
        };
        Solution694_2 solution694 = new Solution694_2();
        System.out.println(solution694.numDistinctIslands(grid));  //
    }
}