package CAMSv2;


public class Authenticator {
    //
    static User check(String UserID, String Password, int login_option) {

        if (login_option == 1) {
            for (int i = 0; i < StaffDataBase.getStaffList().size(); i++) {
                Staff staff = StaffDataBase.getStaffList().get(i);
                if (staff.getEmailID().equals(UserID) && staff.getPassword().equals(Password)) {
                    return staff;
                }
            }
        }


        if (login_option == 2) {
            for (int i = 0; i < StudentDataBase.getStudents().size(); i++) {
                Student student = StudentDataBase.getStudents().get(i);
                if (student.getEmailID().equals(UserID) && student.getPassword().equals(Password)) {
                    return student;
                }
            }
        }


        if (login_option == 3) {
            for (int i = 0; i < CampCommitteeDataBase.getCampCommitteeMembersList().size(); i++) {
                CampCommitteeMember campCommitteeMember = CampCommitteeDataBase.getCampCommitteeMembersList().get(i);
                if (campCommitteeMember.getEmailID().equals(UserID) && campCommitteeMember.getPassword().equals(Password)) {
                    return campCommitteeMember;
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