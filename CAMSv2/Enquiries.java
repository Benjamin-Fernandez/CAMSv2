package CAMSv2;

import java.util.*;

public class Enquiries {
    //enquiry will hold a list of question and replies
    private ArrayList<Questions> Questions;
    //private ArrayList<String> reply;

    public Enquiries(Question question){
        this.Questions.add(question);
        //this.reply = new ArrayList<String>();
    }

    public ArrayList<Question> getQuestion(){
        return Questions;
    }

    
}
