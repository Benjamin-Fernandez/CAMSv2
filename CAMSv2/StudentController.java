package CAMSv2;

import java.util.Scanner;

/**
 * Student Controller class
 */
public class StudentController {
    Student student;
    StudentView view;
    // for camp specific commands to store state
    Camp camp;
    Scanner sc;

    StudentController(Student student) {
        this.student = student;
        this.view = new StudentView();
        this.sc = new Scanner(System.in);
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void startStudentProgram() {
        boolean running = true;
        while (running) {
            view.displayStudentMenu();
            view.displayReturnToPreviousPage();
            int choice = sc.nextInt();
            // get rid of buffered carriage return
            sc.nextLine();
            switch (choice) {
                case 1:
                    // change password
                    break;
                case 2:
                    // view list of camps
                    view.displayListOfCamps(CampManager.getCampListByFacultyAndVisibility(student.getFaculty()));
                    break;
                case 3:
                    // View registered camps
                    view.displayListOfCamps(student.getRegisteredCamps());
                    break;
                case 4:
                    // View profile
                    view.displayProfile(student.getName(), student.getPassword(), student.getFaculty(), student.getRole(), "");
                    break;
                case 5:
                    enterEnquiriesMenu();
                    break;
                case 6:
                    enterCampSpecificOptions();
                
                    break;
                case 111:
                    // return to previous page
                    running = false;
                    break;
                default:
                    break;
        }
    
        }
    }

    public void enterEnquiriesMenu() {
        view.displayEnquiriesMenu();
        view.displayReturnToPreviousPage();
        int choice = sc.nextInt();
        // get rid of carriage
        sc.nextLine();
        boolean running = true;
        do {
            switch (choice) {
            case 1:
                view.displayEnquiries(student.getEnquiries().getQuestions());
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
    public Question enterGetEnquiry() {
        view.displayGetEnquiryId();
        int Id = sc.nextInt();
        sc.nextLine();
        Question question = student.getEnquiryById(Id);
        return question;        
    }

    public void enterEditEnquiry() {
        Question question = enterGetEnquiry();
        if (question == null) {
            view.displayFailureMessage();
            return;
        }

        view.displayEnterNewEnquiryDescription();
        String description = sc.nextLine();

        student.editEnquiry(question, description);
    }

    public void enterDeleteEnquiry() {
        Question question = enterGetEnquiry();
        student.deleteEnquiry(question.getQuestionId(), this.camp);
    }



    public void enterCampSpecificOptions() {

        // select camp
        view.displayListOfCamps(CampManager.getCampList());
        view.displayEnterCampName();

        // enter camp name
        String campName = sc.nextLine();

        // store the camp data temporarily here
        this.camp = CampManager.getCamp(campName);
        System.out.println("You chosed Camp: " + this.camp.getCampName());

        boolean running = true;
        view.displaySubmitEnquiry();
        boolean studentRegistered = camp.isStudentRegistered(student.getName());
        System.out.println("student registered: " + studentRegistered);
        if (studentRegistered) {
            view.displayWithdraw();
        }
        else {
            view.displayRegister();
        }

        int choice = sc.nextInt();
        // clear buffer
        sc.nextLine();
        
        do {
            switch (choice) {
                case 1:
                    // Submit Enquiry to camp
                    view.displayEnterNewEnquiryDescription();
                    String description = sc.nextLine();
                    student.createEnquiry(description, this.camp);
                    break;
                case 2:
                    if (studentRegistered) {
                        // withdraw student
                        enterCampWithdrawal();
                    }
                    else {
                        // register Student
                        enterCampRegister();
                    }
                    break;
                case 3:
                    view.displayRemainingCampSlots(this.camp);
                    break;
                case 111:
                    running = false;
                    // remove state
                    this.camp = null;
                    break;
            }
        } while (running);
    }

    private void enterCampWithdrawal() {
        student.withdrawFromCamp(this.camp);
    }

    private void enterCampRegister() {
        // check if camp is full
        if (!student.canRegisterCamp(this.camp)) {return;}
        Role role;
        while (true) {
            view.displayRoleToRegister();
            String input = sc.nextLine();
            try {
                role = Role.valueOf(input);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Your input does not match any roles!");
            }
        }
        student.registerCampRole(role, this.camp);
    }
}

