package CAMSv2;

import java.util.Scanner;

public class SuggestionManager {
    //attribute

    //methods
    public void createSuggestion(String campName, String suggestion){
        //student will have their own method called makeEnquiry which calls this method, 
        //append student's suggestion to their own list of suggestion
        //find the correct camp
        //append this suggestion to that particular camp
        Advice newAdvice = new Advice(suggestion);
        Suggestion newSuggestion = new Suggestion(newAdvice);
        CampManager campManager = new CampManager();
        Camp camp = campManager.getCamp(campName);
        camp.addSuggestion(newSuggestion);
    }

    //staff fucntion
    public void viewSuggestion(String campName){
        //pritngin and logic will occur in this methoed
        // staff->viewEn->thisviewEnq->getcamp->
        CampManager campManager = new CampManager();
        //for loop to iterate arraylist of suggestion
        for(int i=0;i<campManager.getCampList().size();i++){

                Camp camp = campManager.getCamp(campName);
                System.out.println(campName + "suggestion");


                for(int j=0;j<camp.getSuggestions().size();j++){

                    for(int k=0;k<camp.getSuggestions().get(j).getAdviceList().size();k++){

                    System.out.println("Suggestion " + j+1 + "Advice " + k+1 + ". " + camp.getSuggestions().get(j).getAdviceList().get(k));
                    //enq 1 qns 2 will = 12

                    }//inner for
                }

           

        }//for
    }

    //approve suggestion
    //staff.approvesuggestion-> suggManager.approveAdvice->
    public void approveAdvice(String campName, String staffName){
        Scanner sc = new Scanner(System.in);

        Camp curCamp = new Camp();
        CampManager campManager = new CampManager();
        curCamp = campManager.getCamp(campName);

        int suggIndex;
        int adviceIndex;

        this.viewSuggestion(campName);

        System.out.println("Which suggestion would you like to attend to?");
        //value taken is +1 of actual index
        suggIndex = sc.nextInt();
        suggIndex--; //now is correct index

        System.out.println("Which advice would you like to approve?");
        adviceIndex = sc.nextInt();
        adviceIndex--;

        String sample = "approved";
        String approval;
        boolean approved;
        System.out.println("Input approved to approve advice");
        approval = sc.nextLine();
        approved = sample.equals(approval);

        curCamp.getSuggestions().get(suggIndex).getAdviceList().get(adviceIndex).setApproval(approved);

        if(approved){
            campManager.editCamp(campName, staffName);

            //editCamp

        }

        sc.close();
    }

    
    

    //committee functions
    public void deleteSuggestion(){

    }
   
    public void editSuggestion(){

    }
    
}
