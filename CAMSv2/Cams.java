package CAMSv2;

import java.util.Scanner;

public class Cams {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        setupDatabases();
        boolean running = true;
        while (running) {


            System.out.println("Welcome to CAMS Menu:\n1. Log in\n2. Exit");
            try {
                int choice = sc.nextInt();
                sc.nextLine();
                running = handleMainMenuSwitch(choice);                
            } catch (Exception e) {
                System.out.println("Enter a valid option!");
            }

        }
    }

    private static void setupDatabases() {
        StudentDataBase.getInstance().loadToCSV();
        StaffDataBase.getInstance().loadToCSV();
        CampCommitteeDataBase.getInstance().loadToCSV();  
    }

    private static boolean handleMainMenuSwitch(int choice) {
        switch (choice) {
            case 1:
                // login
                handleLogin();
                return true;
            case 2:
                // exit
                System.out.println("Exiting Program...");
                return false;
            default:
                return false;
        }
    }
    private static void handleLogin() {
        boolean running = true;
        while (running) {
            System.out.print("Enter your Email: ");
            String emailID = sc.nextLine();

            System.out.print("Enter your password: ");
            String password = sc.nextLine();

            System.out.println("Select your option: ");
            System.out.println("1. Staff");
            System.out.println("2. Student");
            System.out.println("3. Camp committee member");
            try {
                int role  = sc.nextInt();
                sc.nextLine();
                running = handleLoginSwitch(role, emailID, password);                
            } catch (Exception e) {
                System.out.println("Enter a valid option!");
            }

        }
    }

    private static boolean handleLoginSwitch(int role, String emailID, String password) {

        switch (role) {
            case 1:
                // invoke staff authentication
                StaffAuthenticator authStaff = new StaffAuthenticator();
                Staff staff = authStaff.authenticate(emailID, password);
                if (staff == null) {return false;}
                StaffController controllerStaff = new StaffController(staff, new StaffView());
                controllerStaff.startProgram();
                return false;
            case 2:
                // invoke student authentication
                StudentAuthenticator authStudent = new StudentAuthenticator();
                Student student = authStudent.authenticate(emailID, password);
                if (student == null) {return false;}
                StudentController controllerStudent = new StudentController(student, new StudentView());
                controllerStudent.startProgram();
                return false;
            case 3:
            // invoke CCM authentication
                CCMAuthenticator authCCM = new CCMAuthenticator();
                CampCommitteeMember ccm = authCCM.authenticate(emailID, password);
                if (ccm == null) {return false;}
                CampCommitteeMemberController controllerCCM = new CampCommitteeMemberController(ccm, new CampCommitteeMemberView());
                controllerCCM.startProgram();
                return false;
            default:
                System.out.println("You did not select one of the available Account Type");
                return true;
        }
    }
}
