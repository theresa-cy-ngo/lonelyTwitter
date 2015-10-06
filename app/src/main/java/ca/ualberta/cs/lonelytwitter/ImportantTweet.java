package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by joshua2 on 9/16/15.
 */
public class ImportantTweet extends Tweet {
    public ImportantTweet(String tweet, Date date) {
        super(tweet, date); //controller
        this.setText(tweet); //view
        this.date = date; //controller
    }

    public ImportantTweet(String tweet) {
        super(tweet);
    } //controller

    public Boolean isImportant() {
        return Boolean.TRUE;
    } //controller

    @Override
    public String getText() {
        return "!!!" + super.getText();
    } //view

}
