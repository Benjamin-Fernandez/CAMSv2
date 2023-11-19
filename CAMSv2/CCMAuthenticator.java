package CAMSv2;

public class CCMAuthenticator implements Authenticator<CampCommitteeMember>{

    @Override
    public CampCommitteeMember authenticate(String userId, String password) {
        for (int i = 0; i < CampCommitteeDataBase.getInstance().getCampCommitteeMembersList().size(); i++) {
            CampCommitteeMember campCommitteeMember = CampCommitteeDataBase.getInstance().getCampCommitteeMembersList().get(i);
            if (campCommitteeMember.getEmailID().equals(userId) && campCommitteeMember.getPassword().equals(password)) {
                System.out.println("Successfully Authenticated");
                return campCommitteeMember;
            }
        }
        System.out.println("Wrong CampCommitteeMember credentials");
        return null;
    }
    
}
