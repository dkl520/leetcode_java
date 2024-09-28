package com.leetcode2.org.滑动窗口;

import java.util.*;

public class Solution480_2 {
    static class SlidingWindowMedian {
        // 小顶堆，用于存放当前窗口内较大的一半元素（但因为是小顶堆，所以实际存储的元素值相对较大）
         PriorityQueue<Integer> small;
        // 大顶堆，用于存放当前窗口内较小的一半元素（因为是大顶堆，所以堆顶元素是这一半中最大的）
         PriorityQueue<Integer> large;
        // 延迟删除的元素计数，用于处理在removeNum时并不直接从堆中移除元素，而是延迟到下一次balance时处理
         HashMap<Integer, Integer> delayed;
        // 窗口大小
         int k;
        // 小顶堆当前存储的元素数量
         int smallSize;
        // 大顶堆当前存储的元素数量
         int largeSize;

        // 构造函数，初始化两个堆和延迟删除元素计数，设置窗口大小
        public SlidingWindowMedian(int k) {
            // 小顶堆使用Collections.reverseOrder()实现从大到小排序
            this.small = new PriorityQueue<>(Collections.reverseOrder());
            // 大顶堆默认从小到大排序
            this.large = new PriorityQueue<>();
            this.delayed = new HashMap<>();
            this.k = k;
            this.smallSize = 0;
            this.largeSize = 0;
        }

        // 向滑动窗口中添加一个新元素
        public void addNum(int num) {
            // 如果小顶堆为空，或者新元素小于等于小顶堆的堆顶元素（即当前窗口中的最大值），则加入小顶堆
            if (small.isEmpty() || num <= small.peek()) {
                small.offer(num);
                smallSize++;
            } else {
                // 否则，加入大顶堆
                large.offer(num);
                largeSize++;
            }
            // 调用balance方法确保两个堆的大小关系正确
            balance();
        }

        // 从滑动窗口中移除一个元素
        public void removeNum(int num) {
            // 在延迟删除的元素计数中增加该元素的计数
            delayed.put(num, delayed.getOrDefault(num, 0) + 1);
            // 如果要移除的元素小于等于小顶堆的堆顶元素，则减少小顶堆的计数
            if (num <= small.peek()) {
                smallSize--;
                // 如果该元素就是小顶堆的堆顶元素，则可能需要从堆中移除（通过prune方法）
                if (num == small.peek()) {
                    prune(small);
                }
            } else {
                // 否则，减少大顶堆的计数
                largeSize--;
                // 如果该元素就是大顶堆的堆顶元素，则可能需要从堆中移除（通过prune方法）
                if (num == large.peek()) {
                    prune(large);
                }
            }
            // 调用balance方法确保两个堆的大小关系正确
            balance();
        }

        // 返回当前滑动窗口的中位数
        public double findMedian() {
            // 如果窗口元素个数为偶数，则中位数为两个中间元素的平均值
            if (k % 2 == 0) {
                return ((double) small.peek() + large.peek()) / 2.0;
            } else {
                // 如果窗口元素个数为奇数，则中位数为小顶堆的堆顶元素
                return (double) small.peek();
            }
        }

        // 清理堆顶元素中的延迟删除元素
        private void prune(PriorityQueue<Integer> heap) {
            while (!heap.isEmpty() && delayed.containsKey(heap.peek())) {
                int num = heap.poll();
                delayed.put(num, delayed.get(num) - 1);
                if (delayed.get(num) == 0) {
                    delayed.remove(num);
                }
            }
        }

        // 调整两个堆的大小，确保小顶堆的大小大于等于大顶堆的大小
        private void balance() {
            while (smallSize > largeSize + 1) {
                large.offer(small.poll());
                smallSize--;
                largeSize++;
                prune(small); // 清理小顶堆中的延迟删除元素
            }
            while (largeSize > smallSize) {
                small.offer(large.poll());
                largeSize--;
                smallSize++;
                prune(large); // 清理大顶堆中的延迟删除元素
            }
        }
    }

    // 滑动窗口中位数的主函数
    public double[] medianSlidingWindow(int[] nums, int k) {
        SlidingWindowMedian swm = new SlidingWindowMedian(k); // 创建滑动窗口中位数对象
        double[] result = new double[nums.length - k + 1]; // 存放结果的数组

        for (int i = 0; i < nums.length; i++) {
            swm.addNum(nums[i]); // 向滑动窗口中添加新元素
            if (i >= k - 1) {
                result[i - k + 1] = swm.findMedian(); // 计算当前窗口的中位数并存入结果数组
                int removeTarget = nums[i - k + 1];
                swm.removeNum(removeTarget); // 移除滑动窗口最左侧的元素
                System.out.println(swm);
            }
        }

        return result; // 返回结果数组
    }

    // 测试主函数
    public static void main(String[] args) {
        int[] nums = {2,1,3,4,5,6,3, 7};
        int k = 6;
        Solution480_2 solution480_2 = new Solution480_2();

        double[] result = solution480_2.medianSlidingWindow(nums, k);
        System.out.println(Arrays.toString(result)); // 输出: [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]
    }
}
