package com.leetcode2.深度优先搜索;

import java.util.*;

public class Solution2115{


    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {

        List<String> result = new ArrayList<>();
        Collections.addAll(result, supplies);

        List<String> tempRecipes = new ArrayList<>();
        Collections.addAll(tempRecipes, recipes);
        for (String recyString : recipes) {
            dfs(recyString, new HashSet<String>(), tempRecipes, ingredients, result);
        }

        result.removeAll(Arrays.asList(supplies));

        return result;
    }

    public boolean dfs(String recipe, Set<String> visited, List<String> recipes, List<List<String>> ingredients, List<String> result) {

        if (result.contains(recipe)) {
            return true;
        }
        int index = recipes.indexOf(recipe);
        if (index < 0) {
            return false;
        }
        if (visited.contains(recipe)) {
            return false;
        }
        visited.add(recipe);
        List<String> ingredient = ingredients.get(index);
        boolean maybe = ingredient.stream().allMatch(v -> dfs(v, visited, recipes, ingredients, result));
        if (maybe) {
            result.add(recipe);
            return true;
        }
        return false;

    }

    public static void main(String[] args) {
        String[] recipes = new String[]{"ju", "fzjnm", "x", "e", "zpmcz", "h", "q"};

        List<List<String>> ingredients = new ArrayList<>();

        ingredients.add(new ArrayList<>(List.of("d")));
        ingredients.add(new ArrayList<>(List.of("hveml", "f", "cpivl")));
        ingredients.add(new ArrayList<>(List.of("cpivl", "zpmcz", "h", "e", "fzjnm", "ju")));
        ingredients.add(new ArrayList<>(List.of("cpivl", "hveml", "zpmcz", "ju", "h")));
        ingredients.add(new ArrayList<>(List.of("h", "fzjnm", "e", "q", "x")));
        ingredients.add(new ArrayList<>(List.of("d", "hveml", "cpivl", "q", "zpmcz", "ju", "e", "x")));
        ingredients.add(new ArrayList<>(List.of("f", "hveml", "cpivl")));

        String[] supplies = new String[]{"f", "hveml", "cpivl", "d"};
        Solution2115 findAllRecipes = new Solution2115();
        List<String> result = findAllRecipes.findAllRecipes(recipes, ingredients, supplies);
        System.out.println(result);

    }
}
