package CAMSv2;

import java.util.Scanner;

public class CampCommitteeMember extends Student {
    private int points;
    private Camp camp;

    public CampCommitteeMember(String emailID, String password, String faculty, String name, Role role, Camp camp) {
        super(emailID, password, faculty, name, role);
        points = 0;
        this.camp = camp;
    }


    public void viewEnquiries() {
        EnquiryManager enquiryManager = new EnquiryManager();
        enquiryManager.viewEnquiryForCampCommitteeMember(camp.getCampName());
    }

    public void replyEnquiries(){
        EnquiryManager enquiryManager = new EnquiryManager();
        enquiryManager.replyEnquiryFromCampCommitteeMember(camp.getCampName());
        points++;
    }

    public void submitSuggestion(){
        Scanner sc = new Scanner(System.in);
        SuggestionManager suggestionManager = new SuggestionManager();
        System.out.println("What suggestion would you like to make");
        String suggestion = sc.nextLine();
        suggestionManager.createSuggestion(camp.getCampName(), suggestion, this.name);
        System.out.println("Suggestion added");
        points++;

    }

    public void viewSuggestion() {
        SuggestionManager suggestionManager = new SuggestionManager();
        suggestionManager.viewSuggestionForCommitteeMember(this.name,camp.getCampName());
    }

    public void editSuggestion(){
        SuggestionManager suggestionManager = new SuggestionManager();
        suggestionManager.editSuggestionForCommitteeMember(this.name,camp.getCampName());

    }

    public void deleteSuggestion(){
        SuggestionManager suggestionManager = new SuggestionManager();
        suggestionManager.deleteSuggestionForCommitteeMember(this.name,camp.getCampName());
    }

    public void addPointsForSuggestions(){
        points +=1;
    }

    public Camp getCamp(){
        return camp;
    }
    public int getPoints(){
        return points;
    }

}
