package com.leetcode2.org.图论;

import java.util.*;

public class Solution3387 {
    static record Exchange(String currency, double rate) {
    }

    ;

    public double maxAmount(String initialCurrency, List<List<String>> pairs1, double[] rates1, List<List<String>> pairs2, double[] rates2) {
        Map<String, Double> exchangesOne = new HashMap<>();
        exchangesOne.put(initialCurrency, 1.0);
        Map<String, List<Exchange>> graph1 = new HashMap<>();
        for (int i = 0; i < pairs1.size(); i++) {
            List<String> pair = pairs1.get(i);
            graph1.computeIfAbsent(pair.get(0), k -> new ArrayList<>()).add(new Exchange(pair.get(1), rates1[i]));
            graph1.computeIfAbsent(pair.get(1), k -> new ArrayList<>()).add(new Exchange(pair.get(0), 1 / rates1[i]));
        }

        for (int i = 0; i < pairs1.size(); i++) {
            Map<String, Double> exchangesCopy = new HashMap<>(exchangesOne);
            for (String currency : exchangesOne.keySet()) {
                List<Exchange> exchanges = graph1.get(currency);
                if (!exchanges.isEmpty()) {
                    for (Exchange exchange : exchanges) {
                        exchangesCopy.compute(
                                exchange.currency,
                                (key, original) -> original == null ? exchange.rate * exchangesCopy.get(currency)
                                        : Math.max(original, exchange.rate * exchangesCopy.get(currency))
                        );
                    }
                }
            }
            exchangesOne = exchangesCopy;
        }

        Map<String, List<Exchange>> graph2 = new HashMap<>();

        for (int i = 0; i < pairs2.size(); i++) {
            List<String> pair = pairs2.get(i);
            graph2.computeIfAbsent(pair.get(0), k -> new ArrayList<>()).add(new Exchange(pair.get(1), rates2[i]));
            graph2.computeIfAbsent(pair.get(1), k -> new ArrayList<>()).add(new Exchange(pair.get(0), 1 / rates2[i]));
        }

        for (int i = 0; i < pairs2.size(); i++) {
            Map<String, Double> exchangesCopy = new HashMap<>(exchangesOne);
            for (String currency : exchangesOne.keySet()) {
                List<Exchange> exchanges = graph2.get(currency);
                if (!exchanges.isEmpty()) {
                    for (Exchange exchange : exchanges) {
                        exchangesCopy.compute(
                                exchange.currency,
                                (key, original) -> original == null ? exchange.rate * exchangesCopy.get(currency)
                                        : Math.max(original, exchange.rate * exchangesCopy.get(currency))
                        );

                    }
                }
            }
            exchangesOne = exchangesCopy;
        }

        return exchangesOne.get(initialCurrency);

    }

    public static void main(String[] args) {

        String initialCurrency = "EUR";
        List<List<String>> pairs1 = Arrays.asList(
                Arrays.asList("EUR", "USD"),
                Arrays.asList("USD", "JPY")
        );
        double[] rates1 = new double[]{2.0, 3.0};
        List<List<String>> pairs2 = Arrays.asList(
                Arrays.asList("JPY", "USD"),
                Arrays.asList("USD", "CHF"),
                Arrays.asList("CHF", "EUR")
        );
        double[] rates2 = new double[]{4.0, 5.0, 6.0};
        Solution3387 solution3387 = new Solution3387();
        System.out.println(solution3387.maxAmount(initialCurrency, pairs1, rates1, pairs2, rates2));


    }


}
