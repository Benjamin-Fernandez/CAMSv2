package CAMSv2;

public interface Role {

    void viewDetails(Camp camp);
    void submitSuggestion(String name, Camp camp);
    void viewEnquiries(String campName);
    void replyEnquiries(String campName);
    void viewSuggestion(String studentName, String campName);

    void editSuggestion(String studentName, String campName);
    void deleteSuggestion(String studentName, String campName);
}

