package com.leetcode2.org.大厂.citadel;

import java.util.*;

public class PrimeFactorVisitation {
    int[] getPrimeFactorVisitation(int[] states, int[] numbers) {
        int n = states.length;
        boolean[] isPrime = new boolean[n];
        Set<Integer> list = new HashSet<>();
        List<Long> primes = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> stateChangedList = new ArrayList<>();
        for (int number : numbers) {
            Set<Long> set = getPrimeFactors(number);
            for (Long prime : set) {
                for (int i = 1; i <= n; i++) {
                    if (i % prime == 0) {
                        map.put(i, map.getOrDefault(i, 0) + 1);
                    }
                }
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int index = entry.getKey();
            int count = entry.getValue();
            if (count % 2 == 1) {
                stateChangedList.add(index);
            }
        }
        for (Integer prime : stateChangedList) {
            if ((int) (prime - 1L) < states.length) {
                states[(int) (prime - 1L)] = states[(int) (prime - 1L)] == 1 ? 0 : 1;
            }
        }
        return states;
    }

    public Set<Long> getPrimeFactors(long n) {
        Set<Long> factors = new HashSet<>();
        // 处理2这个特殊的偶数
        while (n % 2 == 0) {
            factors.add(2L);
            n /= 2;
        }

        // 只需要检查到sqrt(n)的奇数
        for (long i = 3; i * i <= n; i += 2) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }

        // 如果最后剩余的n大于1，那么它本身就是质数
        if (n > 1) {
            factors.add(n);
        }

        return factors;
    }


    public static void main(String[] args) {
        int[] states = new int[]{1, 1, 0, 0, 1, 1, 0, 1, 1, 1};
        int[] numbers = new int[]{3, 4, 15};
        PrimeFactorVisitation visitation = new PrimeFactorVisitation();
        System.out.println(
                Arrays.toString(
                        (visitation.getPrimeFactorVisitation(states, numbers)
                        ))
        );


    }


}
