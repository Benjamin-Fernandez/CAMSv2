package CAMSv2;

import java.util.*;

public class Staff extends User {
    public Staff(String emailID, String password, String faculty, String name, Role role){
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
        //sc.close();
        
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
        //sc.close();
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
        //sc.close();
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
        System.out.println("Enter the camp name of the camps enquiry you want to view ");
        myList();
        campName = sc.nextLine();
        EnquiryManager enqManager = new EnquiryManager();
        enqManager.viewEnquiryForStaff(campName, this.name);
        //sc.close();
    }

    public void replyEnquiries(){
        EnquiryManager enqManager = new EnquiryManager();
        Scanner sc = new Scanner(System.in);
        String campName;
        System.out.println("Enter camp name of enquries you want to reply to");
        myList();
        campName = sc.nextLine();
        enqManager.replyEnquiryFromStaff(campName, this.name);
        //sc.close();

    }

    public void viewSuggestion(){
         //ask which camp name they want to view
        Scanner sc = new Scanner(System.in);
        String campName;
        System.out.println("Enter camp name of suggestions you want to view ");
        myList();
        campName = sc.nextLine();
        SuggestionManager suggManager = new SuggestionManager();
        suggManager.viewSuggestionForStaff(campName, this.name);
        //sc.close();
    }

    public void approveAdvice(){
        //ask which camp name they want to approve;
        Scanner sc = new Scanner(System.in);
        String campName;
        System.out.println("which camp's suggestion would you want to approve? ");
        campName = sc.nextLine();
        SuggestionManager suggManager = new SuggestionManager();
        suggManager.approveAdvice(campName, this.name);
        //sc.close();

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

    public void staffInterface(){
        //print a table of staff methods; e.g.
        //1.viewCamp(); 2.editcamp
        int choice;
        Scanner sc = new Scanner(System.in);
        do{
        System.out.println("Select which action you would like to take");


        System.out.println("1. Create a camp");
        System.out.println("2. Edit one of my camp");
        System.out.println("3. Delete one of my camp");
        System.out.println("4. Change Visibilty of a camp");
        System.out.println("5. View all camps");
        System.out.println("6. View my camps"); // is this needed?purpose is to edit camp?
        System.out.println("7. View enquiries of my camp");
        System.out.println("8. Reply enquiries of my camp");
        System.out.println("9. View suggestions of my camp");
        System.out.println("10. Approve suggestion of my camp");
        System.out.println("11. Generate attendance report");
        System.out.println("12. Generate performance report");
        System.out.println("13. Change Password");
        //choice is valid from 1-13
        choice = sc.nextInt();

        switch(choice){
            case 1:
                createCamp();
                break;
            
            case 2:
                editCamp();
                break;

            case 3:
                deleteCamp();
                break;

            case 4:
                changeVisibility();
                break;

            case 5:
                viewCamp();
                break;

            case 6:
                myList();
                break;
            
            case 7:
                viewEnquiries();
                break;

            case 8:
                replyEnquiries();
                break;

            case 9:
                viewSuggestion();
                break;

            case 10:
                approveAdvice();
                break;
            
            case 11:
                //attendance report
                generateReport();
                break;

            case 12:
                //performance report of cmamp commitee member
                break;

            case 13:
                //change password
                break;

            default:
                break;

        }// end switch
    }while(choice>=1 && choice <=13);

    }// end staff interface

}
