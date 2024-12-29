package com.leetcode2.org.数据结构;

import java.util.*;
import java.util.concurrent.*;

public class AdditionalDataStructuresExample {
    public static void main(String[] args) {
        // NavigableSet example
        NavigableSet<Integer> navSet = new TreeSet<>();
        navSet.add(1);
        navSet.add(2);
        navSet.add(3);
        System.out.println("NavigableSet ceiling(2): " + navSet.ceiling(2));
        System.out.println("NavigableSet lower(2): " + navSet.lower(2));

        // WeakHashMap example
        WeakHashMap<String, String> weakMap = new WeakHashMap<>();
        String key = new String("WeakKey");
        weakMap.put(key, "WeakValue");
        System.out.println("WeakHashMap: " + weakMap);
        key = null; // 使key可能被垃圾回收
        System.gc(); // 触发垃圾回收
        System.out.println("WeakHashMap after GC: " + weakMap);

        // IdentityHashMap example
        IdentityHashMap<String, String> identityMap = new IdentityHashMap<>();
        identityMap.put(new String("Key"), "Value1");
        identityMap.put(new String("Key"), "Value2");
        System.out.println("IdentityHashMap: " + identityMap);

        // DelayQueue example
        DelayQueue<DelayedElement> delayQueue = new DelayQueue<>();
        delayQueue.offer(new DelayedElement("Task 1", 1000));
        delayQueue.offer(new DelayedElement("Task 2", 2000));
        System.out.println("DelayQueue: " + delayQueue);

        // SynchronousQueue example
        SynchronousQueue<String> syncQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println("SynchronousQueue take: " + syncQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        try {
            syncQueue.put("SyncItem");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // ConcurrentSkipListSet example
        ConcurrentSkipListSet<String> skipListSet = new ConcurrentSkipListSet<>();
        skipListSet.add("A");
        skipListSet.add("C");
        skipListSet.add("B");
        System.out.println("ConcurrentSkipListSet: " + skipListSet);

        // ConcurrentLinkedDeque example
        ConcurrentLinkedDeque<String> concDeque = new ConcurrentLinkedDeque<>();
        concDeque.offerFirst("First");
        concDeque.offerLast("Last");
        System.out.println("ConcurrentLinkedDeque: " + concDeque);
    }
}

class DelayedElement implements Delayed {
    private String name;
    private long time;

    public DelayedElement(String name, long delayInMilliseconds) {
        this.name = name;
        this.time = System.currentTimeMillis() + delayInMilliseconds;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = time - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (this.time < ((DelayedElement) o).time) {
            return -1;
        }
        if (this.time > ((DelayedElement) o).time) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return name;
    }
}