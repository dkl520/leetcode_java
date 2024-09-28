package com.leetcode2.org.树状数组;

import java.util.ArrayList;
import java.util.List;

public class Solution2940_4 {
        public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
            int n = heights.length; // 建筑的数量
            int m = queries.length; // 查询的数量

            // 使用List数组来存储每个位置上的查询（按建筑索引划分）
            List<int[]>[] query = new List[n];
            for (int i = 0; i < n; i++) {
                query[i] = new ArrayList<>();
            }

            int[] ans = new int[m]; // 存储每个查询的答案
            List<Integer> st = new ArrayList<>(); // 使用单调栈来存储可能的候选建筑索引

            // 处理每个查询
            for (int i = 0; i < m; i++) {
                int a = queries[i][0];
                int b = queries[i][1];

                // 确保查询的左边界不大于右边界
                if (a > b) {
                    int temp = a;
                    a = b;
                    b = temp;
                }

                // 如果左边界等于右边界或左边界建筑高度小于右边界建筑高度，则直接返回右边界索引
                if (a == b || heights[a] < heights[b]) {
                    ans[i] = b;
                    continue;
                }

                // 否则，将查询及其左边界高度存储在对应右边界建筑的查询列表中
                query[b].add(new int[]{i, heights[a]});
            }

            // 从右向左遍历建筑，使用单调栈找到每个查询的最左侧建筑
            int top = -1; // 栈顶索引
            for (int i = n - 1; i >= 0; i--) {
                // 处理当前建筑上的所有查询
                for (int j = 0; j < query[i].size(); j++) {
                    int q = query[i].get(j)[0]; // 查询索引
                    int val = query[i].get(j)[1]; // 左边界高度

                    // 如果栈为空或栈顶建筑高度小于等于查询的左边界高度，则无法在范围内找到更高的建筑
                    if (top == -1 || heights[st.get(0)] <= val) {
                        ans[q] = -1;
                        continue;
                    }

                    // 在栈中二分查找高度大于查询左边界高度的最高建筑的索引
                    int l = 0, r = top;
                    while (l <= r) {
                        int mid = (l + r) >> 1;
                        if (heights[st.get(mid)] > val) {
                            l = mid + 1;
                        } else {
                            r = mid - 1;
                        }
                    }

                    // 如果没有找到符合条件的建筑（即r < 0），则设置答案为-1；否则答案为r指向的建筑索引
                    ans[q] = (r < 0) ? -1 : st.get(r);
                }

                // 维护单调栈，确保栈中建筑按高度从高到低排列
                while (top >= 0 && heights[st.get(top)] <= heights[i]) {
                    st.remove(st.size() - 1);
                    top--;
                }
                st.add(i); // 将当前建筑索引加入栈中
                top++;
            }

            return ans; // 返回所有查询的答案
        }
    }