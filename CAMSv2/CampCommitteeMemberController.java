package CAMSv2;

public class CampCommitteeMemberController extends StudentController {
    private CampCommitteeMember ccm;
    private CampCommitteeMemberView ccmView;

    public CampCommitteeMemberController(CampCommitteeMember ccm, CampCommitteeMemberView view) {
        super(ccm, view);
        this.ccm = ccm;
        this.ccmView = view;
    }

    @Override
    public void enterCampSpecificOptions() {
        super.handleCampSelection();
        boolean running = true;
        while (running) {

        }
    }

    private boolean handleCampCommitteeMemberMenu(int choice) {
        // Implement additional menu options specific to CampCommitteeMember
        switch (choice) {
            // case 1, 2, etc. for CampCommitteeMember specific actions
            case 111:
                return false;
            default:
                return true;
        }
    }
}
