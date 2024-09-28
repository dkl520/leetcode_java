package com.leetcode2.org.滚动哈希;


public class Solution2156 {
    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {

        long[] powerList = new long[k + 1];
        powerList[0] = 1;
        for (int i = 1; i < k; i++) {
            powerList[i] = (power * powerList[i - 1]) % modulo;
        }
        for (int i = 0; i < s.length() && i + k <= s.length(); i++) {
            String subStr = s.substring(i, i + k);
            int subStrHash = calcHash(powerList, modulo, subStr);
            if (subStrHash == hashValue) {
                return subStr;
            }
        }
//        there is no sense
        return "zxz";
    }

    int calcHash(long[] powerList, int modulo, String s) {
        long hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash = (hash + powerList[i] * (s.charAt(i) - 'a' + 1)) % modulo;
        }
        return (int) hash;
    }


    public static void main(String[] args) {
//        String s = "xmmhdakfursinye";
//        int power = 96;
//        int modulo = 45;
//        int k = 15;
//        int hashValue = 21;
        String s = "leetcode";
        int power = 7;
        int modulo = 20;
        int k = 2;
        int hashValue = 0;
        Solution2156 solution = new Solution2156();
        System.out.println(solution.subStrHash(s, power, modulo, k, hashValue));
    }
}
