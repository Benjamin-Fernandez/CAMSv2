package CAMSv2;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
public class Camp {

    boolean visibility;
    private CampInformation info;
    private HashSet<Student> studentList = new HashSet<Student>();
    private ArrayList<Enquiries> enquiriesList = new ArrayList<Enquiries>();
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
        for (Enquiries enquiries : enquiriesList) {
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

    public boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(boolean choice){
        this.visibility = choice;
    }

    public void addEnquiry(Enquiries enquiry){
        enquiriesList.add(enquiry);
    }

    public void addSuggestion(Suggestion suggestion){
        suggestionsList.add(suggestion);
    }

    public ArrayList<Enquiries> getEnquiries(){
        return enquiriesList;
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
        System.out.println("8. Visibility = " + getVisibility());
    }

    public void printStudentList(HashSet<Student> students) {
        System.out.println("PRINTING STUDENTLIST");
        System.out.println("Student Name" + " | " + "Role");
        for (Student student : students) {
            System.out.println(student.getName() + " | " + student.getRole());
        }
    }

    public void printCCMList() {
        System.out.println("PRINTING CCMLIST");
        System.out.println("CCM Name" + " | " + "Points");
        for (CampCommitteeMember ccm : info.getCampCommitteeSlots()) {
            System.out.println(ccm.getName() + " | " + ccm.getPoints());
        }
    }

    public void printBlackList() {
        System.out.println("PRINTING BLACKLIST");
        for (Student blackStudent : blackList) {
            System.out.println(blackStudent.getName());
        }
    }

    public void printEnquiriesList() {
        for (Enquiries enquiries : enquiriesList) {
            System.out.println("Enquirier: " + enquiries.getEnqurier());
            System.out.println("Enquiry Id" + " | " + "Enquiry");
            for (Question question : enquiries.getQuestions()) {
                System.out.println(question.getQuestionId() + " | " + question.getQuestion());
                for (Reply reply : question.getReplies()) {
                    System.out.println(reply.getName() + " Replied: " + reply.getReply());
                }
            }
        }
    }

    public void printSuggestionList() {
    for (Suggestion suggestion : suggestionsList) {
        System.out.println("Suggester: " + suggestion.getStudent());
        System.out.println("Advice Id" + " | " + "Advice" + " | " + "Status");
        for (Advice advice : suggestion.getAdviceList()) {
            System.out.println(advice.getId() + " | " + advice.getAdvice() + " | " + advice.getApproved());
        }
    }
}


    public void deleteEnquiry(Question question, String studentName) {
        Enquiries enquiries = addStudentEnquiriesInList(studentName);
        ArrayList<Question> questions = enquiries.getQuestions();
        questions.remove(question);

    }

    public Question getEnquiryFromCamp(int Id) {
        for (Enquiries enquiry : enquiriesList) {
            for (Question question : enquiry.getQuestions()) {
                if (question.getQuestionId() == Id) {
                    return question;
                }
            }
        }
        return null;
    }

    public Suggestion getSuggestionBySuggester(String name) {
        for (Suggestion suggestion : suggestionsList) {
            if (suggestion.getStudent().equals(name)) {
                return suggestion;
            }
        }
        System.out.println("Cannot find the suggestion created by " + name);
        return null;
    }

    public Advice getAdviceFromCamp(int id) {
        for (Suggestion suggestion : suggestionsList) {
            for (Advice advice : suggestion.getAdviceList()) {
                if (advice.getId() == id) {
                    return advice;
                }
            }
        }
        return null;
    }

    public void generateCampReport(ReportFilter filter) {
        // list of students attending each camp
        printCampInfoTable();
        printStudentList(filter.getFilteredList(getStudentList()));
        // generate csv
        ReportDatabase.getInstance().generateCampReportCSV(this, filter);
    }

    public void generateCampCommitteeReport() {
        // CCM name, points 
        printCCMList();
        // generate csv
        ReportDatabase.getInstance().generateCCMReportCSV(this);
    }

    public void generateStudentsEnquiryReport() {
        // Student Name, Enquiry
        printEnquiriesList();
        // generate csv
        ReportDatabase.getInstance().generateStudentsEnquiryReport(this);
    }
    



}
