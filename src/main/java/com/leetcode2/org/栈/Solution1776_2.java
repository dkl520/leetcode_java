package com.leetcode2.org.栈;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution1776_2 {

        public double[] getCollisionTimes(int[][] cars) {
            int n = cars.length; // 汽车的数量
            double[] ans = new double[n]; // 用于存储每辆车的碰撞时间，初始化为-1.0表示没有碰撞
            Arrays.fill(ans, -1.0); // 初始化答案数组
            Deque<Integer> stk = new ArrayDeque<>(); // 使用栈来存储车辆的索引，以便从后向前遍历

            // 从后向前遍历每辆车
            for (int i = n - 1; i >= 0; --i) {
                // 当栈不为空时，循环检查当前车i与栈顶车j的碰撞情况
                while (!stk.isEmpty()) {
                    int j = stk.peek(); // 栈顶车辆的索引
                    // 如果后车i的速度大于前车j的速度，那么存在碰撞的可能性
                    if (cars[i][1] > cars[j][1]) {
                        // 计算两车碰撞的时间t
                        double t = (cars[j][0] - cars[i][0]) * 1.0 / (cars[i][1] - cars[j][1]);
                        // 如果前车j还没有计算过碰撞时间，或者计算出的时间t小于等于前车j的碰撞时间，则更新当前车i的碰撞时间
                        if (ans[j] < 0 || t <= ans[j]) {
                            ans[i] = t; // 更新当前车i的碰撞时间
                            break; // 跳出循环，因为当前车i只会与栈顶车j碰撞（如果有碰撞的话）
                        }
                    }
                    // 如果后车i的速度不大于前车j的速度，或者计算出的时间t大于前车j的碰撞时间，则栈顶车j对后车i没有碰撞影响，弹出栈顶车j
                    stk.pop();
                }
                stk.push(i); // 将当前车i的索引压入栈中
            }

            return ans; // 返回每辆车的碰撞时间数组
        }
    }