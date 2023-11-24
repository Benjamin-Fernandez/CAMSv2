package CAMSv2;

public class StaffView extends View{
    public void displayStaffMenu() {
        System.out.println("1. Change Password"); 
        System.out.println("2. View camps");
        System.out.println("3. Create a camp");
        System.out.println("4. Select a camp"); 
        System.out.println("5. Generate attendance report of all my camps");
        System.out.println("6. Generate performance report of all my camps");
        System.out.println("7. Generate enquiries report of all my camps");                
    }

    public void displayViewMyCampsMenu() {
        displayViewOtherCampsMenu();
        System.out.println("2. Edit camp"); 
        System.out.println("3. Delete camp"); 
        System.out.println("4. Manage Enquiries"); 
        System.out.println("5. Manage Suggestions"); 
    }

    public void displayGetReply() {
        System.out.println("Please enter your reply: ");
    }

    public void displayGetEnquiryId() {
        System.out.println("Please enter the enquiry ID: ");
    }

    public void displayGetSuggestionId() {
        System.out.println("Please enter the Suggestion ID: ");
    }

    public void displayViewOtherCampsMenu() {
        System.out.println("1. View Camp Details"); 
    }

    public void displayCampEnquiriesMenu() {
        System.out.println("1. View Camp Enquiries");
        System.out.println("2. Reply to an Enquiry"); 
    }

    public void displayCampSuggestionsMenu() {
        System.out.println("1. View Camp Suggestions");
        System.out.println("2. Approve Suggestion");
    }

    public void displayReportFilter() {
        System.out.println("1. Attendee");
        System.out.println("2. Camp Committee Member");       
        System.out.println("3. All");     
    }

}
