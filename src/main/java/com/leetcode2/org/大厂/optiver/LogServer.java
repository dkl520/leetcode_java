package com.leetcode2.org.大厂.optiver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class LogServer {
    private final int m; // Maximum number of logs to return
    private final LinkedList<LogEntry> logs; // To store log entries (logId, timestamp)

    // Constructor to initialize the log server with the given 'm' value
    public LogServer(int m) {
        this.m = m;
        this.logs = new LinkedList<>();
    }

    // Method to record a new log entry
    public void recordLog(int logId, int timestamp) {
        logs.add(new LogEntry(logId, timestamp));
    }

    // Method to get the latest 'm' logs from the last hour, sorted by timestamp
    public String getLogs() {
        if (logs.isEmpty()) return ""; // If no logs, return an empty string

        int latestTimestamp = logs.getLast().timestamp; // Get the most recent timestamp
        int oneHourAgo = latestTimestamp - 3600;

        // Filter logs within the last hour
        List<LogEntry> recentLogs = new ArrayList<>();
        for (LogEntry log : logs) {
            if (log.timestamp > oneHourAgo) {
                recentLogs.add(log);
            }
        }

        // Sort logs by timestamp in ascending order
        recentLogs.sort(Comparator.comparingInt(log -> log.timestamp));

        // Get the last 'm' logs
        List<LogEntry> latestMLogs = recentLogs.subList(Math.max(0, recentLogs.size() - m), recentLogs.size());

        // Build a comma-separated string of log IDs
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < latestMLogs.size(); i++) {
            if (i > 0) result.append(",");
            result.append(latestMLogs.get(i).logId);
        }

        return result.toString();
    }

    // Method to get the count of logs received within the last hour
    public int getLogCount() {
        if (logs.isEmpty()) return 0; // If no logs, return 0

        int latestTimestamp = logs.getLast().timestamp; // Get the most recent timestamp
        int oneHourAgo = latestTimestamp - 3600;

        // Count logs within the last hour
        int count = 0;
        for (LogEntry log : logs) {
            if (log.timestamp > oneHourAgo) {
                count++;
            }
        }
        return count;
    }

    // Inner class to represent a log entry
    private static class LogEntry {
        int logId;
        int timestamp;

        LogEntry(int logId, int timestamp) {
            this.logId = logId;
            this.timestamp = timestamp;
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        LogServer logServer = new LogServer(10);

        logServer.recordLog(1, 0);
        logServer.recordLog(2, 300);
        System.out.println(logServer.getLogs()); // 输出: 1,2
        System.out.println(logServer.getLogCount()); // 输出: 2

        logServer.recordLog(3, 1200);
        logServer.recordLog(1, 1800);
        System.out.println(logServer.getLogs()); // 输出: 1 2,3,1
        System.out.println(logServer.getLogCount()); // 输出: 4

        logServer.recordLog(4, 3900);
        System.out.println(logServer.getLogs()); // 输出: 3,1,4 3 (logs in the last hour)
    }
}
