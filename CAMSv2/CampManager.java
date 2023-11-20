package CAMSv2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Properties;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.List;


// to become Singleton
public class CampManager {
    //attributes
    private static ArrayList<Camp> campList = new ArrayList<Camp>();

    //method

    public static Enquiries setUpStudentEnquiries(String name) {
        Enquiries studentEnquiries = new Enquiries(name);
        ArrayList<Question> studentQuestions = studentEnquiries.getQuestions();
        for (Camp camp : campList) {
            for (Enquiries enquiries : camp.getEnquiries()) {
                // System.out.println("Enquirer: " + enquiries.getEnqurier());
                // System.out.println("Equals? " + name);
                if (enquiries.getEnqurier().equals(name)) {
                    ArrayList<Question> questions = enquiries.getQuestions();
                    // for (Question question : questions) {
                    //     System.out.println("question: " + question.getQuestion());
                    // }
                    studentQuestions.addAll(questions);
                }
            }
        }
        // System.out.println("StudentQuestions: " + studentQuestions.size());
        // for (Question question : studentQuestions) {
        //     System.out.println("questioninStudent: " + question.getQuestion());            
        // }
        return studentEnquiries;
    }

    public static ArrayList<Camp> setUpStudentRegisteredCamps(String name) {
        System.out.println("Passed Name: " + name);
        ArrayList<Camp> registeredCamps = new ArrayList<Camp>();
        for (Camp camp : campList) {
            for (Student student : camp.getStudentList()) {
                System.out.println("Name to check: " + student.getName());
                if (student.getName().equals(name)) {
                    System.out.println("Camp is registered");
                    registeredCamps.add(camp);
                }
            }
        }
        return registeredCamps;
    }
    
    public static void createCamp(String staffName){
        //print and scan logic is here
        // call constructor from camp, parameters are all the 8 inputs
      //Camp SpecificCamp = new Camp(8 parameters);
      //campList.add(SpecificCamp);
        Scanner sc = new Scanner(System.in);

        String errorMessage = "Camp with this name already exists";
        String campName;
        LocalDate[] Dates;            //NEWLY ADDED
        LocalDateTime registrationClosingDate;
        String userGroup;
        UserGroup validUserGroup;
        String location;
        int totalSlots;
        String description;

        System.out.println("Enter camp name: ");
        campName = sc.nextLine();

        //check if camp already exists
        if(getCamp(campName) != null){
            System.out.println(errorMessage);
            return;
        }

        System.out.println("Enter camp duration(days)");
        int numOfDays = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the starting date (YYYY-MM-DD):");
        String userInput = sc.nextLine();
        Dates = new LocalDate[numOfDays];
        LocalDate currentDate = LocalDate.parse(userInput);
        for (int i = 0; i < numOfDays; i++) {
            Dates[i] = currentDate;
            currentDate = currentDate.plusDays(1); // Move to the next day
        }


        System.out.println("Enter registration closing date");
        String userInput2 = userInput;
        userInput2 += " 00:00";

        LocalDateTime currentDateTime = LocalDateTime.now();
        //Ensures that the registration closing date is after the local clock and before start of camp
        do{
            userInput = sc.nextLine();
            userInput += " 23:59";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            registrationClosingDate = LocalDateTime.parse(userInput, formatter);
            LocalDateTime startDate = LocalDateTime.parse(userInput2, formatter);
            currentDateTime = LocalDateTime.now();
            if (registrationClosingDate.isAfter(currentDateTime) && registrationClosingDate.isBefore(startDate)) {
                System.out.println("Registration closing set as: ");
                break;
            } else {
                System.out.println("Input date must be in the future and be before the start of camp.");
            }
        }while(true);

        System.out.println("Enter user group this camp will be available to (IN CAPS)");
        userGroup = sc.nextLine();
        userGroup.toUpperCase();
        //check if its a valid userGroup
        validUserGroup = checkUserGroupExist(userGroup);

        //if its not valid user group, break
        if(validUserGroup == null){
            System.out.println("INVALID USER GROUP, EXITING");
            return;
        }

        System.out.println("Enter camp location");
        location = sc.nextLine();

        System.out.println("Enter total number of slots");
        totalSlots = sc.nextInt();

        System.out.println("Enter camp description");
        sc.nextLine();
        description = sc.nextLine();

        //sc.close();


        Camp newCamp = new Camp(campName,Dates,registrationClosingDate,validUserGroup,location,totalSlots,description,staffName);

        addCamp(newCamp);

        System.out.println(campName + " camp created");

    }

