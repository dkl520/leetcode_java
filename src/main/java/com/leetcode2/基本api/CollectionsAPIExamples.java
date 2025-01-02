package com.leetcode2.基本api;

import java.util.*;

public class CollectionsAPIExamples {
    public static void main(String[] args) {
        // Collections API Examples

        // 1. 创建和初始化
        List<String> list = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry"));
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));
        Map<String, Integer> map = new HashMap<>();
        map.put("Alice", 25);
        map.put("Bob", 30);

        // 2. 基本操作
        list.add("Date");
        set.add(4);
        map.put("Charlie", 35);

        System.out.println(list); // 输出: [Apple, Banana, Cherry, Date]
        System.out.println(set); // 输出: [1, 2, 3, 4]
        System.out.println(map); // 输出: {Alice=25, Bob=30, Charlie=35}

        // 3. 遍历
        for (String item : list) {
            System.out.println(item);
        }

        for (int numSet : set) {
            System.out.println(numSet);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // 4. 排序
        Collections.sort(list);
        System.out.println(list); // 输出: [Apple, Banana, Cherry, Date]

        // 5. 查找和替换
        System.out.println(Collections.max(set)); // 输出: 4
        System.out.println(Collections.min(set)); // 输出: 1

        Collections.replaceAll(list, "Apple", "Avocado");
        System.out.println(list); // 输出: [Avocado, Banana, Cherry, Date]

        // 6. 线程安全集合
        List<String> syncList = Collections.synchronizedList(new ArrayList<>(list));
        Set<Integer> syncSet = Collections.synchronizedSet(new HashSet<>(set));
        Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>(map));

        System.out.println(syncList);
        System.out.println(syncSet);
        System.out.println(syncMap);

//           List<String> list = new ArrayList<>();
//        list.add("Apple");
//        list.add("Banana");
//        list.add("Cherry");
//        System.out.println("List: " + list);
//
//        // 遍历 List
//        for (String item : list) {
//            System.out.println("Item: " + item);
//        }
//
//        // 排序 List
//        Collections.sort(list);
//        System.out.println("Sorted List: " + list);

        // 2. Queue 示例
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println("Queue: " + queue);

        // 移除和查看元素
        System.out.println("Polled: " + queue.poll());
        System.out.println("Queue after poll: " + queue);

        // 3. Deque 示例
        Deque<String> deque = new ArrayDeque<>();
        deque.addFirst("Front");
        deque.addLast("Back");
        System.out.println("Deque: " + deque);

        // 查看元素
        System.out.println("Peek First: " + deque.peekFirst());
        System.out.println("Peek Last: " + deque.peekLast());

        // 4. Stack 示例
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Stack: " + stack);

        // 弹出和查看顶部元素
        System.out.println("Popped: " + stack.pop());
        System.out.println("Top: " + stack.peek());

        // 5. Set 示例
//        Set<String> set = new HashSet<>();
//        set.add("Apple");
//        set.add("Banana");
//        set.add("Cherry");
//        System.out.println("Set: " + set);
//
//        // 检查存在性
//        System.out.println("Contains 'Apple': " + set.contains("Apple"));

        // 6. Map 示例
//        Map<String, Integer> map = new HashMap<>();
//        map.put("Alice", 25);
//        map.put("Bob", 30);
//        map.put("Charlie", 35);
//        System.out.println("Map: " + map);
//
//        // 遍历 Map
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + " -> " + entry.getValue());
//        }

        // 获取值
        System.out.println("Value for 'Alice': " + map.get("Alice"));

        // 7. LinkedHashSet 示例
        Set<String> linkedSet = new LinkedHashSet<>();
        linkedSet.add("A");
        linkedSet.add("B");
        linkedSet.add("C");
        System.out.println("LinkedHashSet: " + linkedSet);

        // 8. LinkedHashMap 示例
        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("One", 1);
        linkedMap.put("Two", 2);
        linkedMap.put("Three", 3);
        System.out.println("LinkedHashMap: " + linkedMap);

        // 9. TreeSet 示例
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("Dog");
        treeSet.add("Cat");
        treeSet.add("Elephant");
        System.out.println("TreeSet: " + treeSet);

        // 10. TreeMap 示例
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("Zebra", 1);
        treeMap.put("Monkey", 2);
        treeMap.put("Lion", 3);
        System.out.println("TreeMap: " + treeMap);


    }
}
