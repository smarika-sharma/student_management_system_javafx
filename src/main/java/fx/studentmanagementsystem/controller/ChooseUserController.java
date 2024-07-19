package fx.studentmanagementsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import static fx.studentmanagementsystem.Uses.changeSceneMouse;
import java.io.IOException;


public class ChooseUserController {


    @FXML
    private ImageView student_signup;
    @FXML
    private ImageView teacherLogin;
    @FXML
    private ImageView admissionofficerLogin;
    @FXML
    public ImageView librarianLogin;


    @FXML
    public void initialize() {
        student_signup.setOnMouseClicked(this::student_signup);
        teacherLogin.setOnMouseClicked(this::teacherLogin);
        admissionofficerLogin.setOnMouseClicked(this::admissionofficerLogin);
        librarianLogin.setOnMouseClicked(this::librarianLogin);
    }

    protected void student_signup(MouseEvent event) {
        try {
            changeSceneMouse(event,"/Fxml/Student/StudentForm-Signup.fxml","AcademiaFX");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void teacherLogin(MouseEvent event) {
        try {
            changeSceneMouse(event, "/Fxml/loginForOtherThanStudent.fxml","AcademiaFX");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void admissionofficerLogin(MouseEvent event) {
        try {
            changeSceneMouse(event, "/Fxml/loginForOtherThanStudent.fxml","AcademiaFX");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void librarianLogin(MouseEvent event) {
        try {
            changeSceneMouse(event, "/Fxml/loginForOtherThanStudent.fxml", "AcademiaFX");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backToChooseUser(MouseEvent event) {
        try {
            changeSceneMouse(event,"/Fxml/chooseUser-Signup.fxml","AcademiaFX");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}