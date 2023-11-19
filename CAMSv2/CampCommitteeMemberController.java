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
        super.camp = super.handleCampSelection();
        if (camp == null) {return;}
        
        boolean running = true;
        boolean studentRegistered = camp.isStudentRegistered(user.getName());
        System.out.println("student registered: " + studentRegistered);
        boolean isCCM = (camp.equals(ccm.getCamp()));
        // view depends on whether camp is CCM of, and then if it is no CCM camp, check for registration
        ccmView.displayCampSpecificOptions(studentRegistered, isCCM);
        view.displayReturnToPreviousPage();
        int choice = sc.nextInt();
        sc.nextLine();

        while (running) {
            if (!isCCM) {
                running = handleCampSpecificOptions(choice, studentRegistered);                
            }
            else {
                // no registration/withdrawal check since user is CCM and thus cannot leave the camp
                running = handleCampCommitteeMemberMenu(choice);
            }

        }
    }

    private boolean handleCampCommitteeMemberMenu(int choice) {
        // Implement additional menu options specific to CampCommitteeMember
        switch (choice) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;  
            case 5:
                break;
            case 6:
                break;

            case 111:
                return false;
        }
        return true;
    }
}
