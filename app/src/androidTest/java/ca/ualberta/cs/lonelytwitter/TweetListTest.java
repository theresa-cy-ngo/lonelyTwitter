package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

import java.util.Observer;

/**
 * Created by joshua2 on 9/29/15.
 */

public class TweetListTest extends ActivityInstrumentationTestCase2 implements MyObserver {

    public TweetListTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class); //controller
    }

    public void testHoldsStuff() {

        TweetList list = new TweetList();   // Creates a new TweetList //controller
        Tweet tweet = new NormalTweet("test");  // Creates tweet = "test" //controller
        list.add(tweet); //controller
        assertSame(list.getMostRecentTweet(), tweet); // Indicate that both should be the same //controller
    }

    public void testHoldsManyThings() {

        TweetList list = new TweetList();   // Creates a new TweetList //controller
        Tweet tweet = new NormalTweet("test");  // Creates tweet = "test" //controller
        list.add(tweet); //controller
        assertEquals(list.getCount(), 1);//controller
        list.add(new NormalTweet("test"));//controller
        assertEquals(list.getCount(),2);//controller
    }

    public void testCheckDuplicates() {
        TweetList list = new TweetList();   // Creates a new TweetList //controller
        Tweet tweet = new NormalTweet("test");  // Creates tweet = "test" //controller
        list.add(tweet); //controller
        Tweet tweet2 = tweet; //controller
        assertTrue(list.hasTweet(tweet2)); // Assert that list has tweet2 //controller
        Tweet tweet3 = new NormalTweet("test2"); //controller
        assertFalse(list.hasTweet(tweet3)); //controller
    }

    public void testAddDuplicates() {
        try {       // If the try&catch is removed, the test should fail due to the exception
            TweetList list = new TweetList();   // Creates a new TweetList //controller
            Tweet tweet = new NormalTweet("test");  // Creates tweet = "test" //controller
            list.addTweet(tweet); //controller
            Tweet tweet2 = tweet; //controller
            list.addTweet(tweet2); //controller
        } catch (IllegalArgumentException e) {
            System.err.println("Tweet already exists."); //view
        }
    }

    public void testGetList() {
        TweetList list = new TweetList(); //controller
        TweetList list2 = new TweetList(); //controller
        Tweet tweet = new NormalTweet("test"); //controller
        Tweet tweet2 = new NormalTweet("test2"); //controller
        Tweet tweet3 = new NormalTweet("test3"); //controller
        list.addTweet(tweet); //controller
        list.addTweet(tweet2); //controller
        list.addTweet(tweet3); //controller

        assertTrue((list==list2)); //view
    }

    private Boolean weWereNotNotified;

    private Boolean weWereNotified;

    public void myNotify(MyObservable observable) {
        weWereNotified = Boolean.TRUE;
    }

    public void testObservable() {
        TweetList list = new TweetList();
        // needs an addObserver
        list.addObserver(this);
        Tweet tweet = new NormalTweet("test");
        weWereNotified = Boolean.FALSE;
        list.add(tweet);
        // we should have been notified here
        assertTrue(weWereNotified);
    }

    public void testModifyTweetInList() {
        TweetList list = new TweetList();
        // needs an addObserver
        list.addObserver(this);
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        weWereNotified = Boolean.FALSE;
        tweet.setText("diffferent text");
        // we should have been notified here
        assertTrue(weWereNotified);
    }

}

