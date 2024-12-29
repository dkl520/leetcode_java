package com.leetcode2.org.数值运算;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution575 {



    public int distributeCandies(int[] candyType) {
        Set<Integer> differentType= new HashSet<>();
        differentType = Arrays.stream(candyType).boxed().collect(Collectors.toSet());
        // Collections.addAll(differentType,candyType);


        int halfNum= candyType.length/2;
        if(halfNum>differentType.size()){
            return  differentType.size();
        }
        return halfNum;


    }
}
