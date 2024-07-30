package fx.studentmanagementsystem.controller.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static fx.studentmanagementsystem.Uses.changeScene;

public class AdminLoginController implements Initializable {

    public TextField adminusernamefield;
    public PasswordField adminpasscode;
    public Label loginerror_label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginerror_label.setText("");
        addInputListeners();

    }
    private void addInputListeners() {
        adminusernamefield.textProperty().addListener((_) -> clearErrorLabels());
        adminpasscode.textProperty().addListener((_) -> clearErrorLabels());
    }
    private void clearErrorLabels() {
        loginerror_label.setText("");
    }

    public void adminloginButton(ActionEvent event) {
        String username = adminusernamefield.getText();
        String passcode = adminpasscode.getText();

        if (username.equals("admin") && passcode.equals("1234")) {
            try {
                changeScene(event, "/Fxml/Admin/adminDashboard.fxml", "AcademiaFX");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            loginerror_label.setText("Invalid credentials");
        }
    }

    public void chooseuserButton(ActionEvent event) {
        try {
            changeScene(event, "/Fxml/chooseUser-Signup.fxml", "AcademiaFX");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
