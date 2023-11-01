public class EnquiryManager {
    //attribute

    //methods
    createEnquiry(String campName, String enquiry){
        //student will have their own method called makeEnquiry which calls this method, 
        //append student's enquiry to their own lsit of enquiry
        //find the correct camp
        //append this enquiry to that particular camp
        Enquiries enquiry = new Enquiries(enquiry);
        CampManager campManager = new CampManager();
        Camp camp = getCamp(campName);
        camp.addEnquiry(enquiry);


        

    }

    editEnquiry(){

    }

    replyEnquiry(){

    }

}
