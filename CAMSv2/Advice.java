package CAMSv2;

//import java.util.ArrayList;

public class Advice {
    //attritbutes
    private String name;
    private String advice;
    private Boolean approved;
    private int id;

    //constructor
    public Advice(String advice, int id, String name){
        this.advice= advice;
        this.approved = false;
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
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

    public int getId() {
        return id;
    }


}  

