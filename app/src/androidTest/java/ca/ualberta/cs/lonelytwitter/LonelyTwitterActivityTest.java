package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import junit.framework.TestCase;

/**
 * Created by wz on 14/09/15.
 */

// All controllers
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

    private EditText bodyText;
    private Button saveButton;

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();

    }

    public void testEditATweet(){

        // starts lonelyTwitter
        LonelyTwitterActivity activity = (LonelyTwitterActivity) getActivity();
        // reset the app to a known state
        activity.getTweets().clear();

        //user clicks on tweet they want to edit
        bodyText = activity.getBodyText();

        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText("hamburgers");
            }

        });
        getInstrumentation().waitForIdleSync();

        saveButton = activity.getSaveButton();

        activity.runOnUiThread(new Runnable() {
            private Button saveButton;

            public void run() {
                saveButton.performClick();
            }

        });
        getInstrumentation().waitForIdleSync();

        final ListView oldTweetsList = activity.getOldTweetsList();
        Tweet tweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        assertEquals("hamburgers", tweet.getText());



        // Following was taken from https://developer.android.com/training/activity-testing/activity-functional-testing.html
        // Set up an ActivityMonitor
        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(EditTweetActivity.class.getName(),
                        null, false);

        activity.runOnUiThread(new Runnable() {
            private Button saveButton;

            public void run() {
                View v = oldTweetsList.getChildAt(0);
                oldTweetsList.performItemClick(v, 0, v.getId());
            }

        });
        getInstrumentation().waitForIdleSync();

        // Validate that ReceiverActivity is started
        EditTweetActivity receiverActivity = (EditTweetActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                EditTweetActivity.class, receiverActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);

        // test that the tweet being shown on the edit screeen is the tweet we clicked on
        EditTweetActivity activity2 = (EditTweetActivity) getActivity();
        bodyText = activity2.getBodyText();

        activity2.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText("hamburgers");
            }

        });
        final ListView oldTweetsList2 = activity2.getOldTweetsList();
        Tweet tweet2 = (Tweet) oldTweetsList2.getItemAtPosition(0);
        assertEquals("hamburgers", tweet2.getText());


        // edit the text of that tweet
        bodyText.setText("cheeseburgers");

        // save our edits
        activity2.runOnUiThread(new Runnable() {
            private Button saveButton;

            public void run() {
                saveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        // assert that our edits were saved into the tweet correctly
        assertEquals("cheeseburgers", bodyText.getText());

        // assert that our edits are shown on the screen to the user back in the main activity
        activity.onBackPressed();
        final ListView oldTweetsList3 = activity2.getOldTweetsList();
        Tweet tweet3 = (Tweet) oldTweetsList3.getItemAtPosition(0);
        assertEquals("cheeseburgers", tweet3.getText());


        //end of test clear the data

        receiverActivity.finish();
    }
}