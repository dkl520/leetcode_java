package com.leetcode2.org.系统设计;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Twitter355 {
    static class Twittee {
        int tweetId;
        int time;

        public Twittee(int tweetId, int time) {
            this.tweetId = tweetId;
            this.time = time;
        }
    }

    int count;

    static class User {
        List<Integer> followList;
        int userId;
        List<Twittee> tweetList;
        List<Integer> flollowedList;
        List<Twittee> feedList;

        User(int userId) {
            this.userId = userId;
            this.followList = new ArrayList<>();
            this.flollowedList = new ArrayList<>();
            this.tweetList = new ArrayList<>();
            this.feedList = new ArrayList<>();
        }
    }

    Map<Integer, User> dict;

    public Twitter355() {
        this.dict = new HashMap<>();
        this.count = 0;
    }

    public void postTweet(int userId, int tweetId) {
        User user = this.dict.computeIfAbsent(userId, User::new);
        Twittee twittee = new Twittee(tweetId, this.count++);
        if (user.tweetList.stream().anyMatch(t -> t.tweetId == tweetId)) {
            return;
        }
        user.tweetList.add(twittee);
        user.feedList.add(twittee);
        for (int userIds : user.flollowedList) {
            this.dict.computeIfAbsent(userIds, User::new).feedList.add(twittee);
        }

    }

    public List<Integer> getNewsFeed(int userId) {
        if (this.dict.containsKey(userId)) {
            User user = this.dict.get(userId);
            user.feedList.sort((a, b) -> b.time - a.time);
            return user.feedList.stream().limit(10).mapToInt(t -> t.tweetId).boxed().toList();
        }

        return new ArrayList<>();
    }

    public void follow(int followerId, int followeeId) {
        User followUser = this.dict.computeIfAbsent(followerId, User::new);
        if (followUser.followList.contains(followeeId)) {
            return;
        }
        followUser.followList.add(followeeId);
        followUser.feedList.addAll(this.dict.computeIfAbsent(followeeId, User::new).tweetList);

        User followedUser = this.dict.computeIfAbsent(followeeId, User::new);
        followedUser.flollowedList.add(followerId);
    }

    public void unfollow(int followerId, int followeeId) {
        User followUser = this.dict.get(followerId);
        followUser.followList.remove(Integer.valueOf(followeeId));
        User followedUser = this.dict.get(followeeId);
        followedUser.flollowedList.remove(Integer.valueOf(followerId));
        followUser.feedList.removeAll(followedUser.tweetList);
    }


    public static void main(String[] args) {
        Twitter355 twitter = new Twitter355();

        // 执行命令 ["Twitter","postTweet","follow","follow","getNewsFeed"]
        // 参数 [[],[2,5],[1,2],[1,2],[1]]

        twitter.postTweet(2, 5); // 用户 2 发布推文 5
        twitter.follow(1, 2); // 用户 1 关注用户 2
        twitter.follow(1, 2); // 用户 1 重复关注用户 2 (无效操作)

        List<Integer> newsFeed = twitter.getNewsFeed(1); // 获取用户 1 的新闻提要
        System.out.println(newsFeed); // 输出 -> [5]
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */