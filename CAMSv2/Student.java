package CAMSv2;

import java.util.ArrayList;
import java.util.Scanner;

/** 
 Representing a Student user registered in the system.
 @author Zhu Yu Hao
 @since 13-11-2023
 */
public class Student extends User{
    ArrayList<Camp> registeredCamps = new ArrayList<Camp>();
    Enquiries enquiries = new Enquiries(super.name);
    public Student(String emailID, String password, String faculty, String name, Role role) {
        super(emailID, password, faculty, name, role);
    }
    // Student User Interface

    // --- View Camps ---------------------------------------------------
    public ArrayList<Camp> getRegisteredCamps() {
        return registeredCamps;
    }

    public void viewRemainingCampSlots(String campName) {
        // get Camp from CampManager
        Camp camp = CampManager.getCamp(campName);
        // Display available slots
        System.out.println( camp.getCampName() + " Available Slots: " + (camp.getTotalSlots() - camp.getStudentList().size()));
        // Camp Manager should check that the camp is Visible and userGroup
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

    public Question getEnquiryById(int id) {
        for (Question enquiry : enquiries.getQuestions()) {
            if (enquiry.getQuestionId() == id) {
                return enquiry;
            }
        }
        return null;
    }

    public void editEnquiry(Question question, String description) {
        question.setQuestion(description);
    }

    public void deleteEnquiry(int Id) {
        ArrayList<Question> questions = enquiries.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getQuestionId() == Id) {
                // remove
                questions.remove(i);
                // debugging message
                System.out.println("Deleted the question!");
                return;
            }
        }
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
        if (campRole.equals("camp committee member")){
            // CampCommitteeMember campCommitteeMember = new CampCommitteeMember(this.emailID, this.emailID, this.faculty, this.name, Role.CAMP_COMMITTEE_MEMBER,camp);
            // camp.addCampCommitteeMember(campCommitteeMember);
            //call campcommiteelist write to csv method
            //CampAndRole campAndRole = new CampAndRole(camp, campCommitteeMember);
        }
    }

    public void withdrawFromCamp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the camp to withdraw: ");
        String campName = scanner.next();
        Camp camp = CampManager.getCamp(campName);
        // if camp committee member should remove from camp
        camp.getStudentList().remove(this);
        camp.addToBlackList(this);

    }


}

