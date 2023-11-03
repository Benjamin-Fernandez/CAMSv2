package CAMSv2;
//import java.util.ArrayList;

import java.util.ArrayList;

public class CampInformation {

    private String CampName;
    private String Dates;
    private String Registration_closing_date;
    private String User_group;
    private String Location;
    private String Total_Slots;

    private ArrayList<Student> Camp_Committee_slots;
    private String Description;
    private String Staff_in_charge;

    public CampInformation(String CampName , String Dates , String Registration_closing_date, String User_group , String Location , String Total_Slots, String Description, String Staff_in_charge){

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

    public String getDates(){
        return this.Dates;
    }

    public String getRegistrationClosingDate(){
        return this.Registration_closing_date;
    }

    public String getUserGroup(){
        return this.User_group;
    }

    public String getLocation(){
        return this.Location;
    }

    public String getTotalSlots(){
        return this.Total_Slots;
    }

    public ArrayList<CampCommitteeMember> getCampCommitteeSlots(){
        return this.Camp_Committee_slots;
    }
    
    public String getDescription(){
        return this.getDescription();
    }


    //set methods
    public void setCampName(String campName){
        this.CampName = campName;
    }

    public void setDates(String dates){
        this.Dates = dates;
    }

    public void setRegistrationClosingDate(String closingDate){
        this.Registration_closing_date = closingDate;
    }

    public void setUserGroup(String userGroup){
        this.User_group = userGroup;
    }

    public void setLocation(String location){
        this.Location = location;
    }

    public void setTotalSlots(String totalSlots){
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
