package com.leetcode2.org.系统设计;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class FoodRatings {
    static class FoodItem {
        String food;
        int rating;
        String cuisines;

        public FoodItem(String food, int rating, String cuisines) {
            this.food = food;
            this.rating = rating;
            this.cuisines = cuisines;
        }
    }

    Map<String, FoodItem> foodList;
    Map<String, TreeSet<FoodItem>> cuisines;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        int n = foods.length;
        this.foodList = new HashMap<String, FoodItem>();
        this.cuisines = new HashMap<>();
        for (int i = 0; i < n; i++) {
            FoodItem foodItem = new FoodItem(foods[i], ratings[i], cuisines[i]);
            this.foodList.put(foods[i], foodItem);
            this.cuisines.computeIfAbsent(cuisines[i], k -> new TreeSet<>(
                    (f1, f2) -> {
                        if (f1.rating == f2.rating) {
                            return f1.food.compareTo(f2.food);
                        }
                        return  Integer.compare(f2.rating, f1.rating);
                    }
                    )).add(foodItem);
        }
    }

    public void changeRating(String food, int newRating) {
        FoodItem foodItem = this.foodList.get(food);
        this.cuisines.get(foodItem.cuisines).remove(foodItem);
        foodItem.rating = newRating;
        this.cuisines.get(foodItem.cuisines).add(foodItem);
    }

    public String highestRated(String cuisine) {
        return this.cuisines.get(cuisine).first().food;
    }

    public static void main(String[] args) {
        String[] foods = {"kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"};
        String[] cuisines = {"korean", "japanese", "japanese", "greek", "japanese", "korean"};
        int[] ratings = {9, 12, 8, 15, 14, 7};

        // 创建FoodRatings实例
        FoodRatings f = new FoodRatings(foods, cuisines, ratings);

        // 测试调用
        System.out.println(f.highestRated("korean"));     // 输出: kimchi
        System.out.println(f.highestRated("japanese"));   // 输出: ramen
        f.changeRating("sushi", 16);
        System.out.println(f.highestRated("japanese"));   // 输出: sushi
        f.changeRating("ramen", 16);
        System.out.println(f.highestRated("japanese"));   // 输出: ramen
    }
}