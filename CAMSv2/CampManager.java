package CAMSv2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;


public class CampManager {
    //attributes
    private static ArrayList<Camp> campList;

    //method
    public void createCamp(String name){
        //print and scan logic is here
        // call constructor from camp, parameters are all the 8 inputs
      //Camp SpecificCamp = new Camp(8 parameters);
      //campList.add(SpecificCamp);
    }

    public static Camp getCamp(String campName){
        //for each camp in campList
        ArrayList<Camp> campList = new ArrayList<Camp>();
        campList = getCampList();
        Camp camp = new Camp();

        for(int i=0; i<campList.size();i++){
            camp = campList.get(i);
            if(campName.equals(camp.getCampName())){
                return camp;
            }//if
        }//for
       
        return null;//if it doesnt find a camp
    }

    public boolean getStaffinCharge(String campName, String Staffname){
        //for each camp in campList
        //if staffname == campList[i].info.staffincharge && campName == campList[i].info.name return true
        String staffIC;
        Camp camp = new Camp();
        camp = getCamp(campName);
        staffIC = camp.getStaffName();

        return staffIC.equals(Staffname);
        }


    public void editCamp(String campName,String staffName){
        int choice;
        Scanner sc = new Scanner(System.in);
        Camp currentCamp = getCamp(campName);
        String errorMessage = "Staff is not in charge";
        String exitMessage = "Exitting edit";
        String updatedInfo;

        
        if(getStaffinCharge(campName, staffName)) {
            
            currentCamp.printCampInfoTable();
            System.out.println("What would you like to edit?");
            choice = sc.nextInt();
            while(choice>0 && choice<8){
                //switch case that calls and takes in specific string needed for each case
                //call the set method in camp.info.setVariable()
                System.out.println("Enter updated information");
                updatedInfo = sc.nextLine();

                switch(choice){
                    
                        case 1: currentCamp.setCampName(updatedInfo);
                            break;

                        case 2: currentCamp.setDates(updatedInfo);
                            break;
                    
                        case 3:  currentCamp.setRegistrationClosingDate(Integer.parseInt(updatedInfo));
                            break;
                    
                        case 4: currentCamp.setUserGroup(updatedInfo);
                            break;
                    
                        case 5:  currentCamp.setLocation(updatedInfo);
                            break;

                        case 6: currentCamp.setTotalSlots(Integer.parseInt(updatedInfo));
                            break;
                    
                        case 7: currentCamp.setDescription(updatedInfo);
                            break;
                }//end switch
            currentCamp.printCampInfoTable();
            System.out.println("Enter 8 to exit, or enter next attribute to edit");
            choice = sc.nextInt();

            }//end while
            sc.close();
            
            System.out.println(exitMessage);
        }//end if
        else{
            System.out.println(errorMessage);
        }//end else
        return;
    }//end edit camp

    public void deleteCamp(String campName){
        Camp camp = this.getCamp(campName);
        campList.remove(camp);
    }

    public void changeVisibility(String campName){
        //ask the user whether true/false
        String settings;
        boolean choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Set visibility of" + campName + "on or off?");
        settings = sc.nextLine();
        if(settings.equals("on"))
            choice = true;
        else
            choice = false;

        Camp camp = this.getCamp(campName);
        camp.setVisibility(choice);
        sc.close();
    }

    public void StaffCampListGenerator(String staffName){
        //for each camp in campList
        int numOfCamps;
        int index=1;
        Camp curCamp = new Camp();
        ArrayList<Camp> campList = new ArrayList<Camp>();
        numOfCamps = campList.size();

        for(int i=0;i<numOfCamps;i++){
            curCamp = campList.get(i);

            if(curCamp.getStaffName() == staffName){
                System.out.println("Your List:");
                System.out.println(index + ". " + curCamp.getCampName());
                index++;
                }  
        }
        return;
        
    }

    public static ArrayList<Camp> getCampList(){
        // Should also check for Visiblity and UserGroup
        return campList;
    }


    public void generateReport(String staffName){
        ArrayList<Camp> campList = this.getCampList();
        ArrayList<Student> studentList;
        CampManager campManager = new CampManager();

        String campName;
        //Camp camp = new Camp();

        //the 8 details apart from StudentName and Role

        String userGroup;
        String location;
        String description;
        String dates;
        int registrationClosingDate;
        int totalSlots;
        ArrayList<Student> campCommitteeSlots;

        for(int i=0;i<campList.size();i++){
            campName = campList.get(i).getCampName();
            //String name of camp obj

            if(this.getStaffinCharge(campName, staffName)){
                Camp camp = campManager.getCamp(campName); //camp obj itself            
                studentList = camp.getStudentList();
                dates = camp.getDates();
                registrationClosingDate = camp.getRegistrationClosingDate();
                userGroup = camp.getUserGroup();
                location = camp.getLocation();
                totalSlots = camp.getTotalSlots();
                description = camp.getDescription();
                campCommitteeSlots = camp.getCampCommitteeSlots();
                 
                for(int j = 0 ; j < 10 ; j++){
                    //go through campCommitteeSlots array
                    //write each index to csv file.
                }
        
                for(int j=0;j<studentList.size();j++){
                    Student student = studentList.get(j);
                    // System.out.println(student.getName() + student.getStudentRole()); 
                    //getstudentrole is in student class
                }//send everything to a csv file.
               


            }
        }//end outer for


    }//end generateReport

    public void addStudent(String studentName, String campName, String role){
        Camp camp = this.getCamp(campName);
        camp.addStudent(studentName, role);
    }



}

