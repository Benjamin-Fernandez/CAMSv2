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

// abstract
/*public class UserDataBase {
    //
    private String studentCSVFilePath;
    private String staffCSVFilePath;
    private String campCommitteeMemberCSVFilePath;
    private static ArrayList<Student> studentList; //this must be global variable
    private static ArrayList<Staff> staffList;
    private static ArrayList<CampCommitteeMember> campCommitteeList = new ArrayList<CampCommitteeMember>(); //this must be global variable
    public UserDataBase(String studentCSVFilePath, String staffCSVFilePath, String campCommitteeMemberCSVFilePath) {
        this.studentCSVFilePath = studentCSVFilePath;
        this.staffCSVFilePath = staffCSVFilePath;
        this.campCommitteeMemberCSVFilePath = campCommitteeMemberCSVFilePath;

        UserDataBase.studentList = new ArrayList<>(); // Initialize the Student ArrayList//
        UserDataBase.staffList = new ArrayList<>(); // Initialize the Staff ArrayList//
        UserDataBase.campCommitteeList = new ArrayList<>();

        loadDataFromCSV(this.studentCSVFilePath);
        loadDataFromCSV(this.staffCSVFilePath);
        loadDataFromCSV(this.campCommitteeMemberCSVFilePath);
        // Student
        // 
    }

    public void loadDataFromCSV(String csvFilePath) {

        try (InputStream inputStream = new FileInputStream(csvFilePath);
             Reader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
                reader.mark(1);
            String line;

            if (reader.read() != 0xFEFF) {
                reader.reset(); // Reset if the first character is not the BOM
            }

            while ((line = reader.readLine()) != null) {
                if (line.equals("")) {return;}
                // String firstLine = reader.readLine(); // Check the first line
                // if (firstLine == null) {
                //     return; // Exit method if the file is empty
                // }
                String[] values = line.split(",");
                String name = values[0].trim();
                String emailID = values[1];
                String faculty = values[2].trim();
                String password = "password"; // Assume default password
                // if csv == student.csv ,
                //    String role = student;
                //else if cvs == staff.csv ,
                // String role= staff

                // Check if the CSV file is for students or staff
                if (csvFilePath.contains("Student_List.csv")) {
                    Student student = new Student(name,emailID, faculty,password,Role.STUDENT);
                    studentList.add(student);
                }
                else if(csvFilePath.contains("Staff_List.csv")){
                    Staff staff = new Staff(name,emailID, faculty,password, Role.STAFF);
                    staffList.add(staff);
                }
                else {
                    String campName = values[3].trim();
                    Camp camp = CampManager.getCamp(campName);
                    CampCommitteeMember campCommitteeMember = new CampCommitteeMember(name, emailID, faculty,password, Role.CAMP_COMMITTEE_MEMBER,camp);
                    campCommitteeList.add(campCommitteeMember);
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
    public static ArrayList<CampCommitteeMember> getCampCommitteeMembers() {
        return campCommitteeList;
    }
    public static boolean checkStudentInside(String curStudent){
        for(Student student: getStudents()){
            if (curStudent.equals(student.getName())) return true;
        }
        return false;
    }

    // write a method to change user password.

}

 */

