package fx.studentmanagementsystem.controller.Student;

import fx.studentmanagementsystem.DialogsutilLogout.DialogsUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

import static fx.studentmanagementsystem.Uses.changeScene;

public class OthersFormController {
    public void studentLogout(ActionEvent event) throws IOException {
        if(DialogsUtil.showLogoutConfirmation()) {
            changeScene(event, "/Fxml/login.fxml", "Academiafx");
        }
    }

    @FXML
    public void StudentDashboardClicked(ActionEvent event) throws IOException {
        changeScene(event,"/Fxml/Student/StudentDashboard.fxml","AcademiaFX");
    }

    @FXML
    public void questionformclicked(ActionEvent event) throws IOException {
        changeScene(event,"/Fxml/Student/QuestionForm.fxml","AcademiaFX");
    }

    @FXML
    public void problemformclicked(ActionEvent event) throws IOException {
        changeScene(event,"/Fxml/Student/ProblemForm.fxml","AcademiaFX");
    }

    @FXML
    public void sugessionformclicked(ActionEvent event) throws IOException {
        changeScene(event,"/Fxml/Student/SuggestionForm.fxml","AcademiaFX");
    }
}
