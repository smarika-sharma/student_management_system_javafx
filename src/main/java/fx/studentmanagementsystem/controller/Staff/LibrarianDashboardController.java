package fx.studentmanagementsystem.controller.Staff;

import fx.studentmanagementsystem.Utils.DialogsUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

import static fx.studentmanagementsystem.Uses.*;

public class LibrarianDashboardController {


    @FXML
    public void librarianLogout(ActionEvent event) throws IOException {
        if(DialogsUtil.showLogoutConfirmation()){
            changeScene(event, "/Fxml/chooseUser-Signup.fxml","AcademiaFX");
        }
    }
}
