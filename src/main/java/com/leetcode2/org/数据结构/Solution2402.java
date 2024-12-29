package com.leetcode2.org.数据结构;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

public class Solution2402 {

    static record Room(int id, long end) {
    };

    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        int[] results = new int[n];
        TreeSet<Room> treeSet = new TreeSet<>((a, b) -> {
            if (a.end == b.end) {
                return a.id - b.id;
            }
            return Long.compare(a.end, b.end);
        });
        for (int[] meeting : meetings) {
            if (treeSet.isEmpty()) {
                treeSet.add(new Room(0, meeting[1]));
                results[0]++;
            } else {
                SortedSet<Room> set = treeSet.headSet(new Room(Integer.MAX_VALUE, meeting[0]), true);
                if (set.isEmpty()) {
                    if (treeSet.size() < n) {
                        results[treeSet.size()]++;
                        treeSet.add(new Room(treeSet.size(), meeting[1]));
                    } else {
                        Room cur = treeSet.first();
                        treeSet.remove(cur);
                        treeSet.add(new Room(cur.id, cur.end + meeting[1] - meeting[0]));
                        results[cur.id]++;
                    }
                } else {
                    Room cur = set.first();
                    int index = Integer.MAX_VALUE;
                    for (Room room : set) {
                        if (index > room.id) {
                            index = room.id;
                            cur = room;
                        }
                    }
                    results[cur.id]++;
                    treeSet.remove(cur);
                    treeSet.add(new Room(cur.id, meeting[1]));

                }
            }
        }
        int ans = -1;
        int index = -1;
        for (int i = 0; i < results.length; i++) {
            if (ans < results[i]) {
                index = i;
                ans = results[i];
            }
        }
        return index;
    }


    public static void main(String[] args) {
        Solution2402 s = new Solution2402();
//        int[][] meetings = {{18, 19}, {3, 12}, {17, 19}, {2, 13}, {7, 10}};
        int[][] meetings = {{0, 10}, {1, 5}, {2, 7}, {3, 4}};

        s.mostBooked(2, meetings);

    }


}
