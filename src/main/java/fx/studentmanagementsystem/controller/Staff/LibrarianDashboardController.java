package fx.studentmanagementsystem.controller.Staff;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

import static fx.studentmanagementsystem.Uses.*;

public class LibrarianDashboardController {


    @FXML
    public void librarianLogout(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/loginForOtherThanStudent.fxml","Academiafx");
    }
}
