package ca.ualberta.cs.lonelytwitter;
import java.util.Date;

/**
 * Created by tcngo on 9/15/15.
 */
public class MoodA extends Mood {

    private Date date;

    public MoodA(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String currentMood() {
        return "Good Mood";
    }

    public MoodA(String currentMood, Date date){
        currentMood = "Good Mood";
        this.date = date;
    }

}
