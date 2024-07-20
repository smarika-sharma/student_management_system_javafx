package fx.studentmanagementsystem.controller.Admin;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static fx.studentmanagementsystem.Uses.changeScene;

public class AdminDashboardController {
    public Label noofstudentlabel;
    public Label noofteacherlabel;
    public Label nooflibrarian;
    public Label noofadmissionofficer;

    private int countEntriesInCSV(String filename) throws IOException {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while (br.readLine() != null) {
                count++;
            }
        }
        return count;
    }

    public void initialize() {
        try {
            noofstudentlabel.setText(String.valueOf(countEntriesInCSV("Student_credentials.csv")));
            noofteacherlabel.setText(String.valueOf(countEntriesInCSV("teacher_credentials.csv")));
            nooflibrarian.setText(String.valueOf(countEntriesInCSV("librarian_credentials.csv")));
            noofadmissionofficer.setText(String.valueOf(countEntriesInCSV("admission_officer_credentials.csv")));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception or display an error message
        }


    }

    public void adminmanagestudent(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/ManageStudents.fxml", "Manage Students");
    }

    public void adminmanagestaff(ActionEvent event) {
    }

    public void adminmanageteacher(ActionEvent event) {
    }

    public void adminLogout(ActionEvent event) {
    }
}
