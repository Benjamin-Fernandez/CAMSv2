package CAMSv2;

import java.util.*;

public class Staff extends User {
    private HashSet<Camp> createdCamps;
    public Staff(String emailID, String password, UserGroup faculty, String name, Role role){
        super(emailID,password,faculty,name,role);
        // initialize the createdCamps array
        createdCamps = searchForCreatedCamps();
    }

    private HashSet<Camp> searchForCreatedCamps() {
        HashSet<Camp> camps = new HashSet<Camp>();
        for (Camp camp : CampManager.getInstance().getCampList()) {
            if (camp.getStaffName().equals(name)) {
                camps.add(camp);
            }
        }
        return camps;
    }

    public HashSet<Camp> getCreatedCamps() {
        return createdCamps;
    }

    @Override
    public void changePassword(String newPassword) {
        super.changePassword(newPassword);
        StaffDataBase.getInstance().writeToCSV();
    }

    public boolean approveAdvice(Camp camp, int id){
        // get the Advice object (Suggestion is referring to advice here)
        Advice advice = camp.getAdviceFromCamp(id);
        if (advice == null) {
            System.out.println("Please provide a valid Suggestion Id!");
            return true;
            }

        advice.setApproval(true);
        addPointsForApprovedSuggestions(advice.getName());
        System.out.println("Successfully set Suggestion status");
        camp.printSuggestionList();

        return true;
    }

    public void generateStaffReport(ReportFilter filter) {
        // get staff camps
        for (Camp camp : createdCamps) {
            // call each camp to generate report
            camp.generateCampReport(filter);
        }
    }

    public void generateCampCommitteeReport() {
        for (Camp camp : createdCamps) {
            camp.generateCampCommitteeReport();
        }
    }

    public void generateStudentsEnquiryReport() {
        for (Camp camp : createdCamps) {
            camp.generateStudentsEnquiryReport();
        }
    }

    public String getCampName(int indexOfCamp){
        String campName;

        //find name of camp
        ArrayList<Camp> campList = CampManager.getInstance().getCampList();
        campName = campList.get(indexOfCamp).getCampName();

        return campName;
    }

    public void addPointsForApprovedSuggestions(String campCommitteeMemberName){
        for (CampCommitteeMember campCommitteeMember : CampCommitteeDataBase.getInstance().getCampCommitteeMembersList()) {
            if(campCommitteeMember.getName().equals(campCommitteeMemberName)){
                campCommitteeMember.addPointsByOne();
            }            
        }
    }
}
