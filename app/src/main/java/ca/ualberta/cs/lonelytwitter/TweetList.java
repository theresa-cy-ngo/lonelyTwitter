package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import ca.ualberta.cs.lonelytwitter.MyObserver;

/**
 * Created by joshua2 on 9/29/15
 */

public class TweetList extends Object {
    private Tweet mostRecentTweet; //controller
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>(); //controller
    private int counter; //controller

    public void add(Tweet tweet) {
        mostRecentTweet = tweet; //controller
        tweets.add(tweet);//controller
    }

    public Tweet getMostRecentTweet() {
        return mostRecentTweet;
    } //controller


    public int getCount() {
        return tweets.size();
    }


    public boolean hasTweet(Tweet tweet) {
        for (counter = 0; counter < tweets.size(); counter++) {
            if (tweets.get(counter) == tweet) {
                return true;//controller
            }
        }
        return false; //controller
    }

    public void addTweet(Tweet tweet) {
        if (this.hasTweet(tweet)){
            throw new IllegalArgumentException(); //controller
        } else {
            tweets.add(tweet);//controller
        }

    }

    public ArrayList<Tweet> getTweets() {
        ArrayList<Tweet> list2 = new ArrayList<Tweet>(); //controller
        list2 = (ArrayList<Tweet>)tweets.clone(); //controller
        return list2;
    }

    private volatile ArrayList<MyObserver> observers = new ArrayList<MyObserver>(); //controller

    private void notifyAllObservers(){
        for (MyObserver observer: observers){
            observer.myNotify(this); //controller
        }

    }

    public void myNotify(MyObservable observable){
        notifyAllObservers(); //controller
    }


    private volatile ArrayList<MyObserver> observers = new ArrayList<MyObserver>();

    public void addObserver(MyObserver observer) {
        observers.add(observer);
    }

    private void notifyAllObservers() {
        for (MyObserver observer : observers) {
            observer.myNotify(this);
        }
    }

    public void myNotify(MyObservable observable) {
        notifyAllObservers();
    }

}
