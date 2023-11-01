package CAMSv2;

public class User {
    String userID;
    String password;
    String faculty;
    String name;

    String Role; //either staff or student

    boolean login(String password, String UserID , String role){}

    //create authenticator object to call check(password , userid)
    //
    public void changePassword(String newpassword){}

}
