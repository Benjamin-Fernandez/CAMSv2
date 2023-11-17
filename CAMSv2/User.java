package CAMSv2;

import java.util.Scanner;

public class User {
    //attributes
    String emailID;
    String password;
    String faculty;
    String name;
    Role role; //either staff or student or camp committee member
    
    //methods
    public User(String name, String emailID, String faculty, String password, Role role){
        this.emailID = emailID; //before '@'in the email
        this.password = password;
        this.faculty = faculty;
        this.name = name;
        this.role = role;
    }

    public boolean login(String password, String UserID , String role){
        return false;
    }//create authenticator object to call check(password , userid)
    

    public void changePassword(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter new password");
        String newPassword = sc.nextLine();
        this.password = newPassword;
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
    public Role getRole(){
        return this.role;
    }
}
