package CAMSv2;

import java.util.HashSet;

public class CCMFilter implements ReportFilter{

    @Override
    public HashSet<Student> getFilteredList(HashSet<Student> students) {
        HashSet<Student> filteredList = new HashSet<Student>();
        for (Student student : students) {
            if (student.getRole().equals(Role.CAMP_COMMITTEE_MEMBER)) {
                filteredList.add(student);
            }
        }
        return filteredList;
    }
    
}
