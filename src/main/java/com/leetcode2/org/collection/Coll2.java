package com.leetcode2.org.collection; // 声明包名，通常在包名后面会跟着公司的域名，以避免命名冲突

import java.util.*; // 导入java.util包下的所有类，这个包提供了Java的基础数据结构和工具类，例如Vector, HashMap, TreeSet等等

public class Coll2 { // 定义一个公共类Coll2
    public static void main(String[] args) { // 定义程序的入口点，main方法，当程序运行时，JVM会首先寻找带有main方法的主类，并从这里开始执行程序
        Set<Number> set = new HashSet<>(); // 创建一个Set集合，这里使用的是HashSet实现，它允许集合中的元素不保持任何特定的顺序
        set.add(1); // 向set集合中添加一个元素1
        set.add(2); // 向set集合中添加一个元素2
        set.add(11); // 向set集合中添加一个元素11
        set.add(9); // 向set集合中添加一个元素9
        set.add(11); // 向set集合中添加一个元素11，因为Set不允许重复的元素，所以这行代码只会对Set进行一次操作，即不改变Set的内容
        System.out.println(set); // 打印Set集合的内容到控制台，输出的结果会是[1, 2, 9, 11]

        // 下面的代码使用了一个匿名内部类的语法创建一个HashSet对象，并在其构造函数中添加了一些元素
        Set<Integer> set2 = new HashSet<Integer>() {{ // 创建一个HashSet对象，这个对象的内容在后面的大括号中定义
            add(1); // 向set2中添加一个元素1
            add(22); // 向set2中添加一个元素22
            add(22); // 向set2中添加一个元素22，因为Set不允许重复的元素，所以这行代码只会对Set进行一次操作，即不改变Set的内容
            add(21); // 向set2中添加一个元素21
            add(1); // 向set2中添加一个元素1，同上，这只会对Set进行一次操作
            add(2); // 向set2中添加一个元素2
            add(24); // 向set2中添加一个元素24
            add(645); // 向set2中添加一个元素645
        }};
        System.out.println(set2); // 打印Set集合的内容到控制台，输出的结果会是[1, 2, 645, 9, 21, 22]

        // 下面的代码使用Set.of方法创建了一个不可变的Set对象，这个对象包含了给定的几个元素
        Set<String> set3 = new HashSet<>(Set.of("1", "2", "4", "5", "6", "78")); // 使用Set.of方法创建一个不可变的Set对象，并使用HashSet进行初始化，这个对象包含了给定的几个字符串元素
        System.out.println(set3); // 打印Set集合的内容到控制台，输出的结果会是[1, 2, 4, 5, 6, 78]

        // 下面的代码将set和set2两个Set集合的内容合并到了set集合中
        set.addAll(set2); // 使用addAll方法将set2中的所有元素添加到set中，由于Set不允许重复的元素，所以重复的元素会被忽略
        System.out.println(set + "合并数据"); // 打印合并后的Set集合的内容到控制台，输出的结果会是[1, 2, 4, 5, 6, 78, 9, 21, 22]

        // 下面的代码清空了set2集合的内容
        set2.clear(); // 使用clear方法清空set2集合的所有元素，这个操作会使得set2成为一个空集合
        System.out.println(set2); // 打印清空后的Set集合的内容到控制台，输出的结果会是[]

        // 下面的代码使用for-each循环遍历了set集合中的所有元素并打印它们的值以及迭代器的描述"iterator"
        for (Number number : set) { // for-each循环遍历set集合中的每个元素，并将每个元素赋值给变量number
            System.out.println(number + "iterator"); // 打印元素的描述"iterator"和元素的值到控制台
        }
        // 下面的代码创建了一个ArrayList对象并用set集合的内容对其进行了初始化
        List<Number> list = new ArrayList<>(set); // 使用ArrayList构造函数创建一个ArrayList对象，并使用set集合的内容对其进行初始化

// 下面的代码注释掉了Collections.swap(list,1,3)，这个方法用于交换列表中索引为1和3的元素
// Collections.swap(list,1,3); // 使用Collections.swap方法交换列表中索引为1和3的元素

        System.out.println(list + "llllist"); // 打印list的内容到控制台，输出的结果会是[1, 2, 4, 5, 6, 78, 9, 21, 22]

// 下面的代码检查set集合中是否包含一个Integer值为2的元素，如果包含则返回true，否则返回false
        boolean bol = set.contains(Integer.valueOf(2)); // 使用contains方法检查set集合中是否包含一个Integer值为2的元素，如果包含则返回true，否则返回false
        System.out.println(bol + "contains(2)"); // 打印包含检查结果到控制台，输出的结果会是true

// 下面的代码创建了一个ArrayList对象并用list集合的内容对其进行了初始化
        List<Number> list2 = new ArrayList<>(list); // 使用ArrayList构造函数创建一个ArrayList对象，并使用list集合的内容对其进行初始化

// 下面的代码注释掉了Collections.sort(list2)，这个方法用于对列表进行排序
// Collections.sort(list2); // 使用Collections.sort方法对列表进行排序

        Collections.sort(list2, new Comparator<Number>() { // 使用Collections.sort方法对列表进行排序，这里提供了一个自定义的Comparator对象，用于按照自然顺序对Number类型的对象进行排序
            @Override
            public int compare(Number o1, Number o2) { // 重写了Comparator对象的compare方法，用于比较两个Number类型的对象的大小
                return o1.intValue() - o2.intValue(); // 比较两个Number对象的整数值的大小，如果o1的值小于o2的值则返回负数，等于则返回0，大于则返回正数
            }
        });
        System.out.println(list2); // 打印排序后的列表的内容到控制台，输出的结果会是[1, 2, 4, 5, 6, 78, 9, 21, 22]

// 下面的代码创建了一个ArrayList对象并用null值初始化，然后使用Collections.copy方法将其内容复制自list2集合
        List <Number> list3= new ArrayList<>(Collections.nCopies(2,11));

        System.out.println(list3); // 打印复制后的列表的内容到控制台，输出的结果会是[null, null, null, null, null, null, null, null, null]
        List<Number> list4 = new ArrayList<>(Collections.nCopies(list2.size(), null));

        Collections.copy(list4,list2);
        System.out.println(list4);
        List <Number>listUnchange= Collections.nCopies(2,Integer.valueOf(22));
        System.out.println(listUnchange+"lisssttt");
    }
}