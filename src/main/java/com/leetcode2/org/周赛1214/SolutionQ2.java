package com.leetcode2.org.周赛1214;

import java.util.*;

public class SolutionQ2 {
    static record Curs(String curcy, double rate) {
    }

    ;
    List<Curs> listFirst;
    String initialC;

    public double maxAmount(String initialCurrency, List<List<String>> pairs1, double[] rates1, List<List<String>> pairs2, double[] rates2) {
        Map<String, List<Curs>> graph1 = new HashMap<>();
        for (int i = 0; i < pairs1.size(); i++) {
            List<String> pair = pairs1.get(i);
            graph1.computeIfAbsent(pair.get(0), k -> new ArrayList<>()).add(new Curs(pair.get(1), rates1[i]));
            graph1.computeIfAbsent(pair.get(1), k -> new ArrayList<>()).add(new Curs(pair.get(0), 1/rates1[i]));
        }

        listFirst = new ArrayList<>();
        dfsFirst(initialCurrency, graph1, 1,null);
//        listFirst.add(new Curs(initialCurrency, 1));
        initialC = initialCurrency;
        Map<String, List<Curs>> graph2 = new HashMap<>();
        for (int i = 0; i < pairs2.size(); i++) {
            List<String> pair = pairs2.get(i);
            graph2.computeIfAbsent(pair.get(0), k -> new ArrayList<>()).add(new Curs(pair.get(1), rates2[i]));
            graph2.computeIfAbsent(pair.get(1), k -> new ArrayList<>()).add(new Curs(pair.get(0), 1 / (rates2[i])));
        }
        double max = 0;
        for (Curs curs : listFirst) {
            max = Math.max(max, dfs(curs.curcy, graph2, curs.rate, null));
        }
        return max;

    }

    void dfsFirst(String initialCurrency, Map<String, List<Curs>> graph, double start, String prevCurrency) {
        List<Curs> list = graph.get(initialCurrency);
        double max = 0;
        listFirst.add(new Curs(initialCurrency, start));
        for (Curs curs : list) {
            if (prevCurrency == null || !prevCurrency.equals(curs.curcy)) {
                dfsFirst(curs.curcy, graph, start * curs.rate, initialCurrency);
            }
        }
    }

    double dfs(String initialCurrency, Map<String, List<Curs>> graph, double start, String prevCurrency) {
        List<Curs> list = graph.get(initialCurrency);
        if (Objects.equals(initialCurrency, initialC)) {
            return start;
        }
        double max = 0;
        if (list == null || list.isEmpty()) {
            return 0;
        }
        for (Curs curs : list) {
            if (prevCurrency == null || !prevCurrency.equals(curs.curcy)) {
                double curNum = dfs(curs.curcy, graph, start * curs.rate, initialCurrency);
                max = Math.max(max, curNum);
            }

        }
        return max;
    }

    public static void main(String[] args) {
        SolutionQ2 solutionQ2 = new SolutionQ2();
        // 初始货币

        // 初始货币
        String initialCurrency = "EUR";

// 第一组货币对和汇率
        List<List<String>> pairs1 = List.of(
                List.of("EUR", "USD"),
                List.of("USD", "JPY")
        );
        double[] rates1 = {2.0, 3.0};

// 第二组货币对和汇率
        List<List<String>> pairs2 = List.of(
                List.of("JPY", "USD"),
                List.of("USD", "CHF"),
                List.of("CHF", "EUR")
        );
        double[] rates2 = {4.0, 5.0, 6.0};




        solutionQ2.maxAmount(initialCurrency, pairs1, rates1, pairs2, rates2);
    }
}
