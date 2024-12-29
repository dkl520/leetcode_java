package com.leetcode2.org.系统设计;

import java.util.*;

public class MovieRentingSystem2 {
    static class Movie {
        int shopId;
        int movieId;
        int price;
        boolean isRented;

        public Movie(int movieId, int price, int shopId) {
            this.movieId = movieId;
            this.price = price;
            this.shopId = shopId;
            this.isRented = false;
        }
    }

    // 使用TreeSet来维护电影的排序，避免每次搜索时都要排序
    private final Map<Integer, TreeSet<Movie>> movieIndex;
    // 使用TreeSet来维护租赁记录的排序，避免每次报告时都要排序
    private final TreeSet<Movie> rentedMovies;
    private final Map<Pair<Integer, Integer>, Movie> movieCache;

    public MovieRentingSystem2(int n, int[][] entries) {
        movieIndex = new HashMap<>();
        rentedMovies = new TreeSet<>((m1, m2) -> {
            if (m1.price != m2.price) {
                return m1.price - m2.price;
            }
            if (m1.shopId != m2.shopId) {
                return m1.shopId - m2.shopId;
            }
            return m1.movieId - m2.movieId;
        });
        movieCache = new HashMap<>();

        for (int[] entry : entries) {
            Movie movie = new Movie(entry[1], entry[2], entry[0]);
            movieCache.put(new Pair<>(entry[0], entry[1]), movie);
            movieIndex.computeIfAbsent(entry[1], k -> new TreeSet<>((m1, m2) -> {
                if (m1.price != m2.price) {
                    return m1.price - m2.price;
                }
                return m1.shopId - m2.shopId;
            })).add(movie);
        }
    }

    public List<Integer> search(int movie) {
        if (!movieIndex.containsKey(movie)) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();
        int count = 0;

        for (Movie m : movieIndex.get(movie)) {
            if (count >= 5) break;
            if (!m.isRented) {
                result.add(m.shopId);
                count++;
            }
        }

        return result;
    }

    public void rent(int shop, int movie) {
        Movie m = movieCache.get(new Pair<>(shop, movie));
        m.isRented = true;
        rentedMovies.add(m);
    }

    public void drop(int shop, int movie) {
        Movie m = movieCache.get(new Pair<>(shop, movie));
        m.isRented = false;
        rentedMovies.remove(m);
    }

    public List<List<Integer>> report() {
        List<List<Integer>> result = new ArrayList<>();
        int count = 0;

        for (Movie movie : rentedMovies) {
            if (count >= 5) break;
            result.add(Arrays.asList(movie.shopId, movie.movieId));
            count++;
        }

        return result;
    }

    private static class Pair<K, V> {
        K first;
        V second;

        public Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }
}