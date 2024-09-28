package com.leetcode2.org.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Test1 {
    public static void main(String[] args) {
        Set set= new HashSet();
        set.add("aa");
        set.add(123);
        set.add("bb");
        set.add("aa");
        set.add(new Person("dkl",12));
        set.add(new Person("dkl",12));
        System.out.println(set.toString());

        Set set2 = new LinkedHashSet();
        set2.add("aa");
        set2.add(123);
        set2.add("bb");
        set2.add("aa");
        set2.add(new Person("dkl",12));
        set2.add(new Person("dkl",12));
        System.out.println(set2);
        Set set3= new TreeSet();


    }
}
