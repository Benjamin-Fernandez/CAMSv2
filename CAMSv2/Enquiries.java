package CAMSv2;

import java.util.*;

public class Enquiries {
    //enquiry will hold a list of question and replies
    private String enqurier;
    private ArrayList<Question> Questions = new ArrayList<Question>();
    

    public Enquiries(String enqurier){
        this.enqurier = enqurier;       
    }

    public void addQuestion(Question question) {
        Questions.add(question);
    }

    public ArrayList<Question> getQuestions(){
        return Questions;
    }

    public Question getQuestion(int Id) {
        for (Question question : Questions) {
            if (question.getQuestionId() == Id) {
                return question;
            }
        }
        return null;
    }

    public String getEnqurier() {
        return enqurier;
    }

    

    
}
