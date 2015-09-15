package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;

/**
 * Created by tcngo on 9/15/15.
 */
public interface Tweetable { // Methods but they don't have code
    public void setText(String text) throws IOException; // We haven't specified what they do yet
    public String getText();
}
