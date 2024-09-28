package com.leetcode2.org.collection;

//import org.model.Chapter;

import com.leetcode2.org.model.Chapter;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamExample {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 67, 8};
        IntStream steamFromArray = Arrays.stream(numbers);
        System.out.println("steam from array");
        steamFromArray.forEach(System.out::println);
        System.out.println("\n2. Square each element: ");
        Arrays.stream(numbers).map(x -> x * x).forEach(System.out::println);
        IntStream[] filterArr = new IntStream[]{Arrays.stream(numbers).filter(x -> x > 5)};
        // 使用flatMapToInt展平IntStream数组，然后打印每个元素
        System.out.println("Filter Array:");
        Arrays.stream(filterArr)
                .flatMapToInt(x -> x)
                .forEachOrdered(System.out::println);
        int[] boxNums = Arrays.stream(numbers).filter(x -> x > 5).toArray();
        System.out.println(Arrays.toString(boxNums));
        Arrays.stream(boxNums).reduce(Integer::sum).ifPresent(System.out::println);
//        Arrays.stream(filterArr).forEachOrdered(System.out::println);
        BinaryOperator<Integer> sumInt = Integer::sum;

        System.out.println("sum of Intergeners" + sumInt.apply(1, 2));
//        System.out.println();

        BinaryOperator<Double> doubleInt = Double::sum;
        System.out.println("Sum:" + doubleInt.apply(12.1, 3.2));
        BinaryOperator<Integer> max = BinaryOperator.maxBy(Integer::compare);
        BinaryOperator<Integer> min = BinaryOperator.minBy(Integer::compare);
        System.out.println("min:" + min.apply(10, 9));
        System.out.println("max:" + max.apply(10, 8));


        List<Chapter> listC = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Chapter chapter = new Chapter((int) (Math.random() * 1888), String.valueOf((int) (Math.random() * 1888)), null);
            listC.add(chapter);
        }
//        listC.forEach(chapter -> System.out.println(chapter.toString()));

        List<Map<String, Object>> filterMap = listC.stream()
                .map(chapter -> Map.<String, Object>of(
                            "id", chapter.getId(),
                            "name", chapter.getName())
                ).toList();


        System.out.println(listC);

    }
}
