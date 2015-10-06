package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;
import java.util.Date;

/**
 * Created by joshua2 on 9/16/15.
 */
public abstract class Tweet extends Object implements Tweetable {
    private String text; //controller
    protected Date date; //controller

    public Tweet(String tweet, Date date) throws TweetTooLongException {
        this.setText(tweet); //view
        this.date = date; //controller
    }

    public Tweet(String tweet) throws TweetTooLongException {
        this.setText(tweet); //view
        this.date = new Date(); //controller
    }

    public String getText() {
        return text;
    } //view

    public void setText(String text) throws TweetTooLongException {
        if (text.length() <= 140) {
            this.text = text; //controller
        } else {
            throw new TweetTooLongException(); //controller
        }
    }

    public Date getDate() {
        return date;
    } //controller

    public void setDate(Date date) {
        this.date = date; //controller
    }

    public abstract Boolean isImportant(); //controller

    @Override
    public String toString() {
        return date.toString() + " | " + text;
    } //view

}
