package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by joshua2 on 9/16/15.
 */
public class NormalTweet extends Tweet {
    public NormalTweet(String tweet, Date date) {
        super(tweet, date);
    } //controller

    public NormalTweet(String tweet) {
        super(tweet);
    } //controller

    public Boolean isImportant() {
        return Boolean.FALSE;
    } //controller
}
