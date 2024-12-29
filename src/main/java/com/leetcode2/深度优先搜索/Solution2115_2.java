package com.leetcode2.深度优先搜索;

import java.util.*;

public class Solution2115_2 {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        // 构建邻接表表示配方依赖关系
        Map<String, List<String>> graph = new HashMap<>();
        // 记录每个配方的入度（所需原料数量）
        Map<String, Integer> inDegree = new HashMap<>();
        // 将supplies转换为Set以提高查找效率
        Set<String> availableSupplies = new HashSet<>(Arrays.asList(supplies));

        // 初始化图和入度
        for (int i = 0; i < recipes.length; i++) {
            String recipe = recipes[i];
            inDegree.put(recipe, 0);
            for (String ingredient : ingredients.get(i)) {
                if (!availableSupplies.contains(ingredient)) {
                    graph.computeIfAbsent(ingredient, k -> new ArrayList<>()).add(recipe);
                    inDegree.merge(recipe, 1, Integer::sum);
                }
            }
        }

        // 找出所有入度为0的配方（可以直接制作的）
        Queue<String> queue = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }

        // 拓扑排序
        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            String recipe = queue.poll();
            result.add(recipe);

            // 更新依赖于当前配方的其他配方的入度
            if (graph.containsKey(recipe)) {
                for (String dependent : graph.get(recipe)) {
                    inDegree.merge(dependent, -1, Integer::sum);
                    if (inDegree.get(dependent) == 0) {
                        queue.offer(dependent);
                    }
                }
            }
        }
        return result;
    }
}