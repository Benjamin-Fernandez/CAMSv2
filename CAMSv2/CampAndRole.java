package CAMSv2;


import java.util.Scanner;

public class CampAndRole{
    private Camp camp;
    private Role role;

    CampAndRole(Camp camp, Role role) {
        this.camp = camp;
        this.role = role;
    }

    public Camp getCamp() {
        return camp;
    }

    public Role getRole() {
        return role;
    }

    public void ViewDetails(){
        role.viewDetails(this.camp);
    }

    public void viewEnquiries(){
        role.viewEnquiries(camp.getCampName());
    }

    public void replyEnquiries(){
        role.replyEnquiries(camp.getCampName());
    }

    public void submitSuggestion(String studentName){
        role.submitSuggestion(studentName, camp);
    }
    public void viewSuggestion(String studentName){
        role.viewSuggestion(studentName, camp.getCampName());
    }




}
