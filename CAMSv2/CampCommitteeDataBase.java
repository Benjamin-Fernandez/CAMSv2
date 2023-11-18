package CAMSv2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CampCommitteeDataBase {
    private static CampCommitteeDataBase instance;
    private static ArrayList<CampCommitteeMember> campCommitteeMembersList = new ArrayList<>();
    private String filePath;


    public CampCommitteeDataBase(String filePath) {
        this.filePath = filePath;
    }

    public static CampCommitteeDataBase getInstance(String filePath) {
        if (instance == null) {
            instance = new CampCommitteeDataBase(filePath);
        }
        return instance;
    }


    public void loadToCSV(){
        try (InputStream inputStream = new FileInputStream(this.filePath);
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
                String password = values[3].trim(); // Assume default password
                String campName = values[4].trim(); // Assume default password

                Camp camp = CampManager.getCamp(campName);
                CampCommitteeMember campCommitteeMember = new CampCommitteeMember(name,emailID, faculty,password,Role.CAMP_COMMITTEE_MEMBER, camp);
                campCommitteeMembersList.add(campCommitteeMember);
            }
        } catch (IOException e) {e.printStackTrace();}

    }

    public void writeToCSV() {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filePath, false))) {
            if(campCommitteeMembersList.size() == 0){
                return;
            }
            for (CampCommitteeMember campCommitteeMember: campCommitteeMembersList) {
                printWriter.println( campCommitteeMember.getName()+ "," +campCommitteeMember.getEmailID()  + "," + campCommitteeMember.getFaculty()  + ","
                                     + campCommitteeMember.getPassword() + "," + campCommitteeMember.getCamp().getCampName());
            }
        }
        catch (IOException e) {
            System.out.println("CSV file not found");
        }finally {
            // Clear the staffList after writing to the CSV file
            campCommitteeMembersList.clear();}
    }

    public static ArrayList<CampCommitteeMember> getCampCommitteeMembersList() {
        return campCommitteeMembersList;
    }

}
