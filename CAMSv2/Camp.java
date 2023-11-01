package CAMSv2;

import java.sql.Array;
import java.util.ArrayList;
public class Camp {

    boolean visibility;
    private CampInformation info;
    private ArrayList<Student> StudentList;
    private ArrayList<Enquiries> EnquiryList;
    private ArrayList<Suggestion> SuggestionsList;

    public Camp(8 parameters){

        this.visibility = false;
        this.info = new CampInformation(8 parameters);
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
       this.info.getCampName();
    }

    public String getStaffName(){
        this.info.getStaffName();
    }
}
