package CAMSv2;

import java.util.ArrayList;

/**
 * Student Controller class
 */
public class StudentController extends BaseController<Student, StudentView>{
    Camp camp = null;

    StudentController(Student student, StudentView view) {
        super(student, view);
    }

    public void startProgram() {
        boolean running = true;
        while (running) {
            view.displayHeader("STUDENT MENU");
            view.displayStudentMenu();
            view.displayReturnToPreviousPage();
            int choice = sc.nextInt();
            // get rid of buffered carriage return
            sc.nextLine();
            running = handleStudentMenu(choice);
        }
    }

    protected boolean handleStudentMenu(int choice) {
        switch (choice) {
            case 1:
                return enterChangePassword();
            case 2:
                return enterDisplayListOfCampsAvailable();
            case 3:
                return enterDisplayRegisteredCampAndRole(user.getRegisteredCamps(), null);
            case 4:
                return enterDisplayProfile();
            case 5:
                return enterEnquiriesMenu();
            case 6:
                return enterCampSpecificOptions();
            case 111:
                return false; // Exit the loop
            default:
                return true; // Invalid choice, continue loop
        }
    }
    protected boolean enterChangePassword() {
        view.displayHeader("Change Password");
        System.out.println("Enter new password");
        String newPassword = sc.nextLine();
        user.changePassword(newPassword);
        return true;
    }

    protected boolean enterDisplayListOfCampsAvailable() {
        view.displayHeader("LIST OF CAMPS AVAILABLE");
        view.displayListOfCamps(CampManager.getInstance().getCampListByFacultyAndVisibility(user.getFaculty()));
        return true;
    }

    protected boolean enterDisplayRegisteredCampAndRole(ArrayList<Camp> camps, Camp ccmCamp) {
        view.displayHeader("REGISTERED CAMPS");
        view.displayRegisteredCampsAndRole(user.getRegisteredCamps(), ccmCamp);
        return true;
    }

    protected boolean enterDisplayProfile() {
        view.displayHeader("PROFILE");
        user.displayProfile();
        return true;
    }

    protected boolean enterEnquiriesMenu() {
        boolean goToLoginPage = false;
        boolean running = true;
        do {
            view.displayHeader("ENQUIRIES MENU");    
            view.displayEnquiriesMenu();
            view.displayReturnToPreviousPage();
            int choice = sc.nextInt();
            // get rid of carriage
            sc.nextLine();
            running = handleEnquiriesMenu(choice, goToLoginPage);
        } while (running);
        return !goToLoginPage;
    }

    protected boolean handleEnquiriesMenu(int choice, boolean goToLoginPage) {

        switch (choice) {
            case 1:
                // view enquiries
                return enterDisplayEnquiries();
            case 2:
                // edit enquiry
                return enterEditEnquiry();
            case 3:
                // delete enquiry
                return enterDeleteEnquiry();
            case 111:
                return false;
        }
        return true;
    }

    protected boolean enterDisplayEnquiries() {
        view.displayHeader("DISPLAY ENQUIRIES");
        view.displayEnquiries(user.getEnquiries().getQuestions());
        return true;
    }

    /**
     * Display message to get enquiryId and get input
     * @return Enquiry
     */
    protected Question enterGetEnquiry() {
        view.displayGetEnquiryId();
        int Id = sc.nextInt();
        sc.nextLine();
        Question question = user.getEnquiryById(Id);
        return question;        
    }

    protected boolean enterEditEnquiry() {
        view.displayHeader("EDIT ENQUIRY");
        Question question = enterGetEnquiry();
        if (question == null) {
            view.displayFailureMessage();
            return true;
        }

        view.displayEnterNewEnquiryDescription();
        String description = sc.nextLine();

        user.editEnquiry(question, description);
        return true;
    }

    protected boolean enterDeleteEnquiry() {
        view.displayHeader("DELETE ENQUIRY");
        Question question = enterGetEnquiry();
        String campName = question.getCampName();
        Camp camp = CampManager.getInstance().getCamp(campName);
        user.deleteEnquiry(question.getQuestionId(), camp);
        return true;
    }

    protected Camp handleCampSelection() {
        view.displayHeader("CAMP SELECTION");
        // select camp
        view.displayListOfCamps(CampManager.getInstance().getCampListByFacultyAndVisibility(user.getFaculty()));
        view.displayEnterCampName();

        // enter camp name
        String campName = sc.nextLine();

        Camp camp = user.ifCampNameInAvailableListOfCamps(campName);
        if (camp == null) {return null;}
        System.out.println("You chosed Camp: " + camp.getCampName());
        return camp;
    }

    protected boolean enterCampSpecificOptions() {
        this.camp = handleCampSelection();
        if (this.camp == null) {return true;}
        GoToMainMenu goToMainMenu = new GoToMainMenu();
        boolean running = true;
        do {
            boolean studentRegistered = camp.isStudentRegistered(user.getName());
            System.out.println("student registered: " + studentRegistered);
            view.displayHeader("CAMP MENU");
            view.displayCampSpecificOptions(studentRegistered);
            view.displayReturnToPreviousPage();

            int choice = sc.nextInt();
            // clear buffer
            sc.nextLine();
            running = handleCampSpecificOptions(choice, studentRegistered, goToMainMenu);
            // System.out.println("running " + running);
            // System.out.println("goTologinPageReturnVal " + goToMainMenu);
        } while (running);
        return !goToMainMenu.getBooleanValue();
    }

    protected boolean handleSubmitEnquiryToCamp() {
        // Submit Enquiry to camp
        view.displayEnterNewEnquiryDescription();
        String description = sc.nextLine();
        user.createEnquiry(description, this.camp);
        
        enterDisplayEnquiries();
        return true;
    }

    protected boolean handleViewRemainingTimeSlots() {
        view.displayHeader("REMAINING CAMP SLOTS");
        view.displayRemainingCampSlots(this.camp);   
        return true;     
    }

    protected boolean handleCampSpecificOptions(int choice, boolean studentRegistered, GoToMainMenu goToLoginPage) {
        System.out.println("Within handleCampSpecificOptions...");
        switch (choice) {
            case 1:
                return handleSubmitEnquiryToCamp();
            case 2:
                if (studentRegistered) {
                    // withdraw student
                    return enterCampWithdrawal();
                }
                else {
                    // register Student
                    // If he chose CCM, auto logs out
                    return enterCampRegister(goToLoginPage);
                }

            case 3:
                return handleViewRemainingTimeSlots();

            case 111:
                // reset controller camp state
                this.camp = null;
                return false;
        }
        return true;
    }

    protected boolean enterCampWithdrawal() {
        user.withdrawFromCamp(this.camp);
        view.displayWithdrawalFromCamp(this.camp);
        return true;
    }

    protected boolean enterCampRegister(GoToMainMenu goToMainMenu) {
        // check if camp is full
        if (!user.canRegisterCamp(this.camp)) {return true;}
        Role role;
        while (true) {
            view.displayHeader("SELECT ROLES");
            view.displayRoleToRegister();
            String input = sc.nextLine();
            try {
                role = Role.valueOf(input);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Your input does not match any roles!");
            }
        }

        // if registered as CCM, return false
        boolean result = user.registerCampRole(role, this.camp);
        // System.out.println("Result: " + result);
        // set goToLoginPage as true
        goToMainMenu.setBooleanValue(!result);
        // System.out.println("goToLoginPage: " + goToMainMenu.getBooleanValue());
        return result;
    }
}