    public static UserGroup checkUserGroupExist(String userGroup){
        //returns null if user group doesnt exist
        // returns type of usergroup if it does exist
        for (UserGroup usergroups : UserGroup.values()) {
            if(userGroup.equals(usergroups.toString())){
                return usergroups;
            }
        }
        return null;
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
                            System.out.println("Enter start date (YYYY-MM-DD):");
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

                        case 3:  
                            updatedInfo += " 23:59";
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            LocalDateTime registrationClosingDate = LocalDateTime.parse(updatedInfo, formatter);
                            currentCamp.setRegistrationClosingDate(registrationClosingDate);
                            break;

                        case 4: currentCamp.setUserGroup(updatedInfo);
                            break;

                        case 5:  currentCamp.setLocation(updatedInfo);
                            break;

                        case 6:
                            // check with studentList
                            int length = currentCamp.getStudentList().size();
                            int input = Integer.parseInt(updatedInfo);
                            if (input < length) {
                                System.out.println("Value too low");
                                
                            }else {
                                currentCamp.setTotalSlots(input);                                
                            }
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
        System.out.println("Set visibility to on or off?");
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

            if(curCamp.getStaffName().equals(staffName)){
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
        HashSet<Student> studentList;
        //CampManager campManager = new CampManager();
        Scanner sc = new Scanner(System.in);
        int choice;

        String campName;
        //Camp camp = new Camp();

        //the 8 details apart from StudentName and Role
        UserGroup validUserGroup;
        String userGroup;
        String location;
        String description;
        Date[] Dates;
        String registrationClosingDate;
        int totalSlots;
        HashSet<CampCommitteeMember> campCommitteeSlots;
        HashSet<Student> filteredStudentList;

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
                     filteredStudentList = CampManager.filterStudentsByRole(camp, studentList, choice--);
                }
                else{
                    filteredStudentList = studentList;
                }

                LocalDate[] campDates = camp.getDates();
                registrationClosingDate = camp.getRegistrationClosingDate().toString();
                validUserGroup = camp.getUserGroup();
                userGroup = validUserGroup.toString();
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
    private static HashSet<Student> filterStudentsByRole(Camp camp, HashSet<Student>students, int choice){
        // choice 1 is attendee
        // choice 2 is campCommitteeMember
        HashSet<Student> filteredList = new HashSet<>();
        switch (choice) {
            case 1:
                for(Student student : students){
                    if(student.role == Role.STUDENT){ //yuhao pls implement this
                        filteredList.add(student);
                    }
                }                
                break;

            case 2:
                for(Student student : students){
                    if(student.role == Role.CAMP_COMMITTEE_MEMBER){ //yuhao pls implement this
                        filteredList.add(student);
                    }
                }            
                break;
        
            default:
                break;
        }
        return filteredList;

    }

    //method to generate CSV format



    public static void generateCSV(HashSet<Student>filteredStudentList, LocalDate[] campDates,String registrationClosingDate,String userGroup, String location, int totalSlots, String description, Camp camp){
        String projectDirectory = System.getProperty("user.dir");
        String filename = projectDirectory + "\\CAMSv2\\Data CSV\\" + camp.getCampName() + ".csv";

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filename, false))) {
            printWriter.println("Student Name, Student Role");

            for (Student student : filteredStudentList) {
                printWriter.println(student.getName() + "," + student.getRole().toString());
            }

            printWriter.println("Camp Dates");
            // Write dates into csv
            // Assuming dates is a list of dates
            /*for (Date date : campDates) {
                printWriter.println(date.toString());
            }*/

            printWriter.println("Registration Deadline,User Group,Location of Camp,Camp Slots,Camp Description");
            printWriter.println(registrationClosingDate + "," + userGroup + "," + location + "," + totalSlots + "," + description);

            System.out.println("CSV file created successfully: " + filename);
        } catch (IOException e) {
            System.out.println("CSV file not found");
        }

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

    public static ArrayList<Camp> getCampListByFacultyAndVisibility(UserGroup faculty) {
        ArrayList<Camp> filteredCamps = new ArrayList<Camp>();
        for (Camp camp : campList) {
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

