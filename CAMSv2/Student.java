package CAMSv2;

import java.util.ArrayList;

public class Student extends User{

    private ArrayList<CampAndRole> registeredCamps;


    public Student(String emailID, String password, String faculty, String name, String role) {
        super(emailID, password, faculty, name, role);
    }

    public JoiningCamp(){
        //ask what camp he wants to apply
        // IsStudentInBlackList()
        // IsCampFull()
        // IsBeforeCampDeadline()
        CampManager campManager = new CampManager();

    }

    public getEnquries(){
        //ask for camp name
        //CampManager campManager = new CampManager();
        //Camp camp = campManager.getCamp(campName);
        //camp.getListofEnquiries()
        //do for loop for Enquiries.getname();

    }

    public generateReport(){

    }
}
