package fx.studentmanagementsystem.controller.Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StudentInfoReader {
    public static Map<String, String> readStudentInfo(String email) throws IOException {
            Map<String, String> studentInfo = new HashMap<>();
            File directory = new File("Student_info");
            String filename = email + ".txt";
            File studentFile = new File(directory, filename);

            try (BufferedReader br = new BufferedReader(new FileReader(studentFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        studentInfo.put(parts[0].trim(), parts[1].trim());
                    }
                }
            }
            return studentInfo;
    }
}

