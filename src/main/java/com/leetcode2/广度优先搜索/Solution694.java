package com.leetcode2.广度优先搜索;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.*;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Solution694 {
    // 计算不同岛屿的数量
    public int numDistinctIslands(int[][] grid) {
        List<List<int[]>> list = new ArrayList<>();  // 用于存储每个岛屿的坐标
        list = numIslands(grid, list);  // 获取所有岛屿的坐标列表
        // 处理每个岛屿，将其坐标归一化到相对坐标
        List<List<int[]>> processedList = list.stream()
                .map(subList -> {
                    int minA0 = subList.stream().mapToInt(arr -> arr[0]).min().orElse(0);  // 获取a[0]的最小值
                    int minA1 = subList.stream().mapToInt(arr -> arr[1]).min().orElse(0);  // 获取a[1]的最小值
                    return subList.stream()
                            .map(arr -> new int[]{arr[0] - minA0, arr[1] - minA1})  // 将坐标转换为相对坐标
                            .collect(Collectors.toList());
                }).toList();
        // 计算每个岛屿的哈希值
        List<Integer> hashcodeLists = processedList.stream().map(this::calculateHashCode).toList();
        Set<Integer> set = new HashSet<>(hashcodeLists);  // 使用Set去重

        return new ArrayList<>(set).size();  // 返回不同岛屿的数量
    }

    // 计算一个岛屿的哈希值
    private int calculateHashCode(List<int[]> list) {
        return list.stream()
                .map(Arrays::toString)  // 将数组转换为字符串
                .sorted()  // 排序
                .collect(Collectors.joining())  // 拼接成一个字符串
                .hashCode();  // 计算哈希值
    }

    // 获取所有岛屿的坐标列表
    public List<List<int[]>> numIslands(int[][] grid, List<List<int[]>> list) {
        int m = grid.length;  // 行数
        int n = grid[0].length;  // 列数
        boolean[][] visitGrid = new boolean[m][n];  // 记录访问状态

        // 遍历每个网格
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int el = grid[i][j];
                if (el == 1 && !visitGrid[i][j]) {  // 如果是陆地且未访问
                    List<int[]> listPanel = new ArrayList<>();  // 存储当前岛屿的坐标
                    bfs(grid, i, j, visitGrid, listPanel);  // 进行广度优先搜索
                    list.add(listPanel);  // 添加到岛屿列表
                }
            }
        }
        return list;  // 返回岛屿列表
    }

    // 广度优先搜索
    private void bfs(int[][] grid, int row, int col, boolean[][] visitGrid, List<int[]> listPanel) {
        Queue<int[]> queue = new LinkedList<>();  // 创建队列
        int[] dirX = {0, 0, 1, -1};  // 方向数组
        int[] dirY = {1, -1, 0, 0};  // 方向数组
        visitGrid[row][col] = true;  // 标记为已访问
        listPanel.add(new int[]{row, col});  // 添加当前坐标到岛屿列表

        queue.add(new int[]{row, col});  // 将当前坐标加入队列

        // 开始广度优先搜索
        while (!queue.isEmpty()) {
            int[] current = queue.poll();  // 取出队首元素
            int currentRow = current[0];
            int currentCol = current[1];
            for (int index = 0; index < dirX.length; index++) {
                int newY = currentRow + dirY[index];
                int newX = currentCol + dirX[index];
                if (newX >= 0 && newX < grid[0].length && newY >= 0 && newY < grid.length && grid[newY][newX] == 1 && !visitGrid[newY][newX]) {
                    visitGrid[newY][newX] = true;  // 标记为已访问
                    queue.add(new int[]{newY, newX});  // 加入队列
                    listPanel.add(new int[]{newY, newX});  // 添加到岛屿列表
                }
            }
        }
    }

    // 主函数，测试用
    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}
        };
        Solution694 solution694 = new Solution694();
        System.out.println(solution694.numDistinctIslands(grid));  // 输出不同岛屿的数量
    }
}
