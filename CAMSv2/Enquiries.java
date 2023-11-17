package CAMSv2;

import java.util.*;

public class Enquiries {
    //enquiry will hold a list of question and replies
    private String enqurier = "";
    private ArrayList<Question> questions = new ArrayList<Question>();
    

    public Enquiries(String enqurier){
        this.enqurier = enqurier;       
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public ArrayList<Question> getQuestions(){
        return questions;
    }

    public Question getQuestion(int Id) {
        for (Question question : questions) {
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
