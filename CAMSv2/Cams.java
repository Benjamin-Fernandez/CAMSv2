package CAMSv2;

import java.net.UnknownServiceException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Cams {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        // Get the project's base directory
        String projectDirectory = System.getProperty("user.dir");
        //System.out.println(projectDirectory);

        // Construct the relative paths to the CSV files
        String studentCSVPath = projectDirectory + "\\CAMSv2\\Data CSV\\Student_List.csv ";
        String staffCSVPath = projectDirectory + "\\CAMSv2\\Data CSV\\Staff_List.csv ";


        // Initialize user accounts from CSV files
        UserDataBase studentDB = new UserDataBase(studentCSVPath, staffCSVPath);

        ArrayList<Student> studentUsers = UserDataBase.getStudents();
        ArrayList<Staff> staffUsers = UserDataBase.getStaffs();
        // Have two ArrayLists: studentUsers and staffUsers,
        // containing User objects with name, password, email, and faculty.

        System.out.println("Student Data:");
        for (int i = 0; i < studentUsers.size(); i++) {
            User staff = studentUsers.get(i);
            System.out.println("Student Name: " + staff.getName());
            System.out.println("Student Email: " + staff.getEmailID());
            System.out.println("Student Faculty: " + staff.getFaculty());
            System.out.println("Student Password: " + staff.getPassword());
        }
        /*int choice;

        do {
            System.out.println("Welcome to CAMS Menu:\n1. Log in\n2. Change password\n3. Exit");
            choice = scanner.nextInt();
            if(choice == 3){break;}
            // scanner.nextLine(); // Consume the newline character

            // System.out.print("Enter your Email: ");
            // String email = scanner.nextLine();
            // String emailID = email.split("@")[0];

            // System.out.print("Enter your password: ");
            // String password = scanner.nextLine();

            switch (choice) {
                case (1):
                    // Create a Login object for handling logins
                    // LoginService login = new LoginService(studentUsers, staffUsers);
                    // login.performLogin(emailID, password);
                    // break;

                    Student student = UserDataBase.getStudents().get(0);

                case (2):
                //     PasswordService changePassword = new PasswordService(studentUsers, staffUsers);
                //     System.out.print("Enter your new password: ");
                //     String newPassword = scanner.nextLine();
                //     changePassword.performChange(emailID, password,newPassword);
                //     break;

                // default: break;
                    Staff staff = UserDataBase.getStaffs().get(0);
                    System.out.println(staff.getName());

            }
        }while (choice == 1|| choice == 2);

       */
    }
}