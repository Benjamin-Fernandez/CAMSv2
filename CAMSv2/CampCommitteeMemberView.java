package CAMSv2;

public class CampCommitteeMemberView extends StudentView {


    public void displayCampSpecificOptions(boolean isRegistered, boolean isCCM) {
        if (isCCM) {
            System.out.println("1. Submit an Enquiry");
            System.out.println("2. View Remaining Camp Slots");
            System.out.println("3. View Camp Details");
            System.out.println("4. Manage Camp Suggestions"); 
            System.out.println("5. Manage Camp Enquiries");
            System.out.println("6. Generate Camp Attendance Report");
        }
        else {
            super.displayCampSpecificOptions(isRegistered);
        }

    }

    public void displayCampEnquiriesMenu() {
        System.out.println("1. View Camp Enquiries");
        System.out.println("2. Reply to an Enquiry"); 
    }

    public void displaySuggestionsMenu() {
        System.out.println("1. Submit a suggestion"); 
        System.out.println("2. View suggestion"); 
        System.out.println("3. Edit suggestion"); 
        System.out.println("4. Delete suggestion"); 
    }

    public void displaySubmitSuggestion() {
        System.out.println("Please enter the suggestion: ");
    }

    public void displayGetSuggestionIndex() {
        System.out.println("Please enter the suggestion index: ");        
    }


    public void displayCampDetails(Camp camp){

        System.out.println("Camp name: " + camp.getCampName() + '\n' +
                "Camp date: " + camp.getDates()    + '\n' +
                "Camp Registration Closing Date: " + camp.getRegistrationClosingDate() + '\n' +
                "Camp User Group: " + camp.getUserGroup() +'\n' +
                "Location: "+ camp.getLocation() +'\n' +
                "Total slots: " + camp.getTotalSlots() + '\n'+
                "Description" + camp.getDescription() + '\n'
        );
        System.out.println("Camp Committee Slot: " + camp.getCampCommitteeSlots().size());
        System.out.println("Camp Committee Members: ");
        int counter = 1;
        for (Student student : camp.getCampCommitteeSlots()) {
            System.out.println( counter + ". " + student.getName());
            counter++;
        }
    }

    public void displayGetReply() {
        System.out.println("Please enter your reply: ");
    }

    public void displayAllAdvices(Suggestion suggestion) {
        int counter = 1;
        for (Advice advice : suggestion.getAdviceList()) {
            System.out.println(counter + ". " + advice.getAdvice() + " | " + "Approved: " + advice.getApproved());
            counter++;
        }
    }

}
