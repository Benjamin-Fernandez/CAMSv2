package CAMSv2;

import java.util.ArrayList;

public abstract class View {
    public void displayHeader(String header) {
        System.out.println("---------------" + header + "-------------------");        
    }

    public void displaySelectValidOption() {
        System.out.println("Select a valid option");
    }

    public void displaySelectActionToTake() {
        System.out.println("Select an option: ");
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
    public void displayListOfCamps(ArrayList<Camp> camps) {
        int counter = 1;
        for (Camp camp : camps) {
            System.out.println(counter + ": " + camp.getCampName());       
            counter++;     
        }
    }
}
