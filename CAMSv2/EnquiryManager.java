package CAMSv2;
import java.util.Scanner;
import CAMSv2.CampManager;

public class EnquiryManager {
    //attribute

    //methods
    public void createEnquiry(String campName, String enquiry){
        //student will have their own method called makeEnquiry which calls this method, 
        //append student's enquiry to their own lsit of enquiry
        //find the correct camp
        //append this enquiry to that particular camp
        Question newQuestion = new Question(enquiry);
        Enquiries newEnquiry = new Enquiries(newQuestion);
        CampManager campManager = new CampManager();
        Camp camp = campManager.getCamp(campName);
        camp.addEnquiry(newEnquiry);
    }

    //staff fucntion
    public void viewEnquiry(String campName, String staffName){
        //pritngin and logic will occur in this methoed
        // staff->viewEn->thisviewEnq->getcamp->
        CampManager campManager = new CampManager();
        //for loop to iterate arraylist of enquiries
        for(int i=0;i<campManager.getCampList().size();i++){

            if(campManager.getStaffinCharge(campName,staffName)){
                Camp camp = campManager.getCamp(campName);
                System.out.println(campName + "enquiries");


                for(int j=0;j<camp.getEnquiries().size();j++){

                    for(int k=0;k<camp.getEnquiries().get(j).getQuestion().size();k++){

                    System.out.println("Question " + j+1 + "Enquiry " + k+1 + ". " + camp.getEnquiries().get(j).getQuestion().get(k));
                    //enq 1 qns 2 will = 12

                    }//inner for
                }

            }//if

        }//for
    }

    
    public void replyEnquiry(String campName, String staffName){
        Scanner sc = new Scanner(System.in);
        Camp curCamp = new Camp();
        CampManager campManager = new CampManager();
        curCamp = campManager.getCamp(campName);

        int enqIndex;
        int qnsIndex;
        String newReply;
        //call view enquiry 

        this.viewEnquiry(campName,staffName);//prints list of enq

        System.out.println("Which enquiry would you like to reply to?");
        //value taken is +1 of actual index
        enqIndex = sc.nextInt();
        enqIndex--; //now is correct index

        System.out.println("Which question would you like to reply to?");
        qnsIndex = sc.nextInt();
        qnsIndex--;


        //this is reply portion
        //replying to a specific enquiry from a specific camp
        // staff.replyEnq-> enqManager.replyEnq(campname) -> takes input of index of enq, takes input on reply itself-> camp.enqList[index] -> 
        System.out.println("Enter your reply");
        newReply = sc.nextLine();
        curCamp.getEnquiries().get(enqIndex).getQuestion().get(qnsIndex)


        
        




        sc.close();
    }

    //student functions
    public void deleteEnquiry(){

    }
   
    public void editEnquiry(){

    }

}
