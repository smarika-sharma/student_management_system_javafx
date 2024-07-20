package fx.studentmanagementsystem.controller.Staff;

import fx.studentmanagementsystem.DialogsutilLogout.DialogsUtil;
import javafx.event.ActionEvent;

import static fx.studentmanagementsystem.Uses.changeScene;

public class AdmissionofficerDashboardController {


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
