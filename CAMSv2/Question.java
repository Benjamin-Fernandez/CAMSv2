package CAMSv2;
import java.util.*;

public class Question {
    //attritbutes
    // added camp name to identify which camp does question belongs to.
    boolean processed = false;
    int id;
    String campName;
    String question;
    ArrayList<String> reply;

    //constructor
    public Question(String question, String campNameString, int id){
        this.question = question;
        this.campName = campNameString;
        this.reply = new ArrayList<String>();
        this.id = id;
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
    public int getQuestionId() {
        return this.id;
    }

    public void setReply(String Reply) {
        this.reply.add(Reply);
        processed = true;

    }

}
