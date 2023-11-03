package CAMSv2;

import java.util.*;

public class Enquiries {
    //enquiry will hold a list of question and replies
    private String enqurier;
    private ArrayList<Question> Questions;
    

    public Enquiries(Question question){
        this.Questions.add(question);
        
    }

    public ArrayList<Question> getQuestion(){
        return Questions;
    }

    public Enquiries getEnquirier{
        //get function
    }

    

    
}
