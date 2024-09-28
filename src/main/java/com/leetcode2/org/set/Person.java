package com.leetcode2.org.set;

import java.util.Comparator;
import java.util.Objects;

public class Person implements Comparable {
    String name;
    int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public int compareTo(Object o) {
        if (this==o){
            return 0;
        }
        if(o instanceof Person){
            return  this.age- ((Person) o).age;
        }
         throw  new RuntimeException("类型不匹配");

    }
}
