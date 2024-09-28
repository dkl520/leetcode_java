package com.leetcode2.org.图论;

import java.util.*;

enum State {
    kDraw,      // 平局
    kMouseWin,  // 老鼠获胜
    kCatWin     // 猫获胜
}

public class Solution913 {
    public int catMouseGame(int[][] graph) {
        final int n = graph.length;
        int[][][] states = new int[n][n][2]; // 记录每种状态的结果
//        个三维数组用于记录游戏中每种状态的结果。具体来说：
//        第一维 states[cat][mouse][move] 表示当前猫的位置是 cat，老鼠的位置是 mouse，当前轮到 move 行动的状态。
//        第三维的 move 有两个值：
//        0 表示当前轮到老鼠行动。
//        1 表示当前轮到猫行动。
//        该数组的值可以是：
//        0 表示这个状态下游戏的结果是平局（kDraw）。
//        1 表示这个状态下老鼠获胜（kMouseWin）。
//        2 表示这个状态下猫获胜（kCatWin）。
        int[][][] outDegree = new int[n][n][2]; // 记录每种状态的出度
        Queue<int[]> q = new ArrayDeque<>(); // 队列用于广度优先搜索
        // 初始化所有状态的出度
        for (int cat = 0; cat < n; ++cat)
            for (int mouse = 0; mouse < n; ++mouse) {
                outDegree[cat][mouse][0] = graph[mouse].length; // 鼠的移动选择数
                outDegree[cat][mouse][1] = graph[cat].length - (Arrays.stream(graph[cat]).anyMatch(v -> v == 0) ? 1 : 0);
//                三维数组用于记录每种状态的出度，即从当前状态可以到达的其他状态的数量。
//                0 表示当前轮到老鼠行动。
//                1 表示当前轮到猫行动。
                // 猫的移动选择数，不能移动到洞里
//                该数组的值是一个整数，表示在当前状态下，老鼠或猫可以移动到的下一状态的数量
            }
        // 从能够确定胜负的状态开始
        for (int cat = 1; cat < n; ++cat)
//            这个循环遍历所有可能的猫的位置，从 1 到 n-1。猫不能在 0 位置（洞里），因为题目明确规定猫不能进入洞。
            for (int move = 0; move < 2; ++move) {
                // 鼠标在洞里
                states[cat][0][move] = State.kMouseWin.ordinal();
                q.offer(new int[]{cat, 0, move, State.kMouseWin.ordinal()});
                // 猫抓到鼠标
                states[cat][cat][move] = State.kCatWin.ordinal();
                q.offer(new int[]{cat, cat, move, State.kCatWin.ordinal()});
            }
        // 广度优先搜索，处理队列中的状态
        while (!q.isEmpty()) {
            final int cat = q.peek()[0];
            final int mouse = q.peek()[1];
            final int move = q.peek()[2];
            final int state = q.poll()[3];
            if (cat == 2 && mouse == 1 && move == 0) // 判断初始状态
                return state;
            final int prevMove = move ^ 1; // 切换玩家，0变1，1变0

            for (final int prev : graph[prevMove == 0 ? mouse : cat]) {
                final int prevCat = prevMove == 0 ? cat : prev;  //上一步是0说明上一步是老鼠走，所以上一部猫没走，
                if (prevCat == 0) // 猫不能进入洞
                    continue;
                final int prevMouse = prevMove == 0 ? prev : mouse;
                // 如果状态已经确定，跳过
                if (states[prevCat][prevMouse][prevMove] > 0)
                    continue;
                // 根据当前状态来决定前一个状态
//                prevMove == 0 && state == State.kMouseWin.ordinal()：如果前一个移动是老鼠的移动，并且当前状态是老鼠胜利的状态。
//                prevMove == 1 && state == State.kCatWin.ordinal()：如果前一个移动是猫的移动，并且当前状态是猫胜利的状态。
//                --outDegree[prevCat][prevMouse][prevMove] == 0：每次处理一个邻居节点时，将其出度减少 1，如果减少后的出度为 0，表示所有相关的状态都已经处理过，可以确定当前状态的结果。
                outDegree[prevCat][prevMouse][prevMove] -= 1;
                System.out.println(outDegree[prevCat][prevMouse][prevMove]);
                if (prevMove == 0 && state == State.kMouseWin.ordinal() || prevMove == 1 && state == State.kCatWin.ordinal() || outDegree[prevCat][prevMouse][prevMove] == 0) {
                    states[prevCat][prevMouse][prevMove] = state;
                    q.offer(new int[]{prevCat, prevMouse, prevMove, state});
                }
            }
        }
        return states[2][1][0]; // 返回初始状态的结果
    }

    public static void main(String[] args) {

        int[][] graph = {
                {2, 5}, // 节点 0 的邻接节点
                {3}, // 节点 1 的邻接节点
                {0, 4, 5}, // 节点 2 的邻接节点
                {1, 4, 5}, // 节点 3 的邻接节点
                {2, 3}, // 节点 4 的邻接节点
                {0, 2, 3} // 节点 5 的邻接节点
        };
        System.out.println(new Solution913().catMouseGame(graph));
    }
}
