package CAMSv2;
import java.util.ArrayList;

public class CampInformation {

    private String CampName;
    private String Dates;
    private String Registration_closing_date;
    private String User_group;
    private String Location;
    private String Total_Slots;

    private String[] Camp_Committee_slots;
    private String Description;
    private String Staff_in_charge;

    public CampInformation(String CampName , String Dates , String Registration_closing_date, String User_group , String Location , String Total_Slots , String[]Camp_Committee_slots, String Description, String Staff_in_charge){

        this.CampName = CampName;
        this.Dates = Dates;
        this.Registration_closing_date = Registration_closing_date;
        this.User_group = User_group;
        this.Location = Location;
        this.Total_Slots = Total_Slots;
        this.Camp_Committee_slots = Camp_Committee_slots;
        this.Description = Description;
        this.Staff_in_charge = Staff_in_charge;

    }

    public void editCampInfo(int choice){
        //switch case 1- date etc
        //ask what they want chng
        //scanf(newChange)
        // this.Dates = newChange;


    }

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

    public String[] getCampCommitteeSlots(){
        return this.Camp_Committee_slots;
    }
    
    public String getDescription(){
        return this.getDescription();
    }


}
