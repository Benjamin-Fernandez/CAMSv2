package CAMSv2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

/**

 *The {@code CampInformation} class represents information about a camp, including its details and committee members.
 * @author Zhu Yu Hao
 * @since 13-11-2023
 */
public class CampInformation {

    private String CampName;
    private LocalDate[] Dates; 
    private LocalDateTime Registration_closing_date;
    private UserGroup User_group;
    private String Location;
    private int Total_Slots;

    private HashSet<CampCommitteeMember> Camp_Committee_slots = new HashSet<CampCommitteeMember>();
    private String Description;
    private String Staff_in_charge;

    /**
     * Constructor for CampInformation.
     * @param campName The name of the camp.
     * @param dates The dates of the camp.
     * @param registrationClosingDate The closing date for camp registration.
     * @param userGroup The user group associated with the camp.
     * @param location The location of the camp.
     * @param totalSlots The total number of slots available in the camp.
     * @param description The description of the camp.
     * @param staffInCharge The staff in charge of the camp.
     */
    public CampInformation(String campName , LocalDate[] dates , LocalDateTime registrationClosingDate, UserGroup userGroup , String location , int totalSlots, String description, String staffInCharge){

        this.CampName = campName;
        this.Dates = dates;
        this.Registration_closing_date = registrationClosingDate;
        this.User_group = userGroup;
        this.Location = location;
        this.Total_Slots = totalSlots;
        this.Description = description;
        this.Staff_in_charge = staffInCharge;

    }
    
    /**
     * Adds a Camp Committee Member to the set of committee members associated with the camp.
     * @param student The CampCommitteeMember to be added to the camp's committee.
     */
    public void addCampCommitteeMember(CampCommitteeMember student){
        this.Camp_Committee_slots.add(student);
    }

    // public void editCampInfo(int choice){ //do we really want this here? i put in cmapManeger
    //     //switch case 1- date etc
    //     //ask what they want chng
    //     //scanf(newChange)
    //     // this.Dates = newChange
    // }


    //get methods
    /**
     * Retrieves the name of the camp.
     * @return The name of the camp.
     */
    public String getCampName(){
            return this.CampName;
    }

    /**
     * Retrieves the name of the staff in charge of the camp.
     * @return The name of the staff in charge.
     */
    public String getStaffName(){
            return this.Staff_in_charge;
    }

    /**
     * Retrieves the dates of the camp.
     * @return The dates of the camp.
     */
    public LocalDate[] getDates(){
        return this.Dates;
    }

    /**
     * Retrieves the registration closing date for the camp.
     * @return The registration closing date.
     */
    public LocalDateTime getRegistrationClosingDate(){
        return this.Registration_closing_date;
    }

    /**
     * Retrieves the user group associated with the camp.
     * @return The user group of the camp.
     */
    public UserGroup getUserGroup(){
        return this.User_group;
    }

    /**
     * Retrieves the location of the camp.
     * @return The location of the camp.
     */
    public String getLocation(){
        return this.Location;
    }

    /**
     * Retrieves the total number of slots available in the camp.
     * @return The total number of slots in the camp.
     */
    public int getTotalSlots(){
        return this.Total_Slots;
    }

    /**
     * Retrieves the set of committee members associated with the camp.
     * @return The set of committee members in the camp.
     */
    public HashSet<CampCommitteeMember> getCampCommitteeSlots(){
        return this.Camp_Committee_slots;
    }

    /**
     * Retrieves the description of the camp.
     * @return The description of the camp.
     */
    public String getDescription(){
        return this.Description;
    }

    /**
     * Sets the name of the camp.
     * @param campName The new name for the camp.
     */
    //set methods
    public void setCampName(String campName){
        this.CampName = campName;
    }

    /**
     * Sets the dates of the camp.
     * @param Dates The new dates for the camp.
     */
    public void setDates(LocalDate[] Dates){
        this.Dates = Dates;
    }

    /**
     * Sets the registration closing date for the camp.
     * @param closingDate The new registration closing date for the camp.
     */
    public void setRegistrationClosingDate(LocalDateTime closingDate){
        this.Registration_closing_date = closingDate;
    }

    /**
     * Sets the user group associated with the camp.
     * @param userGroup The new user group for the camp.
     */
    public void setUserGroup(String userGroup){
        for (UserGroup usergroups : UserGroup.values()) {
            if(userGroup.equals(usergroups.toString())){
                this.User_group = usergroups;
            }
        }
    }

    /**
     * Sets the location of the camp.
     * @param location The new location for the camp.
     */
    public void setLocation(String location){
        this.Location = location;
    }

    /**
     * Sets the total number of slots available in the camp.
     * @param totalSlots The new total number of slots in the camp.
     */
    public void setTotalSlots(int totalSlots){
        this.Total_Slots = totalSlots;
    }

    /**
     * Sets the description of the camp.
     * @param description The new description for the camp.
     */
    public void setDescription(String description){
        this.Description = description;
    }

    /**
     * Prints the detailed information about the camp to the console.
     * Displays various attributes such as camp name, dates, registration closing date, user group,
     * location, total slots available, and camp description.
     */
    public void printCampInfoTable(){
        System.out.println("1. Camp Name = " + this.CampName);

        System.out.print("2. Camp Dates = ");
        for (LocalDate date : Dates) {
            System.out.println(date);
        }

        System.out.println("3. Registration Closing Date = " + this.Registration_closing_date);

        System.out.println("4. User Group = " + this.User_group);

        System.out.println("5. Camp Location = " + this.Location);

        System.out.println("6. Total Camp Slots = " + this.Total_Slots);

        System.out.println("7. Camp Description = " + this.Description);

    }

    

}
