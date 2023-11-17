package CAMSv2;

import java.util.ArrayList;
import java.util.HashSet;

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
    public void createEnquiry(String description, Camp camp) {
        Question question = new Question(description, camp.getCampName(), EnquiryManager.getEnquiryCounter());
        EnquiryManager.createEnquiry(question, camp, getName());
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
    // camp side?
    public boolean canRegisterCamp(Camp camp) {
        if (camp.isStudentInBlackList(this)) {
            System.out.println("You have been blacklisted from this camp!");
            return false;            
        }
        else if(camp.isCampFull()) {
            System.out.println("Camp is full, select another camp!");
            return false;
        }
        else if (camp.isRegistrationOverDeadline()) {
            System.out.println("Over Registration deadline!");
            return false;            
        }
        return true;
        
    }

    public void registerCampRole(Role role, Camp camp) {
        // to be replaced with factory;
        if (role.equals(Role.CAMP_COMMITTEE_MEMBER)) {
            // could potentially look at initially starting with CAMP COMMITTEE MEMBER, then Downcasting all of them later.
            CampCommitteeMember campCommitteeMember = new CampCommitteeMember(emailID, password, faculty, name, role, camp);
            camp.addCampCommitteeMember(campCommitteeMember);
            camp.addStudent(this);
            // append into database of Camp Committee Member
        }
        else if (role.equals(Role.STUDENT)) {
            camp.addStudent(this);
        }
        camp.printStudentList();
        registeredCamps.add(camp);
        
    }

    public void withdrawFromCamp(Camp camp) {
        // if camp committee member should remove from camp
        camp.getStudentList().remove(this);
        camp.addToBlackList(this);
        System.out.println("Withdrawn from camp");
        
    }

}

