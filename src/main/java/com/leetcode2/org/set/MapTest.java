package com.leetcode2.org.set;

import java.util.*;

public class MapTest {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put(null, null);
        System.out.println(map);
        Map mapTable = new Hashtable();
        mapTable.put("aa", 1213);
        System.out.println(mapTable);
        LinkedHashMap lhp = new LinkedHashMap();
        lhp.put("tom", 123);
        lhp.put(21, 122223);
        lhp.put(21, 123);
        lhp.put("t2222", 1223);
        lhp.remove(21);
//        System.out.println(lhp.get("tom"));
        lhp.put(32, new Date().getTime());
//        System.out.println(lhp);

        for (Object obj :
                lhp.keySet()) {
            System.out.println(obj);
        }
//        Map.Entry是Java中的一个接口，它用于表示Map中的键值对。在Java的标准库中，Map.Entry接口定义了两个主要的方法：getKey()和getValue()。
//        这些方法允许我们获取Map中每个键值对的键和值。
//
//        Map.Entry接口通常与Java中的Map接口一起使用。例如，当我们使用HashMap类时，我们可以通过调用entrySet()方法来获取一个包含所有键值对的Set对象。
//        这个Set对象中的每个元素都是一个Map.Entry对象。
//        Collection Keys = lhp.keySet()
        Object[] value = lhp.values().toArray();
        for (Object obj :
                value) {
            System.out.println(obj);
        }
             Set  entrySet =lhp.entrySet();
//            Iterator iterator = entrySet.iterator();
//            while (iterator.hasNext()){
//                Map.Entry entry=(Map.Entry) iterator.next();
//                System.out.println(entry.getKey()+"---->"+entry.getValue()+"class:"+entry.getClass());
//            }
        for (Object entry:
             entrySet) {
            Map.Entry newEntry= (Map.Entry) entry;
            System.out.println(newEntry.getKey()+"---->"+newEntry.getValue()+"class:"+newEntry.getClass());
        }
//        System.out.println(value.toString());
    }

}
