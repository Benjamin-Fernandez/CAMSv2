package CAMSv2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.PrintWriter;


public class StudentDataBase {
    private static StudentDataBase instance;
    private static ArrayList<Student> studentList = new ArrayList<>();
    private String filePath;

    private StudentDataBase(String filePath) {
        this.filePath = filePath;
    }

    public static StudentDataBase getInstance(String filePath) {
        if (instance == null) {
            instance = new StudentDataBase(filePath);
        }
        return instance;
    }

    public void loadToCSV() {
        try (InputStream inputStream = new FileInputStream(this.filePath);
             Reader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            reader.mark(1);
            String line;

            if (reader.read() != 0xFEFF) {
                reader.reset(); // Reset if the first character is not the BOM
            }

            while ((line = reader.readLine()) != null) {
                if (line.equals("")) {
                    return;
                }
                String[] values = line.split(",");
                String name = values[0].trim();
                String emailID = values[1];
                String faculty = values[2].trim();
                String password = values[3].trim(); // Assume default password

                Student student = new Student(name, emailID, faculty, password, Role.STUDENT);
                studentList.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToCSV() {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filePath, false))) {
            for (Student student : studentList) {
                printWriter.println(student.getName() + "," + student.getEmailID() + "," + student.getFaculty() + "," + student.getPassword());
            }
        } catch (IOException e) {
            System.out.println("CSV file not found");
        }finally {
            // Clear the staffList after writing to the CSV file
            studentList.clear();
        }
    }

    public static ArrayList<Student> getStudents() {
        return studentList;
    }
}