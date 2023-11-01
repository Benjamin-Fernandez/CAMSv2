package CAMSv2;
import java.util.*;

public class Question {
    //attritbutes
    String question;
    ArrayList<String> reply;

    //constructor
    public Question(String question){
        this.question = question;
        this.reply = new ArrayList<String>() ;
    }
    //methods
    public ArrayList<String> getReply() {
        return reply;
    }
}
