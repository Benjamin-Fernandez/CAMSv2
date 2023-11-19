package CAMSv2;

public class StaffAuthenticator implements Authenticator<Staff>{

    @Override
    public Staff authenticate(String emailID, String password) {
        for (int i = 0; i <StaffDataBase.getInstance().getStaffList().size(); i++) {
            Staff staff = StaffDataBase.getInstance().getStaffList().get(i);
            if (staff.getEmailID().equals(emailID) && staff.getPassword().equals(password)) {
                System.out.println("Successfully Authenticated");
                return staff;
            }
        }
        System.out.println("Wrong Credentials");
        return null;
    }
    
}
