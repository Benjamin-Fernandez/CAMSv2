package CAMSv2;
//import java.util.ArrayList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class CampInformation {

    private String CampName;
    private Date[] Dates; 
    private LocalDateTime Registration_closing_date;
    private String User_group;
    private String Location;
    private int Total_Slots;

    private HashSet<Student> Camp_Committee_slots;
    private String Description;
    private String Staff_in_charge;

    public CampInformation(String CampName , Date[] Dates , LocalDateTime Registration_closing_date, String User_group , String Location , int Total_Slots, String Description, String Staff_in_charge){

        this.CampName = CampName;
        this.Dates = Dates;
        this.Registration_closing_date = Registration_closing_date;
        this.User_group = User_group;
        this.Location = Location;
        this.Total_Slots = Total_Slots;
        this.Description = Description;
        this.Staff_in_charge = Staff_in_charge;

    }

    public void addCampCommitteeMember(Student student){
        this.Camp_Committee_slots.add(student);
    }

    // public void editCampInfo(int choice){ //do we really want this here? i put in cmapManeger
    //     //switch case 1- date etc
    //     //ask what they want chng
    //     //scanf(newChange)
    //     // this.Dates = newChange
    // }


    //get methods

    public String getCampName(){
            return this.CampName;
    }

    public String getStaffName(){
            return this.Staff_in_charge;
    }

    public Date[] getDates(){
        return this.Dates;
    }

    public LocalDateTime getRegistrationClosingDate(){
        return this.Registration_closing_date;
    }

    public String getUserGroup(){
        return this.User_group;
    }

    public String getLocation(){
        return this.Location;
    }

    public int getTotalSlots(){
        return this.Total_Slots;
    }

    public HashSet<Student> getCampCommitteeSlots(){
        return this.Camp_Committee_slots;
    }
    
    public String getDescription(){
        return this.Description;
    }


    //set methods
    public void setCampName(String campName){
        this.CampName = campName;
    }

    public void setDates(Date[] Dates){
        this.Dates = Dates;
    }

    public void setRegistrationClosingDate(LocalDateTime closingDate){
        this.Registration_closing_date = closingDate;
    }

    public void setUserGroup(String userGroup){
        this.User_group = userGroup;
    }

    public void setLocation(String location){
        this.Location = location;
    }

    public void setTotalSlots(int totalSlots){
        this.Total_Slots = totalSlots;
    }

    public void setDescription(String description){
        this.Description = description;
    }


    public void printCampInfoTable(){
        System.out.println("1. Camp Name = " + this.CampName);

        System.out.println("2. Camp Dates = " + this.Dates);

        System.out.println("3. Registration Closing Date = " + this.Registration_closing_date);

        System.out.println("4. User Group = " + this.User_group);

        System.out.println("5. Camp Location = " + this.Location);

        System.out.println("6. Total Camp Slots = " + this.Total_Slots);

        System.out.println("7. Camp Description = " + this.Description);

    }

    

}
