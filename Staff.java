package CAMSv2;

public class Staff extends User {
    public Staff(name,emailID, faculty,password,role){
        //super(name,emailID, faculty,password,role)
    }

    public void createCamp(){

    CampManager util = new CampManager();
    util.createCamp(this.Name);

    }

    public void editCamp(String campName) {

     CampManager util = new CampManager();
     util.editCamp(campName,this.name);


    }

    public void deleteCamp() {
//ask for what camp to delete
        //scan the string and then String CampName = scan
        CampManager util = new CampManager();
        util.deleteCamp(campName);


    }

    public void changeVisibility)(){
        //ask for which camp
        //scan and String campName = scan
        // CampManager util = new CampManager();
        //        util.changeVisibility(campName);
        }

    public void viewCamp(){
    Camp camp = new Camp();
    //use for loop to go through array to print all the camp object.name
    }

    public void myList(){
        // CampManager util = new CampManager();
        //        util.StaffCampListGenerator(this.name);

    }












}
