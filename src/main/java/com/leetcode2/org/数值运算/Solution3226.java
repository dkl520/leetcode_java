package com.leetcode2.org.数值运算;

public class Solution3226 {
    public int minChanges(int n, int k) {
        String strN = Integer.toBinaryString(n);
        String strK = Integer.toBinaryString(k);
        int dis = strN.length() - strK.length();
        if(dis>0){
            strK ="0".repeat(dis) + strK ;
        }
        int l = strN.length();
        int count = 0;
        StringBuilder strKB = new StringBuilder(strK);
        for (int i = 0; i < l; i++) {
            if (strN.charAt(i) == '1' && strK.charAt(i) == '0') {
                strKB.setCharAt(i, '1');
                count++;
            }
        }
        if (strN.contentEquals(strKB)) {
            return count;
        }
        return -1;
    }

    public static void main(String[] args) {
        int n=44; int k=2;
        System.out.println(new Solution3226().minChanges(n, k));
    }
}
