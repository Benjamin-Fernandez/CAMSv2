package CAMSv2;

//import java.util.ArrayList;

public class Advice {
    //attritbutes
    private String advice;
    private Boolean approved;

    //constructor
    public Advice(String advice){
        this.advice= advice;
        this.approved = false;
    }


    public void setApproval(boolean bool){
        this.approved = bool;
    }

    public void setNewAdvice(String newAdvice){
        advice = newAdvice;
    }

    public String getAdvice() {
        return advice;
    }

    public Boolean getApproved() {
        return approved;
    }

}  

