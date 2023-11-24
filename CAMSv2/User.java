package CAMSv2;

 
/**

 * The {@code User} User class represents a user in the CAMSv2 system.
 * Each user has an email ID, password, faculty, name, and role.
 * The role can be either staff, student, or camp committee member.
 * This class provides methods for creating and managing users.
 * @author Zhu YuHao
 * @since 13-11-2023
 */
public class User {
    //attributes
    String emailID;
    String password;
    UserGroup faculty;
    String name;
    Role role; //either staff or student or camp committee member
    
    /**
     * Constructor for the User class.
     *
     * @param name The name of the user.
     * @param emailID The email ID of the user.
     * @param faculty The faculty of the user.
     * @param password The password of the user.
     * @param role The role of the user.
     */
    //methods
    public User(String name, String emailID, UserGroup faculty, String password, Role role){
        this.emailID = emailID; 
        this.password = password;
        this.faculty = faculty;
        this.name = name;
        this.role = role;
    }

     /**
     * This method checks if the provided login credentials match the user's credentials.
     *
     * @param password The password to check.
     * @param UserID The user ID to check.
     * @param role The role to check.
     * @return true if the credentials match, false otherwise.
     */
    public boolean login(String password, String UserID , String role){
        return false;
    }//create authenticator object to call check(password , userid)
    
     /**
     * This method changes the user's password.
     * It sets the user's password to the entered password.
     */
    public void changePassword(String newPassword){
        this.password = newPassword;
        System.out.println("The new password will take effect in your next login");
    }

    /**
     * This method returns the user's name.
     *
     * @return The user's name.
     */
    public String getName(){
        return this.name;
    }

     /**
     * This method returns the user's faculty.
     *
     * @return The user's faculty.
     */
    public UserGroup getFaculty(){
        return this.faculty;
    }

     /**
     * This method returns the user's password.
     *
     * @return The user's password.
     */
    public String getPassword(){
        return this.password;
    }

     /**
     * This method returns the user's email ID.
     *
     * @return The user's email ID.
     */
    public String getEmailID(){
        return this.emailID;
    }

     /**
    * This method returns the user's role.
    *
    * @return The user's role.
    */
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
