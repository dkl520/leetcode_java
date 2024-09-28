package com.leetcode2.org.哈希表;

import java.util.HashMap;
import java.util.Map;

public class Solution217 {

    public boolean containsDuplicate(int[] nums) {
            Map<Integer,Boolean> map = new HashMap<Integer,Boolean>();
            for(int num : nums){
                if(map.containsKey(num)){
                    return true;
                }
                map.put(num,true);
            }
            return false;
    }


}
