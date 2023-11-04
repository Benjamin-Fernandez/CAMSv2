package CAMSv2;

import java.util.Scanner;

public class CampCommitteeMember implements Role {
    private int points;

    public CampCommitteeMember(){
        points = 0;
    }
    public void viewDetails(Camp camp){
        System.out.println("Camp name: " + camp.getCampName() + '\n' +
                           "Camp date: " + camp.getDates()    + '\n' +
                           "Camp Registration Closing Date: " + camp.getRegistrationClosingDate() + '\n' +
                           "Camp User Group: " + camp.getUserGroup() +'\n' +
                           "Location: "+ camp.getLocation() +'\n' +
                           "Total slots: " + camp.getTotalSlots() + '\n'+
                           "Description" + camp.getDescription() + '\n'
                );
        System.out.println("Camp Committee Slot: ");
        for (Student student : camp.getCampCommitteeSlots()) {
            System.out.println(student.getName()+ " ");
        }
    }

    public void viewEnquiries(String campName) {
        EnquiryManager enquiryManager = new EnquiryManager();
        enquiryManager.viewEnquiryForCampCommitteeMember(campName);
    }

    public void replyEnquiries(String campName){
        EnquiryManager enquiryManager = new EnquiryManager();
        enquiryManager.replyEnquiryFromCampCommitteeMember(campName);
        points++;
    }

    public void submitSuggestion(String studentName, Camp camp){
        Scanner sc = new Scanner(System.in);
        SuggestionManager suggestionManager = new SuggestionManager();
        System.out.println("What suggestion would you like to make");
        String suggestion = sc.nextLine();
        suggestionManager.createSuggestion(camp.getCampName(), suggestion, studentName);
        System.out.println("Suggestion added");
        points++;

    }

    public void viewSuggestion(String studentName, String campName) {
        SuggestionManager suggestionManager = new SuggestionManager();
        suggestionManager.viewSuggestionForCommitteeMember(studentName,campName);
    }

    public void editSuggestion(String studentName, String campName){
        SuggestionManager suggestionManager = new SuggestionManager();
        suggestionManager.editSuggestionForCommitteeMember(studentName,campName);

    }

    public void deleteSuggestion(String studentName,String campName){
        SuggestionManager suggestionManager = new SuggestionManager();
        suggestionManager.deleteSuggestionForCommitteeMember(studentName,campName);
    }
}
