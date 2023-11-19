package CAMSv2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class StaffDataBase extends DataBase {
    private static StaffDataBase instance;
    private ArrayList<Staff> staffList = new ArrayList<>();
    private String filePath = System.getProperty("user.dir") + "\\CAMSv2\\Data CSV\\Staff_List.csv";

    private StaffDataBase() {
    }

    public static StaffDataBase getInstance() {
        if (instance == null) {
            instance = new StaffDataBase();
        }
        return instance;
    }

    public void loadToCSV() {
        staffList.clear();
        try (InputStream inputStream = new FileInputStream(filePath);
             Reader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            reader.mark(1);
            String line;

            if (reader.read() != 0xFEFF) {
                reader.reset(); // Reset if the first character is not the BOM
            }

            while ((line = reader.readLine()) != null) {
                if (line.equals("")) {
                    return;
                }
                String[] values = line.split(",");
                String name = values[0].trim();
                String emailID = values[1];
                String faculty = values[2].trim();
                String password = values[3].trim(); // Assume default password

                UserGroup userGroup = UserGroup.NTU;
                // convert faculty to user group
                try {
                    userGroup = UserGroup.valueOf(faculty);
                } catch (Exception e) {
                    System.out.println("Cannot convert UserGroup in CSV into ENUM");
                }
                Staff staff = new Staff(name, emailID, userGroup, password, Role.STAFF);
                staffList.add(staff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToCSV() {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filePath, false))) {
            for (Staff staff : staffList) {
                printWriter.println(staff.getName() + "," + staff.getEmailID() + "," + staff.getFaculty() + "," + staff.getPassword());
            }
        } catch (IOException e) {
            System.out.println("CSV file not found");
        }
    }

    public ArrayList<Staff> getStaffList() {
        return staffList;
    }
}