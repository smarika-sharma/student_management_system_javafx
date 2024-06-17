package fx.studentmanagementsystem.controller;

import fx.studentmanagementsystem.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class LoginController {
    @FXML
    public TextField studentlogin_email_field;
    @FXML
    public TextField studentlogin_pass_field;
    @FXML
    public Label loginerror_label;
    Scene scene;


    @FXML
    public void loginpagesignupbtn(@NotNull ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/Fxml/Student/StudentForm-Signup.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void initialize() {
        loginerror_label.setText("");
    }


    @FXML
    public void login_button_clicked(ActionEvent event) {
        String Email = studentlogin_email_field.getText();
        String Pass = studentlogin_pass_field.getText();

        boolean loginSuccessful =false;
        try (BufferedReader br = new BufferedReader(new FileReader("Student_credentials.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if(credentials[0].equals(Email) && credentials[1].equals(Pass)) {
                    loginSuccessful = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (loginSuccessful) {
            loginerror_label.setText(" login successful");
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/Fxml/Student/StudentDashboard.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                stage.setMinHeight(720);
                stage.setMinWidth(1280);
                //stage.setFullScreen(true);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            loginerror_label.setText("Invalid credentials");
        }

    }
}
