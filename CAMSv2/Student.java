package CAMSv2;

import java.util.ArrayList;
import java.util.Scanner;

/** 
 Representing a Student user registered in the system.
 @author Zhu Yu Hao
 @since 13-11-2023
 */
public class Student extends User {
    ArrayList<CampAndRole> registeredCamps = new ArrayList<CampAndRole>();
    Enquiries enquiries = new Enquiries(super.name);

    public Student(String emailID, String password, String faculty, String name, Role role) {
        super(emailID, password, faculty, name, role);
    }
    // Student User Interface

    // --- View Camps ---------------------------------------------------
    public void viewListOfCamps() {
        // get List of Camps
        // Camp Manager Should also check for Visiblity and UserGroup (not implemented)
        ArrayList<Camp> campList = CampManager.getCampList();
        for (Camp camp : campList) {
            System.out.println(camp.getCampName());
        }
    }

    public void viewRemainingCampSlots(String campName) {
        // get Camp from CampManager
        Camp camp = CampManager.getCamp(campName);
        // Display available slots
        System.out.println(camp.getCampName() + " Available Slots: " + (camp.getTotalSlots() - camp.getStudentList().size()));
        // Camp Manager should check that the camp is Visible and userGroup
    }

    public void viewRegisteredCamps() {
        // getRegisteredCamps and print it
        for (CampAndRole campAndRole : registeredCamps) {
            System.out.println(campAndRole.getCamp().getCampName());
        }
    }

    // --- Enquiries -----------------------------------------------------
    // have to decide if we are going to use EnquiryManager for managing the logic or not
    public void createEnquiry() {
        // create new Enquiry Object through EnquiryManager
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter camp name: ");
        String campName = scanner.nextLine();
        System.out.println("Please enter enquiry Description");
        String enquiryDescription = scanner.nextLine();
        EnquiryManager.createEnquiry(campName, enquiryDescription, this);
    }


    public void viewAllQuestions() {
        // prints all enquiries in enquiry list
        ArrayList<Question> questions = enquiries.getQuestions();
        int counter = 1;
        for (Question question : questions) {
            System.out.println(counter + "." + " Enquiry: " + question.getQuestion() + "camp: " + question.getCampName());
            counter++;
        }
    }

    public void viewEnquiry() {
        // prints enquiry based on index
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select the Enquiry Number to view in detail: ");
        int enquiryIndex = scanner.nextInt();
        Question question = enquiries.getQuestions().get(enquiryIndex - 1);
        ArrayList<String> replies = question.getReply();
        System.out.println("Question: " + question.getQuestion());
        int counter = 1;
        for (String string : replies) {
            System.out.println("Reply " + counter + ": " + string);
            counter++;
        }
    }

    public void editEnquiry() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select the Enquiry Number to edit: ");
        int enquiryIndex = scanner.nextInt();
        System.out.println("Please enter the new enquiry Description: ");
        String enquiryInformation = scanner.nextLine();
        Question question = enquiries.getQuestions().get(enquiryIndex - 1);
        // there should be an enquiry user interface responsible for asking inputs
        question.setQuestion(enquiryInformation);
        System.out.println("Successfully set new enquiry description!");
    }

    public void deleteEnquiry() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select the Enquiry Number to delete: ");
        int enquiryIndex = scanner.nextInt();
        enquiries.getQuestions().remove(enquiryIndex - 1);
        System.out.println("Enquiry removed successfully!");


    }

    public void deleteAllEnquiries() {
        enquiries = new Enquiries(super.name);
    }

    public Enquiries getEnquiries() {
        return enquiries;
    }

    // --- Registration Manager --------------------------------------
    public void registerCampInterface() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of camp to register: ");
        String campName = scanner.next();
        System.out.println("Please enter the role to register (attendee or committee): ");
        String role = scanner.next();
        Camp camp = CampManager.getCamp(campName);
        if (camp.isStudentInBlackList(this)) {
            System.out.println("You have been blacklisted from this camp!");
            return;
        } else if (camp.isCampFull()) {
            System.out.println("Camp is full, select another camp!");
            return;
        }

        // register student
        registerCampRole(role, camp);
    }

    // should be implemented on Camp side.
    public boolean IsBeforeCampDeadline() {
        return true;
    }


    // --- Strategy Pattern, inject CampRole interface and directly call registerRole()
    public void registerCampRole(String campRole, Camp camp) {
        // if campRole == "committee"
        // Check in registeredCamps not already a committee
        // Register as committee
        // else register as member
        camp.addStudent(this.getName());
        if (campRole.equals("camp committee member")){
            CampCommitteeMember campCommitteeMember = new CampCommitteeMember(this.emailID, this.emailID, this.faculty, this.name, Role.CAMP_COMMITTEE_MEMBER,camp);
            camp.addCampCommitteeMember(campCommitteeMember);
            //call campcommiteelist write to csv method
            //CampAndRole campAndRole = new CampAndRole(camp, campCommitteeMember);
        }
    }

    public void withdrawFromCamp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the camp to withdraw: ");
        String campName = scanner.next();
        Camp camp = CampManager.getCamp(campName);
        if (getCampRole(camp).equals("committee")) {
            // assuming the campcommittee list is of type Student.
            camp.getCampCommitteeSlots().remove(this);
        }
        camp.getStudentList().remove(this);
        camp.addToBlackList(this);

    }

    // search for student role in this camp
    public Role getCampRole(Camp camp) {
        for (CampAndRole campAndRole : registeredCamps) {
            if (campAndRole.getCamp().equals(camp)) {
                return campAndRole.getRole();
            }
        }
        return null;
    }

    /**
     * Student Interface class that will contain all the choices presented to the Student user.
     * New choices can be registered in the Student Interface class during setup in Cams.java
     */
    public void studentInterface() {
        int choice;

    do{
        System.out.println();
        System.out.println("Select which action you would like to take");
        System.out.println("1. Change Password");
        System.out.println("2. View list of camps available");
        System.out.println("3. Submit Enquiries for a camp");
        System.out.println("4. View registered camps");
        System.out.println("5. View reply to enquiries");
        System.out.println("6. Request to Withdraw from Camps");

        Scanner scanner = new Scanner(System.in);

        choice = scanner.nextInt();

        switch (choice) {
            case 1:

                break;
            case 2:
                viewListOfCamps();
                break;
            case 3:
                // to rename all enquiry to question
                viewListOfCamps();
                createEnquiry();
                viewAllQuestions();
                break;
            case 4:
                viewRegisteredCamps();
                break;
            case 5:
                viewAllQuestions();
                viewEnquiry();
                break;
            case 6:
                viewRegisteredCamps();
                withdrawFromCamp();
                break;

            case 7:
                registerCampInterface();
                break;

            default:
                break;
        }


        }while(choice >0&&choice<=6);

    }
}

