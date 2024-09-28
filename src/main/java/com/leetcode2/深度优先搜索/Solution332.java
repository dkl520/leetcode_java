package com.leetcode2.深度优先搜索;

import java.util.*;

public class Solution332 {

    // 使用哈希表存储图，键为机场名称，值为以该机场为起点的所有目的地的优先队列
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    // 用于存储最终行程的链表，由于是从最后一个访问的机场开始添加，因此使用LinkedList的addFirst方法
    LinkedList<String> route = new LinkedList<>();

    /**
     * 找到从"JFK"出发的完整行程列表
     *
     * @param tickets 机票列表，每个列表项是一个包含两个元素的列表，分别表示起点和终点
     * @return 从"JFK"出发的完整行程列表
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        // 构建图
        for (List<String> ticket : tickets) {
            // 如果图中不存在该起点，则添加一个新的优先队列作为值
            // 如果已存在，则直接将终点添加到对应起点的优先队列中
            graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
        }
        // 从"JFK"开始进行深度优先搜索，以找到完整的行程
        dfs("JFK");

        // 返回构建好的行程列表
        return route;
    }

    /**
     * 深度优先搜索的辅助方法
     *
     * @param airport 当前所在的机场
     */
    private void dfs(String airport) {
        // 获取当前机场可以前往的所有目的地的优先队列
        PriorityQueue<String> destinations = graph.get(airport);
        // 当当前机场还有可去的目的地时，循环进行深度优先搜索
        while (destinations != null && !destinations.isEmpty()) {
            // 取出优先级最高的目的地（这里按照自然顺序，如果需要自定义顺序可以调整PriorityQueue的构造器）
            String nextDest = destinations.poll();
            // 对下一个目的地进行深度优先搜索
            dfs(nextDest);
        }
        // 当当前机场没有可去的目的地时，将其添加到行程列表中（因为是后序遍历，所以这里使用的是addFirst）
        route.addFirst(airport);
        System.out.println(route);
    }

    public static void main(String[] args) {
        String[][] ticketsArray = {
                {"JFK", "SFO"},
                {"JFK", "ATL"},
                {"SFO", "ATL"},
                {"ATL", "JFK"},
                {"ATL", "SFO"}
        };


        Solution332 solution332 = new Solution332();
        List<List<String>> tickets = Arrays.stream(ticketsArray)
                .map(Arrays::asList)
                .toList();
        System.out.println(solution332.findItinerary(tickets));



    }
}