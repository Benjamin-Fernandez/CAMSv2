package CAMSv2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.List;


public class CampManager {
    //attributes
    private static ArrayList<Camp> campList = new ArrayList<Camp>();

    //method
    public static void createCamp(String staffName){
        //print and scan logic is here
        // call constructor from camp, parameters are all the 8 inputs
      //Camp SpecificCamp = new Camp(8 parameters);
      //campList.add(SpecificCamp);
        Scanner sc = new Scanner(System.in);

        String errorMessage = "Camp with this name already exists";
        String campName;
        Date[] Dates;            //NEWLY ADDED
        String registrationClosingDate;
        String userGroup;
        String location;
        int totalSlots;
        String description;

        System.out.println("Enter camp name: ");
        campName = sc.nextLine();

        //check if camp already exists
        if(CampManager.getCamp(campName) != null){
            System.out.println(errorMessage);
            //sc.close();
            return;
        }

        System.out.println("Enter camp duration(days)");
        int numOfDays = sc.nextInt();
        System.out.println("Enter " + numOfDays + " consecutive dates (YYYY-MM-DD):");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


        Date[] dates = new Date[numOfDays];
        for (int i = 0; i < numOfDays; i++) {
            while (true) {
                try {
                    System.out.print("Date " + (i + 1) + ": ");
                    sc.nextLine();
                    String userInput = sc.nextLine();
                    dates[i] = dateFormat.parse(userInput);
                    break; // Break the loop if parsing is successful
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
                }
            }
        }

        System.out.println("Enter registration closing date");
        registrationClosingDate = sc.nextLine();
        registrationClosingDate += " 23:59";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime userDateTime = LocalDateTime.parse(registrationClosingDate, formatter);
        
        LocalDateTime currentDateTime = LocalDateTime.now();
        //Ensures that the registration closing date is after the local clock
        if (userDateTime.isAfter(currentDateTime)) {                                    
            System.out.println("Input date is in the future: " + currentDateTime);
        } else {
            System.out.println("Input date must be in the future.");
        }

        System.out.println("Enter user group this camp will be available to");
        userGroup = sc.nextLine();

        System.out.println("Enter camp location");
        location = sc.nextLine();

        System.out.println("Enter total number of slots");
        totalSlots = sc.nextInt();

        System.out.println("Enter camp description");
        sc.nextLine();
        description = sc.nextLine();
        
        //sc.close();
        
        
        Camp newCamp = new Camp(campName,dates,registrationClosingDate,userGroup,location,totalSlots,description,staffName);

        addCamp(newCamp);
        
        System.out.println(campName + " camp created");
        
        return;

    }

    public static void addCamp(Camp camp){
        campList.add(camp);
    }

    public static Camp getCamp(String campName){
        //for each camp in campList
        ArrayList<Camp> campList = new ArrayList<Camp>();
        campList = CampManager.getCampList();
        Camp camp = new Camp();

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

    public static boolean getStaffinCharge(String campName, String Staffname){
        //for each camp in campList
        //if staffname == campList[i].info.staffincharge && campName == campList[i].info.name return true
        String staffIC;
        Camp camp = new Camp();
        camp = CampManager.getCamp(campName);
        staffIC = camp.getStaffName();

        return staffIC.equals(Staffname);
        }


    public static void editCamp(String campName,String staffName){
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
                sc.nextLine();
                updatedInfo = sc.nextLine();

                switch(choice){
                    
                        case 1: currentCamp.setCampName(updatedInfo);
                            break;

                        case 2: 
                            System.out.println("Enter camp duration(days)");
                            int numOfDays = sc.nextInt();
                            System.out.println("Enter " + numOfDays + " consecutive dates (YYYY-MM-DD):");
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
                            Date[] dates = new Date[numOfDays];
                            for (int i = 0; i < numOfDays; i++) {
                                while (true) {
                                    try {
                                        System.out.print("Date " + (i + 1) + ": ");
                                        String userInput = sc.nextLine();
                                         dates[i] = dateFormat.parse(userInput);
                                        break; // Break the loop if parsing is successful
                                    } catch (ParseException e) {
                                    System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
                                    }
                                }
                            }   
                            break;
                    
                        case 3:  currentCamp.setRegistrationClosingDate(updatedInfo);
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
            //sc.close();
            
            System.out.println(exitMessage);
        }//end if
        else{
            System.out.println(errorMessage);
        }//end else
        return;
    }//end edit camp

    public static void deleteCamp(String campName){
        Camp camp = CampManager.getCamp(campName);
        campList.remove(camp);
    }

    public static void changeVisibility(String campName){
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

        Camp camp = CampManager.getCamp(campName);
        camp.setVisibility(choice);
        //sc.close();
    }

    public static ArrayList<Camp> StaffCampListGenerator(String staffName){
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

            if(curCamp.getStaffName() == staffName){
                System.out.println(index + ". " + curCamp.getCampName());
                index++;
                staffCampList.add(curCamp);
                }  
            }
        }

