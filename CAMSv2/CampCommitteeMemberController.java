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
        while (running) {
            boolean studentRegistered = camp.isStudentRegistered(user.getName());
            System.out.println("student registered: " + studentRegistered);
            boolean isCCM = (camp.equals(ccm.getCamp()));
            // view depends on whether camp is CCM of, and then if it is no CCM camp, check for registration
            ccmView.displayCampSpecificOptions(studentRegistered, isCCM);
            view.displayReturnToPreviousPage();
            int choice = sc.nextInt();
            sc.nextLine();

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
        boolean running = true;
        while (running) {
            ccmView.displayHeader("CAMP SUGGESTIONS");
            ccmView.displaySuggestionsMenu();
            ccmView.displayReturnToPreviousPage();
            int choice = sc.nextInt();
            sc.nextLine();
            running = handleCampSuggestionsSwitch(choice);
        }
    }        

    protected boolean handleCampSuggestionsSwitch(int choice) {
        switch (choice) {
            case 1:
                handleSubmitSuggestion();
                return true;
            case 2:
                handleViewSuggestion();
                return true;
            case 3:
                handleEditSuggestion();
                return true;
            case 4:
                handleDeleteSuggestion();
                return true;
            case 111:
                return false;
            default:
                ccmView.displaySelectValidOption();
                return true;
        }
    }

    protected void handleSubmitSuggestion() {
        ccmView.displaySubmitSuggestion();
        String suggestion = sc.nextLine();
        ccm.submitSuggestion(suggestion);
        ccm.addPointsByOne();
    }

    protected void handleViewSuggestion() {
        Suggestion suggestion = camp.getSuggestionBySuggester(ccm.getName());
        if (suggestion == null) {return;}
        ccmView.displayAllAdvices(suggestion);
    }

    protected Advice acquireSuggestion() {
        handleViewSuggestion();
        ccmView.displayGetSuggestionIndex();
        int index = sc.nextInt() - 1;
        sc.nextLine();

        Suggestion suggestion = camp.getSuggestionBySuggester(ccm.getName());
        if (suggestion == null) {return null;}
        // get the advice
        return suggestion.getAdviceList().get(index);
    }

    protected void handleEditSuggestion() {
        Advice advice = acquireSuggestion();
        ccmView.displaySubmitSuggestion();
        String newSuggestion = sc.nextLine();
        advice.setNewAdvice(newSuggestion);
        System.out.println("Successfully set new suggestion!");
    }

    protected void handleDeleteSuggestion() {
        handleViewSuggestion();
        Advice advice = acquireSuggestion();
        Suggestion suggestion = camp.getSuggestionBySuggester(ccm.getName());
        suggestion.getAdviceList().remove(advice);
        System.out.println("Successfully removed Suggestion");        
    }
    

    protected boolean handleCampEnquiriesSwitch(int choice) {
        switch (choice) {
            case 1:
                camp.printEnquiriesList();
                return true;
            case 2:
                handleReplyToEnquiry();
                return true;
            case 111:
                return false;
            default:
                ccmView.displaySelectValidOption();
                return true;
        }
    }

    protected void handleReplyToEnquiry() {
        camp.printEnquiriesList();
        ccmView.displayGetEnquiryId();
        int id = sc.nextInt();
        sc.nextLine();

        Question question = camp.getEnquiryFromCamp(id);
        if (question == null) {
            System.out.println("Please provide a valid EnquiryId!");
            return;
        }
        ccmView.displayGetReply();
        String reply = sc.nextLine();
        question.getReplies().add(new Reply(ccm.getName(), reply));
        camp.printEnquiriesList();

        // increment student points
        ccm.addPointsByOne();
        System.out.println("Successfully sent reply!");
    }

    protected void handleCampEnquiries() {

        boolean running = true;
        while (running) {
            ccmView.displayHeader("CAMP ENQUIRIES");
            ccmView.displayCampEnquiriesMenu();
            ccmView.displayReturnToPreviousPage();
            int choice = sc.nextInt();
            sc.nextLine();
            running = handleCampEnquiriesSwitch(choice);
        }
    }

    protected void handleCampAttendanceReport() {

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
                handleCampAttendanceReport();
                break;

            case 111:
                return false;
        }
        return true;
    }
}
