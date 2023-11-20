package CAMSv2;

import java.util.Scanner;

/**
 * @author Zhu Yu Hao
 * @since 13-11-2023
 * The {@code CampCommitteeMember} class represents a Camp Committee Member, extending the Student class.
 * Manages committee-specific actions within a camp.
 */

public class CampCommitteeMember extends Student {
    private int points;
    private Camp camp;


    /**
     * Constructor for CampCommitteeMember.
     * @param name      The name of the committee member.
     * @param emailID   The email ID of the committee member.
     * @param faculty   The faculty or user group of the committee member.
     * @param password  The password of the committee member.
     * @param role      The role of the committee member.
     * @param camp      The associated camp for the committee member.
     */
    public CampCommitteeMember(String name, String emailID, UserGroup faculty, String password, Role role, Camp camp) {
        super(name, emailID, faculty, password, role);
        points = 0;
        this.camp = camp;
    }

    /**
     * Overrides the password change method and updates the database.
     */
    @Override
    public void changePassword() {
        // TODO Auto-generated method stub
        super.changePassword();
        CampCommitteeDataBase.getInstance().writeToCSV();
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

    /**
     * Submits a suggestion for the associated camp.
     * @param suggestion The suggestion to be submitted.
     */
    public void submitSuggestion(String suggestion){
        SuggestionManager.getInstance().createSuggestion(camp.getCampName(), suggestion, this.name);
        System.out.println("Suggestion added");
    }

    // public void viewSuggestion() {
    //     SuggestionManager suggestionManager = SuggestionManager.getInstance();
    //     suggestionManager.viewSuggestionForCommitteeMember(this.name,camp.getCampName());
    // }

    // public void editSuggestion(){
    //     SuggestionManager suggestionManager = SuggestionManager.getInstance();
    //     suggestionManager.editSuggestionForCommitteeMember(this.name,camp.getCampName());

    // }

    // public void deleteSuggestion(){
    //     SuggestionManager suggestionManager = SuggestionManager.getInstance();
    //     suggestionManager.deleteSuggestionForCommitteeMember(this.name,camp.getCampName());
    // }

    /**
     * Increments the points of the committee member by one.
     */
    public void addPointsByOne(){
        points +=1;
    }

    /**
     * Retrieves the associated camp.
     * @return The camp associated with the committee member.
     */
    public Camp getCamp(){
        return camp;
    }

    /**
     * Retrieves the points of the committee member.
     * @return The points of the committee member.
     */
    public int getPoints(){
        return points;
    }



}
