package CAMSv2;
import java.util.ArrayList;
import java.util.Scanner;
//import CAMSv2.CampManager;

public class EnquiryManager {
    //attribute
    private static int enquiryCounter = 0;

    //methods
    public static void createEnquiry(Question question, Camp camp, String studentName){
        // check if an Enquiries already exist for the student calling this.
        enquiryCounter++;
        Enquiries newEnquiries = camp.addStudentEnquiriesInList(studentName);
        // add question to camp
        newEnquiries.addQuestion(question);

    } 
    

    //staff fucntion
    public boolean viewEnquiryForStaff(String campName, String staffName){
        //printing and logic will occur in this methoed
        // staff->viewEn->thisviewEnq->getcamp->
        //for loop to iterate arraylist of enquiries
        //returns true if empty, return false if have enquiries
        for(int i=0;i<CampManager.getCampList().size();i++){

            if(CampManager.getStaffinCharge(campName,staffName)){
                Camp camp = CampManager.getCamp(campName);
                System.out.println(campName + " enquiries:");

                //check if empty
                if(camp.getEnquiries().size()==0){
                    System.out.println("There are no enquiries");
                    return true; //it is empty
                }
                else{
                    for(int j=0;j<camp.getEnquiries().size();j++){

                        for(int k=0;k<camp.getEnquiries().get(j).getQuestions().size();k++){

                        System.out.println("Enquiry " + j+1 + " Question " + k+1 + ". " + camp.getEnquiries().get(j).getQuestions().get(k).getQuestion());
                        //enq 1 qns 2 will = 12

                        }//inner for
                    }
                }
            

            }//if

        }//for
        return false; //enquiries are not empty
    }//viewEnquiry


    
    public void replyEnquiryFromStaff(String campName, String staffName){
        Scanner sc = new Scanner(System.in);
        Camp curCamp = CampManager.getCamp(campName);
        boolean empty;
        int enqIndex;
        int qnsIndex;
        String newReply;

        //call view enquiry
        empty = this.viewEnquiryForStaff(campName,staffName);//prints list of enq
        //should i make above a boolean
        if(empty==false){

        
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
            curCamp.getEnquiries().get(enqIndex).getQuestions().get(qnsIndex).setReply(newReply);
            System.out.println("Reply uploaded");
        }

    }//replyEnquiry

    //committee member function
    public void viewEnquiryForCampCommitteeMember(String campName){
        //for loop to iterate arraylist of enquiries
        for(int i=0;i<CampManager.getCampList().size();i++) {
            Camp camp = CampManager.getCamp(campName);
            System.out.println(campName + "enquiries");

            for (int j = 0; j < camp.getEnquiries().size(); j++) {
                for (int k = 0; k < camp.getEnquiries().get(j).getQuestions().size(); k++) {
                    System.out.println("Enquiry " + j + 1 + "Question " + k + 1 + ". " + camp.getEnquiries().get(j).getQuestions().get(k));
                    //enq 1 qns 2 will = 12
                }
            }
        }
    }

    public void replyEnquiryFromCampCommitteeMember(String campName){
        Scanner sc = new Scanner(System.in);
        Camp curCamp = CampManager.getCamp(campName);

        int enqIndex;
        int qnsIndex;
        String newReply;

        //call view enquiry
        this.viewEnquiryForCampCommitteeMember(campName);//prints list of enq

        System.out.println("Which enquiry would you like to reply to?");
        //value taken is +1 of actual index
        enqIndex = sc.nextInt();
        enqIndex--; //now is correct index

        System.out.println("Which question would you like to reply to?");
        qnsIndex = sc.nextInt();
        qnsIndex--;


        //this is reply portion
        //replying to a specific enquiry from a specific camp
        // committeeMember.replyEnq-> enqManager.replyEnq(campname) -> takes input of index of enq, takes input on reply itself-> camp.enqList[index] ->
        System.out.println("Enter your reply");
        newReply = sc.nextLine();
        curCamp.getEnquiries().get(enqIndex).getQuestions().get(qnsIndex).setReply(newReply);
        System.out.println("Reply uploaded");

        sc.close();

    }//replyEnquiry

    public static int getEnquiryCounter() {
        return enquiryCounter;
    }

    public static void printQuestions(Enquiries enquiries) {
        System.out.println(enquiries.getEnqurier());
        ArrayList<Question> questions = enquiries.getQuestions();
        for (Question question : questions) {
            System.out.println(question.getQuestionId());
            System.out.println(question.getQuestion());
            System.out.println(question.getReply());
        }
    }

}
