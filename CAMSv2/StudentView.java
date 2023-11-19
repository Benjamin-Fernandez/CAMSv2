package CAMSv2;

import java.util.ArrayList;
import java.util.HashSet;

public class StudentView {

// --------------------General--------------------
    public void displayHeader(String header) {
        System.out.println("---------------" + header + "-------------------");        
    }

    public void displaySelectValidOption() {
        System.out.println("Select a valid option");
    }

    public void displaySelectActionToTake() {
        System.out.println("Select an option: ");
    }

    public void displayStudentMenu() {
        System.out.println("Select which action you would like to take");
        System.out.println("1. Change Password"); 
        System.out.println("2. View list of camps available"); 
        System.out.println("3. View registered camps");  
        System.out.println("4. View Profile"); 
        System.out.println("5. Manage Enquiries (View, Edit and Delete)");
        System.out.println("6. Select a Camp to access more options(submit enquiry, register/withdraw, view available slots)");
    }

    public void displayReturnToPreviousPage() {
        System.out.println("<--- Return to previous page (type 111)");
    }

    public void displaySuccessfulMessage() {
        System.out.println("Successful!");
    }

    public void displayFailureMessage() {
        System.out.println("Failed!");
    }
// -----------------Enquiries------------------
    public void displayEnquiries(ArrayList<Question> questions) {

        for (Question question : questions) {
            displayEnquiry(question);
        }
    
    }

    public void displayEnquiry(Question question) {

        for (Reply reply : question.getReplies()) {
            System.out.println("EnquiryId: " + question.getQuestionId() + "|" + " Enquiry: " + question.getQuestion() + "|" + " Camp: " + question.getCampName());
            System.out.println(reply.getName() + "replied: " + reply.getReply());
        }
    }

    public void displayGetEnquiryId() {
        System.out.println("Please enter the enquiry ID: ");
    }
    
    public void displayEnquiriesMenu() {
        System.out.println("1. View all enquiries");  
        System.out.println("2. Edit an enquiry"); 
        System.out.println("3. Delete an enquiry");        
    }

    public void displayEnterNewEnquiryDescription() {
        System.out.println("Enter new Enquiry Description: ");
    }




// ---------------------Camp Specific-----------------------
    public void displayCampSpecificOptions(boolean isRegistered) {
        System.out.println("1. Submit an Enquiry");
        if (isRegistered) {
            System.out.println("2. Withdraw");  
        }
        else {
            System.out.println("2. Register");
        }
        System.out.println("3. View Remaining Camp Slots");
    }


    public void displayEnterCampName() {
        System.out.println("Please enter Camp Name: ");               
    }


    public void displayRemainingCampSlots() {
        System.out.println("3. View Remaining Camp Slots");
    }

    public void displayRemainingCampSlots(Camp camp) {
        System.out.println((camp.getTotalSlots() - camp.getStudentList().size()));
        // Camp Manager should check that the camp is Visible and userGroup
    }
// --------------Register------------------------
    public void displayRoleToRegister() {
        for (Role role : Role.values()) {
            if (role.equals(Role.STAFF)) {continue;}
            System.out.println(role.toString());
        }
        System.out.println("Enter the role you would like to register (Full Uppercase): ");
    }
// --------------Withdrawal----------------------

// -------------Password-------------------------
    public void displayEnterNewPassword() {
        System.out.println("Enter your new Password: ");       
    }

    public void displayPasswordChangedSuccessfully() {
        System.out.println("Password changed successfully!");         
    }



// -------------------Camp General---------------
    public void displayListOfCamps(ArrayList<Camp> camps) {
        for (int i = 0; i < camps.size(); i++) {
            System.out.println(i+1 + ": " + camps.get(i).getCampName());
        }
    }

// ------------------Profile----------------------
    public void displayProfile(String name, String password, UserGroup faculty, Role role, String ccmCamp) {
        System.out.println("Name: " + name);
        System.out.println("Password: " + password); 
        System.out.println("Faculty: " + faculty.toString()); 
        System.out.println("Role: " + role.toString());
        System.out.println("Camp(As a Camp Committee Member): " + ccmCamp);       
    }
}
