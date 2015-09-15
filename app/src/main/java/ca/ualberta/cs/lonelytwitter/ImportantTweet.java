package ca.ualberta.cs.lonelytwitter;
import java.util.Date;

/**
 * Created by tcngo on 9/15/15.
 */
public class ImportantTweet extends Tweet{ // Adds everything (such as methods) from the Tweet class

    public ImportantTweet(String text) {
        super(text);
    }

    public ImportantTweet (String tweet, Date date) {
        super(tweet, date);
    }

    public Boolean isImportant(){
        return Boolean.TRUE;
    }

    @Override
    public String getText() {
        return "Important: " + super.getText();
    }
}
