package CAMSv2;

import java.sql.Array;
import java.util.ArrayList;
public class Camp {

    boolean visibility;
    private CampInformation info;
    private ArrayList<Student> StudentList;
    private ArrayList<Enquiries> EnquiryList;


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
}
