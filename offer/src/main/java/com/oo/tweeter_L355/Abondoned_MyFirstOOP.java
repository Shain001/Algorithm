package com.oo.tweeter_L355;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Push 模式版本。
 *
 * 问题： user 2 follow user 1， 然后再 getNews, 无法得到 user1 在被 user2 follow以前post的tweets
 * 解决： （略了没写完）在follow用户的时候获取这个用户的推文， 加入到自己的news里面。 但是这里有个问题， 这个写法没有维护每个用户自己的推文， 得到news里面找。
 *
 * 这个方法不继续了， 看另一个面向对象的。 这版很垃圾， 没有面向对象等等。
 */
public class Abondoned_MyFirstOOP {
    private class Tweet {
        protected int uid;
        protected int tweetId;
        protected LocalDateTime postedAt;

        private Tweet(int uid, int tweetId) {
            this.uid = uid;
            this.tweetId = tweetId;
            this.postedAt = LocalDateTime.now();
        }
    }

    // key -> uId, val -> list of followers;
    private Map<Integer, Set<Integer>> followers;
    // key -> uId, val -> listOfNews
    private Map<Integer, Deque<Tweet>> news;
    // k: uid; val: unfollowed users.
    // 采用lazy delete 策略在获取news时候删除已经取关的用户。
    private Map<Integer, Set<Integer>> unfollowed;
    private final static int NO_OF_NEWS = 10;

    public Abondoned_MyFirstOOP() {
        followers = new HashMap<>();
        news = new HashMap<>();
        unfollowed = new HashMap<>();
    }

    private void addNews(int addTo, int tweetId, int postedBy) {
        Deque<Tweet> myNews = news.getOrDefault(addTo, new LinkedList<>());
        myNews.add(new Tweet(postedBy, tweetId));
        news.put(addTo, myNews);
    }

    public void postTweet(int userId, int tweetId) {
        // 1. add to self news;
        addNews(userId, tweetId, userId);

        // 2. get all followers
        Set<Integer> myFollowers = followers.getOrDefault(userId, new HashSet<Integer>());

        // 3. add to all follower's news
        for (Integer followerId : myFollowers) {
            addNews(followerId, tweetId, userId);
        }
    }

    public List<Integer> getNewsFeed(int userId) {
        // 1. get queue of currentUser
        Deque<Tweet> myNews = news.getOrDefault(userId, new LinkedList<>());

        // 1.1 if not exist, return emptyList
        if (myNews.isEmpty()) {
            return Collections.emptyList();
        }

        // 2. return first 10
        return getNews(userId, myNews, NO_OF_NEWS);
    }

    private List<Integer> getNews(int userId, Deque<Tweet> news, int n) {
        List<Integer> ans = new ArrayList<>();
        Set<Integer> myUnfollowed = unfollowed.getOrDefault(userId, new HashSet<>());

        Iterator<Tweet> iterator = news.iterator();
        while (iterator.hasNext() && n > 0) {
            Tweet cur = iterator.next();
            if (myUnfollowed.contains(cur.uid)) {
                continue;
            }
            n--;
            ans.add(cur.tweetId);
        }

        return ans;
    }

    public void follow(int followerId, int followeeId) {
        // 1. 在被关注用户中添加 followerId
        // 1.1 用户不存在用try catch， 此处省略。
        Set<Integer> myFollowers = followers.getOrDefault(followeeId, new HashSet<>());
        myFollowers.add(followerId);
        followers.put(followeeId, myFollowers);

        // 2. 更新news 的缓存

    }

    public void unfollow(int followerId, int followeeId) {
        if (!followers.containsKey(followeeId)) {
            return;
        }

        Set<Integer> myFollowers = followers.get(followeeId);
        // should check if the follower exist first
        myFollowers.remove(followerId);

        // 把删除的被关注人加入我的已删除列表
        Set<Integer> myUnfollowed = unfollowed.getOrDefault(followerId, new HashSet<>());
        myUnfollowed.add(followeeId);
        unfollowed.put(followerId, myUnfollowed);
    }
}
