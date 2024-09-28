package com.leetcode2.org.set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetList {
    public static void main(String[] args) {
        ArrayList list= new ArrayList<Integer>();
        list.add(11);
        list.add(11);
        list.add(12);
        list.add(13);
        list.add(13);
        list.add(13);
        list.add(13);
//      Set newList= duplicateLIst(list);
        Set set = new HashSet(list);
        System.out.println(set);
        list= new ArrayList(set);
        System.out.println(list);

    }
    public static Set duplicateLIst(ArrayList list){
         Set set= new HashSet(list);
        System.out.println(set);
         return  set;
    }
}



