package CAMSv2;

public class CampAndRole {
    private Camp camp;
    private String role;

    CampAndRole(Camp camp, String role) {
        this.camp = camp;
        this.role = role;
    }

    public Camp getCamp() {
        return camp;
    }

    public String getRole() {
        return role;
    }

}
