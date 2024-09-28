package com.leetcode2.org.stream;

import com.leetcode2.org.enum2.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Stream {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("aa", "bb", "cc");
        list.stream().map(String::toUpperCase).forEach(System.out::println);

        List<Employee> employeeList = Arrays.asList(
                new Employee("John Doe", 25, 50000.0, 1),
                new Employee("Jane Smith", 30, 60000.0, 2),
                new Employee("Bob Johnson", 28, 55000.0, 3),
                new Employee("Alice Williams", 35, 70000.0, 4),
                new Employee("Tom Brown", 22, 48000.0, 5),
                new Employee("Sara Davis", 32, 62000.0, 6),
                new Employee("Mike Wilson", 27, 53000.0, 7),
                new Employee("Emily Taylor", 29, 58000.0, 8),
                new Employee("Chris Anderson", 33, 65000.0, 9),
                new Employee("Olivia Miller", 26, 51000.0, 10)
        );
        System.out.println(employeeList.stream().allMatch(emp -> emp.getAge() > 18));
        System.out.println(employeeList.stream().findFirst());
//        System.out.println(employeeList.stream().sorted(Comparator.comparingInt(Employee::getAge).reversed()).toList());
//        System.out.println(employeeList.stream().sorted(Comparator.comparingDouble(Employee::getSalary)).toList());

        System.out.println(employeeList.stream().max(Comparator.comparingDouble(Employee::getSalary)));

        System.out.println(employeeList.stream().mapToDouble(Employee::getSalary).reduce(0, (s1, s2) -> Double.sum(s2, s1)));
        System.out.println(employeeList.stream().mapToDouble(Employee::getSalary).sum());

//        System.out.println(employeeList.stream().max(Comparator.comparingDouble(Employee::getSalary)));
        System.out.println(employeeList.stream().mapToDouble(Employee::getSalary).max().getAsDouble());
        List<Integer> list1 = Arrays.asList(1, 3, 5, 6, 7, 8, 9, 11, 1);
        System.out.println(list1.stream().reduce(0, Integer::sum));


    }
}
