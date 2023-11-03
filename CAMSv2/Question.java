package CAMSv2;
import java.util.*;

public class Question {
    //attritbutes
    // added camp name to identify which camp does question belongs to.
    boolean processed = false;
    String campName;
    String question;
    ArrayList<String> reply;

    //constructor
    public Question(String question, String campNameString){
        this.question = question;
        this.campName = campNameString;
        this.reply = new ArrayList<String>() ;
    }

    
    //methods
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getQuestion() {
        return question;
    }

    public String getCampName() {
        return campName;
    }
    public ArrayList<String> getReply() {
        return reply;
    }

    public void setReply(String Reply) {
        this.reply.add(Reply);
        processed = true;

    }
}
