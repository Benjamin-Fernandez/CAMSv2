package CAMSv2;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;



// to become Singleton
public class CampManager {
    //attributes
    private static CampManager instance;
    private ArrayList<Camp> campList = new ArrayList<Camp>();
    public Scanner sc = new Scanner(System.in);

    //method
    private CampManager() {};
    public static CampManager getInstance() {
        if (instance == null) {
            instance = new CampManager();
        }
        return instance;
    }

    private void enterCheckAndSetCampName(Camp camp) {

        boolean running = true;
        while (running) {
            String campName;
            System.out.println("Enter camp name: ");
            campName = sc.nextLine();
            //check if camp already exists
            if(getCamp(campName) != null){
                System.out.println("Camp with this name already exists");
                continue;
            }else if (campName.equals("")) {
                System.out.println("Camp name cannot be blank");
                continue;            
            } else {
                camp.setCampName(campName);   
                break;                     
            }
        }

    }
    
    public void createCamp(Staff staff){

        Camp camp = new Camp();
        camp.setStaff_in_charge(staff.getName());
        enterCheckAndSetCampName(camp);
        // System.out.println("Enter camp duration(days)");
        // int numOfDays = sc.nextInt();
        // sc.nextLine();
        // dates = new ArrayList<LocalDate>();
        // String userInput;
        // do{
        //     System.out.print("Enter the starting date(YYYY-MM-DD): ");
        //     userInput = sc.nextLine();
        //     LocalDate currentDate = LocalDate.now();
        //     LocalDate dateCounter = LocalDate.parse(userInput);
        //     if (dateCounter.isAfter(currentDate)) {
        //         for (int i = 0; i < numOfDays; i++) {
        //             dates.add(dateCounter);
        //             dateCounter = dateCounter.plusDays(1); // Move to the next day
        //         }
        //         System.out.println("Dates set!");
        //         break;
        //     } else {
        //         System.out.println("Input date must be in the future.");
        //     }
        // }while(true);
        enterSetCampDates(camp);
        // String userInput2 = userInput;
        // userInput2 += " 00:00";        
        // //Ensures that the registration closing date is after the local clock and before start of camp
        // do{
        //     System.out.println("Enter registration closing date(YYYY-MM-DD): ");
        //     LocalDateTime currentDateTime = LocalDateTime.now();
        //     userInput = sc.nextLine();
        //     userInput += " 23:59";
        //     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        //     registrationClosingDate = LocalDateTime.parse(userInput, formatter);
        //     LocalDateTime startDate = LocalDateTime.parse(userInput2, formatter);
        //     currentDateTime = LocalDateTime.now();
        //     if (registrationClosingDate.isAfter(currentDateTime) && registrationClosingDate.isBefore(startDate)) {
        //         break;
        //     } else {
        //         System.out.println("Input date must be in the future and be before the start of camp.");
        //     }
        // }while(true);
        enterSetRegistrationClosingDate(camp);
        enterSetUserGroup(camp);
        enterSetLocation(camp);
        enterSetCampSlots(camp);
        enterSetCampDescription(camp);
        addCamp(camp);
        staff.getCreatedCamps().add(camp);

        System.out.println(camp.getCampName() + " camp created");

    }

    public UserGroup checkUserGroupExist(String userGroup){
        //returns null if user group doesnt exist
        // returns type of usergroup if it does exist
        for (UserGroup usergroups : UserGroup.values()) {
            if(userGroup.equals(usergroups.toString())){
                return usergroups;
            }
        }
        return null;
    }

    public void addCamp(Camp camp){
        campList.add(camp);
    }

    public Camp getCamp(String campName){
        //for each camp in campList
        ArrayList<Camp> campList = getCampList();
        Camp camp;

        if(campList != null) {
            for (int i = 0; i < campList.size(); i++) {
                camp = campList.get(i);
                if (campName.equals(camp.getCampName())) {
                    return camp;
                }//if
            }//for
        }
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

    public void enterSetCampName(Camp camp) {
        System.out.println("Enter new camp name: ");
        String newCampName = sc.nextLine();
        camp.setCampName(newCampName);
    }

    public void enterSetCampDates(Camp camp) {
        boolean running = true;
        while(running) {
            System.out.println("Enter start date (YYYY-MM-DD):");
            String startDateString = sc.nextLine();
        
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                LocalDate startDate = LocalDate.parse(startDateString, formatter);
                if (!startDate.isAfter(LocalDate.now())) {
                    System.out.println("Input Date must be in the future");
                    continue;
                }

                System.out.println("Enter camp duration (days):");
                int numOfDays = sc.nextInt();
                sc.nextLine();     

                ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
                for (int i = 0; i < numOfDays; i++) {
                    dates.add(startDate.plusDays(i));
                }
            
                // Printing the dates to verify for debugging
                for (LocalDate date : dates) {
                    System.out.println(date.format(formatter));
                }

                camp.setDates(dates);
                break;
            } catch (Exception e) {
                System.out.println("Please follow the required format!");
                continue;
            }

        }

    }

