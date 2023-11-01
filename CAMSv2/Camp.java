package CAMSv2;

import java.sql.Array;
import java.util.ArrayList;
public class Camp {

    boolean visibility;
    private CampInformation info;
    private ArrayList<Student> StudentList;
    private ArrayList<Enquiries> EnquiryList;
    private ArrayList<Suggestion> SuggestionsList;

    // public Camp(){
    //     this.info = null;
    //     this.StudentList = new ArrayList<Student>  ;
    //     this.EnquiryList = null;
    //     this.SuggestionsList = null;
    //     this.visibility = false;


    // }

    public Camp(String CampName , String Dates , String Registration_closing_date, String User_group , String Location , String Total_Slots , String[]Camp_Committee_slots, String Description, String Staff_in_charge){


        this.visibility = false;
        this.info = new CampInformation( CampName ,  Dates ,  Registration_closing_date,  User_group , Location , Total_Slots , Camp_Committee_slots,  Description, Staff_in_charge);
        this.StudentList = new ArrayList<Students>();

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

    public String getDates(){
        return this.info.getDates();
    }

    public String getRegistrationClosingDate(){
        return this.info.getRegistrationClosingDate();
    }

    public String getUserGroup(){
        return this.info.getUserGroup();
    }

    public String getLocation(){
        return this.info.getLocation();
    }

    public String getTotalSlots(){
        return this.info.getTotalSlots();
    }

    public String[] getCampCommitteeSlots(){
        return this.info.getCampCommitteeSlots();
    }

    public String getDescription(){
        return this.info.getDescription();
    }


}
