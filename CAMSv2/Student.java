package CAMSv2;

import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User{
    ArrayList<CampAndRole> registeredCamps = new ArrayList<CampAndRole>();
    Enquiries enquiries = new Enquiries(super.name);
    public Student(String emailID, String password, String faculty, String name, String role) {
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
        System.out.println( camp.getCampName() + " Available Slots: " + (camp.getTotalSlots() - camp.getStudentList().size()));
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
    public void createEnquiry(String camp, String enquiryDescription) {
        // create new Enquiry Object through EnquiryManager
        EnquiryManager.createEnquiry(camp, enquiryDescription, this);
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
        Question question = enquiries.getQuestions().get(enquiryIndex-1); 
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
        Question question = enquiries.getQuestions().get(enquiryIndex-1);
        // there should be an enquiry user interface responsible for asking inputs
        question.setQuestion(enquiryInformation);
        System.out.println("Successfully set new enquiry description!");        
    }

    public void deleteEnquiry() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select the Enquiry Number to delete: ");
        int enquiryIndex = scanner.nextInt();
        enquiries.getQuestions().remove(enquiryIndex-1);
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
        }
        else if(camp.isCampFull()) {
            System.out.println("Camp is full, select another camp!");
            return;
        }
        
        // register student
        registerCampRole(role, camp);
    }

    // should be implemented on Camp side.
    public boolean IsBeforeCampDeadline() {return true;}



    // --- Strategy Pattern, inject CampRole interface and directly call registerRole()
    public void registerCampRole(String campRole, Camp camp) {
        // if campRole == "committee"
        // Check in registeredCamps not already a committee
        // Register as committee
        // else register as member
        camp.addStudent(this.getName());
        if (campRole.equals("committee")) {
            camp.addCampCommitteeMember(this);
            CampCommitteeMember campCommitteeMember = new CampCommitteeMember();
            CampAndRole campAndRole = new CampAndRole(camp,campCommitteeMember);
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

    public void studentInterface(){
        //print a table of student methods; e.g.
        //1.viewCamp(); 2.editcamp
    }

}

