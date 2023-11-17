package CAMSv2;
import java.util.Scanner;
//import CAMSv2.CampManager;

public class EnquiryManager {
    //attribute

    //methods
    public static void createEnquiry(String campName, String enquiry, Student student ){
        //student will have their own method called makeEnquiry which calls this method, 
        //append student's enquiry to their own lsit of enquiry
        //find the correct camp
        //append this enquiry to that particular camp
        Question newQuestion = new Question(enquiry, campName);
        // check if the enquiries for this student exist in that camp
        Camp camp = CampManager.getCamp(campName);
        // add enquiry to camp, if not add the new question to the existing enquiry in camp
        Enquiries newEnquiry = camp.addStudentEnquiriesInList(student.getName());
        // add question to camp
        newEnquiry.addQuestion(newQuestion);
        // add question to student
        student.getEnquiries().addQuestion(newQuestion);

    }  

    //staff fucntion
    public void viewEnquiryForStaff(String campName, String staffName){
        //printing and logic will occur in this methoed
        // staff->viewEn->thisviewEnq->getcamp->
        //for loop to iterate arraylist of enquiries
        for(int i=0;i<CampManager.getCampList().size();i++){

            if(CampManager.getStaffinCharge(campName,staffName)){
                Camp camp = CampManager.getCamp(campName);
                System.out.println(campName + " enquiries:");

                //check if empty
                if(camp.getEnquiries().size()==0){
                    System.out.println("There are no enquiries");
                }
                else{
                    for(int j=0;j<camp.getEnquiries().size();j++){

                        for(int k=0;k<camp.getEnquiries().get(j).getQuestions().size();k++){

                        System.out.println("Enquiry " + j+1 + "Question " + k+1 + ". " + camp.getEnquiries().get(j).getQuestions().get(k));
                        //enq 1 qns 2 will = 12

                        }//inner for
                    }
                }
            

            }//if

        }//for
    }//viewEnquiry


    
    public void replyEnquiryFromStaff(String campName, String staffName){
        Scanner sc = new Scanner(System.in);
        Camp curCamp = CampManager.getCamp(campName);

        int enqIndex;
        int qnsIndex;
        String newReply;

        //call view enquiry
        this.viewEnquiryForStaff(campName,staffName);//prints list of enq

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

        sc.close();
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

    //student functions
    public void deleteEnquiry(){

    }
   
    public void editEnquiry(){

    }

}
