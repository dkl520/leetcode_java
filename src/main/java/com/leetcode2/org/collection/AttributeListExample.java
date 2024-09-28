package com.leetcode2.org.collection;

import javax.management.Attribute;
import javax.management.AttributeList;
public class AttributeListExample {
    public static void main(String[] args) {
        AttributeList attributeList = new AttributeList();
        attributeList.add(new Attribute("Name", "John DOE"));
        attributeList.add(new Attribute("age", 30));
        attributeList.add(new Attribute("salary", 5000.0));

        System.out.println(attributeList);
        boolean containsSalary= attributeList.contains(new Attribute("salary",5000.0));
        System.out.println(containsSalary);
//        attributeList.add(2222,222);
//        attributeList.add(222);
        System.out.println(attributeList);
        attributeList.remove(1);
        System.out.println(attributeList);
        System.out.println(attributeList.size());
        for (Object a:
             attributeList) {
            Attribute attribute= (Attribute)a;
            System.out.println(attribute.getName());
            System.out.println(attribute.getValue());
        }
    }
}
