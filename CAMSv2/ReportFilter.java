package CAMSv2;

import java.util.*;
/**
 * The {@code ReportFilter} An interface for filtering a list of students based on specific criteria.
 */
public interface ReportFilter {
    /**
     * Filters the provided set of students based on specific criteria.
     *
     * @param students The set of students to be filtered.
     * @return A filtered set of students based on the implemented criteria.
     */
    public HashSet<Student> getFilteredList(HashSet<Student> students);
}
