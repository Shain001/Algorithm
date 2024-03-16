package com.design.tweeter_L355;

import java.time.LocalDateTime;

public class TweetFactory {
    public static Tweet createTweet(int createdBy, int tweetId) {
        return new Tweet(tweetId, createdBy, LocalDateTime.now());
    }
}
