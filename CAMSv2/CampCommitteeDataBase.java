package CAMSv2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CampCommitteeDataBase extends DataBase{
    private static CampCommitteeDataBase instance;
    private ArrayList<CampCommitteeMember> campCommitteeMembersList = new ArrayList<>();
    private String filePath = System.getProperty("user.dir") + "\\CAMSv2\\Data CSV\\CampCommitteeMember_List.csv";


    private CampCommitteeDataBase() {
    }

    // can remove filePath argument and instead intialize into the class itself
    public static CampCommitteeDataBase getInstance() {
        if (instance == null) {
            instance = new CampCommitteeDataBase();
        }
        return instance;
    }


    public void loadToCSV(){
        campCommitteeMembersList.clear();
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

                String[] values = line.split(",");
                String name = values[0].trim();
                String emailID = values[1];
                String faculty = values[2].trim();
                String password = values[3].trim();
                String campName = values[4].trim();

                UserGroup userGroup = UserGroup.NTU;
                // convert faculty to user group
                try {
                    userGroup = UserGroup.valueOf(faculty);
                } catch (Exception e) {
                    System.out.println("Cannot convert UserGroup in CSV into ENUM");
                }
                Camp camp = CampManager.getCamp(campName);
                // if (camp == null) {return;}
                CampCommitteeMember campCommitteeMember = new CampCommitteeMember(name, emailID, userGroup, password, Role.CAMP_COMMITTEE_MEMBER, camp);
                campCommitteeMembersList.add(campCommitteeMember);
            }
        } catch (IOException e) {e.printStackTrace();}

    }

    public void writeToCSV() {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filePath, false))) {
            if(campCommitteeMembersList.size() == 0){
                // printWriter.println("");
                return;
            }
            for (CampCommitteeMember campCommitteeMember: campCommitteeMembersList) {
                System.out.println("Within CCMwriteToCSV: " + campCommitteeMember.getCamp());
                printWriter.println(campCommitteeMember.getName() + "," + campCommitteeMember.getEmailID() + "," +
                                    campCommitteeMember.getFaculty() + "," + campCommitteeMember.getPassword() + "," +
                                    campCommitteeMember.getCamp().getCampName());
            }
        }
        catch (IOException e) {
            System.out.println("CSV file not found");
        }
    }

    public ArrayList<CampCommitteeMember> getCampCommitteeMembersList() {
        return campCommitteeMembersList;
    }

}
