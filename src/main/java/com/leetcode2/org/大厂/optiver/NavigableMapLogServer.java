package com.leetcode2.org.大厂.optiver;


import java.util.*;

//record Log(int logId, int timestamp) {}

public class NavigableMapLogServer {
    private final int m; // 最大返回日志数
    private final NavigableMap<Integer, Deque<Log>> logs;
    Log lastLog;

    public NavigableMapLogServer(int m) {
        this.m = m;
        this.logs = new TreeMap<>((a, b) -> a - b);
    }

    // 记录日志
    public void recordLog(int logId, int timestamp) {
        Log log = new Log(logId, timestamp);
        logs.computeIfAbsent(timestamp, k -> new ArrayDeque<>()).offerFirst(log);
        this.lastLog = log;
    }

    // 获取指定时间范围内的日志
    public String getLogs() {
        if (logs.isEmpty()) return "";

        int currentTimestamp = this.lastLog.timestamp();
        int oneHourAgo = currentTimestamp - 3600;

        // 获取最近一小时的日志映射
        NavigableMap<Integer, Deque<Log>> recentLogs =
                this.logs.subMap(oneHourAgo, false, currentTimestamp, true);

        // 使用降序迭代，取最近的m条日志
        StringBuilder result = new StringBuilder();
        int count = 0;

//        NavigableMap<Integer, Deque<Log>> map =  recentLo
        for (var entry : recentLogs.descendingMap().entrySet()) {
            for (Log log : entry.getValue()) {
                if (count > 0) result.append(",");
                result.append(log.logId());
                if (++count == m) break;
            }
            if (count == m) break;
        }

        return result.reverse().toString();
    }

    // 获取指定时间范围内的日志数量
    public int getLogCount() {
        int currentTimestamp = this.lastLog.timestamp();
        int oneHourAgo = currentTimestamp - 3600;

        // 获取最近一小时的日志映射
        NavigableMap<Integer, Deque<Log>> recentLogs =
                logs.subMap(oneHourAgo, false, currentTimestamp, true);
        return recentLogs.values().stream().mapToInt(Deque::size).sum();
    }

    public static void main(String[] args) {
        NavigableMapLogServer logServer = new NavigableMapLogServer(10);

        logServer.recordLog(1, 0);
        logServer.recordLog(2, 300);
        System.out.println(logServer.getLogs()); // 输出: 1,2
        System.out.println(logServer.getLogCount()); // 输出: 2

        logServer.recordLog(3, 1200);
        logServer.recordLog(1, 1800);
        System.out.println(logServer.getLogs()); // 输出: 1 2,3,1
        System.out.println(logServer.getLogCount()); // 输出: 4

        logServer.recordLog(4, 3900);
        System.out.println(logServer.getLogs()); // 输出: 3,1,4  (logs in the last ho


    }
}