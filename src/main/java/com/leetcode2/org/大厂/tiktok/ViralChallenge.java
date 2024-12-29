package com.leetcode2.org.大厂.tiktok;

import java.util.ArrayDeque;
import java.util.Deque;

public class ViralChallenge {
    int getViralChallenge(String video, int[] engagementArray, int k) {
        char[] charsList = video.toCharArray();
        int countViral = 0;
        int result = 0;
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < charsList.length; i++) {
            char alpha = charsList[i];
            deque.offer(alpha);
            if (engagementArray[alpha - 'a'] == 0) {
                    countViral++;
            }
            while (!deque.isEmpty() &&  countViral >= k ){
                    deque.pollFirst();
                    if (engagementArray[deque.peekLast() - 'a'] == 0) {
                        countViral--;
                    }
            }
            result+= deque.size();
        }
        return result;

    }

    public static void main(String[] args) {
        String video = "abc";
        int[] engagementArray = {0, 1, 0, 1, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0};
        int k = 2;
        ViralChallenge viralChallenge = new ViralChallenge();
        System.out.println(viralChallenge.getViralChallenge(video, engagementArray, k));
    }
}
