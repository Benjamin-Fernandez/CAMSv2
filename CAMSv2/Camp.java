package CAMSv2;
import java.util.ArrayList;
public class Camp {

    boolean visibility;
    private CampInformation info;
    private ArrayList<Student> StudentList;

    //blacklist
    private ArrayList<Enquiries> EnquiryList;
    private ArrayList<Suggestion> SuggestionsList;

    // Constructors
    public Camp(){ 
        //empty constructor
    }
   

    public Camp(String CampName , String Dates , int Registration_closing_date, String User_group , String Location , int Total_Slots, String Description, String Staff_in_charge){

        this.visibility = false;
        this.info = new CampInformation( CampName ,  Dates ,  Registration_closing_date,  User_group , Location , Total_Slots,  Description, Staff_in_charge);
        this.StudentList = new ArrayList<Student>();

    }

    // Methods
    public void addStudent(String studentName, String role){
        //logic to add student into StudentList

    }


    public void setVisibility(boolean choice){
        this.visibility = choice;
    }

    public void addEnquiry(Enquiries enquiry){
        EnquiryList.add(enquiry);
    }

    public void addSuggestion(Suggestion suggestion){
        SuggestionsList.add(suggestion);
    }

    public ArrayList<Enquiries> getEnquiries(){
        return EnquiryList;
    }

    public ArrayList<Suggestion> getSuggestions(){
        return SuggestionsList;
    }

    public ArrayList<Student> getStudentList(){
            return StudentList;
    }

    public String getCampName(){
      return this.info.getCampName();
    }

    public String getStaffName(){
       return this.info.getStaffName();
    }

    
    //Camp information functions

    //get methods

    public String getDates(){
        return this.info.getDates();
    }

    public int getRegistrationClosingDate(){
        return this.info.getRegistrationClosingDate();
    }

    public String getUserGroup(){
        return this.info.getUserGroup();
    }

    public String getLocation(){
        return this.info.getLocation();
    }

    public int getTotalSlots(){
        return this.info.getTotalSlots();
    }

    public ArrayList<Student> getCampCommitteeSlots(){
        return this.info.getCampCommitteeSlots();
    }

    public String getDescription(){
        return this.info.getDescription();
    }
    


    //set methods
    public void setCampName(String campName){
        this.info.setCampName(campName);
    }

    public void setDates(String dates){
        this.info.setDates(dates);
    }

    public void setRegistrationClosingDate(int closingDate){
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
        this.setDescription(description);
    }


    //additional methods
    public void printCampInfoTable(){
        this.info.printCampInfoTable();
    }


}
