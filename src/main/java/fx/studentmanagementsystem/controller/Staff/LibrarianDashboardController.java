package fx.studentmanagementsystem.controller.Staff;

import fx.studentmanagementsystem.DialogsutilLogout.DialogsUtil;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

import static fx.studentmanagementsystem.Uses.changeScene;

public class LibrarianDashboardController {



    // logout button
    public void librarianLogout(ActionEvent event) {
        if(DialogsUtil.showLogoutConfirmation()){
            try {
                changeScene(event, "/Fxml/chooseUser-Signup.fxml", "AcademiaFX");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
