package CAMSv2;

import java.util.ArrayList;

public class Suggestion {
    private String studentName;
    private ArrayList<Advice> AdviceList;
    
    //methods
    public Suggestion(String student){
        this.studentName = student;
        this.AdviceList = new ArrayList<Advice>();
    }

    public void addAdvice(Advice advice) {
        AdviceList.add(advice);
    }

    public String getStudent() {
        return studentName;
    }
    public ArrayList<Advice> getAdviceList(){
        return AdviceList;
    }


}
