package com.leetcode2.org.数据结构;

import java.util.*;
import java.util.concurrent.*;

public class ComprehensiveDataStructuresExample {

    enum Day {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY}

    public static void main(String[] args) {
        // 1. 树结构相关
        treeStructures();

        // 2. 队列和双端队列
        queuesAndDeques();

        // 3. 并发数据结构
        concurrentCollections();

        // 4. 集合类
        setListAndMap();

        // 5. 双端链表
        linkedListExample();

        // 6. Stack
        stackExample();

        // 7. BitSet
        bitSetExample();

        // 8. EnumSet / EnumMap
        enumSetAndMapExample();

        // 9. 其他数据结构
        otherDataStructures();
    }

    private static void treeStructures() {
        System.out.println("\n--- Tree Structures ---");

        TreeSet<Integer> treeSet = new TreeSet<>(Comparator.comparingInt((a) -> a));
        treeSet.add(3);
        treeSet.add(1);
        treeSet.add(2);
        System.out.println("TreeSet: " + treeSet);  // 输出: [1, 2, 3]

        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("C", 3);
        treeMap.put("A", 1);
        treeMap.put("B", 2);
        System.out.println("TreeMap: " + treeMap);  // 输出: {A=1, B=2, C=3}
    }

    private static void queuesAndDeques() {
        System.out.println("\n--- Queues and Deques ---");

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(3);
        priorityQueue.offer(1);
        priorityQueue.offer(2);
        System.out.println("PriorityQueue: " + priorityQueue);  // 输出可能是 [1, 3, 2]
        System.out.println("PriorityQueue poll: " + priorityQueue.poll());  // 输出: 1

        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        arrayDeque.addFirst("First");
        arrayDeque.addLast("Last");
        System.out.println("ArrayDeque: " + arrayDeque);  // 输出: [First, Last]
    }

    private static void concurrentCollections() {
        System.out.println("\n--- Concurrent Collections ---");

        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("A", 1);
        concurrentMap.put("B", 2);
        System.out.println("ConcurrentHashMap: " + concurrentMap);

        CopyOnWriteArrayList<String> copyOnWriteList = new CopyOnWriteArrayList<>();
        copyOnWriteList.add("Item 1");
        copyOnWriteList.add("Item 2");
        System.out.println("CopyOnWriteArrayList: " + copyOnWriteList);

        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
        blockingQueue.offer("Task 1");
        blockingQueue.offer("Task 2");
        System.out.println("LinkedBlockingQueue: " + blockingQueue);
    }

    private static void setListAndMap() {
        System.out.println("\n--- Set, List, and Map ---");

        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("Apple");
        hashSet.add("Banana");
        hashSet.add("Apple");  // 重复元素不会被添加
        System.out.println("HashSet: " + hashSet);

        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("C");
        linkedHashSet.add("A");
        linkedHashSet.add("B");
        System.out.println("LinkedHashSet: " + linkedHashSet);  // 保持插入顺序

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        System.out.println("ArrayList: " + arrayList);

        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("One", 1);
        hashMap.put("Two", 2);
        System.out.println("HashMap: " + hashMap);

        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("A", 1);
        linkedHashMap.put("C", 3);
        linkedHashMap.put("B", 2);
        System.out.println("LinkedHashMap: " + linkedHashMap);  // 保持插入顺序
    }

    private static void linkedListExample() {
        System.out.println("\n--- Linked List ---");

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("First");
        linkedList.addLast("Last");
        linkedList.addFirst("Very First");
        System.out.println("LinkedList: " + linkedList);
    }

    private static void stackExample() {
        System.out.println("\n--- Stack ---");

        Stack<String> stack = new Stack<>();
        stack.push("Bottom");
        stack.push("Middle");
        stack.push("Top");
        System.out.println("Stack: " + stack);
        System.out.println("Stack pop: " + stack.pop());
    }

    private static void bitSetExample() {
        System.out.println("\n--- BitSet ---");

        BitSet bitSet = new BitSet(8);
        bitSet.set(0);
        bitSet.set(2);
        bitSet.set(4);
        bitSet.set(6);
        System.out.println("BitSet: " + bitSet);
    }

    private static void enumSetAndMapExample() {
        System.out.println("\n--- EnumSet and EnumMap ---");

        EnumSet<Day> weekend = EnumSet.of(Day.SATURDAY, Day.SUNDAY);
        System.out.println("EnumSet (weekend): " + weekend);

        EnumMap<Day, String> daySchedule = new EnumMap<>(Day.class);
        daySchedule.put(Day.MONDAY, "Work");
        daySchedule.put(Day.SATURDAY, "Relax");
        System.out.println("EnumMap: " + daySchedule);
    }

    private static void otherDataStructures() {
        System.out.println("\n--- Other Data Structures ---");

        Hashtable<String, Integer> hashtable = new Hashtable<>();
        hashtable.put("A", 1);
        hashtable.put("B", 2);
        System.out.println("Hashtable: " + hashtable);
    }
}