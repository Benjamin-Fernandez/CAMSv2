package CAMSv2;

import java.util.ArrayList;


public class CampManager {
    //attribtues
    private static ArrayList<Camp> campList;

    //method
    public void createCamp(String name){
        //print and scan logic is here
    // call constructor from camp, parameters are all the 8 inputs
      //Camp SpecificCamp = new Camp(8 parameters);
      //campList.add(SpecificCamp);
    }

    public Camp getCamp(String campName){
        //for each camp in campList
        ArrayList<Camp> campList = new ArrayList<Camp>();
        campList = this.getCampList();
        Camp camp = new Camp();

        for(int i=0; i<campList.size();i++){
            camp = campList.get(i);
            if(campName.equals(camp.getCampName())){
                return camp;
            }//if
        }//for
       
        return null;//if it doesnt find a camp
    }

    public boolean getStaffinCharge(String campName, String Staffname){
        //for each camp in campList
        //if staffname == campList[i].info.staffincharge && campName == campList[i].info.name return true
        String staffIC;
        Camp camp = new Camp();
        camp = getCamp(campName);
        staffIC = camp.getStaffName();

        return staffIC.equals(Staffname);
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

    public ArrayList<Camp> getCampList(){
        return campList;
    }

    // public ArrayList<Student> getStudentList(){
    //     Camp camp = new Camp();
    //     return camp.getStudentList();
    // }

    public void generateReport(String staffName){
        ArrayList<Camp> campList = this.getCampList();
        ArrayList<Student> studentList;
        CampManager campManager = new CampManager();

        String campName;
        //Camp camp = new Camp();

        //the 8 details apart from StudentName and Role
        String dates;
        String registrationClosingDate;
        String userGroup;
        String location;
        String totalSlots;
        String[] campCommitteeSlots;
        String description;

        for(int i=0;i<campList.size();i++){
            campName = campList.get(i).getCampName();
            //String name of camp obj

            if(this.getStaffinCharge(campName, staffName)){
                Camp camp = campManager.getCamp(campName); //camp obj itself            
                studentList = camp.getStudentList();
                dates = camp.getDates();
                registrationClosingDate = camp.getRegistrationClosingDate();
                userGroup = camp.getUserGroup();
                location = camp.getLocation();
                totalSlots = camp.getTotalSlots();
                description = camp.getDescription();
                campCommitteeSlots = camp.getCampCommitteeSlots();
                 
                for(int j = 0 ; j < 10 ; j++){
                    //go through campCommitteeSlots array
                    //write each index to csv file.
                }
        
                for(int j=0;j<studentList.size();j++){
                    Student student = new Student();
                    student = studentList.get(j);
                    // System.out.println(student.getName() + student.getStudentRole()); 
                    //getstudentrole is in student class
                }//send everything to a csv file.
               


            }
        }


    }


}

