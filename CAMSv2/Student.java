package CAMSv2;

public class Student extends User{
    public Student(String emailID, String password, String faculty, String name, String role) {
        super(emailID, password, faculty, name, role);
    }

    public JoiningCamp(){
        //ask what camp he wants to apply
        // IsStudentInBlackList()
        // IsCampFull()
        // IsBeforeCampDeadline()
        CampManager campManager = new CampManager();

    }
}
