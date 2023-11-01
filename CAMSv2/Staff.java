package CAMSv2;

import java.util.Scanner;

public class Staff extends User {
    public Staff(name,emailID, faculty,password,role){
        //super(name,emailID, faculty,password,role)
    }

    public void createCamp(){

    CampManager util = new CampManager();
    util.createCamp(this.nameame);

    }

    public void editCamp(String campName) {

     CampManager util = new CampManager();
     util.editCamp(campName,this.name);


    }

    public void deleteCamp() {
//ask for what camp to delete
        //scan the string and then String CampName = scan
        CampManager util = new CampManager();
        util.deleteCamp(campName);


    }

    public void changeVisibility(){
        //ask for which camp
        //scan and String campName = scan
        // CampManager util = new CampManager();
        //        util.changeVisibility(campName);
        }

    public void viewCamp(){
    Camp camp = new Camp();
    //use for loop to go through array to print all the camp object.name
    }

    public void myList(){
        // CampManager util = new CampManager();
        //        util.StaffCampListGenerator(this.name);

    }

    public void viewEnquiries(){
        //ask which ccamp name they want to view
        //scann a string
        EnquiryManager enqManager = new EnquiryManager();
        enqManager.viewEnquiry(campName, this.name);
    }

    public void replyEnquiries(){
        EnquiryManager enqManager = new EnquiryManager();
        Scanner sc = new Scanner(System.in);
        String campName;
        System.out.println("which camp's enquiry would you want to reply to? ");
        campName = sc.nextLine();
        enqManager.replyEnquiry(campName, this.name);

    }

    public void viewSuggestion(){
         //ask which ccamp name they want to view
        SuggestionManager suggManager = new SuggestionManager();
        suggManager.viewSuggestion(campName, this.name);
    }

    public void approveAdvice(){
        //ask which ccamp name they want to approve;
        SuggestionManager suggManager = new SuggestionManager();
        suggManager.approveAdvice(campName, this.name)

    }

    public void generateReport(){
        CampManager campManager = new CampManager();
        campManager.generateReport(this.name);
    }











}
