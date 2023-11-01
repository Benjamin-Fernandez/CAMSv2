package CAMSv2;

import java.util.ArrayList;


public class CampManager {
    private static ArrayList<Camp> campList;
    public void createCamp(String name){

        //print and scan logic is here
    // call constructor from camp, parameters are all the 8 inputs
        Camp SpecificCamp = new Camp(8 parameters);
        campList.add(SpecificCamp);


    }
    public Camp getCamp(String campName){
        //for each camp in campList
        //if campName == campList[i].info.name, return campList[i]
    }

    public boolean getStaffinCharge(String campName, String Staffname){
        //for each camp in campList
        //if staffname == campList[i].info.staffincharge && campName == campList[i].info.name return true


        //return false
    }

    public void editCamp(String campName,String staffName){
        if(getStaffinCharge(campName, staffName)) {
            //ask for what u want to edit
            //make it a whileloop
            //print table 1-7 for edit of which info
            //8 is exit
            //scan for the choice
            //e.g. he want change date -> scanner.nextint
            Camp currentCamp = getCamp(campName);

            //currentCamp.info.editCampInfo(int choice)
        }
        //else print "Staff is not incharge"

    }

    public void deleteCamp(String campName){
        //use getCamp function; ->return camp object
        //use .remove(currentCamp);
    }

    public void changeVisibility(String campName){
        //ask the user whether true/false
        //getCamp(campName)
        //resultofgetcamp.setVisibility(true/false)
    }

    public void StaffCampListGenerator(String staffname){
        //for each camp in campList
        //if staffName == campList[i].info.staffname, print(campList[i].info.campname
    }
}
