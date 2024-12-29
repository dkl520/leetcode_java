package com.leetcode2.org.大厂.optiver;

import java.util.Random;


public class LogServerTest {
    public static void main(String[] args) {
        // 创建日志服务器，设置时间窗口为1小时（3600秒）
        NavigableMapLogServer logServer = new NavigableMapLogServer(7200);
//        LogServer logServer = new LogServer(7200);
        // 创建随机数生成器
        Random random = new Random();
        // 测试参数
        int totalTests = 1000000;  // 10万次测试
        int timeSpan = 7200;      // 2小时的时间跨度（秒）
        // 性能测试开始时间
        long startTime = System.currentTimeMillis();
        // 生成大量随机日志记录
        for (int i = 1; i <= totalTests; i++) {
            // 生成随机时间戳（0到7200秒之间）
            int timestamp = random.nextInt(timeSpan);
            // 记录日志
            logServer.recordLog(i, timestamp);
            // 每10000次操作打印一次状态
            if (i % 10000 == 0) {
                System.out.println("已处理 " + i + " 条记录");
                System.out.println("当前最近一小时内的所有日志数量: " + logServer.getLogCount());
                System.out.println("时间窗口内的日志: 当前时间下，从前一个小时的正序，的最大m 条的数据 " + logServer.getLogs());
                System.out.println("--------------------");
            }
        }
        // 性能测试结束时间
        long endTime = System.currentTimeMillis();
        // 打印最终结果和性能数据
        System.out.println("\n测试完成!");
        System.out.println("总处理记录数: " + totalTests);
        System.out.println("最终日志数量: " + logServer.getLogCount());
        System.out.println("处理耗时: " + (endTime - startTime) + " 毫秒");
        System.out.println("平均每条记录处理时间: " +
                String.format("%.2f", (double) (endTime - startTime) / totalTests) + " 毫秒");
        System.out.println("最终时间窗口内的日志: " + logServer.getLogs());
    }
}