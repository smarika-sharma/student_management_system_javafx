package fx.studentmanagementsystem.controller.Admin;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import static fx.studentmanagementsystem.Uses.*;

public class TeacherFormController implements Initializable {
    @FXML
    public TextField id;

    @FXML
    public TextField username;

    @FXML
    public PasswordField password;

    @FXML
    public TextField teacherFirstName;

    @FXML
    public TextField teacherLastName;

    @FXML
    public ChoiceBox<String> faculty;
    private final String[] faculties = {"BIHM", "BBA", "BCS"};

    @FXML
    public TextField email;

    @FXML
    public ChoiceBox<String> gender;
    private final String[] genders = {"Male", "Female", "Others"};

    @FXML
    public TextField phoneNumber;


    @FXML
    public Button submitButton;

    @FXML
    public ImageView backButton;

    @FXML
    public void backToManageTeacher(javafx.scene.input.MouseEvent event) throws IOException {
        try {
            changeSceneMouse(event, "/Fxml/Admin/ManageTeacher.fxml", "Manage Teacher");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gender.getItems().addAll(genders);
        faculty.getItems().addAll(faculties);
        backButton.setOnMouseClicked(event -> {
            try {
                backToManageTeacher(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void saveTeacher() throws IOException{
        String teacherID = id.getText();
        String userName = username.getText();
        String teacherFirstname = teacherFirstName.getText();
        String teacherLastname = teacherLastName.getText();
        String teacherEmail = email.getText();
        String teacherPhoneNumber = phoneNumber.getText();
        String Password = password.getText();
        String Faculty = faculty.getValue();
        String Gender = gender.getValue();

        try {
            saveTeacherDataCSV("teacher_credentials.csv", teacherID, userName, teacherFirstname, teacherLastname, teacherEmail, teacherPhoneNumber, Password, Faculty, Gender);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void onSubmitClicked(ActionEvent event) throws IOException {
        saveTeacher();
        changeScene(event, "/Fxml/Admin/ManageTeacher.fxml", "Manage Teacher");
    }
}