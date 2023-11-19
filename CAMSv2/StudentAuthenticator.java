package CAMSv2;

public class StudentAuthenticator implements Authenticator<Student>{

    @Override
    public Student authenticate(String userId, String password) {
        for (Student student : StudentDataBase.getInstance().getStudents()) {
            if (student.getEmailID().equals(userId) && student.getPassword().equals(password)) {
                System.out.println("Successfully Authenticated");
                return student;
            }
        }
        System.out.println("Wrong Student Credentials");
        return null;
    }
    
}
