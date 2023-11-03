package CAMSv2;

import java.util.*;

public class Staff extends User {
    public Staff(String emailID, String password, String faculty, String name, String role){
        super(emailID,password,faculty,name,role);
    }

    public void createCamp(){
        CampManager campManager = new CampManager();
        campManager.createCamp(this.name);
    }

    public void editCamp() {
        String campName;
        int index;
        Scanner sc = new Scanner(System.in);
        CampManager campManager = new CampManager();
  
        this.viewCamp(); //this prints a table of camp choices

        System.out.println("Enter the index of the camp to edit");
        index = sc.nextInt();
        campName = getCampName(--index); //index is one larger than actual index
        
        // calls campManager
        campManager.editCamp(campName,this.name);
        sc.close();
        
    }

    public void deleteCamp() {
        String campName;
        int index;
        Scanner sc = new Scanner(System.in);
        CampManager campManager = new CampManager();
  
        this.viewCamp();
        //take in integer of which camp they want to view, i--; and get its camp name from its index

        System.out.println("Enter the index of the camp to delete");
        index = sc.nextInt();
        campName = this.getCampName(--index);
        
        //delete calls campManager
        campManager.deleteCamp(campName);
        sc.close();
    }

    public void changeVisibility(){
        String campName;
        int index;
        Scanner sc = new Scanner(System.in);
        CampManager campManager = new CampManager();
  
        this.viewCamp();
        //take in integer of which camp they wnat to view, i--; and get its camp name from its index

        System.out.println("Enter the index of the camp to change visibility");
        index = sc.nextInt();
        campName = this.getCampName(--index);
        
        //visibility calls campManager
        campManager.changeVisibility(campName);
        sc.close();
        }

    public void viewCamp(){
        //any staff can view all camps
        //all this method does is print out a list of camps
        CampManager campManager = new CampManager();
        ArrayList<Camp> campList = campManager.getCampList();
        int numOfCamps = campList.size();
        String campName;

        for(int i=0;i<numOfCamps;i++){
            Camp camp = campList.get(i);
            campName = camp.getCampName();
            System.out.println(i+1 + ". "+ campName);
        }//end for

    }//end viewCamp()

    public void myList(){
        CampManager campManager = new CampManager();
        campManager.StaffCampListGenerator(this.name);

    }

    public void viewEnquiries(){
        //ask which camp name they want to view
        Scanner sc = new Scanner(System.in);
        String campName;
        System.out.println("which camp's enquiry would you want to view? ");
        campName = sc.nextLine();
        EnquiryManager enqManager = new EnquiryManager();
        enqManager.viewEnquiry(campName, this.name);
        sc.close();
    }

    public void replyEnquiries(){
        EnquiryManager enqManager = new EnquiryManager();
        Scanner sc = new Scanner(System.in);
        String campName;
        System.out.println("which camp's enquiry would you want to reply to? ");
        campName = sc.nextLine();
        enqManager.replyEnquiry(campName, this.name);
        sc.close();

    }

    public void viewSuggestion(){
         //ask which camp name they want to view
        Scanner sc = new Scanner(System.in);
        String campName;
        System.out.println("which camp's suggestion would you want to view? ");
        campName = sc.nextLine();
        SuggestionManager suggManager = new SuggestionManager();
        suggManager.viewSuggestion(campName, this.name);
        sc.close();
    }

    public void approveAdvice(){
        //ask which camp name they want to approve;
        Scanner sc = new Scanner(System.in);
        String campName;
        System.out.println("which camp's suggestion would you want to approve? ");
        campName = sc.nextLine();
        SuggestionManager suggManager = new SuggestionManager();
        suggManager.approveAdvice(campName, this.name);
        sc.close();

    }

    public void generateReport(){
        CampManager campManager = new CampManager();
        campManager.generateReport(this.name);
    }


    public String getCampName(int indexOfCamp){
        String campName;
        CampManager campManager = new CampManager();

        //find name of camp
        ArrayList<Camp> campList = campManager.getCampList();
        campName = campList.get(indexOfCamp).getCampName();

        return campName;
    }








}
