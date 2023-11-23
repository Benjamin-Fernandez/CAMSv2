package CAMSv2;

public class CampCommitteeMember extends Student {
    private int points;
    private Camp camp;
    private Suggestion suggestion; 

    public CampCommitteeMember(String name, String emailID, UserGroup faculty, String password, Role role, Camp camp) {
        super(name, emailID, faculty, password, role);
        points = 0;
        this.camp = camp;
        // initialize Suggestion
        this.suggestion = new Suggestion(name);
    }

    @Override
    public void changePassword(String newPassword) {
        super.changePassword(newPassword);
        CampCommitteeDataBase.getInstance().writeToCSV();
    }




    public void submitSuggestion(String suggestion){
        Advice advice = new Advice(suggestion, SuggestionManager.getInstance().getId(), this.name);
        SuggestionManager.getInstance().createSuggestion(advice, this.camp, this.name);
        System.out.println("Suggestion added");
    }

    public void generateCampReport(ReportFilter filter) {
        camp.generateCampReport(filter);
    }

    public void generateCampCommitteeReport() {
        camp.generateCampCommitteeReport();
    }

    public void generateStudentsEnquiryReport() {
        camp.generateStudentsEnquiryReport();
    }



    public void addPointsByOne(){
        points +=1;
    }

    public Camp getCamp(){
        return camp;
    }
    public int getPoints(){
        return points;
    }

    @Override
    public void displayProfile() {
        super.displayProfile();
        System.out.println("Camp(CCM): " + camp.getCampName());
    }



}
