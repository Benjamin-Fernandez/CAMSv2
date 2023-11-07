package CAMSv2;

public class User {
    //attributes
    String emailID;
    String password;
    String faculty;
    String name;
    String role; //either staff or student
    
    //methods
    public User(String emailID, String password, String faculty, String name, String role){
        this.emailID = emailID; //before '@'in the email
        this.password = password;
        this.faculty = faculty;
        this.name = name;
        this.role = role;
    }

    public boolean login(String password, String UserID , String role){
        return false;
    }//create authenticator object to call check(password , userid)
    

    public void changePassword(String newpassword){

    }

    public String getName(){
        return this.name;
    }
    public String getFaculty(){
        return this.faculty;
    }
    public String getPassword(){
        return this.password;
    }
    public String getEmailID(){
        return this.emailID;
    }
    public String getRole(){
        return this.role;
    }
}
