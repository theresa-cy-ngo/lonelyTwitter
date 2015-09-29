package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

/**
 * Created by tcngo on 9/29/15.
 */
public class TweetList {
    private Tweet mostRecentTweet;
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
    private int counter;


    public void add(Tweet tweet) {
        mostRecentTweet = tweet;
        tweets.add(tweet);
    }

    public Tweet getMostRecentTweet() {
        return mostRecentTweet;
    }

    public int getCount() {
        return tweets.size();
    }

    public boolean hasTweet(Tweet tweet) {
        for (counter = 0; counter < tweets.size(); counter++) {
            if (tweets.get(counter) == tweet) {
                return true;
            }
        }
        return false;
    }

    public void addTweet(Tweet tweet) {
        if (this.hasTweet(tweet)){
            throw new IllegalArgumentException();
        } else {
            tweets.add(tweet);
        }

    }

    public ArrayList<Tweet> getTweets() {
        ArrayList<Tweet> list2 = new ArrayList<Tweet>();
        list2 = (ArrayList<Tweet>)tweets.clone();
        return list2;
    }


}
