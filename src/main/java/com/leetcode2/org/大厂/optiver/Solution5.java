package com.leetcode2.org.大厂.optiver;

import java.util.*;

class OrderStateX {
    static class Store {
        private final int storeId;
        private final Map<String, Integer> beverageList;

        public Store(int storeId) {
            this.storeId = storeId;
            this.beverageList = new HashMap<>();
        }
    }

    // 系统限制
    private int numberOfStores;
    private int perBeverageTotal;

    // 存储基础数据
    private final Map<Integer, Store> stores;
    private final List<Integer> rejectedOrders;

    // 缓存统计数据
    private final Map<String, Integer> globalBeverageCounts; // 每种饮料的总数
    private int totalBeverages; // 所有饮料的总数
    private final Set<String> uniqueBeverages; // 不同饮料的种类

    public OrderStateX() {
        this.stores = new HashMap<>();
        this.rejectedOrders = new ArrayList<>();
        this.globalBeverageCounts = new HashMap<>();
        this.uniqueBeverages = new HashSet<>();
        this.totalBeverages = 0;
    }

    public void updateLimit(int numberOfStores, int perBeverageTotal) {
        if (numberOfStores <= 0 || perBeverageTotal <= 0) {
            throw new IllegalArgumentException("Limits must be positive");
        }

        this.numberOfStores = numberOfStores;
        this.perBeverageTotal = perBeverageTotal;

        // 验证现有数据是否符合新限制
        if (this.stores.size() > numberOfStores ||
                globalBeverageCounts.values().stream().anyMatch(count -> count > perBeverageTotal)) {
            reset();
        }
    }

    public void processOrder(int uniqueId, int storeId, String beverageName, int quantity) {
        if (quantity < 0 || beverageName == null || beverageName.isEmpty()) {
            rejectedOrders.add(uniqueId);
            return;
        }

        Store store = stores.get(storeId);

        // 处理删除订单
        if (quantity == 0) {
            if (store != null) {
                Integer oldQuantity = store.beverageList.remove(beverageName);
                if (oldQuantity != null) {
                    updateGlobalCounters(beverageName, -oldQuantity);
                }
                if (store.beverageList.isEmpty()) {
                    stores.remove(storeId);
                }
            }
            return;
        }

        // 处理新订单
        if (store == null) {
            if (stores.size() >= numberOfStores) {
                rejectedOrders.add(uniqueId);
                return;
            }
            store = new Store(storeId);

        }

        // 检查是否超过总量限制
        int currentTotal = globalBeverageCounts.getOrDefault(beverageName, 0);
        int oldQuantity = store.beverageList.getOrDefault(beverageName, 0);
        int newTotal = currentTotal - oldQuantity + quantity;

        if (newTotal > perBeverageTotal) {
            rejectedOrders.add(uniqueId);
            return;
        }

        // 更新所有计数器
        updateGlobalCounters(beverageName, quantity - oldQuantity);
        store.beverageList.put(beverageName, quantity);
        stores.put(storeId, store);
    }

    public void closeStore(int storeId) {
        Store store = stores.remove(storeId);
        if (store != null) {
            // 更新全局计数器
            for (Map.Entry<String, Integer> entry : store.beverageList.entrySet()) {
                updateGlobalCounters(entry.getKey(), -entry.getValue());
            }
        }
    }

    public void printState() {
        if (!rejectedOrders.isEmpty()) {
            rejectedOrders.forEach(id -> System.out.println("reject_order: " + id));
            return;
        }

        int numberOfOrders = stores.values().stream()
                .mapToInt(store -> store.beverageList.size())
                .sum();

        System.out.printf("number_of_stores:%d, number_of_orders:%d, number_of_different_beverages:%d, number_of_beverages:%d%n",
                stores.size(),
                numberOfOrders,
                uniqueBeverages.size(),
                totalBeverages);
    }

    private void reset() {
        stores.clear();
        rejectedOrders.clear();
        globalBeverageCounts.clear();
        uniqueBeverages.clear();
        totalBeverages = 0;
    }

    private void updateGlobalCounters(String beverageName, int quantityChange) {
        // 更新饮料总数
        totalBeverages += quantityChange;

        // 更新特定饮料的计数
        int newCount = globalBeverageCounts.getOrDefault(beverageName, 0) + quantityChange;
        if (newCount > 0) {
            globalBeverageCounts.put(beverageName, newCount);
            uniqueBeverages.add(beverageName);
        } else {
            globalBeverageCounts.remove(beverageName);
            if (globalBeverageCounts.get(beverageName) == null) {
                uniqueBeverages.remove(beverageName);
            }
        }
    }
}

public class Solution5 {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            OrderStateX orderState = new OrderStateX();
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