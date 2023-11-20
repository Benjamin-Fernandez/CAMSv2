package CAMSv2;

import java.util.Scanner;

/**
 * Student Controller class
 */

 /** 
 * This class represents the controller for the Student model.
 * It extends the BaseController class and includes additional properties and methods specific to a Student.
 * 
 * @author Zhu Yu Hao
 * @since 13-11-2023
 */

public class StudentController extends BaseController<Student, StudentView>{
    Camp camp = null;

     /**
     * Constructor for the StudentController class.
     * It initializes the student and the view.
     *
     * @param student The student to be controlled.
     * @param view The view to be used.
     */
    StudentController(Student student, StudentView view) {
        super(student, view);
    }

     /**
     * This method starts the program for the student.
     * It displays the student menu and handles the student's choices.
     */
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

     /**
     * This method handles the student's menu choices.
     *
     * @param choice The student's menu choice.
     * @return true if the program should continue running, false otherwise.
     */
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
                view.displayProfile(user.getName(), user.getPassword(), user.getFaculty(), user.getRole(), "");
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

    protected void enterEnquiriesMenu() {
        boolean running = true;
        do {
            view.displayHeader("ENQUIRIES MENU");    
            view.displayEnquiriesMenu();
            view.displayReturnToPreviousPage();
            int choice = sc.nextInt();
            // get rid of carriage
            sc.nextLine();

            switch (choice) {
            case 1:
                view.displayEnquiries(user.getEnquiries().getQuestions());
                break;
            case 2:
                // edit enquiry
                enterEditEnquiry();
                break;
            case 3:
                // delete enquiry
                enterDeleteEnquiry();
                break;
            case 111:
                running = false;
                break;
            }
        } while (running);
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

    /**
     * This method handles the student's choice to view the remaining time slots for a camp.
     */

    protected void enterEditEnquiry() {
        view.displayHeader("EDIT ENQUIRY");
        Question question = enterGetEnquiry();
        if (question == null) {
            view.displayFailureMessage();
            return;
        }

        view.displayEnterNewEnquiryDescription();
        String description = sc.nextLine();

        user.editEnquiry(question, description);
    }

     /**
     * This method handles the student's choice to delete an enquiry.
     * It prompts the student for the enquiry ID and then deletes the enquiry.
     */

    protected void enterDeleteEnquiry() {
        view.displayHeader("DELETE ENQUIRY");
        Question question = enterGetEnquiry();
        String campName = question.getCampName();
        Camp camp = CampManager.getCamp(campName);
        user.deleteEnquiry(question.getQuestionId(), camp);
    }

    /**
     * This method handles the student's selection of a camp.
     * It prompts the student for the camp name and sets the selected camp.
     *
     * @return true if the camp was successfully selected, false otherwise.
     */
    protected Camp handleCampSelection() {
        view.displayHeader("CAMP SELECTION");
        // select camp
        view.displayListOfCamps(CampManager.getCampListByFacultyAndVisibility(user.getFaculty()));
        view.displayEnterCampName();

        // enter camp name
        String campName = sc.nextLine();

        // store the camp data temporarily here
        Camp camp = user.ifCampNameInAvailableListOfCamps(campName);
        if (camp == null) {return null;}
        System.out.println("You chosed Camp: " + camp.getCampName());
        return camp;
    }

    /**
     * This method handles the student's selection of camp-specific options.
     * It displays the camp-specific menu and handles the student's choices.
     *
     * @return true if the program should continue running, false otherwise.
     */

    protected void enterCampSpecificOptions() {
        this.camp = handleCampSelection();
        if (this.camp == null) {return;}
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
            running = handleCampSpecificOptions(choice, studentRegistered);
        } while (running);
    }

     /**
     * This method handles the student's choice to submit an enquiry to a camp.
     * It prompts the student for the enquiry description and then creates the enquiry.
     */
    protected void handleSubmitEnquiryToCamp() {
        // Submit Enquiry to camp
        view.displayEnterNewEnquiryDescription();
        String description = sc.nextLine();
        user.createEnquiry(description, this.camp);
        view.displayEnquiries(user.getEnquiries().getQuestions());
    }

    /**
     * This method handles the student's choice to view the remaining time slots for a camp.
     */
    protected void handleViewRemainingTimeSlots() {
        view.displayRemainingCampSlots(this.camp);        
    }

    protected boolean handleCampSpecificOptions(int choice, boolean studentRegistered) {
        switch (choice) {
            case 1:
                handleSubmitEnquiryToCamp();
                break;
            case 2:
                if (studentRegistered) {
                    // withdraw student
                    enterCampWithdrawal();
                }
                else {
                    // register Student
                    return enterCampRegister();
                }
                break;
            case 3:
                handleViewRemainingTimeSlots();
                break;
            case 111:
                // reset controller camp state
                this.camp = null;
                return false;
        }
        return true;
    }

     /**
     * This method handles the student's choice to withdraw from a camp.
     */
    protected void enterCampWithdrawal() {
        user.withdrawFromCamp(this.camp);
    }

     /**
     * This method handles the student's choice to enter the camp registration process.
     *
     * @return true if the registration was successful, false otherwise.
     */

    protected boolean enterCampRegister() {
        // check if camp is full
        if (!user.canRegisterCamp(this.camp)) {return false;}
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
        return user.registerCampRole(role, this.camp);
    }
}


