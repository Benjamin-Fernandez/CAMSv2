package CAMSv2;
import java.util.*;

public class Question {
    //attritbutes
    // added camp name to identify which camp does question belongs to.
    boolean processed = false;
    int id;
    String campName;
    String question;
    ArrayList<Reply> replies;

    //constructor
    public Question(String question, String campNameString, int id){
        this.question = question;
        this.campName = campNameString;
        this.replies = new ArrayList<Reply>();
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
    public ArrayList<Reply> getReplies() {
        return replies;
    }
    public int getQuestionId() {
        return this.id;
    }

    public void setReply(Reply Reply) {
        this.replies.add(Reply);
        processed = true;

    }

    public boolean getStatus() {
        return processed;
    }

}
