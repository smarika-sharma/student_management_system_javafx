package fx.studentmanagementsystem.controller;

import fx.studentmanagementsystem.Utils.HashEncryption;
import fx.studentmanagementsystem.controller.Student.SessionManager;
import fx.studentmanagementsystem.controller.Student.StudentMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

import java.security.NoSuchAlgorithmException;
import java.util.List;


import static fx.studentmanagementsystem.Uses.*;


public class LoginController {
    @FXML
    public TextField adminusernamefield;
    @FXML
    public PasswordField adminpasscode;
    @FXML
    private TextField studentlogin_email_field;
    @FXML
    private PasswordField studentlogin_pass_field;
    @FXML
    private Label loginerror_label;


    @FXML
    protected void loginpagesignupbtn(@NotNull ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/StudentForm-Signup.fxml", "AcademiaFX");

    }

    @FXML
    public void initialize() {
        loginerror_label.setText("");
        //input listeners to clear error labels if user starts typing again
        addInputListeners();
    }
    private void addInputListeners() {
        studentlogin_email_field.textProperty().addListener((_) -> clearErrorLabels());
        studentlogin_pass_field.textProperty().addListener((_) -> clearErrorLabels());
    }
    private void clearErrorLabels() {
        loginerror_label.setText("");
    }


    @FXML
    public void login_button_clicked(ActionEvent event) throws NoSuchAlgorithmException {
        String Email = studentlogin_email_field.getText();
        //String Pass = studentlogin_pass_field.getText();
        String Pass = HashEncryption.hashPassword(studentlogin_pass_field.getText());

        boolean loginSuccessful = false;
        try (BufferedReader br = new BufferedReader(new FileReader("Student_credentials.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[0].equals(Email) && credentials[1].equals(Pass)) {
                    loginSuccessful = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (loginSuccessful) {
            SessionManager.setStudentEmail(Email);
            loginerror_label.setText(" login successful");
            try {
                FXMLLoader fxmlLoader = changeScene(event, "/Fxml/Student/StudentDashboard.fxml", "AcademiaFX");
                StudentMenuController controller = fxmlLoader.getController();
                controller.setStudentEmail(Email);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Get current stage
                if (stage != null) {
                    stage.setMaximized(true);
                } else {
                    //System.err.println("Stage is null");
                }


            } catch (IOException e) {
                //System.out.println("Error logging in" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            loginerror_label.setText("Invalid credentials");
        }

    }

    public void LoginbtnTAL(ActionEvent event) {
        String email = studentlogin_email_field.getText();
        String password = studentlogin_pass_field.getText();

        // for teacher

        try {
            List<String[]> teacherCredentials = readCSV("teacher_credentials.csv");
            boolean loginSuccessful = false;
            for (String[] credentials : teacherCredentials) {
                if (credentials[4].equals(email) && credentials[6].equals(password)) {
                    loginSuccessful = true;
                    break;
                }
            }

            if (loginSuccessful) {
                changeScene(event, "/Fxml/Teacher/TeacherDashboard.fxml", "AcademiaFX");
            } else {
                loginerror_label.setText("Invalid credentials");
            }
        } catch (IOException e) {
            e.printStackTrace();
            loginerror_label.setText("Error reading credentials");
        }

        // for librarian
        try {
            List<String[]> librarianCredentials = readCSV("librarian_credentials.csv");
            boolean loginSuccessful = false;
            for (String[] credentials : librarianCredentials) {
                if (credentials.length >= 6 && credentials[4].equals(email) && credentials[5].equals(password)) {
                    loginSuccessful = true;
                    break;
                }
            }

            if (loginSuccessful) {
                changeScene(event, "/Fxml/Staff/LibrarianDashboard.fxml", "AcademiaFX");
            } else {
                loginerror_label.setText("Invalid credentials");
            }
        } catch (IOException e) {
            e.printStackTrace();
            loginerror_label.setText("Error reading credentials");
        }
        //for admission officer
        try {
            List<String[]> admissionOfficerCredentials = readCSV("admission_officer_credentials.csv");
            boolean loginSuccessful = false;
            for (String[] credentials : admissionOfficerCredentials) {
                if (credentials.length >= 6 && credentials[4].equals(email) && credentials[5].equals(password)) {
                    loginSuccessful = true;
                    break;
                }
            }

            if (loginSuccessful) {
                changeScene(event, "/Fxml/Staff/AdmissionOfficerDashboard.fxml", "AcademiaFX");
            } else {
                loginerror_label.setText("Invalid credentials");
            }
        } catch (IOException e) {
            e.printStackTrace();
            loginerror_label.setText("Error reading credentials");
        }

    }

    public void chooseuserButton(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/chooseUser-Signup.fxml", "AcademiaFX");
    }

    public void backToChooseUser(MouseEvent event) {
        try {
            changeSceneMouse(event, "/Fxml/chooseUser-Signup.fxml", "AcademiaFX");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
