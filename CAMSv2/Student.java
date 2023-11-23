package CAMSv2;

import java.time.LocalDate;
import java.util.ArrayList;
/** 
 Representing a Student user registered in the system.
 @author Zhu Yu Hao
 @since 13-11-2023
 */
public class Student extends User{
    ArrayList<Camp> registeredCamps;
    Enquiries enquiries;
    public Student(String name, String emailID, UserGroup faculty, String password, Role role) {
        super(name, emailID, faculty, password, role);
        // setup the enquiries and registeredCamps
        enquiries = setUpStudentEnquiries();
        // System.out.println("EnquirySetUp: " + getEnquiries().getQuestions());
        registeredCamps = setUpStudentRegisteredCamps();
        // System.out.println("Registered Camp: " + getRegisteredCamps());
    }
    public Enquiries setUpStudentEnquiries() {
        Enquiries studentEnquiries = new Enquiries(name);
        ArrayList<Question> studentQuestions = studentEnquiries.getQuestions();
        for (Camp camp : CampManager.getInstance().getCampList()) {
            for (Enquiries enquiries : camp.getEnquiries()) {
                if (enquiries.getEnqurier().equals(name)) {
                    ArrayList<Question> questions = enquiries.getQuestions();
                    studentQuestions.addAll(questions);
                }
            }
        }
        return studentEnquiries;
    }

    public ArrayList<Camp> setUpStudentRegisteredCamps() {
        ArrayList<Camp> registeredCamps = new ArrayList<Camp>();
        for (Camp camp : CampManager.getInstance().getCampList()) {
            for (Student student : camp.getStudentList()) {
                if (student.getName().equals(name)) {
                    System.out.println("Camp is registered");
                    registeredCamps.add(camp);
                }
            }
        }
        return registeredCamps;
    }
    /**
     * This method changes the password and write to database.
     */
    @Override
    public void changePassword(String newPassword) {
        super.changePassword(newPassword);
        StudentDataBase.getInstance().writeToCSV();
    }

    public Camp ifCampNameInAvailableListOfCamps(String campName) {
        Camp camp = CampManager.getInstance().getCamp(campName);
        if (camp == null) {
            System.out.println("Camp not found!");
            return null;
        }
        else if (!CampManager.getInstance().getCampListByFacultyAndVisibility(faculty).contains(camp)) {
            System.out.println("The chosen camp is not available to you.");
            return null;
        } 
        else {
            return camp;
        }
    }

    public ArrayList<Camp> getRegisteredCamps() {
        return registeredCamps;
    }

    public void viewRemainingCampSlots(String campName) {
        // get Camp from CampManager
        Camp camp = CampManager.getInstance().getCamp(campName);
        // Display available slots
        System.out.println( camp.getCampName() + " Available Slots: " + (camp.getTotalSlots() - camp.getStudentList().size()));
        // Camp Manager should check that the camp is Visible and userGroup
    }



    // --- Enquiries -----------------------------------------------------
    public void createEnquiry(String description, Camp camp) {
        Question question = new Question(description, camp.getCampName(), EnquiryManager.getEnquiryCounter());
        System.out.println("Created Question: " + question);
        enquiries.addQuestion(question);
        System.out.println("EnquiryList: " + enquiries.getQuestions());
        EnquiryManager.getInstance().createEnquiry(question, camp, getName());
    }

    public Question getEnquiryById(int id) {
        for (Question enquiry : enquiries.getQuestions()) {
            if (enquiry.getQuestionId() == id) {
                return enquiry;
            }
        }
        return null;
    }

    /**
     * Don't need to change the Camp Question because they are the same reference.
     * @param question
     * @param description
     */
    public void editEnquiry(Question question, String description) {
        question.setQuestion(description);   
    }

    public void deleteEnquiry(int Id, Camp camp) {
        ArrayList<Question> questions = enquiries.getQuestions();
        Question thisQuestion = null;
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getQuestionId() == Id) {
                // remove
                thisQuestion = questions.get(i);
                questions.remove(i);
                // debugging message
                System.out.println("Deleted the Enquiry!");
                break;
            }
        }
        if (thisQuestion == null) {
            System.out.println("No such Enquiry!");
            return;
        }
        else {
            camp.deleteEnquiry(thisQuestion, getName());            
        }



    }

    public void deleteAllEnquiries() {
        enquiries = new Enquiries(super.name);
    }

    public Enquiries getEnquiries() {
        return enquiries;
    }

    // --- Registration--------------------------------------
    public boolean canRegisterCamp(Camp camp) {
        if (role.equals(Role.CAMP_COMMITTEE_MEMBER)) {
            System.out.println("You are already a CAMP COMMITTEE MEMBER!");
            return false;               
        }
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
        // camp overlaps with already registered camps deadlines
        else if (overlappingCampDates(camp)) {
            System.out.println("Overlapping Camp Dates!");
            return false;                 
        }
        return true;
        
    }

    public boolean registerCampRole(Role role, Camp camp) {
        // to be replaced with factory;
        if (role.equals(Role.CAMP_COMMITTEE_MEMBER)) {
            // could potentially look at initially starting with CAMP COMMITTEE MEMBER, then Downcasting all of them later.
            System.out.println("Camp: " + camp);
            camp.addStudent(this);
            
            CampCommitteeMember campCommitteeMember = new CampCommitteeMember(name, emailID, faculty, password, role, camp);
            System.out.println("Camp from CCM: " + campCommitteeMember.getCamp());
            camp.addCampCommitteeMember(campCommitteeMember);

            // append into database of Camp Committee Member
            CampCommitteeDataBase.getInstance().getCampCommitteeMembersList().add(campCommitteeMember);
            CampCommitteeDataBase.getInstance().printList();
            CampCommitteeDataBase.getInstance().writeToCSV();
            System.out.println("REMINDER: Please Re-Login to access Camp Committee Member Privileges...");

            // Delete this Student Object from Student Database
            StudentDataBase.getInstance().getStudents().remove(this);
            StudentDataBase.getInstance().writeToCSV();
            return false;
        }
        else if (role.equals(Role.STUDENT)) {
            camp.addStudent(this);
            registeredCamps.add(camp);
            return true;
        }

        return true;
    }

    public void withdrawFromCamp(Camp camp) {
        // if camp committee member should remove from camp
        camp.getStudentList().remove(this);
        camp.addToBlackList(this);
        System.out.println("Withdrawn from camp");
        
    }

    public boolean overlappingCampDates(Camp camp) {
        LocalDate[] currentCampDates = camp.getDates();
        LocalDate currentStart = currentCampDates[0];
        LocalDate currentEnd = currentCampDates[currentCampDates.length - 1];
        System.out.println("currentStart" + currentStart.toString());
        System.out.println("currentEnd" + currentEnd.toString());                

        for (Camp tempCamp : getRegisteredCamps()) {
            LocalDate[] tempCampDates = tempCamp.getDates();
            LocalDate tempStart = tempCampDates[0];
            System.out.println("tempStart" + tempStart.toString());
            LocalDate tempEnd = tempCampDates[tempCampDates.length - 1];
            System.out.println("tempEnd" + tempEnd.toString());
            // current start date must be after end date of temp camp
            // current end date must be before start date of temp camp
            if (!(currentStart.isAfter(tempEnd) || currentEnd.isBefore(tempStart))) {
                return true;
            }
        }
        return false;
    }

}

