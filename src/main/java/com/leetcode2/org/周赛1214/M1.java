package com.leetcode2.org.周赛1214;

public class M1 {

    public static int solution(String[] instructions) {
        int start = 0;
        for (String ins : instructions) {
            if (ins.equals("++")) {
                start++;
            } else {
                start--;
            }
        }

        return start;
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"++", "--", "++"}) == 1);
        System.out.println(solution(new String[]{"++", "++", "--", "--"}) == 0);
        System.out.println(solution(new String[]{"++", "++", "--"}) == 1);
    }
}
