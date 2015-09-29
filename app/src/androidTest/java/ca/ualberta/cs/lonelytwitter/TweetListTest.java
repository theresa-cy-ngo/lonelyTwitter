package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by tcngo on 9/29/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 {

    public TweetListTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testHoldsStuff() {
        TweetList list = new TweetList();   // Creates a new TweetList
        Tweet tweet = new NormalTweet("test");  // Creates tweet = "test"
        list.add(tweet);
        assertSame(list.getMostRecentTweet(), tweet); // Indicate that both should be the same
    }

    public void testHoldsManyThings() {
        TweetList list = new TweetList();   // Creates a new TweetList
        Tweet tweet = new NormalTweet("test");  // Creates tweet = "test"
        list.add(tweet);
        assertEquals(list.getCount(), 1);
        list.add(new NormalTweet("test"));
        assertEquals(list.getCount(),2);
    }

    public void testCheckDuplicates() {
        TweetList list = new TweetList();   // Creates a new TweetList
        Tweet tweet = new NormalTweet("test");  // Creates tweet = "test"
        list.add(tweet);
        Tweet tweet2 = tweet;
        assertTrue(list.hasTweet(tweet2)); // Assert that list has tweet2
        Tweet tweet3 = new NormalTweet("test2");
        assertFalse(list.hasTweet(tweet3));
    }

    public void testAddDuplicates() {
        try {       // If the try&catch is removed, the test should fail due to the exception
            TweetList list = new TweetList();   // Creates a new TweetList
            Tweet tweet = new NormalTweet("test");  // Creates tweet = "test"
            list.addTweet(tweet);
            Tweet tweet2 = tweet;
            list.addTweet(tweet2);
        } catch (IllegalArgumentException e) {
            System.err.println("Tweet already exists.");
        }
    }

    public void testGetList() {
        TweetList list = new TweetList();
        TweetList list2 = new TweetList();
        Tweet tweet = new NormalTweet("test");
        Tweet tweet2 = new NormalTweet("test2");
        Tweet tweet3 = new NormalTweet("test3");
        list.addTweet(tweet);
        list.addTweet(tweet2);
        list.addTweet(tweet3);
        list2 = list.getTweets();
        assertTrue((list==list2));
    }

}