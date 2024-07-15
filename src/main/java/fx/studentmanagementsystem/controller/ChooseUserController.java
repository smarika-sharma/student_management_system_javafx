package fx.studentmanagementsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import static fx.studentmanagementsystem.Uses.changeSceneMouse;
import java.io.IOException;


public class ChooseUserController {

    @FXML
    public ImageView student_signup;

    @FXML
    public void initialize() {
        student_signup.setOnMouseClicked(this::student_signup);
    }

    private void student_signup(MouseEvent event) {
        try {
            changeSceneMouse(event,"/Fxml/Student/StudentForm-Signup.fxml","AcademiaFX");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}