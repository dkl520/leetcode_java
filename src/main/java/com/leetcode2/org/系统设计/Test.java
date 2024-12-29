package com.leetcode2.org.系统设计;

import java.util.TreeSet;

public class Test {
    static class Numbers {
        Integer num;

        public Numbers(Integer num) {
            this.num = num;
        }
    }

    public static void main(String[] args) {
        TreeSet<Numbers> set = new TreeSet<>((n1, n2) -> n1.num.compareTo(n2.num));
        Numbers n1 = new Numbers(1);
        Numbers n2 = new Numbers(2);
        Numbers n3 = new Numbers(3);
        set.add(n2);
        set.add(n1);
        n1.num = 3;
        set.remove(n1);
        System.out.println(set.first().num.toString());

    }


}
