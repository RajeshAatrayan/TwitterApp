package com.ibrickedlabs.Database;

import com.ibrickedlabs.Data.Tweets;

import java.util.ArrayList;
import java.util.HashMap;

/*
TweetDataBase is a singleton class which acts as Database
 */
public class TweetDataBase {
    public static  TweetDataBase tweetDataBase;

    private TweetDataBase() {

    }

    public HashMap<String, Integer> hashTagDb = new HashMap<>();
    public ArrayList<Tweets> tweetsDb = new ArrayList<>();

    public static TweetDataBase openConnection() {
        if (tweetDataBase == null) {
            synchronized (TweetDataBase.class) {
                tweetDataBase = new TweetDataBase();
            }
        }
        return tweetDataBase;
    }
}
