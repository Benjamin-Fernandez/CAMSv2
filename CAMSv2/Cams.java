package CAMSv2;

import java.net.UnknownServiceException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cams {

    public static void main(String[] args) {
        // Define the format for displaying date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Create a separate thread for updating and displaying date and time
        Thread dateTimeThread = new Thread(() -> {
            while (true) {
                // Get the current date and time
                LocalDateTime now = LocalDateTime.now();

                // Format and display the date and time
                String formattedDateTime = now.format(formatter);
                System.out.print("\r" + formattedDateTime); // Use carriage return for updating in the same line

                // Wait for 1 second before updating again (adjust the sleep duration as needed)
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // Start the thread
        dateTimeThread.start();

        Scanner scanner = new Scanner(System.in);

        // Get the project's base directory
        String projectDirectory = System.getProperty("user.dir");
        //System.out.println(projectDirectory);

        // Construct the absolute paths to the CSV files
        String studentCSVPath = projectDirectory + "\\CAMSv2\\Data CSV\\Student_List.csv";
        String staffCSVPath = projectDirectory + "\\CAMSv2\\Data CSV\\Staff_List.csv";


        // Initialize user accounts from CSV files
        UserDataBase dataBase = new UserDataBase(studentCSVPath, staffCSVPath);

        ArrayList<Student> studentUsers = UserDataBase.getStudents();
        ArrayList<Staff> staffUsers = UserDataBase.getStaffs();
        // Have two ArrayLists: studentUsers and staffUsers,
        // containing Student/Staff objects with name, password, email, and faculty.

        /*System.out.println("Student Data:");
        for (int i = 0; i < studentUsers.size(); i++) {
            User student = studentUsers.get(i);
            System.out.println("Student Name: " + student.getName());
            System.out.println("Student Email: " + student.getEmailID());
            System.out.println("Student Faculty: " + student.getFaculty());
            System.out.println("Student Password: " + student.getPassword());
        }*/
        int choice;

        do {
            System.out.println("Welcome to CAMS Menu:\n1. Log in\n2. Change password\n3. Exit");
            choice = scanner.nextInt();
            if(choice == 3){break;}
            // scanner.nextLine(); // Consume the newline character

            System.out.print("Enter your Email: ");
            String email = scanner.nextLine();
            String emailID = email.split("@")[0];

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            System.out.print("Enter your role: ");
            String role = scanner.nextLine();



            switch (choice) {
                case (1):
                    // Create a Login object for handling logins
                    // LoginService login = new LoginService(studentUsers, staffUsers);
                    // login.performLogin(emailID, password);
                    // break;
                    User user = Authenticator.check(emailID,password,role);
                    if(user == null){
                        System.out.println("Wrong credentials");
                        break;
                    }
                    if(role == "staff"){
                        Staff staff = (Staff) user;
                        staff.staffInterface();
                    }
                    else{
                        Student student = (Student) user;
                        student.studentInterface();
                    }
                    break;

                default: break;


            }
        }while (choice == 1|| choice == 2);


    }
}
