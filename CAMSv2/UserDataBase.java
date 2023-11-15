package CAMSv2;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class UserDataBase {
    private String studentCSVFilePath;
    private String staffCSVFilePath;
    private static ArrayList<Student> studentList; //this must be global variable
    private static ArrayList<Staff> staffList;
    public UserDataBase(String studentCSVFilePath, String staffCSVFilePath) {
        this.studentCSVFilePath = studentCSVFilePath;
        this.staffCSVFilePath = staffCSVFilePath;

        UserDataBase.studentList = new ArrayList<>(); // Initialize the Student ArrayList//
        UserDataBase.staffList = new ArrayList<>(); // Initialize the Staff ArrayList//

        loadDataFromCSV(this.studentCSVFilePath);
        loadDataFromCSV(this.staffCSVFilePath);
    }

    public void loadDataFromCSV(String csvFilePath) {

        try (InputStream inputStream = new FileInputStream(csvFilePath);
             Reader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
                reader.mark(1);
            if (reader.read() != 0xFEFF) {
                reader.reset(); // Reset if the first character is not the BOM
            }
            String line;
            while ((line = reader.readLine()) != null) {

                String[] values = line.split(",");
                String name = values[0].trim();
                String emailID = values[1].split("@")[0].trim(); //Retrieve only the ID before @ part in email
                String faculty = values[2].trim();
                String password = "password"; // Assume default password
                // if csv == student.csv ,
                //    String role = student;
                //else if cvs == staff.csv ,
                // String role= staff

                // Check if the CSV file is for students or staff
                if (csvFilePath.contains("Student_List.csv")) {
                    String role = "student";
                    Student student = new Student(name,emailID, faculty,password,role);
                    studentList.add(student);
                }
                else {
                    String role = "staff";
                    Staff staff = new Staff(name,emailID, faculty,password, role);
                    staffList.add(staff);
                }
            }
        } catch (IOException e) {e.printStackTrace();}
    }

    //public void writeToCSV

    //get methods
    public static ArrayList<Student> getStudents() {
        return studentList;
    }

    public static ArrayList<Staff> getStaffs() {
        return staffList;
    }

    public static boolean checkStudentInside(String curStudent){
        for(Student student: getStudents()){
            if (curStudent.equals(student.getName())) return true;
        }
        return false;
    }


}