package CAMSv2;

import java.util.ArrayList;
/** 
 Representing a Student user registered in the system.
 @author Zhu Yu Hao
 @since 13-11-2023
 */
public class Student extends User{
    ArrayList<Camp> registeredCamps = new ArrayList<Camp>();
    Enquiries enquiries;

      /**
     * Constructor for the Student class.
     * It initializes the student's name, email ID, faculty, password, role, enquiries, and registered camps.
     *
     * @param name The student's name.
     * @param emailID The student's email ID.
     * @param faculty The faculty to which the student belongs.
     * @param password The student's password.
     * @param role The student's role.
     */

    public Student(String name, String emailID, UserGroup faculty, String password, Role role) {
        super(name, emailID, faculty, password, role);
        // setup the enquiries and registeredCamps
        enquiries = CampManager.setUpStudentEnquiries(name);
        System.out.println("EnquirySetUp: " + getEnquiries().getQuestions());
        registeredCamps = CampManager.setUpStudentRegisteredCamps(name);
        System.out.println("Registered Camp: " + getRegisteredCamps());
    }

    @Override
    public void changePassword() {
        super.changePassword();
        StudentDataBase.getInstance().writeToCSV();
    }

     /**
     * Checks if a given camp name is in the list of available camps.
     *
     * @param campName The name of the camp to check.
     * @return The Camp object if found, null otherwise.
     */

    public Camp ifCampNameInAvailableListOfCamps(String campName) {
        Camp camp = CampManager.getCamp(campName);
        if (camp == null) {
            System.out.println("Camp not found!");
            return null;
        }
        else if (!CampManager.getCampListByFacultyAndVisibility(faculty).contains(camp)) {
            System.out.println("The chosen camp is not available to you.");
            return null;
        } 
        else {
            return camp;
        }
    }

     /**
     * This method gets the list of camps registered by the student.
     *
     * @return An ArrayList of Camp objects representing the camps registered by the student.
     */
    public ArrayList<Camp> getRegisteredCamps() {
        return registeredCamps;
    }

    /**
     * This method allows the student to view the remaining slots for a given camp.
     *
     * @param campName The name of the camp to check.
     */
    public void viewRemainingCampSlots(String campName) {
        // get Camp from CampManager
        Camp camp = CampManager.getCamp(campName);
        // Display available slots
        System.out.println( camp.getCampName() + " Available Slots: " + (camp.getTotalSlots() - camp.getStudentList().size()));
        // Camp Manager should check that the camp is Visible and userGroup
    }



    // --- Enquiries -----------------------------------------------------

     /**
     * This method allows the student to create an enquiry for a given camp.
     *
     * @param description The description of the enquiry.
     * @param camp The camp for which the enquiry is being made.
     */
    public void createEnquiry(String description, Camp camp) {
        Question question = new Question(description, camp.getCampName(), EnquiryManager.getEnquiryCounter());
        System.out.println("Created Question: " + question);
        enquiries.addQuestion(question);
        System.out.println("EnquiryList: " + enquiries.getQuestions());
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

     /**
     * This method deletes all enquiries made by the student.
     */

    public void deleteAllEnquiries() {
        enquiries = new Enquiries(super.name);
    }

     /**
     * This method gets the enquiries made by the student.
     *
     * @return An Enquiries object representing the enquiries made by the student.
     */

    public Enquiries getEnquiries() {
        return enquiries;
    }

     /**
     * This method checks if the student can register for a given camp.
     *
     * @param camp The camp to check.
     * @return true if the student can register for the camp, false otherwise.
     */
    // --- Registration--------------------------------------
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
            camp.printCCMList();
            camp.printStudentList();
            registeredCamps.add(camp);

            // Auto logout to login page
            // Delete this Student Object from Student Database
            StudentDataBase.getInstance().getStudents().remove(this);
            StudentDataBase.getInstance().writeToCSV();
            return false;
        }
        else if (role.equals(Role.STUDENT)) {
            camp.addStudent(this);
            camp.printStudentList();
            registeredCamps.add(camp);
            return true;
        }

        return true;
    }

     /**
     * This method withdraws a student from a given camp.
     *
     * @param camp The camp to check.
    * Blacklists the student from the given cmap and removes the student from the camp's student list.
     * .
     */
    public void withdrawFromCamp(Camp camp) {
        // if camp committee member should remove from camp
        camp.getStudentList().remove(this);
        camp.addToBlackList(this);
        System.out.println("Withdrawn from camp");
        
    }

}

