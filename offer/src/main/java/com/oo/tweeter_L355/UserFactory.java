package com.oo.tweeter_L355;

public class UserFactory {
    public static User createUserById(int uid) {
        return new User(uid);
    }
}
