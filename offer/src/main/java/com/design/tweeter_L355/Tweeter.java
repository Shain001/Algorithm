package com.design.tweeter_L355;

import java.util.HashMap;
import java.util.List;

/**
 * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近 10 条推文。
 * <p>
 * 实现 Twitter 类：
 * <p>
 * Twitter() 初始化简易版推特对象
 * void postTweet(int userId, int tweetId) 根据给定的 tweetId 和 userId 创建一条新推文。每次调用此函数都会使用一个不同的 tweetId 。
 * List<Integer> getNewsFeed(int userId) 检索当前用户新闻推送中最近  10 条推文的 ID 。新闻推送中的每一项都必须是由用户关注的人或者是用户自己发布的推文。推文必须 按照时间顺序由最近到最远排序 。
 * void follow(int followerId, int followeeId) ID 为 followerId 的用户开始关注 ID 为 followeeId 的用户。
 * void unfollow(int followerId, int followeeId) ID 为 followerId 的用户不再关注 ID 为 followeeId 的用户。
 */
public class Tweeter {
    private static final int MAX_NEWS = 10;
    private final HashMap<Integer, User> users;

    public Tweeter() {
        this.users = new HashMap<>();
    }

    private User getOrCreateUser(int userId) {
        users.putIfAbsent(userId, UserFactory.createUserById(userId));

        return users.get(userId);
    }

    public void postTweet(int userId, int tweetId) {
        User user = getOrCreateUser(userId);
        user.post(TweetFactory.createTweet(userId, tweetId));
    }

    public List<Integer> getNewsFeed(int userId) {
        User user = getOrCreateUser(userId);
        return user.getNews(MAX_NEWS);
    }

    public void follow(int followerId, int followeeId) {
        User user = getOrCreateUser(followerId);
        user.follow(getOrCreateUser(followeeId));
    }

    public void unfollow(int followerId, int followeeId) {
        User follower = getOrCreateUser(followerId);
        User followee = getOrCreateUser(followeeId);
        follower.unfollow(followee);
    }
}