    public void enterSetRegistrationClosingDate(Camp camp) {
        //Ensures that the registration closing date is after the local clock and before start of camp
        do{
            System.out.println("Enter registration closing date(YYYY-MM-DD): ");
            LocalDateTime currentDateTime = LocalDateTime.now();
            String userInput = sc.nextLine();
            userInput += " 23:59";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            try {
                LocalDateTime registrationClosingDate = LocalDateTime.parse(userInput, formatter);
                LocalDateTime startDate = camp.getDates().get(0).atStartOfDay();
                currentDateTime = LocalDateTime.now();
                if (registrationClosingDate.isAfter(currentDateTime) && registrationClosingDate.isBefore(startDate)) {
                    camp.setRegistrationClosingDate(registrationClosingDate);
                    break;
                } else {
                    System.out.println("Input date must be in the future and be before the start of camp.");
                }                
            } catch (Exception e) {
                System.out.println("Please follow the date format!");
                continue;
            }
        }while(true);
    }


    public void editCamp(String campName,String staffName){
        int choice;
        Camp currentCamp = getCamp(campName);
        String errorMessage = "Staff is not in charge";
        String exitMessage = "Exitting edit";

        if(getStaffinCharge(campName, staffName)) {
            boolean running = true;
            while(running){
                currentCamp.printCampInfoTable();
                System.out.println("Select an option to edit(Eg. 1): ");
                System.out.println("<--- Exit edit menu (type 111)");
                choice = sc.nextInt();
                sc.nextLine();
                switch(choice){

                        case 1: 
                            enterSetCampName(currentCamp);
                            break;

                            case 2:
                            enterSetCampDates(currentCamp);
                            break;

                        case 3:  
                            enterSetRegistrationClosingDate(currentCamp);
                            break;

                        case 4: enterSetUserGroup(currentCamp);
                            break;

                        case 5:  enterSetLocation(currentCamp);
                            break;

                        case 6:
                            enterSetCampSlots(currentCamp);
                            break;

                        case 7: 
                            enterSetCampDescription(currentCamp);
                            break;
                        case 8: 
                            changeVisibility(campName);
                            break;
                        case 111:
                            running = false;
                }//end switch
            }//end while

            System.out.println(exitMessage);
        }//end if
        else{
            System.out.println(errorMessage);
        }//end else
        return;
    }//end edit camp

    private void enterSetCampDescription(Camp currentCamp) {
        System.out.println("Enter new description: ");
        String description = sc.nextLine();
        currentCamp.setDescription(description);
        
    }
    private void enterSetCampSlots(Camp currentCamp) {
        boolean running = true;
        while (running) {
            System.out.println("Enter Camp Slots: ");
            String campSlots = sc.nextLine();
            int length = currentCamp.getStudentList().size();
            try {
                int input = Integer.parseInt(campSlots);
                if (input < length) {
                    System.out.println("Value too low");
                    continue;
                    
                }else {
                    currentCamp.setTotalSlots(input);
                    running = false;                          
                }           
            } catch (Exception e) {
                System.out.println("You did not enter an integer!");
                continue;
            }
                        
        }

    }
    

    private void enterSetLocation(Camp currentCamp) {
        boolean running = true;
        while (running) {
            System.out.println("Enter new location: ");
            String location = sc.nextLine();
            if (location.equals("")) {
                System.out.println("Location cannot be blank");
                continue;
            }
            currentCamp.setLocation(location);
            running = false;            
        }    
    }
    private void enterSetUserGroup(Camp camp) {
        boolean running = true;
        while(running) {
            System.out.println("Enter user group: ");

            String userGroup = sc.nextLine();
            userGroup = userGroup.toUpperCase();
            if (userGroup.equals("")) {
                System.out.println("User Group cannot be blank");
                continue;
            }
            //check if its a valid userGroup
            UserGroup validUserGroup = checkUserGroupExist(userGroup);

            //if its not valid user group, break
            if(validUserGroup == null){
                System.out.println("Invalid User Group!");
                continue;
            } else {
                camp.setUserGroup(userGroup);
                running = false;
            }            
        }
    }
    public void deleteCamp(Camp camp){
        campList.remove(camp);
    }

    public void changeVisibility(String campName){
        //ask the user whether true/false
        String settings;
        boolean choice;
        System.out.println("Set visibility to on or off?");
        settings = sc.nextLine();
        if(settings.equals("on"))
            choice = true;
        else
            choice = false;

        Camp camp = getCamp(campName);
        camp.setVisibility(choice);
        //sc.close();
    }

