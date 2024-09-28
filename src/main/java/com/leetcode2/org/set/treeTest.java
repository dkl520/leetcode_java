package com.leetcode2.org.set;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class treeTest {
    public static void main(String[] args) {

//        Comparator comparator = new Comparator() {
//            @Override
//            public int compare(Object o1, Object o2) {
//                if (o1 instanceof Person && o2 instanceof Person) {
//                    Person p1 = (Person) o1;
//                    Person p2 = (Person) o2;
//                    int value = p1.getName().compareTo(p2.getName());
//                    if (value != 0) {
//                        return value;
//                    }
//                    return ((Person) o1).getAge() - ((Person) o2).getAge();
//
//                }
//                throw  new RuntimeException("类型不匹配");
//            }
//        };
        Comparator<Person> comparator = (o1, o2) -> {
            if (!(o1 instanceof Person) || !(o2 instanceof Person)) {
                throw new RuntimeException("类型不匹配");
            }
            Person p1 = (Person) o1;
            Person p2 = (Person) o2;
            int nameComparison = p1.getName().compareTo(p2.getName());
            if (nameComparison != 0) {
                return nameComparison;
            }
            return p1.getAge() - p2.getAge();
        };

//        Set set = new TreeSet();
//        set.add("aaa");
//        set.add("12222");
////        set.add(new Person("DKL", 21));
//        Iterator iterator = set.iterator();
//        for (Object obj :
//                set) {
//            System.out.println(obj);
//        }
        Set pp = new TreeSet<Person>(comparator);
        Person p1 = new Person("p1", 11);
        Person p2 = new Person("p2", 11);
        Person p3 = new Person("p3", 13);
        Person p5 = new Person("p5", 17);
        Person p4 = new Person("p4", 112);
        pp.add(p1);
        pp.add(p2);
        pp.add(p3);
        pp.add(p4);
        pp.add(p5);
        System.out.println(pp);

    }
}
