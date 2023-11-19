package CAMSv2;

public class CampCommitteeMemberController extends StudentController {
    private CampCommitteeMember ccm;
    private CampCommitteeMemberView ccmView;

    public CampCommitteeMemberController(CampCommitteeMember ccm, CampCommitteeMemberView view) {
        super(ccm, view);
        this.ccm = ccm;
        this.ccmView = view;
    }

    @Override
    protected boolean handleStudentMenu(int choice) {
        switch (choice) {
            case 1:
                user.changePassword();
                return true;
            case 2:
                view.displayListOfCamps(CampManager.getCampListByFacultyAndVisibility(user.getFaculty()));
                return true;
            case 3:
                view.displayListOfCamps(user.getRegisteredCamps());
                return true;
            case 4:
                view.displayProfile(user.getName(), user.getPassword(), user.getFaculty(), user.getRole(), ccm.getCamp().getCampName());
                return true;
            case 5:
                enterEnquiriesMenu();
                return true;
            case 6:
                enterCampSpecificOptions();
                return true;
            case 111:
                return false; // Exit the loop
            default:
                return true; // Invalid choice, continue loop
        }
    }

    @Override
    public void enterCampSpecificOptions() {
        super.camp = super.handleCampSelection();
        if (camp == null) {return;}
        
        boolean running = true;
        boolean studentRegistered = camp.isStudentRegistered(user.getName());
        System.out.println("student registered: " + studentRegistered);
        boolean isCCM = (camp.equals(ccm.getCamp()));
        // view depends on whether camp is CCM of, and then if it is no CCM camp, check for registration
        ccmView.displayCampSpecificOptions(studentRegistered, isCCM);
        view.displayReturnToPreviousPage();
        int choice = sc.nextInt();
        sc.nextLine();

        while (running) {
            if (!isCCM) {
                running = handleCampSpecificOptions(choice, studentRegistered);                
            }
            else {
                // no registration/withdrawal check since user is CCM and thus cannot leave the camp
                running = handleCampCommitteeMemberMenu(choice);
            }

        }
    }

    protected void handleCampSuggestions() {

    }

    protected boolean handleCampEnquiriesSwitch(int choice) {
        switch (choice) {
            case 1:
                camp.printEnquiriesList();
                return true;
            case 2:

                return true;
            case 111:
                return false;
            default:
                return true;
        }
    }

    protected void handleReplyToEnquiry() {
        // get enquiry id
        // set a reply in the enquiry reply list.
        // print completed.
    }

    protected void handleCampEnquiries() {

        boolean running = true;
        while (running) {
            ccmView.displayHeader("CAMP ENQUIRIES");
            ccmView.displayCampEnquiriesMenu();
            int choice = sc.nextInt();
            sc.nextLine();
            running = handleCampEnquiriesSwitch(choice);
        }
    }


    protected boolean handleCampCommitteeMemberMenu(int choice) {
        // Implement additional menu options specific to CampCommitteeMember
        switch (choice) {
            case 1:
                super.handleSubmitEnquiryToCamp();
                break;
            case 2:
                super.handleViewRemainingTimeSlots();
                break;
            case 3:
                ccmView.displayCampDetails(camp);
                break;
            case 4:
                handleCampSuggestions();
                break;  
            case 5:
                handleCampEnquiries();
                break;
            case 6:
                break;

            case 111:
                return false;
        }
        return true;
    }
}
