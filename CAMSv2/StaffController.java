package CAMSv2;

public class StaffController extends BaseController<Staff, StaffView>{
    Camp camp;

    public StaffController(Staff user, StaffView view) {
        super(user, view);
    }

    @Override
    public void startProgram() {
        boolean running = true;
        while (running) {
            view.displayHeader("STAFF MENU");
            view.displayStaffMenu();
            view.displayReturnToPreviousPage();
            int choice = sc.nextInt();
            // get rid of buffered carriage return
            sc.nextLine();
            running = handleStaffMenu(choice);
        }
    }

    private boolean handleStaffMenu(int choice) {
        switch (choice) {
            case 1:
                // Change Password
                return enterChangePassword();
            case 2:
                // View camps
                return enterViewCamps();
            case 3:
                // Create Camp
                return enterCreateCamp();
            case 4:
                // Select a camp
                return enterCampSelection();
            case 5:
                // Generate Attendance Report
                return enterGenerateAttendanceReport();
            case 6:
                // Display Performance report
                return enterGeneratePerformanceReport();
            case 7:
                return enterGenerateEnquiriesReport();
            default:
                break;
        }
        return false;
    }

    private boolean enterGenerateEnquiriesReport() {
        view.displayHeader("GENERATE STUDENT ENQUIRIES REPORT");
        user.generateStudentsEnquiryReport();
        return true;
    }

    protected boolean enterGenerateAttendanceReport() {
        boolean running = true;
        while(running) {        
            view.displayHeader("GENERATE ATTENDANCE REPORT");
            view.displayReportFilter();
            view.displaySelectActionToTake();
            view.displayReturnToPreviousPage();
            int choice = sc.nextInt();
            running = handleGenerateAttendanceReport(choice);
        }
        return true;
    }

    protected boolean handleGenerateAttendanceReport(int choice) {
        ReportFilter filter;
        switch (choice) {
            case 1:
                filter = new AttendeeFilter();
                break;
            case 2:
                filter = new CCMFilter();
                break;
            case 3:
                filter = new AllFilter();
                break;
            case 111:
                return false;
            default:
                return true;
        }
        // generate report
        user.generateStaffReport(filter);
        return false;

    }

    protected boolean enterGeneratePerformanceReport() {
        view.displayHeader("GENERATE PERFORMANCE REPORT");
        // generate report
        user.generateCampCommitteeReport();
        return true;
    }



    protected boolean enterCreateCamp() {
        view.displayHeader("CREATE CAMP");
        CampManager.getInstance().createCamp(user);
        return true;
    }

    protected boolean enterViewCamps() {
        view.displayHeader("CAMPS");        
        view.displayListOfCamps(CampManager.getInstance().getCampList());
        return true;
    }

    protected boolean enterChangePassword() {
        view.displayHeader("CHANGE PASSWORD");
        System.out.println("Enter new password");
        String newPassword = sc.nextLine();
        user.changePassword(newPassword); 
        return true;       
    }

    protected boolean enterCampSelection() {
        view.displayHeader("CAMP SELECTION");
        // select camp
        view.displayListOfCamps(CampManager.getInstance().getCampList());
        view.displayEnterCampName();

        // enter camp name
        String campName = sc.nextLine();

        Camp camp = CampManager.getInstance().getCamp(campName);
        if (camp == null) {
            System.out.println("Unable to find " + campName);
            return true;
        }
        System.out.println("You chosed Camp: " + camp.getCampName());
        this.camp = camp;

        boolean isInCreatedCamps = user.getCreatedCamps().contains(camp);
        boolean running = true;
        while (running) {
            view.displayHeader("CAMP MENU");
            if (isInCreatedCamps) {
                view.displayViewMyCampsMenu();
            }
            else {
                view.displayViewOtherCampsMenu();
            }
            view.displayReturnToPreviousPage();
            
            int choice = sc.nextInt();

            if (isInCreatedCamps) {
                running = handleCampSelectionOfMyCamp(choice);                
            }
            else {
                running = handleCampSelectionOfOtherCamps(choice);                   
            }
        }
        return true;
    }

    protected boolean handleCampSelectionOfOtherCamps(int choice) {
        switch (choice) {
            case 1:
                return enterDisplayListOfCamps();
            case 111:
                return false;
            default:
                return true;
        }
    }

    protected boolean enterDisplayListOfCamps() {
        view.displayHeader("Camp Information");
        camp.printCampInfoTable();
        return true;
    }

    protected boolean handleCampSelectionOfMyCamp(int choice) {
        switch (choice) {
            case 1:
                return enterDisplayListOfCamps();
            case 2:
                return enterEditCamp();
            case 3:
                return enterDeleteCamp();
            case 4:
                return enterManageEnquiries();
            case 5:
                return enterManageSuggestions();
            case 111:
                return false;
            default:
                return true;
        }
    }

    private boolean enterChangeCampVisibility() {
        CampManager.getInstance().changeVisibility(camp.getCampName());
        return true;
    }

    protected boolean enterManageSuggestions() {
        boolean running = true;
        while(running) {
            view.displayHeader("Suggestions Menu");
            view.displayCampSuggestionsMenu();
            view.displayReturnToPreviousPage();
            view.displaySelectActionToTake();
            int choice = sc.nextInt();
            running = handleManageSuggestions(choice);
        }
        return true;
    }

    private boolean handleManageSuggestions(int choice) {
        switch (choice) {
            case 1:
                
                return enterViewCampSuggestions();

            case 2:
                return enterApproveSuggestion();

            case 111:
                return false;
        
            default:
                return true;
        }
    }

    protected boolean enterViewCampSuggestions() {
        view.displayHeader("CAMP SUGGESTIONS LIST");
        camp.printSuggestionList();
        return true;
    }

    protected boolean enterApproveSuggestion() {
        enterViewCampSuggestions();
        view.displayGetSuggestionId();
        int id = sc.nextInt();
        sc.nextLine();

        return user.approveAdvice(camp, id);
    }

    protected boolean enterReplyCampEnquiries() {
        camp.printEnquiriesList();
        view.displayGetEnquiryId();
        int id = sc.nextInt();
        sc.nextLine();

        Question question = camp.getEnquiryFromCamp(id);
        if (question == null) {
            System.out.println("Please provide a valid EnquiryId!");
            return true;
        }
        view.displayGetReply();
        String reply = sc.nextLine();
        question.getReplies().add(new Reply(user.getName(), reply));
        camp.printEnquiriesList();

        System.out.println("Successfully sent reply!");
        return true;
    }

    protected boolean enterViewCampEnquiries() {
        view.displayHeader("CAMP ENQUIRIES LIST");
        camp.printEnquiriesList();
        return true;
    }

    protected boolean enterManageEnquiries() {
        boolean running = true;
        while(running) {
            view.displayHeader("Enquiries Menu");
            view.displayCampEnquiriesMenu();
            view.displayReturnToPreviousPage();
            view.displaySelectActionToTake();
            int choice = sc.nextInt();
            running = handleManageEnquiries(choice);
        }
        return true;
    }

    protected boolean handleManageEnquiries(int choice) {
        switch (choice) {
            case 1:
                
                return enterViewCampEnquiries();

            case 2:
                return enterReplyCampEnquiries();

            case 111:
                return false;
        
            default:
                return true;
        }
    }

    protected boolean enterDeleteCamp() {
        CampManager.getInstance().deleteCamp(camp);
        view.displaySuccessfulMessage();
        return false;
    }

    protected boolean enterEditCamp() {
        CampManager.getInstance().editCamp(camp.getCampName(), user.getName());    
        return true;
    }


    
}
