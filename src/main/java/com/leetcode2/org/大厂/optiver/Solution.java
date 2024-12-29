package com.leetcode2.org.大厂.optiver;

import java.util.*;
import java.util.stream.Collectors;

class OrderState {
    static class Store {
        int perBeverageTotal;
        Map<String, Integer> beverageList;
        int storeId;

        public Store(int perBeverageTotal, int storeId) {
            this.perBeverageTotal = perBeverageTotal;
            this.storeId = storeId;
            this.beverageList = new HashMap<>();
        }
    }

    int numberOfStores;
    int perBeverageTotal;
    Map<Integer, Store> stores;
    int number_of_stores;
    int number_of_orders;
    int number_of_different_beverages;
    int number_of_beverages;
    List<Integer> reject_order;

    public OrderState() {
        this.stores = new HashMap<>();
        this.reject_order = new ArrayList<>();
    }

    public void UpdateLimit(int numberOfStores, int perBeverageTotal) {
        this.numberOfStores = numberOfStores;
        this.perBeverageTotal = perBeverageTotal;
        if (this.stores.size() > numberOfStores) {
            this.stores.clear();
            this.reject_order = new ArrayList<>();
            return;
        }
        Map<String, Integer> beverages = new HashMap<>();
        for (Store store : this.stores.values()) {
            store.beverageList.forEach((bName, count) -> {
                beverages.put(bName, beverages.getOrDefault(bName, 0) + count);
                if (beverages.getOrDefault(bName, 0) > perBeverageTotal) {
                    this.stores.clear();
                    this.reject_order = new ArrayList<>();
                    return;
                }
            });
        }
    }

    public void ProcessOrder(int uniqueId, int storeId, String beverageName, int quantity) {
        Store store = stores.get(storeId);
        if (quantity < 0) return;
        if (stores.get(storeId) == null && quantity == 0) return;
        if (stores.get(storeId) == null) {
            if (stores.size() + 1 > this.numberOfStores) {
                this.reject_order.add(uniqueId);
                return;
            }
            store = new Store(this.perBeverageTotal, storeId);

        }
        if (quantity == 0) {
            store.beverageList.remove(beverageName);
            if (store.beverageList.isEmpty()) {
                stores.remove(storeId);
            }
        } else {
            int remainingQuantity = this.stores.entrySet().stream().filter(e -> e.getKey() != storeId).map(Map.Entry::getValue)
                    .mapToInt(s -> s.beverageList.get(beverageName) != null ? s.beverageList.get(beverageName) : 0).sum();
            if (remainingQuantity + quantity > this.perBeverageTotal) {
                this.reject_order.add(uniqueId);
                return;
            }
            store.beverageList.put(beverageName, quantity);
            stores.put(storeId, store);
        }
    }

    public void CloseStore(int storeId) {
        this.stores.remove(storeId);
    }

    public void PrintState() {
        if (!reject_order.isEmpty()) {
            for (int i : reject_order) {
                System.out.println("reject_order: " + i);
            }
            return;
        }
        this.number_of_orders = this.stores.values().stream().mapToInt(s -> s.beverageList.size()).sum();
        this.number_of_different_beverages = this.stores.values().stream().map(s -> s.beverageList.keySet()).collect(Collectors.toSet()).size();
        this.number_of_stores = this.stores.size();
        this.number_of_beverages = this.stores.values().stream().mapToInt(s -> s.beverageList.values().stream().mapToInt(v -> v).sum()).sum();
        System.out.println(
                "number_of_stores:" + this.number_of_stores +
                        ", number_of_orders:" + this.number_of_orders +
                        ", number_of_different_beverages:" + this.number_of_different_beverages +
                        ", number_of_beverages:" + this.number_of_beverages
        );
    }
}

public class Solution {
    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        OrderState orderState = new OrderState();
        while (in.hasNext()) {
            String operation = in.next();
            int storeId;
            switch (operation) {
                case "UPDATE_LIMIT": {
                    int numberOfStores = in.nextInt();
                    int perBeverageTotal = in.nextInt();
                    orderState.UpdateLimit(numberOfStores, perBeverageTotal);
                }
                break;
                case "ORDER_UPDATE": {
                    int uniqueId = in.nextInt();
                    storeId = in.nextInt();
                    String beverageName = in.next();
                    int quantity = in.nextInt();
                    orderState.ProcessOrder(uniqueId, storeId, beverageName, quantity);
                }
                break;
                case "CLOSE_STORE": {
                    storeId = in.nextInt();
                    orderState.CloseStore(storeId);
                }
                break;
                case "PRINT_STATE": {
                    orderState.PrintState();
                }
                break;
                default:
                    throw new IllegalArgumentException("Invalid operation: " + operation);
            }
        }
    }
}











