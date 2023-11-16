package CAMSv2;

import java.util.Scanner;

public class CampCommitteeMember  extends Student {
    private int points;

    public CampCommitteeMember(String emailID, String password, String faculty, String name, Role role) {
        super(emailID, password, faculty, name, role);
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

    /**
     *
     */
    public void committeeInterface(){
        //print a table of commitee methods; e.g.
        //1.viewCamp(); 2.editcamp
        super.studentInterface();
        System.out.println("1. View camp details"); // thecamps they are registered for
        System.out.println("2. Submit a suggestion"); //only for camp they are comitee of
        System.out.println("3. View enquiries"); //print table with index of enquiries
        System.out.println("4. Reply to an enquiry"); //choose one
        System.out.println("5. Manage open suggestions"); //will show open suggestions and ask to edit/delete
        System.out.println("6. Generate attendance report"); //of participants and roles -- wiht filters for format
        System.out.println("Select which action you would like to take");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
       // sc.close();

        switch(choice){

            case 1:

                // viewDetails(null); how do we do this
                break;

            case 2: 
                // submitSuggestion(null, null);
                break;

            case 3:
                // viewEnquiries(null);
                break;

            case 4:
                // replyEnquiries(null);
                break;

            case 5:
                // viewSuggestion(null, null);
                break;

            case 6:
                //generateReport
                break;

            default:
                break;

        }//end switch
        
    }//end interface

}
