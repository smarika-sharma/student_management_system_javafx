package fx.studentmanagementsystem.controller.Staff;

import fx.studentmanagementsystem.DialogsutilLogout.DialogsUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;

import static fx.studentmanagementsystem.Uses.changeScene;

public class AdmissionofficerDashboardController {

    @FXML
    public Button studentForm;

    // Student Form

    @FXML
    public void studentFormclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/StudentForm.fxml", "StudentForm");
    }

    // logout button

    public void admissionofficerLogout(ActionEvent event) {
        if(DialogsUtil.showLogoutConfirmation()){
            try {
                changeScene(event, "/Fxml/chooseUser-Signup.fxml", "AcademiaFX");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
