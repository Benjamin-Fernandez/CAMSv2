package CAMSv2;

public class CCMAuthenticator implements Authenticator<CampCommitteeMember>{

    @Override
    public CampCommitteeMember authenticate(String userId, String password) {
        for (CampCommitteeMember campCommitteeMember : CampCommitteeDataBase.getInstance().getCampCommitteeMembersList()) {
            if (campCommitteeMember.getEmailID().equals(userId) && campCommitteeMember.getPassword().equals(password)) {
                System.out.println("Successfully Authenticated");
                return campCommitteeMember;
            }            
        }
        System.out.println("Wrong CampCommitteeMember credentials");
        return null;
    }
}
