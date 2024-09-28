package com.leetcode2.广度优先搜索;

import java.util.*;



public class Solution675_2 {

    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0) return 0;
        int N = forest.size();
        int M = forest.get(0).size();
        long startTime = System.nanoTime();
        // 按树的高度排序
        PriorityQueue<PointOne> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.height));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int height = forest.get(i).get(j);
                if (height > 1) {
                    queue.offer(new PointOne(i, j, height));
                }
            }
        }
        long endTime = System.nanoTime();
        System.out.println(endTime-startTime);
        System.out.println("two 前部分循环计时");
//        3898400
/*        3444500
        two 前部分循环计时*/
        int totalSteps = 0;
        int[] currPos = {0, 0};

        while (!queue.isEmpty()) {
            PointOne nextTree = queue.poll();
            int steps = bfs(forest, currPos, new int[]{nextTree.x, nextTree.y});
            if (steps == -1) return -1;
            totalSteps += steps;
            currPos[0] = nextTree.x;
            currPos[1] = nextTree.y;
        }

        return totalSteps;
    }

    private int bfs(List<List<Integer>> forest, int[] start, int[] end) {
        int N = forest.size();
        int M = forest.get(0).size();
        boolean[][] visited = new boolean[N][M];
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        visited[start[0]][start[1]] = true;
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                if (curr[0] == end[0] && curr[1] == end[1]) return steps;

                for (int[] dir : directions) {
                    int newX = curr[0] + dir[0];
                    int newY = curr[1] + dir[1];
                    if (newX >= 0 && newX < N && newY >= 0 && newY < M && !visited[newX][newY] && forest.get(newX).get(newY) != 0) {
                        queue.offer(new int[]{newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
            steps++;
        }

        return -1;
    }

    public static void main(String[] args) {

        int[][] array =  {
                {69438, 55243, 0, 43779, 5241, 93591, 73380},
                {847, 49990, 53242, 21837, 89404, 63929, 48214},
                {90332, 49751, 0, 3088, 16374, 70121, 25385},
                {14694, 4338, 87873, 86281, 5204, 84169, 5024},
                {31711, 47313, 1885, 28332, 11646, 42583, 31460},
                {59845, 94855, 29286, 53221, 9803, 41305, 60749},
                {95077, 50343, 27947, 92852, 0, 0, 19731},
                {86158, 63553, 56822, 90251, 0, 23826, 17478},
                {60387, 23279, 78048, 78835, 5310, 99720, 0},
                {74799, 48845, 60658, 29773, 96129, 90443, 14391},
                {65448, 63358, 78089, 93914, 7931, 68804, 72633},
                {93431, 90868, 55280, 30860, 59354, 62083, 47669},
                {81064, 93220, 22386, 22341, 95485, 20696, 13436},
                {50083, 0, 89399, 43882, 0, 13593, 27847},
                {0, 12256, 33652, 69301, 73395, 93440, 0},
                {42818, 87197, 81249, 33936, 7027, 5744, 64710},
                {35843, 0, 99746, 52442, 17494, 49407, 63016},
                {86042, 44524, 0, 0, 26787, 97651, 28572},
                {54183, 83466, 96754, 89861, 84143, 13413, 72921},
                {89405, 52305, 39907, 27366, 14603, 0, 14104},
                {70909, 61104, 70236, 30365, 0, 30944, 98378},
                {20124, 87188, 6515, 98319, 78146, 99325, 88919},
                {89669, 0, 64218, 85795, 2449, 48939, 12869},
                {93539, 28909, 90973, 77642, 0, 72170, 98359},
                {88628, 16422, 80512, 0, 38651, 50854, 55768},
                {13639, 2889, 74835, 80416, 26051, 78859, 25721},
                {90182, 23154, 16586, 0, 27459, 3272, 84893},
                {2480, 33654, 87321, 93272, 93079, 0, 38394},
                {34676, 72427, 95024, 12240, 72012, 0, 57763},
                {97957, 56, 83817, 45472, 0, 24087, 90245},
                {32056, 0, 92049, 21380, 4980, 38458, 3490},
                {21509, 76628, 0, 90430, 10113, 76264, 45840},
                {97192, 58807, 74165, 65921, 45726, 47265, 56084},
                {16276, 27751, 37985, 47944, 54895, 80706, 2372},
                {28438, 53073, 0, 67255, 38416, 63354, 69262},
                {23926, 75497, 91347, 58436, 73946, 39565, 10841},
                {34372, 69647, 44093, 62680, 32424, 69858, 68719},
                {24425, 4014, 94871, 1031, 99852, 88692, 31503},
                {24475, 12295, 33326, 37771, 37883, 74568, 25163},
                {0, 18411, 88185, 60924, 29028, 69789, 0},
                {34697, 75631, 7636, 16190, 60178, 39082, 7052},
                {24876, 9570, 53630, 98605, 22331, 79320, 88317},
                {27204, 89103, 15221, 91346, 35428, 94251, 62745},
                {26636, 28759, 12998, 58412, 38113, 14678, 0},
                {80871, 79706, 45325, 3861, 12504, 0, 4872},
                {79662, 15626, 995, 80546, 64775, 0, 68820},
                {25160, 82123, 81706, 21494, 92958, 33594, 5243}
        };

        List<List<Integer>> forest = new ArrayList<>();
        for (int[] row : array) {
            List<Integer> list = new ArrayList<>();
            for (int num : row) {
                list.add(num);
            }
            forest.add(list);
        }
        long startTime = System.nanoTime();
        Solution675_2 solution = new Solution675_2();
        System.out.println(solution.cutOffTree(forest));
        System.out.println("第二个计时");
        long endTime = System.nanoTime();
        System.out.println(endTime - startTime);
    }
}