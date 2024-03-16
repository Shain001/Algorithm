package com.design.tweeter_L355;

import java.time.LocalDateTime;

public class Tweet {
    protected int id;
    protected int postedBy;
    protected LocalDateTime postedAt;

    public Tweet(int id, int postedBy, LocalDateTime postedAt) {
        this.id = id;
        this.postedBy = postedBy;
        this.postedAt = postedAt;
    }

    public LocalDateTime getPostedAt() {
        return this.postedAt;
    }
}
