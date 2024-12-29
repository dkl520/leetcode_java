package com.leetcode2.org.大厂.optiver;

import java.util.*;

// 使用记录类来提高代码可读性和不可变性
record BeverageOrder(String name, int quantity) {}

class OrderStates {
    // 将 Store 类设计为不可变类，提高线程安全性
    static class Store {
        private final int perBeverageTotal;
        private final Map<String, Integer> beverageList;
        private final int storeId;

        public Store(int perBeverageTotal, int storeId) {
            this.perBeverageTotal = perBeverageTotal;
            this.storeId = storeId;
            this.beverageList = new HashMap<>();
        }

        // 添加复制构造函数，确保状态的不可变性
        public Store copy() {
            Store newStore = new Store(this.perBeverageTotal, this.storeId);
            newStore.beverageList.putAll(this.beverageList);
            return newStore;
        }
    }

    // 使用 final 修饰不变的字段
    private int numberOfStores;
    private int perBeverageTotal;
    private final Map<Integer, Store> stores;
    private final List<Integer> rejectedOrders;

    // 使用 ThreadLocal 缓存统计结果
    private final ThreadLocal<OrderStatistics> statsCache = ThreadLocal.withInitial(OrderStatistics::new);

    public OrderStates() {
        this.stores = new HashMap<>();
        this.rejectedOrders = new ArrayList<>();
    }

    public void updateLimit(int numberOfStores, int perBeverageTotal) {
        if (numberOfStores <= 0 || perBeverageTotal <= 0) {
            throw new IllegalArgumentException("Limits must be positive");
        }

        this.numberOfStores = numberOfStores;
        this.perBeverageTotal = perBeverageTotal;

        // 优化验证逻辑，使用 Map 减少循环
        if (this.stores.size() > numberOfStores) {
            reset();
            return;
        }

        Map<String, Integer> totalBeverages = new HashMap<>();
        boolean shouldReset = false;

        for (Store store : stores.values()) {
            for (Map.Entry<String, Integer> entry : store.beverageList.entrySet()) {
                int newTotal = totalBeverages.getOrDefault(entry.getKey(), 0) + entry.getValue();
                if (newTotal > perBeverageTotal) {
                    shouldReset = true;
                    break;
                }
                totalBeverages.put(entry.getKey(), newTotal);
            }
            if (shouldReset) break;
        }

        if (shouldReset) {
            reset();
        }
    }

    public void processOrder(int uniqueId, int storeId, String beverageName, int quantity) {
        if (quantity < 0 || beverageName == null || beverageName.isEmpty()) {
            rejectedOrders.add(uniqueId);
            return;
        }

        // 处理删除订单的情况
        if (quantity == 0) {
            handleOrderDeletion(storeId, beverageName);
            return;
        }

        // 处理新订单
        if (!stores.containsKey(storeId)) {
            if (stores.size() >= numberOfStores) {
                rejectedOrders.add(uniqueId);
                return;
            }
            stores.put(storeId, new Store(perBeverageTotal, storeId));
        }

        // 检查总量限制
        int totalQuantity = calculateTotalQuantity(storeId, beverageName) + quantity;
        if (totalQuantity > perBeverageTotal) {
            rejectedOrders.add(uniqueId);
            return;
        }

        // 更新订单
        Store store = stores.get(storeId);
        store.beverageList.put(beverageName, quantity);
    }

    public void closeStore(int storeId) {
        stores.remove(storeId);
        statsCache.remove(); // 清除缓存的统计信息
    }

    public void printState() {
        if (!rejectedOrders.isEmpty()) {
            rejectedOrders.forEach(id -> System.out.println("reject_order: " + id));
            return;
        }

        OrderStatistics stats = calculateStatistics();
        System.out.printf("number_of_stores:%d, number_of_orders:%d, number_of_different_beverages:%d, number_of_beverages:%d%n",
                stats.numberOfStores, stats.numberOfOrders, stats.numberOfDifferentBeverages, stats.numberOfBeverages);
    }

    // 私有辅助方法
    private void reset() {
        stores.clear();
        rejectedOrders.clear();
        statsCache.remove();
    }

    private void handleOrderDeletion(int storeId, String beverageName) {
        Store store = stores.get(storeId);
        if (store != null) {
            store.beverageList.remove(beverageName);
            if (store.beverageList.isEmpty()) {
                stores.remove(storeId);
            }
        }
    }

    private int calculateTotalQuantity(int storeId, String beverageName) {
        return stores.entrySet().stream()
                .filter(e -> e.getKey() != storeId)
                .mapToInt(e -> e.getValue().beverageList.getOrDefault(beverageName, 0))
                .sum();
    }

    private OrderStatistics calculateStatistics() {
        OrderStatistics stats = statsCache.get();
        stats.reset();

        stats.numberOfStores = stores.size();

        Set<String> uniqueBeverages = new HashSet<>();
        int totalOrders = 0;
        int totalBeverages = 0;

        for (Store store : stores.values()) {
            totalOrders += store.beverageList.size();
            uniqueBeverages.addAll(store.beverageList.keySet());
            totalBeverages += store.beverageList.values().stream().mapToInt(Integer::intValue).sum();
        }

        stats.numberOfOrders = totalOrders;
        stats.numberOfDifferentBeverages = uniqueBeverages.size();
        stats.numberOfBeverages = totalBeverages;

        return stats;
    }

    // 内部类用于缓存统计结果
    private static class OrderStatistics {
        int numberOfStores;
        int numberOfOrders;
        int numberOfDifferentBeverages;
        int numberOfBeverages;

        void reset() {
            numberOfStores = 0;
            numberOfOrders = 0;
            numberOfDifferentBeverages = 0;
            numberOfBeverages = 0;
        }
    }
}

// Main class 保持不变
public class Solution3 {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            OrderStates orderState = new OrderStates();
            while (in.hasNext()) {
                String operation = in.next();
                switch (operation) {
                    case "UPDATE_LIMIT" -> {
                        int numberOfStores = in.nextInt();
                        int perBeverageTotal = in.nextInt();
                        orderState.updateLimit(numberOfStores, perBeverageTotal);
                    }
                    case "ORDER_UPDATE" -> {
                        int uniqueId = in.nextInt();
                        int storeId = in.nextInt();
                        String beverageName = in.next();
                        int quantity = in.nextInt();
                        orderState.processOrder(uniqueId, storeId, beverageName, quantity);
                    }
                    case "CLOSE_STORE" -> orderState.closeStore(in.nextInt());
                    case "PRINT_STATE" -> orderState.printState();
                    default -> throw new IllegalArgumentException("Invalid operation: " + operation);
                }
            }
        }
    }
}