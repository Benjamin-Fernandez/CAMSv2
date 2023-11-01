package CAMSv2;

public class User {
    String UserID;
    String Password;
    String Faculty;
    String Name;

    String Role; //either staff or student

    boolean login(String password, String UserID , String role){}

    //create authenticator object to call check(password , userid)
    //
    public void changePassword(String newpassword){}

}
