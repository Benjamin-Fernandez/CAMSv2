package CAMSv2;

public class User {
    //attributes
    String userID;
    String password;
    String faculty;
    String name;
    String Role; //either staff or student
    
    //methods

    public boolean login(String password, String UserID , String role){
        return false;
    }//create authenticator object to call check(password , userid)
    

    public void changePassword(String newpassword){

    }

    public String getName(){
        return this.name;
    }

}
