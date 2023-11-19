package CAMSv2;

import java.util.Scanner;

public class CampCommitteeMember extends Student {
    private int points;
    private Camp camp;

    public CampCommitteeMember(String name, String emailID, UserGroup faculty, String password, Role role, Camp camp) {
        super(name, emailID, faculty, password, role);
        points = 0;
        this.camp = camp;
    }


    // public void viewEnquiries() {
    //     EnquiryManager enquiryManager = new EnquiryManager();
    //     enquiryManager.viewEnquiryForCampCommitteeMember(camp.getCampName());
    // }

    // public void replyEnquiries(){
    //     EnquiryManager enquiryManager = new EnquiryManager();
    //     enquiryManager.replyEnquiryFromCampCommitteeMember(camp.getCampName());
    //     points++;
    // }

    public void submitSuggestion(String suggestion){
        SuggestionManager.getInstance().createSuggestion(camp.getCampName(), suggestion, this.name);
        System.out.println("Suggestion added");
        points++;
    }

    public void viewSuggestion() {
        SuggestionManager suggestionManager = SuggestionManager.getInstance();
        suggestionManager.viewSuggestionForCommitteeMember(this.name,camp.getCampName());
    }

    public void editSuggestion(){
        SuggestionManager suggestionManager = SuggestionManager.getInstance();
        suggestionManager.editSuggestionForCommitteeMember(this.name,camp.getCampName());

    }

    public void deleteSuggestion(){
        SuggestionManager suggestionManager = SuggestionManager.getInstance();
        suggestionManager.deleteSuggestionForCommitteeMember(this.name,camp.getCampName());
    }

    public void addPointsByOne(){
        points +=1;
    }

    public Camp getCamp(){
        return camp;
    }
    public int getPoints(){
        return points;
    }



}
