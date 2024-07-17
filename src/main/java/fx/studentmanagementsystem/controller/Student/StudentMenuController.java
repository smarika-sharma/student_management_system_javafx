package fx.studentmanagementsystem.controller.Student;


import javafx.fxml.FXML;
import static fx.studentmanagementsystem.Uses.changeScene;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;


import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class StudentMenuController implements Initializable {
    @FXML
    public Label studentIdLabel;
    public Label emailLabel;
    public Label facultyLabel;
    public Label usernameLabel;
    private String studentEmail;
    @FXML
    private Button StudentDashboard;
    @FXML
    private Button QuestionForm;
    @FXML
    private Button ProblemForm;
    @FXML
    private Button SugessionForm;

    public void setStudentEmail(String email) {
        this.studentEmail = email;
        // Now that we have the email, attempt to load student info
        loadStudentInfo();
    }


    private void loadStudentInfo() {
        try {
            Map<String, String> student_info = StudentInfoReader.readStudentInfo(studentEmail);
            studentIdLabel.setText(student_info.get("Student ID"));
            emailLabel.setText(student_info.get("Email"));
            facultyLabel.setText(student_info.get("Faculty"));
            usernameLabel.setText(student_info.get("Username"));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error (e.g., show an error message)
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Removed the direct call to loadStudentInfo here to avoid null email issue
    }

    @FXML
    protected void StudentDashboardClicked(ActionEvent event) {
        try {
            changeScene(event,"/Fxml/Student/StudentDashboard.fxml","AcademiaFX");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void questionformclicked(ActionEvent event) {


    }
    @FXML
    public void problemformclicked(ActionEvent event) {

    }
    @FXML
    public void sugessionformclicked(ActionEvent event) {

    }
}
