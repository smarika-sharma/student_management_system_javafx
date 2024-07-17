package fx.studentmanagementsystem.controller.Student;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StudentIDGenerator {
    private static final String ID_FILE_PATH = "last_student_id.txt";
    private static int currentID = -1;

    public static synchronized String generateID() throws IOException {
        if (currentID == -1) {
            currentID = readLastUsedID();
        }
        currentID++; // Increment the ID
        updateLastUsedID(currentID);
        return String.format("0362%04d", currentID);
    }

    private static int readLastUsedID() throws IOException {
        File file = new File(ID_FILE_PATH);
        if (!file.exists()) {
            return 10; // Start from 10 if file doesn't exist
        }
        String content = new String(Files.readAllBytes(Paths.get(ID_FILE_PATH)));
        return Integer.parseInt(content.trim());
    }

    private static void updateLastUsedID(int newID) throws IOException {
        Files.write(Paths.get(ID_FILE_PATH), String.valueOf(newID).getBytes());
    }
}
