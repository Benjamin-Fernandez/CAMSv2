package CAMSv2;

import java.util.Scanner;

/**
 * The {@code CampCommitteeMember} class represents a Camp Committee Member, extending the Student class.
 * Manages committee-specific actions within a camp.
 * @author Zhu Yu Hao
 * @since 13-11-2023
 */

public class CampCommitteeMember extends Student {
    private int points;
    private Camp camp;
    private Suggestion suggestion; 


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
        // initialize Suggestion
        this.suggestion = new Suggestion(name);
    }

    /**
     * Overrides the password change method and updates the database.
     */
    @Override
    public void changePassword(String newPassword) {
        super.changePassword(newPassword);
        CampCommitteeDataBase.getInstance().writeToCSV();
    }


    /**
     * Submits a suggestion for the associated camp.
     * @param suggestion The suggestion to be submitted.
     */
    public void submitSuggestion(String suggestion){
        Advice advice = new Advice(suggestion, SuggestionManager.getInstance().getId(), this.name);
        SuggestionManager.getInstance().createSuggestion(advice, this.camp, this.name);
        System.out.println("Suggestion added");
    }

    public void generateCampReport(IReportFilter filter) {
        camp.generateCampReport(filter);
    }

    public void generateCampCommitteeReport() {
        camp.generateCampCommitteeReport();
    }

    public void generateStudentsEnquiryReport() {
        camp.generateStudentsEnquiryReport();
    }



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

    @Override
    public void displayProfile() {
        super.displayProfile();
        System.out.println("Camp(CCM): " + camp.getCampName());
    }



}
