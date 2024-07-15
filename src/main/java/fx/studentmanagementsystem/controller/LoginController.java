package fx.studentmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.NotNull;
import static fx.studentmanagementsystem.Uses.changeScene;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class LoginController {
    @FXML
    private TextField studentlogin_email_field;
    @FXML
    private TextField studentlogin_pass_field;
    @FXML
    private Label loginerror_label;


    @FXML
    protected void loginpagesignupbtn(@NotNull ActionEvent event) throws IOException {
        changeScene(event,"/Fxml/Student/StudentForm-Signup.fxml","AcademiaFX");

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
                changeScene(event,"/Fxml/Student/StudentDashboard.fxml","AcademiaFX");
            } catch (IOException e) {
                //System.out.println("Error logging in" + e.getMessage());
                e.printStackTrace();
            }
        }else {
            loginerror_label.setText("Invalid credentials");
        }

    }
}
