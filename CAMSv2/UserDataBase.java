package CAMSv2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UserDataBase {
    private String csvFilePath;
    private static ArrayList<Student> Student; //this must be global variable
    private static ArrayList<Staff> Staff;
    public UserDataBase(String csvFilePath) {
        this.csvFilePath = csvFilePath;
        if(csvFilePath.contains("student.csv")) {
            this.Student = new ArrayList<>(); // Initialize the Student ArrayList//
        }
        else{
            this.Staff = new ArrayList<>(); // Initialize the Staff ArrayList//
        }

        loadDataFromCSV(this.csvFilePath);
    }

    public void loadDataFromCSV(String csvFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[0];
                String emailID = values[1].split("@")[0]; //Retrieve only the ID before @ part in email
                String faculty = values[2];
                String password = "password"; // Assumedefault password
                // if csv == student.csv ,
                //    String role = student;
                //else if cvs == staff.csv ,
                // String role= staff

                // Check if the CSV file is for students or staff
                if (csvFilePath.contains("student.csv")) {
                    String role = "student";
                    Student student = new Student(name,emailID, faculty,password,role);
                    Student.add(student);
                }
                else {
                    String role = "staff";
                    Staff staff = new Staff(name,emailID, faculty,password, role);
                    Staff.add(staff);
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //public void writeToCSV


    public static ArrayList<Student> getStudents() {
        return Student;
        //hi ethan
    }

    public static ArrayList<Staff> getStaffs() {
        return Staff;
        //hi ethan
    }


}