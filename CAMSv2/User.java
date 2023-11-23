package CAMSv2;

import java.util.Scanner;

public class User {
    //attributes
    String emailID;
    String password;
    UserGroup faculty;
    String name;
    Role role; //either staff or student or camp committee member
    
    //methods
    public User(String name, String emailID, UserGroup faculty, String password, Role role){
        this.emailID = emailID; 
        this.password = password;
        this.faculty = faculty;
        this.name = name;
        this.role = role;
    }

    public boolean login(String password, String UserID , String role){
        return false;
    }//create authenticator object to call check(password , userid)
    

    public void changePassword(String newPassword){
        this.password = newPassword;
    }

    public String getName(){
        return this.name;
    }
    public UserGroup getFaculty(){
        return this.faculty;
    }
    public String getPassword(){
        return this.password;
    }
    public String getEmailID(){
        return this.emailID;
    }
    public Role getRole(){
        return this.role;
    }

    public void displayProfile() {
        System.out.println("Name: " + name);
        System.out.println("Password: " + password); 
        System.out.println("Faculty: " + faculty.toString()); 
        System.out.println("Role: " + role.toString());  
    }
}
