package fx.studentmanagementsystem.controller.Student;


import fx.studentmanagementsystem.DialogsutilLogout.DialogsUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import static fx.studentmanagementsystem.Uses.changeScene;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;

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
        if (studentEmail == null || studentEmail.isEmpty()) {
            //System.err.println("Student email is null or empty.");
            return;
        }
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
        if (studentIdLabel != null) {
            loadStudentInfo();
        } else {
            //System.out.println("studentIdLabel is null");
            // Consider using Platform.runLater if the operation depends on fully rendered UI
            Platform.runLater(() -> {
                // Ensure the component is now available and then call loadStudentInfo
                if (studentIdLabel != null) {
                    loadStudentInfo();
                }
            });
        }

    }

    @FXML
    protected void StudentDashboardClicked(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader= changeScene(event,"/Fxml/Student/StudentDashboard.fxml","AcademiaFX");
            StudentMenuController controller = fxmlLoader.getController();
            controller.setStudentEmail(SessionManager.getStudentEmail());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void questionformclicked(ActionEvent event) throws IOException {
        changeScene(event,"/Fxml/Student/QuestionForm.fxml","AcademiaFX");


    }
    @FXML
    public void problemformclicked(ActionEvent event) throws IOException {
        changeScene(event,"/Fxml/Student/ProblemForm.fxml","AcademiaFX");



    }
    @FXML
    public void sugessionformclicked(ActionEvent event) throws IOException {
        changeScene(event,"/Fxml/Student/SuggestionForm.fxml","AcademiaFX");

    }
    @FXML
    public void studentLogout(ActionEvent event) throws IOException {
        if(DialogsUtil.showLogoutConfirmation()) {
            changeScene(event, "/Fxml/login.fxml", "Academiafx");
        }
    }
}
