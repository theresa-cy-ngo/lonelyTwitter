package ca.ualberta.cs.lonelytwitter;
import java.util.Date;

/**
 * Created by tcngo on 9/15/15.
 */
public class MoodB extends Mood {

    private Date date;

    public MoodB(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String currentMood() {
        return "Bad Mood";
    }

    public MoodB(String currentMood, Date date){
        currentMood = "Bad Mood";
        this.date = date;
    }

}
