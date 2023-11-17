package CAMSv2;

import java.util.Scanner;

public class CampCommitteeMember  extends Student {
    private int points;
    private Camp camp;

    public CampCommitteeMember(String emailID, String password, String faculty, String name, Role role, Camp camp) {
        super(emailID, password, faculty, name, role);
        points = 0;
        this.camp = camp;
    }
    public void viewDetails(){

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

    /**
     *
     */
    public void committeeInterface(){
        //print a table of commitee methods; e.g.
        //1.viewCamp(); 2.editcamp
        // super.studentInterface();
        System.out.println("1. View camp details"); // thecamps they are registered for
        System.out.println("2. Submit a suggestion"); //only for camp they are comitee of
        System.out.println("3. View suggestion"); //only for camp they are comitee of
        System.out.println("4. Edit suggestion"); //only for camp they are comitee of
        System.out.println("5. Delete suggestion"); //only for camp they are comitee of
        System.out.println("6. View enquiries"); //print table with index of enquiries
        System.out.println("7. Reply to an enquiry"); //choose one
        System.out.println("8. Generate attendance report"); //of participants and roles -- wiht filters for format
        System.out.println("Select which action you would like to take");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        // sc.close();

        switch(choice){

            case 1:
                viewDetails();
                break;

            case 2:
                submitSuggestion();
                break;

            case 3:
                viewSuggestion();
                break;

            case 4:
                editSuggestion();
                break;

            case 5:
                deleteSuggestion();
                break;

            case 6:
                viewEnquiries();
                break;

            case 7:
                replyEnquiries();
                break;

            case 8:

                break;

            default:
                break;

        }//end switch

    }//end interface

}
