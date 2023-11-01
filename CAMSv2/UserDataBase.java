package CAMSv2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UserDataBase {
    private String csvFilePath;
    public static ArrayList<Student> Student; //this must be global variable
    public static ArrayList<Staff> Staff;
    public UserDataBase(String csvFilePath) {
        this.csvFilePath = csvFilePath;
        this.users = new ArrayList<>(); // Initialize the ArrayList
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
                String password = "password"; // Assume default password
                // if csv == student.csv , String role = student
                //else if cvs == staff.csv , String role= staff

                User user;
                // Check if the CSV file is for students or staff
                if (csvFilePath.contains("student.csv")) {
                    user = new Student(name,emailID, faculty,password,role);
                }
                else {
                    user = new Staff(name,emailID, faculty,password);
                }

                users.add(user);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToCSV


    public ArrayList<User> getUsers() {
        return users;
        //hi ethan
    }
}
