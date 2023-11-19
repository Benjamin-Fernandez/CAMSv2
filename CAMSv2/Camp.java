package CAMSv2;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
public class Camp {

    boolean visibility;
    private CampInformation info;
    private HashSet<Student> studentList = new HashSet<Student>();
    private ArrayList<Enquiries> enquiryList = new ArrayList<Enquiries>();
    private ArrayList<Suggestion> suggestionsList = new ArrayList<Suggestion>();
    private HashSet<Student> blackList = new HashSet<Student>();

    // Constructors
    public Camp(){ 
        //empty constructor
    }
   

    public Camp(String CampName , LocalDate[] Dates , LocalDateTime Registration_closing_date, UserGroup User_group , String Location , int Total_Slots, String Description, String Staff_in_charge){
        //String Dates will need change to an Arraylist of integers instead
        this.visibility = false;
        this.info = new CampInformation(CampName ,  Dates ,  Registration_closing_date,  User_group , Location , Total_Slots,  Description, Staff_in_charge);

    }

    // Methods
    public boolean isRegistrationOverDeadline() {
        // get registration deadline
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(info.getRegistrationClosingDate());
    }
    public void addToBlackList(Student student) {
        blackList.add(student);
    }

    public void addCampCommitteeMember(CampCommitteeMember student) {
        info.getCampCommitteeSlots().add(student);
    }

    public boolean isStudentInBlackList(Student student) {
        for (Student s : blackList) {
            if (s.getName().equals(student.getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean isStudentRegistered(String name) {
        for (Student student : studentList) {
            if (student.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void addStudent(Student student){
        studentList.add(student);
    }

    // check if student Enquiries is already in Camp
    // if not, create a new enquiry
    // add to camp
    public Enquiries addStudentEnquiriesInList(String studentName) {
        for (Enquiries enquiries : enquiryList) {
            if (enquiries.getEnqurier().equals(studentName)) {
                return enquiries;
            }
        
        }

        Enquiries newEnquiry = new Enquiries(studentName);
        addEnquiry(newEnquiry);
        return newEnquiry;
    }

    public boolean isCampFull() {
        int length = studentList.size();
        // System.out.println("StudentList: "  + length + "getTotalSlots: " + getTotalSlots());
        // System.out.println(info.getTotalSlots() == length);
        return info.getTotalSlots() == length;
    }

    public void editSuggestion(String studentName, String newAdvice, int AdviceIndex){
        for (Suggestion suggestion : suggestionsList) {
            if (suggestion.getStudent() == studentName) {
                suggestion.getAdviceList().get(AdviceIndex).setNewAdvice(newAdvice);
            }

        }
    }

    public void deleteSuggestion(String studentName, int AdviceIndex){
        for (Suggestion suggestion : suggestionsList) {
            if (suggestion.getStudent() == studentName) {
                suggestion.getAdviceList().remove(AdviceIndex);
            }

        }
    }

    public void setVisibility(boolean choice){
        this.visibility = choice;
    }

    public void addEnquiry(Enquiries enquiry){
        enquiryList.add(enquiry);
    }

    public void addSuggestion(Suggestion suggestion){
        suggestionsList.add(suggestion);
    }

    public ArrayList<Enquiries> getEnquiries(){
        return enquiryList;
    }

    public ArrayList<Suggestion> getSuggestions(){
        return suggestionsList;
    }

    public HashSet<Student> getStudentList(){
            return studentList;
    }

    public String getCampName(){
      return this.info.getCampName();
    }

    public String getStaffName(){
       return this.info.getStaffName();
    }

    
    //Camp information functions

    //get methods

    public LocalDate[] getDates(){
        return this.info.getDates();
    }

    public LocalDateTime getRegistrationClosingDate(){
        return this.info.getRegistrationClosingDate();
    }

    public UserGroup getUserGroup(){
        return this.info.getUserGroup();
    }

    public String getLocation(){
        return this.info.getLocation();
    }

    public int getTotalSlots(){
        return this.info.getTotalSlots();
    }

    public HashSet<CampCommitteeMember> getCampCommitteeSlots(){
        return this.info.getCampCommitteeSlots();
    }

    public String getDescription(){
        return this.info.getDescription();
    }
    


    //set methods
    public void setCampName(String campName){
        this.info.setCampName(campName);
    }

    public void setDates(LocalDate[] Dates){
        this.info.setDates(Dates);
    }

    public void setRegistrationClosingDate(LocalDateTime closingDate){
        this.info.setRegistrationClosingDate(closingDate);
    }

    public void setUserGroup(String userGroup){
        this.info.setUserGroup(userGroup);
    }

    public void setLocation(String location){
        this.info.setLocation(location);
    }

    public void setTotalSlots(int totalSlots){
        this.info.setTotalSlots(totalSlots);
    }

    public void setDescription(String description){
        this.info.setDescription(description);
    }


    //additional methods
    public void printCampInfoTable(){
        this.info.printCampInfoTable();
    }

    public void printStudentList() {
        System.out.println("PRINTING STUDENTLIST");
        for (Student student : studentList) {
            System.out.println(student.getName());
        }
    }

    public void printCCMList() {
        System.out.println("PRINTING CCMLIST");
        for (CampCommitteeMember student : info.getCampCommitteeSlots()) {
            System.out.println(student.getName());
        }
    }

    public void printBlackList() {
    System.out.println("PRINTING BLACKLIST");
    for (Student blackStudent : blackList) {
        System.out.println(blackStudent.getName());
    }
    }


    public void deleteEnquiry(Question question, String studentName) {
        Enquiries enquiries = addStudentEnquiriesInList(studentName);
        ArrayList<Question> questions = enquiries.getQuestions();
        questions.remove(question);

    }


}