        return staffCampList;
        
    }

    public static ArrayList<Camp> getCampList(){
        // Should also check for Visiblity and UserGroup
        return campList;
    }


    public static void generateReport(String staffName){
        ArrayList<Camp> campList = CampManager.getCampList();
        ArrayList<Student> studentList;
        //CampManager campManager = new CampManager();
        Scanner sc = new Scanner(System.in);
        int choice;

        String campName;
        //Camp camp = new Camp();

        //the 8 details apart from StudentName and Role

        String userGroup;
        String location;
        String description;
        Date[] Dates;
        String registrationClosingDate;
        int totalSlots;
        ArrayList<Student> campCommitteeSlots;
        ArrayList<Student> filteredStudentList;

        for(int i=0;i<campList.size();i++){
            campName = campList.get(i).getCampName();
            //String name of camp obj

            if(CampManager.getStaffinCharge(campName, staffName)){
                Camp camp = CampManager.getCamp(campName); //camp obj itself

                studentList = camp.getStudentList();
                System.out.println("Filter by 1) Attendee");
                System.out.println("Filter by 2) Camp Committee Member");
                System.out.println("3) Display all members");
                choice = sc.nextInt();
                if(choice == 1 || choice == 2 ){
                     filteredStudentList = CampManager.filterStudentsByRole(camp,studentList, choice--);
                }
                else{
                    filteredStudentList = studentList;
                }
                
                Date[] campDates = camp.getDates();
                registrationClosingDate = camp.getRegistrationClosingDate();
                userGroup = camp.getUserGroup();
                location = camp.getLocation();
                totalSlots = camp.getTotalSlots();
                description = camp.getDescription();
                campCommitteeSlots = camp.getCampCommitteeSlots();

                CampManager.generateCSV(filteredStudentList,campDates,registrationClosingDate,userGroup,location,totalSlots,description ,camp);
                


                 
            //     for(int j = 0 ; j < 10 ; j++){
            //         //go through campCommitteeSlots array
            //         //write each index to csv file.
            //     }
        
            //     for(int j=0;j<studentList.size();j++){
            //         Student student = studentList.get(j);
            //         // System.out.println(student.getName() + student.getStudentRole()); 
            //         //getstudentrole is in student class
            //     }//send everything to a csv file.
               


            // }
        }//end outer for
    }


    }//end generateReport


    //method to filter students based on their role
    private static ArrayList<Student> filterStudentsByRole(Camp camp,ArrayList<Student>students, int choice){
        ArrayList<Student> filteredList = new ArrayList<>();
        Role[] roles = Role.values();
        for(Student student : students){
            if(student.getCampRole(camp) == roles[choice]){ //yuhao pls implement this 
                filteredList.add(student);
            }
        }
        return filteredList;


    }

    //method to generate CSV format

    public static void generateCSV(ArrayList<Student>filteredStudentList, Date[] campDates,String registrationClosingDate,String userGroup, String location, int totalSlots, String description, Camp camp){
        String filename = "camp_report.csv";
        StringBuilder csvContent = new StringBuilder();
        String totalSlot = Integer.toString(totalSlots);

        csvContent.append("Student Name , Student Role\n");

        for(Student student : filteredStudentList){
            csvContent.append(String.format("%s , %s", student.getName() , student.getCampRole(camp)));

        }

        csvContent.append("Camp Dates");
    

        //for(int i = 0 ; i<) write dates into csv

        csvContent.append("Registration Deadline, User Group, Location of Camp, Camp Slots, Camp Description\n");

        csvContent.append(String.format("%s, %s ,%s, %s ,%s",registrationClosingDate,userGroup,location,totalSlot,description));


        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename,true))){
            writer.write(csvContent.toString());
            System.out.println("CSV file created successfully" + filename);

        } catch (IOException e) {
            System.out.println("CSV file not found");
        }

    }

    public static void addStudent(String studentName, String campName, String role){
        Camp camp = CampManager.getCamp(campName);
       // camp.addStudent(studentName, role);
    }

    public static boolean isValidDate(String input) {
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

}

