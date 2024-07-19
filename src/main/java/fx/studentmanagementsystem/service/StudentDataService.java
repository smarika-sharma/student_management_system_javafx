package fx.studentmanagementsystem.service;

import fx.studentmanagementsystem.model.Student;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentDataService {

    public List<Student> loadStudentsFromDirectory() {
        List<Student> students = new ArrayList<>();
        File directory = new File("Student_info");
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                try (Scanner scanner = new Scanner(file)) {
                    String studentID = scanner.nextLine().split(": ")[1];
                    String username = scanner.nextLine().split(": ")[1];
                    String phoneNumber = scanner.nextLine().split(": ")[1];
                    String email = scanner.nextLine().split(": ")[1];
                    String faculty = scanner.nextLine().split(": ")[1];
                    String gender = scanner.nextLine().split(": ")[1];
                    students.add(new Student(studentID, username, phoneNumber, email, faculty, gender));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return students;
    }
}
