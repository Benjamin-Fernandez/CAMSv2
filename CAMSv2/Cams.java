package CAMSv2;

import java.util.Scanner;

public class Cams {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the project's base directory
        String projectDirectory = System.getProperty("user.dir");

        // Paths to the CSV files
        String studentCSVPath = projectDirectory + "\\CAMSv2\\Data CSV\\Student_List.csv";
        String staffCSVPath = projectDirectory + "\\CAMSv2\\Data CSV\\Staff_List.csv";
        String campCommitteeMemberCSVPath = projectDirectory + "\\CAMSv2\\Data CSV\\CampCommitteeMember_List.csv";

        StudentDataBase studentDataBase = StudentDataBase.getInstance(studentCSVPath);
        StaffDataBase staffDataBase = StaffDataBase.getInstance(staffCSVPath);
        CampCommitteeDataBase campCommitteeDataBase = CampCommitteeDataBase.getInstance(campCommitteeMemberCSVPath);

        boolean running = true;

        while (running) {
            // Load CSV data at the start of each loop iteration
            studentDataBase.loadToCSV();
            staffDataBase.loadToCSV();
            campCommitteeDataBase.loadToCSV();

            int choice;
            int loginOption;

            System.out.println("Welcome to CAMS Menu:\n1. Log in\n2. Exit");
            choice = scanner.nextInt();
            if (choice == 2) {
                break;
            }

            System.out.print("Enter your Email: ");
            scanner.nextLine(); // Consume the newline character
            String emailID = scanner.nextLine();

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            System.out.println("Select your option: ");
            System.out.println("1. Staff");
            System.out.println("2. Student");
            System.out.println("3. Camp committee member");
            loginOption = scanner.nextInt();

            switch (choice) {
                case 1:
                    User user = Authenticator.check(emailID, password, loginOption);
                    if (user == null) {
                        System.out.println("Wrong credentials");
                        break;
                    }

                    System.out.println("Correct credentials");
                    if (loginOption == 1) {
                        Staff staff = (Staff) user;
                        staff.staffInterface();
                    } else if (loginOption == 2) {
                        Student student = (Student) user;
                        StudentController studentController = new StudentController(student);
                        studentController.startStudentProgram();
                    } else {
                        CampCommitteeMember campCommitteeMember = (CampCommitteeMember) user;
                        campCommitteeMember.committeeInterface();
                    }
                    break;

                default:
                    running = false;
                    break;
            }

            // Write changes to the CSV files if needed
            studentDataBase.writeToCSV();
            staffDataBase.writeToCSV();
            if(CampCommitteeDataBase.getCampCommitteeMembersList().size() >0){
                campCommitteeDataBase.writeToCSV();}
        }
    }
}
