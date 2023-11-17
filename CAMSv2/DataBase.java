package CAMSv2;

import java.util.ArrayList;

public abstract class DataBase<T> {
    // Singleton
    // private ArrayList<T> list;
    // private String filePath;

    public abstract void loadToCSV();
    public abstract void writeToCSV();
    public abstract void appendToCSV();
}


// public class StudentDataBase extends DataBase<Student>{
//     @Override
//     public void loadToCSV() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'loadToCSV'");
//     }

//     @Override
//     public void writeToCSV() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'writeToCSV'");
//     }

// }
