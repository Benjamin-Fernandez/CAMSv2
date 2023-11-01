package CAMSv2;

//import java.util.ArrayList;

public class Advice {
    //attritbutes
    String advice;
    Boolean approved;

    //constructor
    public Advice(String advice){
        this.advice= advice;
        this.approved = false;
    }

    public void setApproval(boolean bool){
        this.approved = bool;
    }

}  

