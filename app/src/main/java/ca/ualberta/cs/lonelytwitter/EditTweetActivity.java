package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class EditTweetActivity extends Activity {

    private static final String FILENAME = "file.sav"; //model
    private ListView oldTweetsList1;

    public Button getSaveButton() {
        return saveButton;
    }

    private Button saveButton;

    public EditText getBodyText() {
        return bodyText;
    }

    private EditText bodyText; //controller
    private ListView oldTweetsList; //controller
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>(); //controller
    private ArrayAdapter<Tweet> adapter; //controller

    public ListView getOldTweetsList() {
        return oldTweetsList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tweet);

        bodyText = (EditText) findViewById(R.id.body); //view
        saveButton = (Button) findViewById(R.id.save);
        Button clearButton = (Button) findViewById(R.id.clear); //view

        oldTweetsList1 = oldTweetsList;
        oldTweetsList1 = (ListView) findViewById(R.id.oldTweetsList); //view

        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK); //model
                String text = bodyText.getText().toString(); //controller
                tweets.add(new NormalTweet(text)); //controller
                adapter.notifyDataSetChanged(); // view because it changes the screen
                saveInFile(); //model - changing stuff on the disk
                //saveInFile(text, new Date(System.currentTimeMillis()));
                //finish();

            }
        });

        // Implements the clear button
        clearButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK); // model
                tweets.clear();        // Clears the array - controller
                adapter.notifyDataSetChanged(); //view
                saveInFile();        // Uses saveInFile to save the cleared array - model

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_tweet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadFromFile() {

        //ArrayList<String> tweets = new ArrayList<String>();

        try {
            FileInputStream fis = openFileInput(FILENAME); //model
            BufferedReader in = new BufferedReader(new InputStreamReader(fis)); //model
            Gson gson = new Gson(); //model

            // Taken from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html
            Type listType = new TypeToken<ArrayList<NormalTweet>>() {}.getType(); //controller
            tweets = gson.fromJson(in, listType); //controller
            //String line = in.readLine();
            //while (line != null) {
            //	tweets.add(line);
            //	line = in.readLine();
            //}

        } catch (FileNotFoundException e) {
            tweets = new ArrayList<Tweet>(); //controller
            //e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e); //controller
            //e.printStackTrace();
        }
        //return tweets.toArray(new String[tweets.size()]);

    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,0); //model
            OutputStreamWriter writer = new OutputStreamWriter(fos); //model
            Gson gson = new Gson(); //model
            gson.toJson(tweets, writer); //model
            writer.flush(); //model
            fos.close(); //model
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e); //controller

            //e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e); //controller
            //e.printStackTrace();
        }
    }
}
