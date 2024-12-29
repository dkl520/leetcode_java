package com.leetcode2.org.大厂.citadel;

import java.util.*;
import java.util.stream.Collectors;

public class CitadelBitSubsequences {
    public static List<Integer> findSubsequences(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        List<List<Integer>>[] records = new List[nums.length];
        for (int i = 0; i < nums.length; i++) {
            records[i] = new ArrayList<>();
        }
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> curList = records[i];
            curList.add(List.of(nums[i]));
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    for (List<Integer> cur : records[j]) {
                        List<Integer> newList = new ArrayList<>(cur);
                        newList.add(nums[i]);
                        records[i].add(newList);
                    }
                }
            }
        }
        List<Integer> result = new ArrayList<>();
//        result = new ArrayList<>(Arrays.stream(records).
//                map(listofList -> listofList.stream().mapToInt(
//                                listOne -> listOne.stream().reduce(0, (acc, v) -> (acc | v)))
//                        .boxed().toList()).reduce(new ArrayList<>(), (set, li) -> {
//                    set.addAll(li);
//                    return set;
//                }).stream().toList());

        result = new ArrayList<>(Arrays.stream(records).
                flatMap(listOfList ->
                        listOfList.stream()
                                .map(innerList ->
                                        innerList.stream()
                                                .reduce(0, (acc, v) -> acc | v)
                                )
                ).collect(Collectors.toSet())
                .stream().sorted().toList()
        );
        result.sort((a, b) -> a - b);
        Set<Integer> setresult = new HashSet<>(result);


        List<Integer> results = new ArrayList<>(setresult);

        return results;
    }


    public static List<Integer> findSubsequences2(int[] nums) {
        List<List<Integer>> records = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            records.add(new ArrayList<>());
        }
        for (int i = 0; i < nums.length; i++) {
            List<Integer> curList = records.get(i);
            curList.add(nums[i]);
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    for (int cur : records.get(j)) {
                        int newNum = cur | nums[i];
                        curList.add(newNum);
                    }
                }
            }
        }
        Set<Integer> result = new HashSet<>();
        for (List<Integer> record : records) {
            result.addAll(record);
        }
        result.add(0);
        List<Integer> sortedResult = new ArrayList<>(result);
        sortedResult.sort((a, b) -> a - b); // 按升序排序
        return sortedResult;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[][] testCases = new int[10][100];

        // 生成100个测试用例，每个用例10000个元素
        for (int i = 0; i < testCases.length; i++) {
            for (int j = 0; j < testCases[i].length; j++) {
                // 生成 0-1000 范围内的随机整数
                testCases[i][j] = random.nextInt(1001);
            }
        }

        long startTime1 = System.nanoTime();

        for (int[] A : testCases) {
            List<Integer> result = findSubsequences2(A);
            // 如果不想打印全部结果，可以只打印结果大小
            System.out.println(Arrays.toString(A) + " -> " + result);
        }

        long endTime1 = System.nanoTime();

        System.out.println("我的代码耗时: " + (endTime1 - startTime1) / 1000000 + "ms");
    }

}
