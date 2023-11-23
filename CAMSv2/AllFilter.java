package CAMSv2;

import java.util.HashSet;

public class AllFilter implements ReportFilter{

    @Override
    public HashSet<Student> getFilteredList(HashSet<Student> students) {
        return students;
    }
    
}
