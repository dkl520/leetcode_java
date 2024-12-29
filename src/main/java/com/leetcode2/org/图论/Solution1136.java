package com.leetcode2.org.图论;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution1136 {
    public int minimumSemesters(int n, int[][] relations) {
        // Create adjacency list and in-degree map
        List<List<Integer>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        int[] inDegrees = new int[n + 1];

        // Build graph and calculate in-degrees
        for (int[] relation : relations) {
            graph.get(relation[0]).add(relation[1]);
            inDegrees[relation[1]]++;
        }

        // Queue for courses with no prerequisites
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        int semesters = 0;
        int completedCourses = 0;

        // Perform topological sorting
        while (!queue.isEmpty()) {
            int size = queue.size();
            semesters++;

            // Process all courses in the current semester
            for (int i = 0; i < size; i++) {
                int course = queue.poll();
                completedCourses++;

                // Reduce in-degrees of dependent courses
                for (int nextCourse : graph.get(course)) {
                    if (--inDegrees[nextCourse] == 0) {
                        queue.offer(nextCourse);
                    }
                }
            }

        }

        // Check if all courses can be completed
        return completedCourses == n ? semesters : -1;
    }
}