    public ArrayList<Camp> StaffCampListGenerator(String staffName){
        //hello ethan
        //this function prints staff camp list as well as return the array
        //for each camp in campList
        int numOfCamps;
        int index=1;
        Camp curCamp = new Camp();
        ArrayList<Camp> staffCampList = new ArrayList<>();
        //ArrayList<Camp> campList = new ArrayList<Camp>();
        numOfCamps = campList.size();
        if(numOfCamps==0){
            System.out.println("You have no camps");
        }

        else{
            System.out.println("Your List:");
            for(int i=0;i<numOfCamps;i++){
            curCamp = campList.get(i);

            if(curCamp.getStaffName().equals(staffName)){
                System.out.println(index + ". " + curCamp.getCampName());
                index++;
                staffCampList.add(curCamp);
                }
            }
        }

        return staffCampList;

    }

    public ArrayList<Camp> getCampList(){
        // Should also check for Visiblity and UserGroup
        return campList;
    }



    // public void generateReport(String staffName){
    //     ArrayList<Camp> campList = getCampList();
    //     HashSet<Student> studentList;
    //     //CampManager campManager = new CampManager();
    //     Scanner sc = new Scanner(System.in);
    //     int choice;

    //     String campName;
    //     //Camp camp = new Camp();

    //     //the 8 details apart from StudentName and Role
    //     UserGroup validUserGroup;
    //     String userGroup;
    //     String location;
    //     String description;
    //     Date[] Dates;
    //     String registrationClosingDate;
    //     int totalSlots;
    //     HashSet<CampCommitteeMember> campCommitteeSlots;
    //     HashSet<Student> filteredStudentList;

    //     for(int i=0;i<campList.size();i++){
    //         campName = campList.get(i).getCampName();
    //         //String name of camp obj

    //         if(getStaffinCharge(campName, staffName)){
    //             Camp camp = getCamp(campName); //camp obj itself

    //             studentList = camp.getStudentList();
    //             System.out.println("Filter by 1) Attendee");
    //             System.out.println("Filter by 2) Camp Committee Member");
    //             System.out.println("3) Display all members");
    //             choice = sc.nextInt();
    //             if(choice == 1 || choice == 2 ){
    //                  filteredStudentList = CampManager.getInstance().filterStudentsByRole(camp, studentList, choice--);
    //             }
    //             else{
    //                 filteredStudentList = studentList;
    //             }

    //             LocalDate[] campDates = camp.getDates();
    //             registrationClosingDate = camp.getRegistrationClosingDate().toString();
    //             validUserGroup = camp.getUserGroup();
    //             userGroup = validUserGroup.toString();
    //             location = camp.getLocation();
    //             totalSlots = camp.getTotalSlots();
    //             description = camp.getDescription();
    //             campCommitteeSlots = camp.getCampCommitteeSlots();

    //             CampManager.getInstance().generateCSV(filteredStudentList,campDates,registrationClosingDate,userGroup,location,totalSlots,description ,camp);

    //     }//end outer for
    // }




    // //method to filter students based on their role
    // private HashSet<Student> filterStudentsByRole(Camp camp, HashSet<Student>students, int choice){
    //     // choice 1 is attendee
    //     // choice 2 is campCommitteeMember
    //     HashSet<Student> filteredList = new HashSet<>();
    //     switch (choice) {
    //         case 1:
    //             for(Student student : students){
    //                 if(student.role == Role.STUDENT){ //yuhao pls implement this
    //                     filteredList.add(student);
    //                 }
    //             }                
    //             break;

    //         case 2:
    //             for(Student student : students){
    //                 if(student.role == Role.CAMP_COMMITTEE_MEMBER){ //yuhao pls implement this
    //                     filteredList.add(student);
    //                 }
    //             }            
    //             break;
        
    //         default:
    //             break;
    //     }
    //     return filteredList;

    // }

    //method to generate CSV format

    

    public boolean isValidDate(String input) {
        // Define a custom date format that you expect
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            // Try to parse the input as a LocalDate using the defined format
            LocalDate date = LocalDate.parse(input, dateFormatter);
            return true; // Input is a valid date
        } catch (DateTimeParseException e) {
            return false; // Input is not a valid date
        }
    }

    public ArrayList<Camp> getCampListByFacultyAndVisibility(UserGroup faculty) {
        ArrayList<Camp> filteredCamps = new ArrayList<Camp>();
        for (Camp camp : getCampList()) {
            UserGroup campUserGroup = camp.getUserGroup();

            if(camp.visibility){
                if(campUserGroup.equals(faculty) || campUserGroup.equals(UserGroup.NTU)){
                    filteredCamps.add(camp);
                }
            }
        }
        return filteredCamps;
    }
}

