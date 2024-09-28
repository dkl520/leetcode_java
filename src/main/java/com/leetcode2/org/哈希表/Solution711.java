package com.leetcode2.org.哈希表;

import java.util.*;

public class Solution711 {
    // 定义一个二维数组,表示四个方向的移动:右、下、左、上
    private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int numDistinctIslands2(int[][] grid) {
        // 检查输入网格是否为空或无效
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0; // 如果网格无效,返回0个不同岛屿
        }

        int m = grid.length; // 获取网格的行数
        int n = grid[0].length; // 获取网格的列数
        Set<String> uniqueIslands = new HashSet<>(); // 创建一个HashSet用于存储唯一的岛屿形状

        boolean[][] visited = new boolean[m][n]; // 创建一个布尔型二维数组,用于标记每个单元格是否被访问过

        // 双重循环遍历整个网格
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果当前单元格是陆地(值为1)且未被访问过
                if (grid[i][j] == 1 && !visited[i][j]) {
                    List<int[]> island = new ArrayList<>(); // 创建一个列表用于存储当前岛屿的所有单元格坐标
                    dfs(grid, visited, i, j, island); // 调用深度优先搜索函数来遍历整个岛屿
                    String newIsland = normalize(island); // 将岛屿标准化为一个唯一的字符串表示
                    uniqueIslands.add(newIsland); // 将标准化后的岛屿添加到唯一岛屿集合中
                }
            }
        }
        return uniqueIslands.size(); // 返回唯一岛屿的数量
    }

    private void dfs(int[][] grid, boolean[][] visited, int x, int y, List<int[]> island) {
        visited[x][y] = true; // 标记当前单元格为已访问
        island.add(new int[]{x, y}); // 将当前单元格的坐标添加到岛屿列表中

        // 遍历四个方向
        for (int[] dir : DIRS) {
            int nx = x + dir[0]; // 计算相邻单元格的x坐标
            int ny = y + dir[1]; // 计算相邻单元格的y坐标
            // 检查相邻单元格是否在网格范围内,是否为陆地,且未被访问过
            if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && grid[nx][ny] == 1 && !visited[nx][ny]) {
                dfs(grid, visited, nx, ny, island); // 递归访问相邻的陆地单元格
            }
        }
    }

    private String normalize(List<int[]> island) {
        List<String> forms = new ArrayList<>(); // 创建一个列表用于存储岛屿的所有可能变换形态

        // 定义八种变换矩阵,包括原始形态、旋转和翻转
        int[][] transforms = {
                {1, 1}, {1, -1}, {-1, 1}, {-1, -1}, // 原始和180度旋转
                {1, 1}, {-1, 1}, {1, -1}, {-1, -1}  // 90度和270度旋转
        };

        // 对岛屿应用所有可能的变换
        for (int i = 0; i < 8; i++) {
            List<int[]> transformed = new ArrayList<>(); // 创建一个列表用于存储变换后的岛屿坐标
            for (int[] point : island) { // 遍历原始岛屿的每个点
                int[] newPoint = new int[2]; // 创建一个新数组用于存储变换后的坐标
                newPoint[0] = point[0] * transforms[i][0]; // 应用x轴变换
                newPoint[1] = point[1] * transforms[i][1]; // 应用y轴变换
                if (i >= 4) { // 如果是90度或270度旋转
                    int temp = newPoint[0]; // 交换x和y坐标
                    newPoint[0] = newPoint[1];
                    newPoint[1] = temp;
                }
                transformed.add(newPoint); // 将变换后的坐标添加到列表中
            }
            forms.add(canonical(transformed)); // 将变换后的岛屿转换为规范化字符串并添加到形态列表中
        }

        Collections.sort(forms); // 对所有形态进行字典序排序
        return forms.get(0); // 返回字典序最小的形态,这就是岛屿的唯一标识
    }

    private String canonical(List<int[]> island) {
        // 对岛屿的所有点进行排序,首先按x坐标升序,如果x相同则按y坐标升序
        Collections.sort(island, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        StringBuilder sb = new StringBuilder(); // 创建一个StringBuilder用于构建规范化字符串
        int x0 = island.get(0)[0]; // 获取排序后第一个点的x坐标作为参考点
        int y0 = island.get(0)[1]; // 获取排序后第一个点的y坐标作为参考点
        for (int[] point : island) { // 遍历排序后的所有点
            // 将每个点相对于参考点的相对坐标添加到字符串中,用冒号分隔
            sb.append(point[0] - x0).append(":").append(point[1] - y0).append(":");
        }
        System.out.println(sb.toString()); // 打印规范化后的字符串(用于调试)
        return sb.toString(); // 返回规范化的字符串表示
    }

    public static void main(String[] args) {
        Solution711 solution = new Solution711(); // 创建Solution711类的实例
        // 定义第一个测试用例
        int[][] grid1 = {
                {1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1}
        };

        // 定义第二个测试用例(与第一个相同)
        int[][] grid2 = {
                {1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1}
        };

        // 调用numDistinctIslands2方法并打印结果
        System.out.println(solution.numDistinctIslands2(grid1)); // 预期输出: 1
        System.out.println(solution.numDistinctIslands2(grid2)); // 预期输出: 1
    }
}