// 声明一个名为org.collection的包
package com.leetcode2.org.collection;

// 导入所需的Java管理扩展（Java Management Extensions，JMX）类
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

// 定义一个名为MemoryMonitoringExample的公共类
public class MemoryMonitoringExample {

    // 定义程序的入口点，一个名为main的方法，该方法接收一个String数组作为参数
    public static void main(String[] args) {
        // 从ManagementFactory获取一个MemoryMXBean实例，该实例提供了访问内存管理信息的接口
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

        // 使用MemoryMXBean实例获取堆内存的使用情况
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        System.out.println("Heap Memory Usage:");  // 输出“Heap Memory Usage:”到控制台
        System.out.println("   Initial: " + heapMemoryUsage.getInit());  // 输出堆内存的初始大小
        System.out.println("   Used: " + heapMemoryUsage.getUsed());  // 输出当前已使用的堆内存大小
        System.out.println("   Committed: " + heapMemoryUsage.getCommitted());  // 输出已提交给JVM的堆内存大小
        System.out.println("   Max: " + heapMemoryUsage.getMax());  // 输出可使用的最大堆内存大小

        // 使用MemoryMXBean实例获取非堆内存的使用情况
        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();
        System.out.println("Non-Heap Memory Usage:");  // 输出“Non-Heap Memory Usage:”到控制台
        System.out.println("   Initial: " + nonHeapMemoryUsage.getInit());  // 输出非堆内存的初始大小
        System.out.println("   Used: " + nonHeapMemoryUsage.getUsed());  // 输出当前已使用的非堆内存大小
        System.out.println("   Committed: " + nonHeapMemoryUsage.getCommitted());  // 输出已提交给JVM的非堆内存大小
        System.out.println("   Max: " + nonHeapMemoryUsage.getMax());  // 输出可使用的最大非堆内存大小

        // 初始化两个长整型变量，用于累计垃圾回收的次数和时间
        long gcCount = 0;
        long gcTime = 0;
        // 通过ManagementFactory获取所有的GarbageCollectorMXBean实例，这些实例提供了关于垃圾回收的信息
        for (java.lang.management.GarbageCollectorMXBean gcBean : ManagementFactory.getGarbageCollectorMXBeans()) {
            gcCount += gcBean.getCollectionCount();  // 累加每个垃圾回收器的回收次数
            gcTime += gcBean.getCollectionTime();  // 累加每个垃圾回收器的回收时间，单位是毫秒
        }
        System.out.println("Garbage Collection:");  // 输出“Garbage Collection:”到控制台
        System.out.println("   Count: " + gcCount);  // 输出总的垃圾回收次数
        System.out.println("   Time: " + gcTime + " ms");  // 输出总的垃圾回收时间，单位是毫秒
    }
}