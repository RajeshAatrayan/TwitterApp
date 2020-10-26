package com.ibrickedlabs;

import com.ibrickedlabs.Data.Tweets;
import com.ibrickedlabs.Database.TweetDataBase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class TwitterApp {
    private static TweetDataBase tweetDataBase;

    public static void main(String[] args) throws Exception {
        /*
        BufferedReader to read the input from the console
         */
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the number of tweets you want to input");
        int n = Integer.parseInt(bufferedReader.readLine());
        /*
                Open the db connection to store the hashtags and tweets
         */
        if (n > 0) tweetDataBase = TweetDataBase.openConnection();
        else return;
        /*
        To Read the input n` times
         */
        while (n-- > 0) {
            System.out.println("Enter the tweet");
            String inputTweet = bufferedReader.readLine();
            saveInputToDb(inputTweet);

        }
        displayTopTenHashTags();
        closeDbConnection();
    }

    private static void closeDbConnection() {
        tweetDataBase = null;
    }

    private static void displayTopTenHashTags() {
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(tweetDataBase.hashTagDb.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return -(o1.getValue() - o2.getValue());
            }
        });
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        int count = 0;
        System.out.println("Here are the Top 10 HashTags");
        for (Map.Entry<String, Integer> x : list) {
            if (count > 10) return;
            System.out.println(x.getKey());
            count++;
        }

    }

    private static void saveInputToDb(String inputTweet) {
        String str[] = inputTweet.split("\\s");
        String hashTag = "";
        for (String word : str) {
            if (word.contains("#")) {
                hashTag = word;
                tweetDataBase.hashTagDb.put(word, tweetDataBase.hashTagDb.getOrDefault(word, 0) + 1);
            }
        }
        tweetDataBase.tweetsDb.add(new Tweets(inputTweet, hashTag));
    }
}
