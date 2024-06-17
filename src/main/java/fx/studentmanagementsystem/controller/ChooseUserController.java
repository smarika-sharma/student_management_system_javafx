package fx.studentmanagementsystem.controller;

import fx.studentmanagementsystem.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseUserController {

    @FXML
    public ImageView student_signup;

    @FXML
    public void initialize() {
        student_signup.setOnMouseClicked((MouseEvent event) -> student_signup());
    }

    private void student_signup() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/Fxml/Student/StudentForm-Signup.fxml"));
            Stage stage = (Stage) student_signup.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}