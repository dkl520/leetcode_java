package com.leetcode2.org.系统设计;

import java.util.*;

public class MovieRentingSystem {

    static class Movie {
        int shopId;
        int mId;
        int price;
        int count;

        public Movie(int mId, int price, int count, int shopId) {
            this.mId = mId;
            this.price = price;
            this.count = count;
            this.shopId = shopId;
        }
    }

    List<Movie> records;
    HashMap<Integer, Movie>[] systemBoard;

    public MovieRentingSystem(int n, int[][] entries) {
        this.systemBoard = new HashMap[n];
        for (int i = 0; i < n; i++) {
            this.systemBoard[i] = new HashMap<Integer, Movie>();
        }

        for (int[] entry : entries) {
            this.systemBoard[entry[0]].put(entry[1], new Movie(entry[1], entry[2], 1, entry[0]));
        }
        this.records = new ArrayList<>();
    }

    public List<Integer> search(int movie) {


        return Arrays.stream(this.systemBoard).flatMap(m -> m.values().stream()).filter(m -> m.mId == movie && m.count > 0)
                .sorted((m1, m2) -> {
                    if (m1.price == m2.price) {
                        return m1.mId - m2.mId;
                    }
                    return m1.price - m2.price;
                }).limit(5)
                .mapToInt(m -> m.shopId).boxed().toList();
    }

    public void rent(int shop, int movie) {
        this.systemBoard[shop].get(movie).count--;
        this.records.add(this.systemBoard[shop].get(movie));
    }

    public void drop(int shop, int movie) {
        this.systemBoard[shop].get(movie).count++;
        this.records.remove(this.systemBoard[shop].get(movie));
    }

    public List<List<Integer>> report() {
        int count = 5;
        List<List<Integer>> result = new ArrayList<>();

        this.records.sort((m1, m2) -> {
            if (m1.price == m2.price) {
                if (m1.shopId == m2.shopId) {
                    return m1.mId - m2.mId;
                }
                return m1.shopId - m2.shopId;
            }
            return m1.price - m2.price;
        });
        for (Movie movie : this.records) {
            if (count == 0) {
                break;
            }
            result.add(List.of(movie.shopId, movie.mId));
            count--;
        }

        return result;
    }

    public static void main(String[] args) {


    }
}

