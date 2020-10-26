package com.ibrickedlabs.Data;


public class Tweets {
    private String tweet;
    private String hashtag;

    public Tweets(String tweet, String hashtag) {
        this.tweet = tweet;
        this.hashtag = hashtag;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }
}
