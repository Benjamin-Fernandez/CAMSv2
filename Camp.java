import java.sql.Array;
import java.util.ArrayList;
public class Camp {

    boolean visibility;
    private CampInformation info;
    private ArrayList<Students> StudentList;


    public Camp(8 parameters){

        this.visibility = false;
        this.info = new CampInformation(8 parameters);
        this.StudentList = new ArrayList<Students>();

    }

    public void setVisibility(boolean choice){
        this.visibility = choice;
    }
}
