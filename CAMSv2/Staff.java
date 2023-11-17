package CAMSv2;

import java.util.*;

public class Staff extends User {
    public Staff(String emailID, String password, String faculty, String name, Role role){
        super(emailID,password,faculty,name,role);
    }


    public void createCamp(){
        CampManager.createCamp(this.name);
    }

    public void editCamp() {
        String campName;
        int index;
        Scanner sc = new Scanner(System.in);
  
        this.viewCamp(); //this prints a table of camp choices

        System.out.println("Enter the index of the camp to edit");
        index = sc.nextInt();
        campName = getCampName(--index); //index is one larger than actual index
        
        // calls campManager
        CampManager.editCamp(campName,this.name);
        //sc.close();
        
    }

    public void deleteCamp() {
        String campName;
        int index;
        Scanner sc = new Scanner(System.in);
  
        this.viewCamp();
        //take in integer of which camp they want to view, i--; and get its camp name from its index

        System.out.println("Enter the index of the camp to delete");
        index = sc.nextInt();
        campName = this.getCampName(--index);
        
        //delete calls campManager
        CampManager.deleteCamp(campName);
        //sc.close();
    }

    public void changeVisibility(){
        String campName;
        int index;
        Scanner sc = new Scanner(System.in);

        this.viewCamp();
        //take in integer of which camp they wnat to view, i--; and get its camp name from its index

        System.out.println("Enter the index of the camp to change visibility");
        index = sc.nextInt();
        campName = this.getCampName(--index);
        
        //visibility calls campManager
        CampManager.changeVisibility(campName);
        //sc.close();
        }

    public void viewCamp(){
        //any staff can view all camps
        //all this method does is print out a list of camps
        ArrayList<Camp> campList = CampManager.getCampList();
        int numOfCamps = campList.size();
        String campName;

        for(int i=0;i<numOfCamps;i++){
            Camp camp = campList.get(i);
            campName = camp.getCampName();
            System.out.println(i+1 + ". "+ campName);
        }//end for

    }//end viewCamp()

    public ArrayList<Camp> myList(){
        return CampManager.StaffCampListGenerator(this.name);
    }

    public void viewEnquiries(){
        //ask which camp name they want to view
        int campIndex;
        Scanner sc = new Scanner(System.in);
        String campName;
        Camp curCamp;
        ArrayList<Camp> staffCampList = new ArrayList<>();
        EnquiryManager enqManager = new EnquiryManager();


        System.out.println("Enter the index of the camp's enquiry you want to view ");
        
        staffCampList = myList();
        campIndex = sc.nextInt();
        curCamp = staffCampList.get(--campIndex);
        campName = curCamp.getCampName();
        enqManager.viewEnquiryForStaff(campName, this.name);
        //sc.close();
    }

    public void replyEnquiries(){
        EnquiryManager enqManager = new EnquiryManager();
        Scanner sc = new Scanner(System.in);
        String campName;
        Camp curCamp;
        int campIndex;
        ArrayList<Camp> staffCampList = new ArrayList<>();


        System.out.println("Enter index of camp's enquries you want to reply to");

        staffCampList = myList();
        campIndex = sc.nextInt();
        curCamp = staffCampList.get(--campIndex);
        campName = curCamp.getCampName();
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
        Suggestion suggestion = suggManager.approveAdvice(campName, this.name);
        //sc.close();
        addPointsForApprovedSuggestions(suggestion.getStudent());

    }

    public void generateReport(){

        CampManager.generateReport(this.name);
    }


    public String getCampName(int indexOfCamp){
        String campName;

        //find name of camp
        ArrayList<Camp> campList = CampManager.getCampList();
        campName = campList.get(indexOfCamp).getCampName();

        return campName;
    }

    public void addPointsForApprovedSuggestions(String campCommitteeMemberName){
        for(int i = 0; i< CampCommitteeDataBase.getCampCommitteeMembersList().size(); i++){
            CampCommitteeMember campCommitteeMember = CampCommitteeDataBase.getCampCommitteeMembersList().get(i);
            if(campCommitteeMember.getName().equals(campCommitteeMemberName)){
                // campCommitteeMember.addPointsForSuggestions();
            }

        }

    }



    public void staffInterface(){
        //print a table of staff methods; e.g.
        //1.viewCamp(); 2.editcamp
        int choice;
        Scanner sc = new Scanner(System.in);
        do{
        System.out.println();
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
        System.out.println("14. Exit");
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
                super.changePassword();
                break;

            default:
                break;

        }// end switch
    }while(choice>=1 && choice <=13);

    }// end staff interface

}
