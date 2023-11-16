package CAMSv2;


public class Authenticator {
    //
    static User check(String UserID, String Password, String role) {

        if (role.equals("student")) {
            for (int i = 0; i < UserDataBase.getStudents().size(); i++) {
                Student student = UserDataBase.getStudents().get(i);
                if (student.getEmailID().equals(UserID) && student.getPassword().equals(Password)) {
                    return student;
                }
            }
        }

        if (role.equals("staff")) {
            for (int i = 0; i < UserDataBase.getStaffs().size(); i++) {
                Staff staff = UserDataBase.getStaffs().get(i);
                if (staff.getEmailID().equals(UserID) && staff.getPassword().equals(Password)) {
                    return staff;
                }
            }
        }


        //we preassign staff/student(role) to database
        //depending on what the role is , we check the respective database for the credentials ,
        // if credentials are not inside , he is not a student
        //use the userDatabase attribute (Staff and student)


        return null;
    }
}