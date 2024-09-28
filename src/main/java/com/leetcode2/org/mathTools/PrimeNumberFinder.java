package com.leetcode2.org.mathTools;
import java.util.ArrayList;
import java.util.List;

public class PrimeNumberFinder {
    public static void main(String[] args) {
        int limit = 100000000;
        int numThreads = 32;
        long startTime =System.currentTimeMillis();

        System.out.println("Prime numbers between 1 and " + limit + ":");

        List<Thread> threads = new ArrayList<>();
        List<PrimeNumberCalculator> calculators = new ArrayList<>();

        int chunkSize = limit / numThreads;
        int start = 1;
        int end = chunkSize;

        // 创建并启动线程
        for (int i = 0; i < numThreads; i++) {
            if (i == numThreads - 1) {
                // 最后一个线程处理剩余的数字
                end = limit;
            }

            PrimeNumberCalculator calculator = new PrimeNumberCalculator(start, end);
            calculators.add(calculator);

            Thread thread = new Thread(calculator);
            threads.add(thread);

            thread.start();

            start = end + 1;
            end += chunkSize;
        }

        // 等待所有线程完成
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 打印结果
        for (PrimeNumberCalculator calculator : calculators) {
            for (int prime : calculator.getPrimeNumbers()) {
                System.out.print(prime + " ");
            }
        }
        long endTime =System.currentTimeMillis();
        long runTime = endTime-startTime;
        System.out.println("程序运行时间："+runTime/1000);
    }

    // 用于计算质数的线程
    static class PrimeNumberCalculator implements Runnable {
        private int start;
        private int end;
        private List<Integer> primeNumbers;

        public PrimeNumberCalculator(int start, int end) {
            this.start = start;
            this.end = end;
            this.primeNumbers = new ArrayList<>();
        }

        @Override
        public void run() {
            for (int number = start; number <= end; number++) {
                if (isPrime(number)) {
                    primeNumbers.add(number);
                }
            }
        }

        public List<Integer> getPrimeNumbers() {
            return primeNumbers;
        }

        private boolean isPrime(int number) {
            if (number <= 1) {
                return false;
            }

            // 从 2 到平方根之间进行试除
            int sqrt = (int) Math.sqrt(number);
            for (int i = 2; i <= sqrt; i++) {
                if (number % i == 0) {
                    return false;
                }
            }

            return true;
        }

    }
}